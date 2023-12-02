
package charging_station;

import capstone.Log;
import vehicle.Car;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Charger  extends Thread{
		
	// Attribute
	private Lock waitingCarsGuard; 
	private ArrayList<Car> waitingCars;
	int id;
	boolean occupied;
	int chargingStationId;
	Log logger;
	Car handledCar;
	
	// Constructor
	public Charger(int id, int chargingStationId, ArrayList<Car> waitingCars, Lock waitingCarsGuard){
		/*
		 * input : id of the charger, id of charger station that this charger belong to, queue of waiting car, guard for the waiting queue 
		 */
		setId(id);
		logger = new Log("charger\\charger"+String.valueOf(getId_()),"Charger "+ String.valueOf(getId_()));
		setChargingStationId(chargingStationId);
		this.waitingCars = waitingCars;
		this.waitingCarsGuard = waitingCarsGuard;
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
		while (true) {
			pop();
			start_charge();
			stop_charge();
		}
	}

	public void charging() {
		/*
		 * this function is to simulate charging a car by implementing delay function or sleep function
		 */
		try {
			Thread.sleep(5000);

		} catch (InterruptedException e) {
			e.printStackTrace();
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
		 *  stop charging and set self to free
		 */
		logger.info("Charger " + String.valueOf(id) + " - Stop charging car " + this.handledCar.getId_());
		// set the occupied to false
		setOccupied(false);
		// reset
		handledCar = null;

	}

	void pop() {
		/*
		 * this function to pop a car from car waiting list and store the car for charge use
		 */
		logger.info("Waiting for new car");
		while (handledCar == null) {
			waitingCarsGuard.lock();
			if (waitingCars.size() > 0) {
				handledCar = waitingCars.remove(0);
			}
			waitingCarsGuard.unlock();
			
		}
		logger.info("Car " + handledCar.id + " was taken from the queue and will be charged");
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
	
	// to strings
	@Override
	public String toString() {
		return "Charger [id=" + id + ", occupied=" + Boolean.toString(this.occupied) + "]";
	}
	 public static void main(String args[]) {
	
		 int stationId = 0;
		 Lock waitinQueueGuard = new ReentrantLock(); // mutual exclusion for waiting car queue
		 ArrayList<Car> waitingCars = new ArrayList<>(); // queue of waiting cars in the charging station
		 Charger charger1 = new Charger(0,stationId,waitingCars, waitinQueueGuard);
		 charger1.run();
	 }
	
}