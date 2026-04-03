package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.najah.code.Product;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@DisplayName("Product Tests")
@Execution(ExecutionMode.CONCURRENT)
public class ProductTest {
    Product p;

	@BeforeEach
	void setUp() throws Exception {
        p = new Product("item1",200);
	}

    @Test
    @DisplayName("Test Product")
    void testProduct() {
        assertEquals("item1", p.getName());
        assertEquals(200, p.getPrice());
    }

    @Test
    @DisplayName("Test Product Negative Price")
    void testProduct2() {
        assertThrows(IllegalArgumentException.class, () -> new Product("item",-200));
    }

    @Test
    @DisplayName("Test Applying Discount")
    void testDiscount() {
        p.applyDiscount(30);
        assertEquals(30, p.getDiscount());
        assertEquals(140, p.getFinalPrice());
    }

    @Test
    @DisplayName("Test Invalid Discount")
    void testDiscount2() {
        assertThrows(IllegalArgumentException.class,() -> p.applyDiscount(-30));
        assertThrows(IllegalArgumentException.class,() -> p.applyDiscount(70));
    }

    @ParameterizedTest
    @ValueSource(doubles = {5, 25, 40})
    @DisplayName("Parameterized discounts")
    void testDiscountParameterized(double discount) {
        p.applyDiscount(discount);
        assertEquals(discount, p.getDiscount());
    }



    @Test
    @Timeout(1)
    @DisplayName("Calculate final price fast")
    void testFinalPriceTimeout() {
        p.applyDiscount(30);
        assertEquals(140, p.getFinalPrice());
    }

}
