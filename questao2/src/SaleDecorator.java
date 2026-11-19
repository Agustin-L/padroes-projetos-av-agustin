public abstract class SaleDecorator implements Sale {

    protected final Sale sale;

    public SaleDecorator(Sale sale) {
        this.sale = sale;
    }

    @Override
    public double getSalePrice() {
        return sale.getSalePrice();
    }

}
