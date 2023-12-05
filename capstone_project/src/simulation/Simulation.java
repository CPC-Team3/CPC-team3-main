package simulation;

import java.util.ArrayList;

import capstone.Standard;
import capstone.exception_handler.listeningException;
import system.ChargingSystem;
import vehicle.Car;


public class Simulation {
	static volatile  ArrayList<int[]> comChannel = new ArrayList<int[]>();
	static ArrayList<Car> simCar = new ArrayList<Car>();
	
	public static void run(){
		
		// wait certain time (to execute fake car by charging station)
		long start = System.currentTimeMillis();
		//while (System.currentTimeMillis() - start < 20000) {
		//}
		
		
		// create car for simulation
		int id_ = 0;
		for (;id_<20; id_++) {
			simCar.add(new Car(id_, comChannel));
		}
		
		// start the car
		// for now fix arrival time
		for(Car car: simCar) {
			car.start();
		}
		
		// keep simulation alive
		while(System.currentTimeMillis() - start < Standard.SimulationDuration) {
		}
	}
	

	public static void main(String[] args) throws listeningException, InterruptedException {
		
		//execute the system
		ChargingSystem sys1 = new ChargingSystem("bookingFilePath", comChannel);
		sys1.start();

		// execute some car
		run();
		System.out.println("simulationfinish");
	}
}



