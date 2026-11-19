import java.util.ArrayList;
import java.util.List;

public class ExpressDeliveryDecorator extends SaleDecorator {

    public ExpressDeliveryDecorator(Sale sale) {
        super(sale);
    }

    @Override
    public double getTotalPrice() {
        return sale.getTotalPrice() + 450.00;
    }

    @Override
    public List<String> getDescriptions() {
        List<String> descriptions =
                new ArrayList<>(sale.getDescriptions());

        descriptions.add("Entrega expressa: R$ 450,00");

        return descriptions;
    }
}