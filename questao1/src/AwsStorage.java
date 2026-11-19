import java.util.concurrent.atomic.AtomicInteger;

public class AwsStorage implements Storage {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private final String id;
    private final double gigabytes;

    public AwsStorage(double gigabytes) {
        this.id = "s3://" + counter.incrementAndGet();
        this.gigabytes = gigabytes;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getMonthlyCost() {
        return gigabytes * 0.023;
    }
}