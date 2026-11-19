import java.util.ArrayList;
import java.util.List;

public class Environment {

    private final String name;
    private final String provider;
    private final String region;

    private final List<VirtualMachine> virtualMachines;
    private final Storage storage;
    private final MessageQueue messageQueue;

    private final double monthlyCost;

    public Environment(
            String name,
            String provider,
            String region,
            List<VirtualMachine> virtualMachines,
            Storage storage,
            MessageQueue messageQueue,
            double monthlyCost
    ) {
        this.name = name;
        this.provider = provider;
        this.region = region;
        this.virtualMachines = new ArrayList<>(virtualMachines);
        this.storage = storage;
        this.messageQueue = messageQueue;
        this.monthlyCost = monthlyCost;
    }

    public void printSummary() {
        System.out.println("\n========== AMBIENTE PROVISIONADO ==========");
        System.out.println("Nome: " + name);
        System.out.println("Provedor: " + provider);
        System.out.println("Região: " + region);

        System.out.println("\nMáquinas virtuais:");

        for (VirtualMachine vm : virtualMachines) {
            System.out.println("- " + vm.getId());
        }

        System.out.println("Armazenamento: " + storage.getId());
        System.out.println("Fila: " + messageQueue.getId());

        System.out.printf(
                "Custo mensal estimado: R$ %.2f%n",
                monthlyCost
        );
    }
}