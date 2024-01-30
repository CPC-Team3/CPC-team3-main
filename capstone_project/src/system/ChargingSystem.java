package system;

import java.util.ArrayList;

import capstone.Log;
import capstone.Standard;
import charging_station.ChargingStation;
import energy_controller.Controller;
import energy_controller.Energy;
import energy_controller.Weather;

public class ChargingSystem extends Thread {
	/*
	 * combine everything as a system
	 */

	// Attribute
	public String bookFilePath;
	public String bookFilePath2;

	public Controller energyController1;
	ArrayList<int[]> carComChannel;
	ArrayList<int[]> carComChannel2;
	Log logger;
	int id;
	String date;

	public ChargingStation station1;
	public ChargingStation station2;

	// constructor
	public ChargingSystem(String bookFilePath, String bookFilePath2, ArrayList<int[]> carComChannel,
			ArrayList<int[]> carComChannel2) {
		this.bookFilePath = bookFilePath;
		this.bookFilePath2 = bookFilePath2;
		logger = new Log("system\\system" + String.valueOf(getId_()), "System " + String.valueOf(getId_()),
				Standard.date);
		this.carComChannel = carComChannel;
		this.carComChannel2 = carComChannel2;
		setDate(Standard.date);

	}

	// functionalities
	public void init() {
		/*
		 * this function is to initialize each component
		 */

		// initialize charging station
		logger.info("Initialize charging station");
		station1 = new ChargingStation(0, bookFilePath, carComChannel);
		station2 = new ChargingStation(1, bookFilePath2, carComChannel2);

		station1.addCharger(0);
		station1.addCharger(1);
		//station1.addCharger(2);
		//station1.addCharger(3);

		station2.addCharger(0);
		station2.addCharger(1);
		//station2.addCharger(2);
		//station2.addCharger(3);

		logger.info("Charging stations is ready");

		// initialize energy controller
		logger.info("Initialize energy controller");
		energyController1 = new Controller(0, new ArrayList<Weather>());
		energyController1.addEnergy(new Energy("solar", "renewable"));
		energyController1.addEnergy(new Energy("wind", "renewable"));
		// add energy source
		logger.info("energy controller is ready");
	}

	public void run() {

		logger.info("starting system1");
		init();

		logger.info("starting first station");
		station1.start();
		logger.info("starting second station");
		station2.start();

		energyController1.start();

		try {
			station1.join();
			station2.join();
			energyController1.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		// update date and create new logger for new date
		if (this.date != Standard.date) {
			setDate(Standard.date);
			logger = new Log("system\\system" + String.valueOf(getId_()), "System " + String.valueOf(getId_()),
					Standard.date);
		}
		logger.info("system1 finish");
	}

	// setters and getters
	private int getId_() {
		return id;
	}

	public void setDate(String date) {
		this.date = date;
	}

	// simulation
	public static void main(String[] args) {
		ChargingSystem sys1 = new ChargingSystem("bookingFilePath", "bookingFilePath2", new ArrayList<int[]>(),
				new ArrayList<int[]>());
		sys1.start();

	}
}
