import java.util.ArrayList;
import java.util.List;

public class BasicSale implements Sale {

    private final Lot lot;

    public BasicSale(Lot lot) {
        if (!lot.isSold()) {
            throw new IllegalArgumentException(
                    "Não é possível criar uma venda para um lote não vendido."
            );
        }

        this.lot = lot;
    }

    @Override
    public double getSalePrice() {
        return lot.getCurrentBid();
    }

    @Override
    public double getTotalPrice() {
        return getSalePrice();
    }

    @Override
    public List<String> getDescriptions() {
        List<String> descriptions = new ArrayList<>();
        descriptions.add("Venda do lote: " + lot.getName());
        return descriptions;
    }
}