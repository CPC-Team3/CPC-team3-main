package charging_station;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import capstone.Log;
import capstone.exception_handler.listeningException;
import vehicle.Car;

public class ChargingStation extends Thread{
	
	// Attributes	
	int id; // charging station id
	ArrayList<Charger> chargers = new ArrayList<Charger>(); // list of charger in the charging station
	ArrayList<Thread> chargerThreads = new ArrayList<Thread>(); // list of charger in the charging station
	String bookFilePath; // list of booking
	Log logger;
	
	private ArrayList<Car> waitingCars = new ArrayList<>(); // queue of waiting cars in the charging station
	private final Lock waitingCarGuard = new ReentrantLock();;
	
	
	// Constructor
	public ChargingStation(int id,String bookFilePath) {
		/*
		 * input : id of the charging station
		 */
		logger = new Log("station\\ChargingStation"+String.valueOf(this.id),"Charging Station "+ String.valueOf(this.id));
		setId(id);
		this.bookFilePath = bookFilePath;
	
	}


	


	// Functionalities
	public void run() {
		/*
		 * run the listening function as a process to update waiting car queue
		 * run each charger as a process to charge handle the cars in car queue
		 * 
		 */
		
		// run listening
		logger.info("starting listening function");
		try {
			listening(bookFilePath);
		} catch (listeningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//run each charger
		logger.info("starting each charger");
		for (Charger charger: chargers) {
			logger.info("starting charger " + charger.getId_());
			charger.start();
		}
		
		// wait all the charger to turned off

		logger.info("waiting for all charger to finish");
		for (Thread thread : chargerThreads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	void listening( String bookFilePath) throws listeningException{
		/* 
		 * read the booking file and wait for new incoming cars and put them in car waiting 
		 */
			
		// put new incoming vehicle to queue
		// for now we just use fix list of vehicle
		boolean executed = false;
		while (executed == false) {

			logger.info("adding fake cars");
			executed = true;
			for (int i = 0 ; i < 10; i++) {
				logger.info("adding fake cars " + i);
				waitingCars.add(new Car(i));
			}
		}
		executed = true;
		
			
		// put vehicle from book file to queue (if booking time >= current time)
		
			
	}
	

	
	void book(int id, double timeSlot) {
		/* 
		 * book a time slot by any car to charge
		 */
		
		// Logging
		
	}
	
	public void addCharger(int chargerId) {
		chargers.add(new Charger(chargerId, this.id, waitingCars, waitingCarGuard));
		logger.info(" adding new charger to station "+ getId_() + " with id " + chargerId);
	}
	
	
	// setters and getters
	
	private void setId(int id) {
		this.id = id;
		
	}
	public String getBookFilePath() {
		return bookFilePath;
	}

	public void setBookFilePath(String bookFilePath) {
		this.bookFilePath = bookFilePath;
	}
	
	// to strings
	@Override
	public String toString() {
		return "Charging Station [id=" + this.id + "]";
	}


	public int getId_() {
		return this.id;
	}
	
	public Lock getWaitingCarGuard() {
		return waitingCarGuard;
	}
	
	
	
	
	// test
	public static void main(String args[]) {
		 
		 String bookFilePath = "dummy";
		 
		 ChargingStation station1 = new ChargingStation(0, bookFilePath);
		 for (int i = 0; i < 2; i++) {
			 station1.addCharger(i);
		 }
		 station1.start();
		 
		 
	 }
	

}