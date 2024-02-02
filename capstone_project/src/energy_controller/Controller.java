package energy_controller;

import java.util.ArrayList;

import capstone.Log;
import capstone.Standard;
import capstone.exception_handler.energySwitchException;
import java.util.Random;

public class Controller extends Thread{
	// Oder in energy source


	// Attributes
	int id; // charging station id
	private ArrayList<Energy> EnergySource = new ArrayList<Energy>(); // list of energy source
	Log logger;
	public Energy currentEnergySource;
	boolean output = false;
	boolean running = true;
	ArrayList<Weather> wheatherForecast;
	public Weather currentWeather;

	String date;

	// Constructor
	public Controller(int id, ArrayList<Weather> weatherForecast) {
		this.id = id;
		this.wheatherForecast = weatherForecast;
		this.currentWeather = !weatherForecast.isEmpty() ? weatherForecast.get(0) : new Weather(); // Use first weather forecast or default
		logger = new Log("controller\\controller" + id, "Controller " + id, Standard.date);
		logger.init(); // Initialize the logger if required
		this.output = false;
		this.running = true;
		this.date = Standard.date;
	}

	// Functionalities

	public synchronized void updateWeather(Weather newWeather) {
		//System.out.println(this.currentWeather);
		//System.out.println(newWeather);
		//System.out.println(!newWeather.equals(this.currentWeather));
		if (!newWeather.equals(this.currentWeather)) {
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			this.currentWeather = newWeather;
			switchEnergySource();

			logger.info("Changed weather to: " + newWeather.toString());
		}
	}

	private void switchEnergySource() {
		try {
			if (currentWeather.isSunny()) {
				//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				for (Energy energy : EnergySource) {
					if (energy.getName().equalsIgnoreCase("solar") && !energy.equals(currentEnergySource)) {
						//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
						currentEnergySource = energy;
						logger.info("Switched to Solar Energy");
						break;
					}
				}
			} else if (currentWeather.isWindy()) {
				//System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				for (Energy energy : EnergySource) {
					if (energy.getName().equalsIgnoreCase("wind") && !energy.equals(currentEnergySource)) {
						//System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
						currentEnergySource = energy;
						logger.info("Switched to Wind Energy");
						break;
					}
				}
			}
			// Additional conditions for other types of energy can be added here
		} catch (Exception e) {
			logger.warning("Failed to switch energy source: " + e.getMessage());
		}

		//System.out.println("NOTHING");
	}

	@Override
	public void run() {
		startSupply();
		Weather setWeather = new Weather(); // Initial weather state

		while (running) {
			try {
				// Simulate fetching current weather data
				Weather currentWeather = fetchCurrentWeather();

				// Update weather and possibly switch energy sources
				updateWeather(currentWeather);

				// Update date and logger if the date has changed
				if (!this.date.equals(Standard.date)) {
					this.date = Standard.date;
					logger = new Log("controller\\controller" + this.id, "Controller " + this.id, Standard.date);
				}

				Thread.sleep(10000); // Pause for simulation purposes

			} catch (InterruptedException e) {
				logger.warning("Controller thread interrupted");
				running = false; // Stop the thread if interrupted
			}
		}
		logger.info("Energy Controller stopped");
	}

	public void stopController() {
		this.running = false;
		this.interrupt();
	}

	private Weather fetchCurrentWeather() {
		// Create a new Weather instance
		Weather simulatedWeather = new Weather();

		// Randomly set weather conditions
		Random random = new Random();
		simulatedWeather.setSunny(random.nextBoolean());
		simulatedWeather.setWindy(random.nextBoolean());
		simulatedWeather.setRaining(random.nextBoolean());

		// Ensure that not all conditions are false or true at the same time
		while (simulatedWeather.isSunny() == simulatedWeather.isWindy() &&
				simulatedWeather.isWindy() == simulatedWeather.isRaining()) {
			simulatedWeather.setSunny(random.nextBoolean());
			simulatedWeather.setWindy(random.nextBoolean());
			simulatedWeather.setRaining(random.nextBoolean());
		}

		return simulatedWeather;
	}

	public void addEnergy(Energy newEnergy) {
		/*
		 * Add new energy source to the controller
		 */
		logger.info("Adding new energy source - " + newEnergy);
		EnergySource.add(newEnergy);
	}

	// energy source switcher
	void switcher(Energy newEnergySource) throws energySwitchException {
		/*
		 * switch the current energy to new energySource
		 */
		logger.info("Change energy source to " + newEnergySource);
		this.currentEnergySource = newEnergySource;
	}

	// start supply
	void startSupply(){
		currentEnergySource = EnergySource.get(0);
		logger.info("Start supplying with current energy " + this.currentEnergySource);
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

	void removeEnergySource(Energy unwantedEnergy) {
		this.EnergySource.remove(unwantedEnergy);
	}

	public void setDate(String date) {
		this.date = date;
	}


	//test
	public static void main(String[] args) {
		// Create a list of weather forecasts for testing
		ArrayList<Weather> weatherForecast = new ArrayList<>();
		weatherForecast.add(new Weather(true, false, false)); // Sunny
		weatherForecast.add(new Weather(false, true, false)); // Windy

		// Initialize the controller with the weather forecast
		Controller energyController1 = new Controller(0, weatherForecast);

		// Add energy sources
		energyController1.addEnergy(new Energy("solar", "renewable"));
		energyController1.addEnergy(new Energy("wind", "renewable"));

		// Start the controller
		energyController1.start();

		// Simulation running for a while
		try {
			Thread.sleep(10000); // Let the simulation run for 10 seconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Stop the controller
		energyController1.stopController();
	}





}