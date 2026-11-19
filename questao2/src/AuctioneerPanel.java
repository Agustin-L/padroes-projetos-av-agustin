public class AuctioneerPanel implements AuctionObserver {

    @Override
    public void update(Lot lot, Bid bid) {
        System.out.printf(
                "[PAINEL] Lote: %s | Maior lance: R$ %.2f | Total de lances: %d%n",
                lot.getName(),
                lot.getCurrentBid(),
                lot.getBids().size()
        );
    }
}