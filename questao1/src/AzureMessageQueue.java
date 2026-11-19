import java.util.concurrent.atomic.AtomicInteger;

public class AzureMessageQueue implements MessageQueue {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private final String id;

    public AzureMessageQueue() {
        this.id = "sb-" + counter.incrementAndGet();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getMonthlyCost() {
        return 0.50;
    }
}