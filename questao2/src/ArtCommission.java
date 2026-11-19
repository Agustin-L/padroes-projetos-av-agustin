public class ArtCommission implements CommissionStrategy {

    @Override
    public double calculate(double saleValue) {
        return saleValue * 0.20;
    }
}