package capstone;

import java.util.ArrayList;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Scanner;

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
class FileNotExistException extends Exception {
    public FileNotExistException(String message) {
        super(message);
    }
}
class ChargingStationException extends Exception{
	public ChargingStationException(String message) {
		        super(message);
	    	}
}

class NoAvailableChargingLocationException extends ChargingStationException{
	public NoAvailableChargingLocationException(String message) {
		        super(message);
	    	}
}
class LongWaitingTimeException extends ChargingStationException{
	public LongWaitingTimeException(String message) {
		        super(message);
	����	}
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
		 /* 
         * return  id of waiting vehicle from requestCar and bookFile
         */
        try (Scanner scanner = new Scanner (new FileInputStream(bookFilePath))){
            boolean file_exist = false;
            // read booking file
            if (file_exist) {

            } else {
                throw new FileNotExistException("Booking file not found");
            }

            // Logging

            return 0;

        } catch (FileNotExistException e) {
            // Log the exception or take appropriate actions locally
            System.err.println("Error: " + e.getMessage());
            // Re-throw the exception for higher-level handling
            throw new listeningException("Error while listening for vehicle");
        }
    }
	
	void assigning(int carID, int chargerID) throws assigningException {
		 /* car id as the input
         * assign the vehicle to a specific charger
         */
        try {
            int vehicle_id = -1;
            // get vehicle id 
            if (vehicle_id < 0) {
                throw new assigningException("Vehicle is not exist");
            }

            // Logging

        } catch (assigningException e) {
            // Log the exception or take appropriate actions locally
            System.err.println("Error: " + e.getMessage());
            // Re-throw the exception for higher-level handling
            throw e;
        }
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