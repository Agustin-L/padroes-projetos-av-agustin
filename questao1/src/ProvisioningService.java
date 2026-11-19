import java.util.ArrayList;
import java.util.List;

public class ProvisioningService {

    private final CloudFactory factory;

    public ProvisioningService(CloudFactory factory) {
        this.factory = factory;
    }

    public void provision(
            String environmentName,
            int virtualMachineQuantity,
            double gigabytes
    ) {
        Configuration config = Configuration.getInstance();
        AuditLog audit = AuditLog.getInstance();

        String provider = factory.getProviderName();

        try {
            if (virtualMachineQuantity <= 0 || gigabytes < 0) {
                throw new IllegalArgumentException(
                        "Quantidade de máquinas deve ser positiva e GB não pode ser negativo."
                );
            }

            double vmCost =
                    virtualMachineQuantity
                    * factory.getVirtualMachineHourlyCost()
                    * 730;

            double storageCost =
                    gigabytes * factory.getStorageCostPerGb();

            double queueCost = factory.getQueueMonthlyCost();

            double totalCost = vmCost + storageCost + queueCost;

            // A verificação ocorre antes da criação dos recursos.
            if (totalCost > config.getMonthlyBudget()) {
                String reason = String.format(
                        "RECUSADO: custo R$ %.2f ultrapassa o orçamento de R$ %.2f",
                        totalCost,
                        config.getMonthlyBudget()
                );

                System.out.println(reason);

                audit.addRecord(new AuditRecord(
                        environmentName,
                        provider,
                        reason
                ));

                return;
            }

            List<VirtualMachine> machines = new ArrayList<>();

            for (int i = 0; i < virtualMachineQuantity; i++) {
                machines.add(factory.createVirtualMachine());
            }

            Storage storage = factory.createStorage(gigabytes);
            MessageQueue queue = factory.createMessageQueue();

            Environment environment = new Environment(
                    environmentName,
                    provider,
                    config.getRegion(),
                    machines,
                    storage,
                    queue,
                    totalCost
            );

            environment.printSummary();

            audit.addRecord(new AuditRecord(
                    environmentName,
                    provider,
                    "ACEITO"
            ));

        } catch (Exception e) {
            audit.addRecord(new AuditRecord(
                    environmentName,
                    provider,
                    "RECUSADO: " + e.getMessage()
            ));

            System.out.println(
                    "Erro ao provisionar " + environmentName
                            + ": " + e.getMessage()
            );
        }
    }
}