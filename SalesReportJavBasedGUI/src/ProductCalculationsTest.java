


import org.junit.Test;
import static org.junit.Assert.*;

public class ProductCalculationsTest {

    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        double price = 3500.0;
        ProductCalculations calc = new ProductCalculations(price);

        // VAT is 15% of 3500 = 525.0
        assertEquals(525.0, calc.GetVat(), 0.001);
    }

    @Test
    public void GetCommission_WithValidData_ReturnsCorrectValue() {
        double price = 3500.0;
        ProductCalculations calc = new ProductCalculations(price);

        // Commission is 5% of 3500 = 175.0
        assertEquals(175.0, calc.GetCommission(), 0.001);
    }

    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        double price = 3500.0;
        ProductCalculations calc = new ProductCalculations(price);

        // Total = 3500 + 525 + 175 = 4200.0
        assertEquals(4200.0, calc.GetTotal(), 0.001);
    }
}
