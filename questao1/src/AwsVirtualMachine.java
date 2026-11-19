import java.util.concurrent.atomic.AtomicInteger;

public class AwsVirtualMachine implements VirtualMachine {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private final String id;

    public AwsVirtualMachine() {
        this.id = "i-" + counter.incrementAndGet();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getHourlyCost() {
        return 0.10;
    }
}