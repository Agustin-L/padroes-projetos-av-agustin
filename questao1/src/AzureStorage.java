import java.util.concurrent.atomic.AtomicInteger;

public class AzureStorage implements Storage {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private final String id;
    private final double gigabytes;

    public AzureStorage(double gigabytes) {
        this.id = "blob://" + counter.incrementAndGet();
        this.gigabytes = gigabytes;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getMonthlyCost() {
        return gigabytes * 0.02;
    }
}