package unitTest;

import energy_controller.Controller;
import energy_controller.Energy;
import energy_controller.Weather;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ControllerTest {
	private Controller controller;
    private ArrayList<Weather> weatherForecast;

    @Before
    public void setUp() {
        weatherForecast = new ArrayList<>();
        weatherForecast.add(new Weather(true, false, false)); // Initially sunny
        controller = new Controller(1, weatherForecast);
        // Add energy sources to ensure the controller can switch between them
        controller.addEnergy(new Energy("solar", "renewable"));
        controller.addEnergy(new Energy("wind", "renewable"));
    }

    @Test
    public void testUpdateWeatherAndSwitchEnergySource() {
        // Update weather to windy
        Weather windyWeather = new Weather(false, true, false);
        controller.updateWeather(windyWeather);
        System.out.println(controller.currentWeather);
        assertEquals("Energy source should switch to wind on windy weather", "wind", controller.currentEnergySource.getName());

    }

    @Test
    public void testWeatherUpdate() {
        Weather initialWeather = controller.currentWeather;
        Weather newWeather = new Weather(false, false, true); // Raining
        controller.updateWeather(newWeather);
        assertNotEquals("Weather should be updated to new state", initialWeather, controller.currentWeather);
        assertEquals("Updated weather should be raining", newWeather, controller.currentWeather);
    }

    @Test
    public void testControllerStop() throws InterruptedException {
        controller.start();
        controller.stopController();
        Thread.sleep(100); // Allow time for the controller to stop
        assertFalse("Controller should no longer be running", controller.getRunning());
    }
}
