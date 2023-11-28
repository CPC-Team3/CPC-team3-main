package energy_controller;

import java.io.*;
import java.util.ArrayList;
import energy_controller.ExceptionHandler.InitException;
import energy_controller.ExceptionHandler.energySwitchException;

public class Controller {
	// Attributes	
	int id; // charging station id
	private ArrayList<Energy> EnergySource = new ArrayList<Energy>(); // list of energy source
	Log logger;
	Energy currentEnergySource;
	Boolean output = false;
	
	// Constructor
	Controller(int id){
		/*
		 * input : id of the controller
		 */
		try {
			logger = new Log("charger"+String.valueOf(this.id),"Charger "+ String.valueOf(this.id));
			logger.init();
			this.id = id;
		}catch(InitException e) {
			System.out.print("Unable to initialize Charging Station "+ String.valueOf(id));
		}
	}

	public void writeToStream(OutputStream outputStream) throws IOException {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
			objectOutputStream.writeInt(id);
			objectOutputStream.writeObject(EnergySource);
			objectOutputStream.writeObject(currentEnergySource);
			objectOutputStream.writeBoolean(output);
		}
	}

	// Method to read Controller information from a byte stream
	public void readFromStream(InputStream inputStream) throws IOException, ClassNotFoundException {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
			id = objectInputStream.readInt();
			EnergySource = (ArrayList<Energy>) objectInputStream.readObject();
			currentEnergySource = (Energy) objectInputStream.readObject();
			output = objectInputStream.readBoolean();
		}
	}

	// Method to write Controller information to a character stream
	public void writeToFile(Writer writer) throws IOException {
		try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
			bufferedWriter.write(String.valueOf(id));
			bufferedWriter.newLine();
			for (Energy energy : EnergySource) {
				energy.writeToFile(bufferedWriter);
			}
			bufferedWriter.write(currentEnergySource.toString());
			bufferedWriter.newLine();
			bufferedWriter.write(String.valueOf(output));
			bufferedWriter.newLine();
		}
	}

	// Method to read Controller information from a character stream
	public void readFromFile(Reader reader) throws IOException {
		try (BufferedReader bufferedReader = new BufferedReader(reader)) {
			id = Integer.parseInt(bufferedReader.readLine());
			for (Energy energy : EnergySource) {
				energy.readFromFile(bufferedReader);
			}
			currentEnergySource = new Energy(bufferedReader.readLine()); // Adjust this line based on your Energy class
			output = Boolean.parseBoolean(bufferedReader.readLine());
		}
	}
	
	// energy source switcher
	void switcher(Energy newEnergySource) throws energySwitchException {
		/*
		 * switch the current energy to new energySource
		 */
		this.currentEnergySource = newEnergySource;
	}
		
	// start supply
	void startSupply(){
		this.output = true;
	}
	
	// stop supply
	void stopSupply(){
		this.output = true;
	}
		
	// setters and getters

	public ArrayList<Energy> getEnergySource() {
		return EnergySource;
	}

	public void setEnergySource(ArrayList<Energy> energySource) {
		EnergySource = energySource;
	}
	
	void addEnergySource(Energy newEnergy) {
		this.EnergySource.add(newEnergy);
	}
	
	void aremoveEnergySource(Energy unwantedEnergy) {
		this.EnergySource.remove(unwantedEnergy);
	}
	
	
	
	
	
	
		

}
