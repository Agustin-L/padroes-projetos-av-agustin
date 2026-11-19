import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditRecord {

    private final LocalDateTime dateTime;
    private final String environmentName;
    private final String provider;
    private final String result;

    public AuditRecord(
            String environmentName,
            String provider,
            String result
    ) {
        this.dateTime = LocalDateTime.now();
        this.environmentName = environmentName;
        this.provider = provider;
        this.result = result;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return dateTime.format(formatter)
                + " | Ambiente: " + environmentName
                + " | Provedor: " + provider
                + " | Resultado: " + result;
    }
}