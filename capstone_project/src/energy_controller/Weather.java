package energy_controller;

public class Weather {
	private boolean sunny;
	private boolean windy;
	private boolean raining;

	// Constructor
	public Weather(boolean sunny, boolean windy, boolean raining) {
		this.sunny = sunny;
		this.windy = windy;
		this.raining = raining;
	}

	// Default constructor for a generic weather condition
	public Weather() {
		this(false, true, false); // Default to a neutral weather state
	}

	// Getters and setters for each weather condition
	public boolean isSunny() {
		return sunny;
	}

	public void setSunny(boolean sunny) {
		this.sunny = sunny;
	}

	public boolean isWindy() {
		return windy;
	}

	public void setWindy(boolean windy) {
		this.windy = windy;
	}

	public boolean isRaining() {
		return raining;
	}

	public void setRaining(boolean raining) {
		this.raining = raining;
	}

	// Overriding the equals method to compare weather objects
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Weather weather = (Weather) obj;
		return sunny == weather.sunny && windy == weather.windy && raining == weather.raining;
	}

	// Overriding the toString method for logging
	@Override
	public String toString() {
		return "Weather{sunny=" + sunny + ", windy=" + windy + ", raining=" + raining + "}";
	}
}
