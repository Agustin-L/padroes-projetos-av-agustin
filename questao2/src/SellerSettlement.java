public class SellerSettlement {

    public static void print(Lot lot) {

        if (!lot.isSold()) {
            System.out.println(
                    "Não há acerto: o lote não foi vendido."
            );
            return;
        }

        double saleValue = lot.getCurrentBid();
        double commission = lot.calculateCommission();
        double netValue = saleValue - commission;

        System.out.println("\n========== ACERTO DO VENDEDOR ==========");

        System.out.println("Lote: " + lot.getName());

        System.out.printf(
                "Valor da venda: R$ %.2f%n",
                saleValue
        );

        System.out.printf(
                "Comissão: R$ %.2f%n",
                commission
        );

        System.out.printf(
                "Valor líquido ao vendedor: R$ %.2f%n",
                netValue
        );
    }
}