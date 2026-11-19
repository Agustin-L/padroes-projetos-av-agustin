public class PropertyCommission implements CommissionStrategy {

    @Override
    public double calculate(double saleValue) {
        return (saleValue * 0.03) + 1500.00;
    }
}