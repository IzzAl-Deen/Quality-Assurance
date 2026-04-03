package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import main.najah.code.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Calculator Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {

    Calculator calc;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Setup complete before all");
    }

	@BeforeEach
	void setUp() {
            calc = new Calculator();
            System.out.println("Setup complete before each");
	}

    @AfterAll
    static void afterAll() {
        System.out.println("Teardown complete after all");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Teardown complete after each");
    }

    @Test
    @Order(2)
    @DisplayName("Test Add")
    void testAdd() {
        assertEquals(7,calc.add(4,1,2));
        assertEquals(1,calc.add(3,-2));
        assertEquals(0,calc.add(0,0));
    }

    @ParameterizedTest
    @DisplayName("Test Add Parameterized")
    @CsvSource({"6,4,10", "17,3,20"})
    void testAddParameterized(int number1, int number2, int result) {
        assertEquals(result, calc.add(number1,number2));
    }

    @Test
    @Timeout(1)
    @DisplayName("Test Add Timeout")
    void testAddTimeout() {
        assertEquals(200000000,calc.add(100000000,100000000));
    }

    @Test
    @Order(3)
    @DisplayName("Test Divide")
    void testDivide() {
        assertEquals(2,calc.divide(4,2));
        assertEquals(-2,calc.divide(-4,2));
    }

    @Test
    @DisplayName("Test Divide By Zero")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(5,0));
    }

    @Test
    @Order(1)
    @DisplayName("Test Factorial")
    void testFactorial() {
        assertEquals(24,calc.factorial(4));
    }

    @Test
    @DisplayName("Test Factorial Negative Input")
    void testFactorialByNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-4));
    }

    @Test
    @DisplayName("Test Factorial of Zero")
    void testFactorialOfZero() {
        assertEquals(1,calc.factorial(0));
    }


}
