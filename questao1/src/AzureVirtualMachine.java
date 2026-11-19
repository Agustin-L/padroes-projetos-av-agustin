import java.util.concurrent.atomic.AtomicInteger;

public class AzureVirtualMachine implements VirtualMachine {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private final String id;

    public AzureVirtualMachine() {
        this.id = "vm-" + counter.incrementAndGet();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getHourlyCost() {
        return 0.12;
    }
}