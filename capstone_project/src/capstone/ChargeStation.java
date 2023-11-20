package capstone;

import java.util.ArrayList;

public class ChargeStation {
	
	
	ArrayList<Charger> chargers = new ArrayList<Charger>; // list of charger in the charging station
	int requestCar[]; // list of vehicle (id) that request to charge on the spot
	String bookFilePath; // list of booking

	int availability() {
		/* 
		 * return  id of free charger
		 */
		
		// Logging
		
		return 0;
	}
	
	int listening( String bookFilePath) {
		/* 
		 * return  id of waiting vehicle from requestCar and bookFile
		 */
		
		// Logging
		
		return 0;
	}
	
	void assigning(int carID, int chargerID) {
		/* car id as the input
		 * assign the vehicle to a specific charger
		 */
		
		// Logging
		
	}
	
	void book(int id, double timeSlot) {
		/* 
		 * book a time slot by any car to charge
		 */
		
		// Logging
		
	}
	
	// exceptions
	
	class assigningException extends Exception {
		public assigningException(String message) {
	        super(message);
	    }
	}
	
	class bookException extends Exception {
		public bookException(String message) {
	        super(message);
	    }
	}
	
	
	
	// setters and getters

	public Charger[] getChargers() {
		return chargers;
	}

	public void setChargers(Charger[] chargers) {
		this.chargers = chargers;
	}

	public int[] getRequestCar() {
		return requestCar;
	}

	public void setRequestCar(int[] requestCar) {
		this.requestCar = requestCar;
	}

	public String getBookFilePath() {
		return bookFilePath;
	}

	public void setBookFilePath(String bookFilePath) {
		this.bookFilePath = bookFilePath;
	}

}
