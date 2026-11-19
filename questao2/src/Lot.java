import java.util.ArrayList;
import java.util.List;

public class Lot {

    private final String name;
    private final String category;
    private final double startingPrice;
    private final double reservePrice;

    private double currentBid;
    private boolean open = true;
    private boolean sold = false;

    private final List<Bid> bids = new ArrayList<>();
    private final List<AuctionObserver> observers = new ArrayList<>();

    private CommissionStrategy commissionStrategy;

    public Lot(
            String name,
            String category,
            double startingPrice,
            double reservePrice,
            CommissionStrategy commissionStrategy
    ) {
        this.name = name;
        this.category = category;
        this.startingPrice = startingPrice;
        this.reservePrice = reservePrice;
        this.currentBid = startingPrice;
        this.commissionStrategy = commissionStrategy;
    }

    public synchronized void addObserver(AuctionObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public synchronized void removeObserver(AuctionObserver observer) {
        observers.remove(observer);
    }

    public synchronized boolean placeBid(Bidder bidder, double value) {

        if (!open) {
            System.out.println("Lance recusado: o lote está encerrado.");
            return false;
        }

        double minimumBid = currentBid * 1.05;

        if (value < minimumBid) {
            System.out.printf(
                    "Lance recusado para %s. O mínimo é R$ %.2f%n",
                    name,
                    minimumBid
            );
            return false;
        }

        Bid bid = new Bid(bidder.getName(), value);

        currentBid = value;
        bids.add(bid);

        System.out.printf(
                "Lance aceito! %s ofereceu R$ %.2f no lote %s%n",
                bidder.getName(),
                value,
                name
        );

        notifyObservers(bid, bidder);

        return true;
    }

    private void notifyObservers(Bid bid, Bidder bidder) {
        List<AuctionObserver> copy = new ArrayList<>(observers);

        for (AuctionObserver observer : copy) {
            if (observer != bidder) {
                observer.update(this, bid);
            }
        }
    }

    public synchronized void closeAuction() {

        if (!open) {
            System.out.println("O lote já está encerrado.");
            return;
        }

        open = false;
        sold = currentBid >= reservePrice;

        System.out.println("\n========== ENCERRAMENTO ==========");
        System.out.println("Lote: " + name);

        if (sold) {
            System.out.printf("VENDIDO por R$ %.2f%n", currentBid);
        } else {
            System.out.println("NÃO VENDIDO. Preço de reserva não atingido.");
        }
    }

    public void changeCommissionStrategy(CommissionStrategy strategy) {
        if (!open) {
            throw new IllegalStateException(
                    "Não é possível trocar a comissão de um lote encerrado."
            );
        }

        this.commissionStrategy = strategy;
        System.out.println("Regra de comissão alterada para o lote " + name);
    }

    public double calculateCommission() {
        if (!sold) {
            throw new IllegalStateException(
                    "Não existe comissão: o lote não foi vendido."
            );
        }

        return commissionStrategy.calculate(currentBid);
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public double getReservePrice() {
        return reservePrice;
    }

    public synchronized double getCurrentBid() {
        return currentBid;
    }

    public synchronized boolean isOpen() {
        return open;
    }

    public synchronized boolean isSold() {
        return sold;
    }

    public synchronized List<Bid> getBids() {
        return new ArrayList<>(bids);
    }

}
