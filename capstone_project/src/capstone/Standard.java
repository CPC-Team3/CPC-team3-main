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
	public static final int BOOK = 3;
	
	// duration
	public static final long MAX_WAIT = 15000; //Milliseconds
	public static final long chargeDuration = 5000; //Milliseconds

	// component life time
	public static final long chargerLifeDuration = 200000; //Milliseconds
	public static final long stationLifeDuration = chargerLifeDuration + 3000; //Milliseconds
	public static final long systemLifeDuration = stationLifeDuration + 3000; //Milliseconds
	public static final long SimulationDuration = systemLifeDuration + 3000; //Milliseconds
	
	// message guard - (no messages is transmitted or received at the same time during simulation)
	public final static Lock messageTransmitReceiveSimulationGuard = new ReentrantLock();
	
	// starting time slot reference for booking
	public static long  T0;  //Milliseconds
	
	// date
	public static volatile String date = "1_1_2023";
	private static int day = 1;
	private static int month = 1;
	private static int year = 2023;
	public static void updateDate(String date) {
		if (date != null) {
			Standard.date = date;
		} else {
			day++;
			if ((month%2 == 0 && day > 30) || (month%2 == 1 && day > 31)) {
				month++;
				day = 1;
			}
			if ((month > 12)) {
				year++;
				month =1;
				day = 1;
			}
			date = String.valueOf(day) + "_" + String.valueOf(month) + "_" + String.valueOf(year);
			
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
