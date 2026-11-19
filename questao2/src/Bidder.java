public class Bidder implements AuctionObserver {

    private final String name;

    public Bidder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(Lot lot, Bid bid) {
        System.out.printf(
                "%s recebeu atualização: lote %s teve novo lance de R$ %.2f%n",
                name,
                lot.getName(),
                bid.getValue()
        );
    }
}