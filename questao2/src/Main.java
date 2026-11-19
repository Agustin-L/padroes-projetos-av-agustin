public class Main {

    public static void main(String[] args) {

        // Participantes
        Bidder ana = new Bidder("Ana");
        Bidder bruno = new Bidder("Bruno");
        Bidder carla = new Bidder("Carla");

        // Interessados que acompanham os lotes
        AuctioneerPanel panel = new AuctioneerPanel();
        Auditor auditor = new Auditor();

        // Lote de Arte
        Lot painting = new Lot(
                "Quadro Moderno",
                "Arte",
                1000.00,
                1300.00,
                new ArtCommission()
        );

        // Lote de Veículos
        Lot vehicle = new Lot(
                "Carro Esportivo",
                "Veículos",
                20000.00,
                25000.00,
                new VehicleCommission()
        );

        // Inscrição dos observadores no lote de Arte
        painting.addObserver(ana);
        painting.addObserver(bruno);
        painting.addObserver(carla);
        painting.addObserver(panel);
        painting.addObserver(auditor);

        // Inscrição dos observadores no lote de Veículos
        vehicle.addObserver(ana);
        vehicle.addObserver(bruno);
        vehicle.addObserver(carla);
        vehicle.addObserver(panel);
        vehicle.addObserver(auditor);

        // Demonstração de saída do acompanhamento
        painting.removeObserver(carla);

        System.out.println("\n========== LEILÃO DE ARTE ==========");

        // Lance aceito
        painting.placeBid(ana, 1100.00);

        // Lance recusado: não é 5% maior que 1100
        painting.placeBid(bruno, 1120.00);

        // Lance aceito
        painting.placeBid(bruno, 1200.00);

        // Carla pode continuar dando lances mesmo sem acompanhar o lote
        painting.placeBid(carla, 1350.00);

        // Troca da regra de comissão durante o leilão
        // O lote é de Arte, mas a campanha usa a regra de Eletrônicos.
        painting.changeCommissionStrategy(
                new ElectronicsCommission()
        );

        // Encerramento: vendido, pois 1350 >= 1300
        painting.closeAuction();

        // Tentativa de lance após o encerramento
        painting.placeBid(ana, 1500.00);

        // Fatura com serviços adicionais.
        // Certificado contratado duas vezes.
        if (painting.isSold()) {

            Sale sale = new BasicSale(painting);

            sale = new InsuranceDecorator(sale);
            sale = new CertificateDecorator(sale);
            sale = new PackagingDecorator(sale);
            sale = new CertificateDecorator(sale);
            sale = new ExpressDeliveryDecorator(sale);

            Invoice.print(sale);
            SellerSettlement.print(painting);
        }

        System.out.println("\n========== LEILÃO DE VEÍCULOS ==========");

        // Lance aceito
        vehicle.placeBid(carla, 22000.00);

        // Lance aceito: pelo menos 5% acima de 22000
        vehicle.placeBid(ana, 23100.00);

        // Lance recusado: abaixo do mínimo de 24255
        vehicle.placeBid(bruno, 24000.00);

        // Encerramento: não vendido, pois 23100 < 25000
        vehicle.closeAuction();

        SellerSettlement.print(vehicle);

        System.out.println("\n========== FIM DOS LEILÕES ==========");
    }
}