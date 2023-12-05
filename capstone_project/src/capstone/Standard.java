package capstone;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Standard {
	// Weather
	public static final int SUNNY = 0;
	public static final int WINDY = 1;
	
	// message type
	public static final int REQUEST = 0;
	public static final int LEAVE = 1;
	public static final int DONE = 2;
	
	// duration
	public static final long MAX_WAIT = 15000; //Milliseconds
	public static final long chargeDuration = 4000; //Milliseconds

	// component life time
	public static final long chargerLifeDuration = 200000; //Milliseconds
	public static final long stationLifeDuration = chargerLifeDuration + 3000; //Milliseconds
	public static final long systemLifeDuration = stationLifeDuration + 3000; //Milliseconds
	public static final long SimulationDuration = systemLifeDuration + 3000; //Milliseconds
	
	// message guard - (no messages is transmitted or received at the same time during simulation)
	public final static Lock messageTransmitReceiveSimulationGuard = new ReentrantLock();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
