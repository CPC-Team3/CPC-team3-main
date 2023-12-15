package simulation;

import java.util.ArrayList;

import capstone.Standard;
import capstone.exception_handler.listeningException;
import system.ChargingSystem;
import vehicle.Car;

public class Simulation {
	static ArrayList<int[]> comChannel = new ArrayList<int[]>();
	static ArrayList<int[]> comChannel2 = new ArrayList<int[]>();
	static ArrayList<Car> simCar = new ArrayList<Car>();
	
	public static void run(){
		
		// set t0 as reference for a day measurement or booking slot 
		Standard.T0 = System.currentTimeMillis();
		
		// wait certain time (to execute fake car by charging station)
		long start = System.currentTimeMillis();
		//while (System.currentTimeMillis() - start < 20000) {
		//}
		
		// create car for simulation
		int id_ = 0;
		
		// add real time car
		for (;id_<5; id_++) {
			simCar.add(new Car(id_, comChannel, comChannel2));
		}
		// add booking car
		simCar.add(new Car(5, comChannel, comChannel2,5000,0));
		simCar.add(new Car(6, comChannel, comChannel2,10000,0));
		simCar.add(new Car(7, comChannel, comChannel2,60000,0));
		
		// start the car 
		for(Car car: simCar) {
			car.start();
		}
		
		// keep simulation alive
		while(System.currentTimeMillis() - start < Standard.SimulationDuration) {
			//change date after a certain time 
		}
	}
	
	public static void main(String[] args) throws listeningException, InterruptedException {
		
		//execute the system
		ChargingSystem sys1 = new ChargingSystem("bookingFilePath", "bookingFilePath2", comChannel, comChannel2);
		sys1.start();

		// execute some car
		run();
		System.out.println("simulationfinish");
	}
}
