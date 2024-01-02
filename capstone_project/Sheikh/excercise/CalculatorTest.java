/**
 * 
 */
package excercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 * 
 */

class CalculatorTest {

	@Test
    public void sumTest() {
        Calculator calculator = new Calculator();
        double result = calculator.sum(3, 7);
        assertEquals(10, result);
    }

    @Test
    public void multiplicationTest() {
        Calculator calculator = new Calculator();
        double result = calculator.multiplication(4, 5);
        assertEquals(20, result);
    }

    @Test
    public void divisionTest() {
        Calculator calculator = new Calculator();
        double result = calculator.division(10, 0);
        assertEquals(5, result);
        //assertThrows(ArithmeticException.class, () -> calculator.division(5, 0));
    }

    @Test
    public void factorialTest() {
        Calculator calculator = new Calculator();
        double result = calculator.factorial(5);
        assertEquals(120, result);
    }

    @Test
    public void testPower() {
        Calculator calculator = new Calculator();
        double result = calculator.power(2, 3);
        assertEquals(8, result);
    }

}
