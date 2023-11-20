package capstone;

import java.util.ArrayList;
import java.io.IOException;

// exceptions

class assigningException extends Exception {
	public assigningException(String message) {
        super(message);
    }
}

class listeningException extends Exception {
	public listeningException(String message) {
        super(message);
    }
}

public class ChargeStation {
	
	
	ArrayList<Charger> chargers = new ArrayList<Charger>(); // list of charger in the charging station
	int requestCar[]; // list of vehicle (id) that request to charge on the spot
	String bookFilePath; // list of booking

	int availability() {
		/* 
		 * return  id of free charger
		 */
		
		// Logging
		
		return 0;
	}
	
	int listening( String bookFilePath) throws listeningException{
		/* 
		 * return  id of waiting vehicle from requestCar and bookFile
		 */
		boolean file_exist = false;
		// read booking file
		if (file_exist) {
			
		}else {
			throw new listeningException("could not found booking file");
		}
			
		
		// Logging
		
		return 0;
	}
	
	void assigning(int carID, int chargerID) throws assigningException {
		/* car id as the input
		 * assign the vehicle to a specific charger
		 */
		int vehicle_id = -1;
		// get vehicle id 
		if (vehicle_id < 0) {
			throw new assigningException("vehicle is not exist");
		}
		
		
		// Logging
		
	}
	
	void book(int id, double timeSlot) {
		/* 
		 * book a time slot by any car to charge
		 */
		
		// Logging
		
	}
	

	
	
	// setters and getters

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
