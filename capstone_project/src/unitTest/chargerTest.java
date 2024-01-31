/**
 * 
 */
package unitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.jupiter.api.Test;

import charging_station.Charger;
import vehicle.Car;

/**
 * 
 */
public class chargerTest {


	@Test
    public void popCarTest() {
		int chargerId = 0;
		int stationId = 0;
		ArrayList<Car> waitingCars = new ArrayList<Car>();
		final Lock waitingCarGuard = new ReentrantLock();
		ArrayList<Car> bookingCars = new ArrayList<Car>();
		final Lock bookingCarGuard = new ReentrantLock();
		
		waitingCars.add(new Car(0));
		
		Charger charger = new Charger(chargerId, stationId, waitingCars, waitingCarGuard, bookingCars, bookingCarGuard, null);
		charger.pop();
		
        assertEquals(waitingCars.isEmpty(), true);
    }

	@Test
    public void startChargingTest() {
		int chargerId = 0;
		int stationId = 0;
		ArrayList<Car> waitingCars = new ArrayList<Car>();
		final Lock waitingCarGuard = new ReentrantLock();
		ArrayList<Car> bookingCars = new ArrayList<Car>();
		final Lock bookingCarGuard = new ReentrantLock();
		
		Charger charger = new Charger(chargerId, stationId, waitingCars, waitingCarGuard, bookingCars, bookingCarGuard, null);
		charger.listenerComChannel = new ArrayList<int[]>();
		charger.handledCar = new Car(0);
		charger.start_charge();
		
        assertEquals(charger.occupied, true);
    }

	@Test
    public void stopChargingTest() {
		int chargerId = 0;
		int stationId = 0;
		ArrayList<Car> waitingCars = new ArrayList<Car>();
		final Lock waitingCarGuard = new ReentrantLock();
		ArrayList<Car> bookingCars = new ArrayList<Car>();
		final Lock bookingCarGuard = new ReentrantLock();
		
		Charger charger = new Charger(chargerId, stationId, waitingCars, waitingCarGuard, bookingCars, bookingCarGuard, null);
		charger.listenerComChannel = new ArrayList<int[]>();
		charger.handledCar = new Car(0);
		charger.stop_charge();
		
        assertEquals(charger.occupied, false);
    }

}
