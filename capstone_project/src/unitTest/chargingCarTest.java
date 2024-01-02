/**
 * 
 */
package unitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import charging_station.Charger;
import excercise.Calculator;
import vehicle.Car;

/**
 * 
 */
public class chargingCarTest {


	@Test
    public void popCarTest() {
		ArrayList<Car> waitingCars = new ArrayList<Car>();
		waitingCars.add(new Car(0));
		Charger charger = new Charger(0, 0, waitingCars, null, null, null, null);
		
		
        double result = calculator.multiplication(4, 5);
        assertEquals(20, result);
    }

}
