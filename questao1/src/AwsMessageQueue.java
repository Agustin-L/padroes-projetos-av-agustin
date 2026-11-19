import java.util.concurrent.atomic.AtomicInteger;

public class AwsMessageQueue implements MessageQueue {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private final String id;

    public AwsMessageQueue() {
        this.id = "sqs-" + counter.incrementAndGet();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getMonthlyCost() {
        return 0.40;
    }
}