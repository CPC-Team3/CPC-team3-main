package charging_station;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import capstone.Log;
import capstone.Standard;
import capstone.exception_handler.listeningException;
import vehicle.Car;

public class ChargingStation extends Thread{
	
	// Attributes	
	int id; // charging station id
	ArrayList<Charger> chargers = new ArrayList<Charger>(); // list of charger in the charging station
	ArrayList<Thread> chargerThreads = new ArrayList<Thread>(); // list of charger in the charging station
	ArrayList<int[]> listenerComChannel; 
	
	String bookFilePath; // list of booking
	Log logger;
	String date;
	
	
	public ArrayList<Car> waitingCars = new ArrayList<>(); // queue of waiting cars in the charging station
	public final Lock waitingCarGuard = new ReentrantLock();;
	
	
	// Constructor
	public ChargingStation(int id,String bookFilePath, ArrayList<int[]> comChannel) {
		/*
		 * input : id of the charging station
		 */
		logger = new Log("station\\ChargingStation"+String.valueOf(this.id),"Charging Station "+ String.valueOf(this.id), Standard.date);
		setId(id);
		this.bookFilePath = bookFilePath;
		this.listenerComChannel = comChannel;
		setDate(Standard.date);
	
	}

	// Functionalities
	public void run() {
		/*
		 * run the listening function as a process to update waiting car queue
		 * run each charger as a process to charge handle the cars in car queue
		 * 
		 */
		
		//run each charger
		logger.info("starting each charger");
		for (Charger charger: chargers) {
			logger.info("starting charger " + charger.getId_());
			charger.start();
		}
		
		// run listening
		logger.info("starting listening function");
		try {
			listening(bookFilePath);
		} catch (listeningException e) {
			
			e.printStackTrace();
		}
		
		// wait all the charger to turned off
		logger.info("waiting for all charger to finish");
		for (Thread thread : chargerThreads) {
			try {
				thread.join();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		logger.info("All charging station components are off");
	}
	
	void listening( String bookFilePath) throws listeningException{
		/* 
		 * read the booking file and wait for new incoming cars and put them in car waiting 
		 */
		
		//boolean SimulationExecuted = false; // for simulation
		// put new incoming vehicle to queue
		long start = System.currentTimeMillis();
		logger.info("Listener component is on");
		while(System.currentTimeMillis() - start < Standard.stationLifeDuration) {
			
			// for now we just use fix list of vehicle
			//for (int i = 0 ; i < 10 && !SimulationExecuted; i++) {
			//	logger.info("adding fake cars " + i);
			//	waitingCarGuard.lock();
			//	waitingCars.add(new Car(i));
			//	waitingCarGuard.unlock();
			//}
			//SimulationExecuted = true;
			
			// read any message
			Standard.messageTransmitReceiveSimulationGuard.lock();
			Iterator<int[]> iterator = listenerComChannel.iterator();
			while(iterator.hasNext()) {
				int[] element = iterator.next();
				if(element[1] == Standard.REQUEST) {
					logger.info("cars with id " + element[0] + " request to charge");
					waitingCarGuard.lock();
					waitingCars.add(new Car(element[0]));
					logger.info("cars with id " + element[0] + " is added to waiting queue");
					waitingCarGuard.unlock();
					iterator.remove();
				}
				if(element[1] == Standard.LEAVE) {
					logger.info("cars with id " + element[0] + " leave the station");
					waitingCarGuard.lock();
					Iterator<Car> carIterator = waitingCars.iterator();
					while (carIterator.hasNext()) {
					    Car car = carIterator.next();
					    if (car.getId_() == element[0]) {
					        logger.info("Car with id " + element[0] + " is removed from waiting queue");
					        carIterator.remove();
					    }
					}
					waitingCarGuard.unlock();
					iterator.remove();
				}
				
				
			}
			Standard.messageTransmitReceiveSimulationGuard.unlock();
			
			// update date and create new logger for new date
			if (this.date != Standard.date) {
				setDate(Standard.date);
				logger = new Log("station\\ChargingStation"+String.valueOf(this.id),"Charging Station "+ String.valueOf(this.id), Standard.date);
			}
		}
		logger.info("Listener component is off" );
		// put vehicle from book file to queue (if booking time >= current time)
	}
	

	
	void book(int id, double timeSlot) {
		/* 
		 * book a time slot by any car to charge
		 */
		
		// Logging
		
	}
	
	public void addCharger(int chargerId) {
		chargers.add(new Charger(chargerId, this.id, waitingCars, waitingCarGuard, listenerComChannel));
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
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
	// test
	public static void main(String args[]) {
		 
		 String bookFilePath = "dummy";
		 ArrayList<int[]> dummyMessageQueue = new ArrayList<int[]>();
		 
		 ChargingStation station1 = new ChargingStation(0, bookFilePath, dummyMessageQueue);
		 for (int i = 0; i < 2; i++) {
			 station1.addCharger(i);
		 }
		 station1.start();
		 
		 
	 }
	

}