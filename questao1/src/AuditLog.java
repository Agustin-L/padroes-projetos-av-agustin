import java.util.ArrayList;
import java.util.List;

public class AuditLog {

    private final List<AuditRecord> records = new ArrayList<>();

    private AuditLog() {
    }

    private static class Holder {
        private static final AuditLog INSTANCE = new AuditLog();
    }

    public static AuditLog getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized void addRecord(AuditRecord record) {
        records.add(record);
    }

    public synchronized void printHistory() {
        System.out.println("\n========== HISTÓRICO DE AUDITORIA ==========");

        for (AuditRecord record : records) {
            System.out.println(record);
        }

        System.out.println("Total de registros: " + records.size());
    }
}