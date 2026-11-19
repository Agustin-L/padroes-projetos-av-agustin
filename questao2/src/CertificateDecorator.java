import java.util.ArrayList;
import java.util.List;

public class CertificateDecorator extends SaleDecorator {

    public CertificateDecorator(Sale sale) {
        super(sale);
    }

    @Override
    public double getTotalPrice() {
        return sale.getTotalPrice() + 300.00;
    }

    @Override
    public List<String> getDescriptions() {
        List<String> descriptions =
                new ArrayList<>(sale.getDescriptions());

        descriptions.add("Certificado de autenticidade: R$ 300,00");

        return descriptions;
    }
}