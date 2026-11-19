import java.util.ArrayList;
import java.util.List;

public class PackagingDecorator extends SaleDecorator {

    public PackagingDecorator(Sale sale) {
        super(sale);
    }

    @Override
    public double getTotalPrice() {
        return sale.getTotalPrice() + 180.00;
    }

    @Override
    public List<String> getDescriptions() {
        List<String> descriptions =
                new ArrayList<>(sale.getDescriptions());

        descriptions.add("Embalagem especial: R$ 180,00");

        return descriptions;
    }
}