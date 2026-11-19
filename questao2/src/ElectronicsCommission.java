public class ElectronicsCommission implements CommissionStrategy {

    @Override
    public double calculate(double saleValue) {
        return Math.max(saleValue * 0.12, 50.00);
    }
}