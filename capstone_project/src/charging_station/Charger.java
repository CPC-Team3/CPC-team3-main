
package charging_station;

import capstone.Log;
import capstone.Standard;
import vehicle.Car;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Charger extends Thread {

	// Attribute
	public Lock waitingCarsGuard;
	public ArrayList<Car> waitingCars;
	public Lock bookingCarsGuard;
	public ArrayList<Car> bookingCars;
	public ArrayList<int[]> listenerComChannel;
	int id;
	boolean occupied;
	int chargingStationId;
	Log logger;
	public Car handledCar = new Car(-1);
	String date = "";

	// Constructor
	public Charger(int id, int chargingStationId, ArrayList<Car> waitingCars, Lock waitingCarsGuard,
			ArrayList<Car> bookingCars, Lock bookingCarsGuard, ArrayList<int[]> listenerComChannel) {
		/*
		 * input : id of the charger, id of charger station that this charger belong to,
		 * queue of waiting car, guard for the waiting queue
		 */
		setId(id);
		logger = new Log(
				"charger\\station_" + String.valueOf(chargingStationId) + "_charger_" + String.valueOf(getId_()),
				"Charger " + String.valueOf(getId_()), Standard.date);
		setChargingStationId(chargingStationId);
		this.waitingCars = waitingCars;
		this.waitingCarsGuard = waitingCarsGuard;
		this.bookingCars = bookingCars;
		this.bookingCarsGuard = bookingCarsGuard;
		this.listenerComChannel = listenerComChannel;
		setDate(Standard.date);
	}

	// Functionality
	public void run() {
		/*
		 * the main process of the charger
		 * 1. take a car from waiting list
		 * 2. charge the car (set self to occupied)
		 * 3. stop charging (set self to free))
		 */
		logger.info("charger " + this.id + " starting ....");
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < Standard.chargerLifeDuration) {
			pop();
			start_charge();
			stop_charge();

			// update date and create new logger for new date
			if (this.date != Standard.date) {
				setDate(Standard.date);
				logger = new Log("charger\\charger" + String.valueOf(getId_()), "Charger " + String.valueOf(getId_()),
						Standard.date);
			}
		}
		logger.info("charger " + this.id + " shuting down");
	}

	private void charging() {
		/*
		 * this function is to simulate charging a car by implementing delay function or
		 * sleep function
		 */
		long start = System.currentTimeMillis();
		while ((System.currentTimeMillis() - start) < Standard.chargeDuration) {
			// wait 3 seconds
		}

	}

	void start_charge() {
		/*
		 * charge the taken car and set self to occupied
		 */
		logger.info("Charger " + String.valueOf(id) + " - Start charging car " + this.handledCar.getId_());
		setOccupied(true);
		charging();
	}

	void stop_charge() {
		/*
		 * stop charging and set self to free
		 */
		logger.info("Charger " + String.valueOf(id) + " - Stop charging car " + this.handledCar.getId_());
		// set the occupied to false
		setOccupied(false);

		// send done message to the car
		int[] message = { handledCar.id, Standard.DONE };
		Standard.messageTransmitReceiveSimulationGuard.lock();
		listenerComChannel.add(message);
		Standard.messageTransmitReceiveSimulationGuard.unlock();
		// reset
		handledCar = new Car(-1);

	}

	public void pop() {
		/*
		 * this function to pop a car from car waiting list or booking list and store
		 * the car for charge use
		 */
		logger.info("Waiting for new car");
		while (handledCar.id == -1) {

			// check for booking car
			bookingCarsGuard.lock();
			if (bookingCars.size() > 0) {
				Iterator<Car> iterator = bookingCars.iterator();
				while (iterator.hasNext()) {
					Car element = iterator.next();
					if (System.currentTimeMillis() - Standard.T0 > (long) element.bookingTime
							&& element.bookingTime > 1) {
						handledCar = element;
						iterator.remove();
						logger.info("Car " + handledCar.id + " was taken from the booking queue and will be charged");
					}
				}
			}
			bookingCarsGuard.unlock();
			// break if found
			if (handledCar.id != -1) {
				break;
			}

			// check for real time waiting car
			waitingCarsGuard.lock();
			if (waitingCars.size() > 0) {
				handledCar = waitingCars.remove(0);
				logger.info("Car " + handledCar.id + " was taken from the waiting queue and will be charged");
			}
			waitingCarsGuard.unlock();

		}
	}

	// setters and getter
	public int getId_() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChargingStationId() {
		return this.chargingStationId;
	}

	public void setChargingStationId(int id) {
		this.chargingStationId = id;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public void setDate(String date) {
		this.date = date;
	}

	// to strings
	@Override
	public String toString() {
		return "Charger [id=" + id + ", occupied=" + this.occupied + "]";
	}

	public static void main(String args[]) {

		int stationId = 0;
		Lock waitinQueueGuard = new ReentrantLock(); // mutual exclusion for waiting car queue
		ArrayList<Car> waitingCars = new ArrayList<>(); // queue of waiting cars in the charging station
		Lock bookinQueueGuard = new ReentrantLock(); // mutual exclusion for waiting car queue
		ArrayList<Car> bookingCars = new ArrayList<>(); // queue of waiting cars in the charging station

		Charger charger1 = new Charger(0, stationId, waitingCars, waitinQueueGuard, bookingCars, bookinQueueGuard,
				new ArrayList<int[]>());
		charger1.run();
	}
}