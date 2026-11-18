
/**
 * Interface for calculations relating to product pricing, VAT, and commission.
 */
public interface IProductCalculations {
    double GetVat();
    double GetCommission();
    double GetTotal();
}
