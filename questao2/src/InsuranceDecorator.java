import java.util.ArrayList;
import java.util.List;

public class InsuranceDecorator extends SaleDecorator {

    public InsuranceDecorator(Sale sale) {
        super(sale);
    }

    @Override
    public double getTotalPrice() {
        return sale.getTotalPrice() + sale.getSalePrice() * 0.015;
    }

    @Override
    public List<String> getDescriptions() {
        List<String> descriptions =
                new ArrayList<>(sale.getDescriptions());

        descriptions.add(String.format(
                "Seguro de transporte: R$ %.2f",
                sale.getSalePrice() * 0.015
        ));

        return descriptions;
    }
}