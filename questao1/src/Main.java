public class Main {

    public static void main(String[] args) {

        Configuration.getInstance();

        CloudFactory awsFactory = new AwsFactory();
        CloudFactory azureFactory = new AzureFactory();

        ProvisioningService awsService =
                new ProvisioningService(awsFactory);

        ProvisioningService azureService =
                new ProvisioningService(azureFactory);

        Thread t1 = new Thread(() ->
                awsService.provision("Ambiente AWS 1", 2, 100)
        );

        Thread t2 = new Thread(() ->
                awsService.provision("Ambiente AWS 2", 1, 200)
        );

        Thread t3 = new Thread(() ->
                azureService.provision("Ambiente Azure", 3, 150)
        );

        Thread t4 = new Thread(() ->
                awsService.provision("Ambiente acima do orçamento", 100, 50000)
        );

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Execução interrompida.");
        }

        AuditLog.getInstance().printHistory();
    }
}