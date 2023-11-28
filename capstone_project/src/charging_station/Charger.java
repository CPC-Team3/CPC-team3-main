package charging_station;

import charging_station.ExceptionHandler.InitException;

import java.io.*;
public class Charger {
		
	// Attribute
	int id;
	boolean occupied;
	int chargingStationId;
	Log logger;
	
	// Constructor
	Charger(int id, int chargingStationId) throws InitException{
		/*
		 * input : id of the charger, id of charger station that this charger belong to
		 */
		try {
			logger = new Log("charger"+String.valueOf(this.id),"Charger "+ String.valueOf(this.id));
			logger.init();
			this.id = id;
			this.chargingStationId = chargingStationId;
		}catch(InitException e) {
			System.out.print("Unable to initialize Charger "+ String.valueOf(id));
		}
		
		
	}
	
	// Functionality

	public void writeToStream(OutputStream outputStream) throws IOException {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
			objectOutputStream.writeInt(id);
			objectOutputStream.writeBoolean(occupied);
			objectOutputStream.writeInt(chargingStationId);
		}
	}

	// Method to read Charger information from a byte stream
	public void readFromStream(InputStream inputStream) throws IOException, ClassNotFoundException {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
			id = objectInputStream.readInt();
			occupied = objectInputStream.readBoolean();
			chargingStationId = objectInputStream.readInt();
		}
	}

	// Method to write Charger information to a character stream
	public void writeToFile(Writer writer) throws IOException {
		try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
			bufferedWriter.write(String.valueOf(id));
			bufferedWriter.newLine();
			bufferedWriter.write(String.valueOf(occupied));
			bufferedWriter.newLine();
			bufferedWriter.write(String.valueOf(chargingStationId));
			bufferedWriter.newLine();
		}
	}

	// Method to read Charger information from a character stream
	public void readFromFile(Reader reader) throws IOException {
		try (BufferedReader bufferedReader = new BufferedReader(reader)) {
			id = Integer.parseInt(bufferedReader.readLine());
			occupied = Boolean.parseBoolean(bufferedReader.readLine());
			chargingStationId = Integer.parseInt(bufferedReader.readLine());
		}
	}


	void start_charge() {
		this.logger.log.info("Charger " + String.valueOf(id) + "Start charging");
	}
	
	void stop_charge() {
		this.logger.log.info("Charger " + String.valueOf(id) + "Stop charging");
		// set the occupied to false
		this.occupied = false;
	}
	
	
	// setters and getter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "Charger [id=" + id + ", occupied=" + occupied + "]";
	}
	
}
