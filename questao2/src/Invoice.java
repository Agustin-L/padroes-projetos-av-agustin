public class Invoice {

    public static void print(Sale sale) {

        System.out.println("\n========== FATURA DO COMPRADOR ==========");

        for (String description : sale.getDescriptions()) {
            System.out.println(description);
        }

        System.out.printf(
                "Valor da venda: R$ %.2f%n",
                sale.getSalePrice()
        );

        System.out.printf(
                "TOTAL A PAGAR: R$ %.2f%n",
                sale.getTotalPrice()
        );
    }
}