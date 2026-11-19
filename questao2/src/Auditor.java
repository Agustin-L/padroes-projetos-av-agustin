import java.time.format.DateTimeFormatter;

public class Auditor implements AuctionObserver {

    @Override
    public void update(Lot lot, Bid bid) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println(
                "[AUDITORIA] "
                + bid.getDateTime().format(formatter)
                + " | Lote: " + lot.getName()
                + " | Participante: " + bid.getBidderName()
                + " | Lance: R$ " + String.format("%.2f", bid.getValue())
        );
    }
}