
package vehicle;

import java.util.ArrayList;
import java.util.Iterator;

import capstone.Standard;

public class Car extends Thread{
	// Attribute
    public int id = -1;
    ArrayList<int[]> comChannel;	// communication channel for charging station 0
    ArrayList<int[]> comChannel2;	// communication channel for charging station 1
    ArrayList<ArrayList<int[]>> comch = new ArrayList<ArrayList<int[]>>(); // list of communication channel
    long start;						// start waiting time
	boolean done = false;			// either the car is already charged or not
	String carCat;  				// Category of the car (fake car/ real time car/ booking car)
	public int bookingTime;				// seconds
	int bookingStation;
	
    
    // constructors - used for charger to store basic information needed or creating fake car
    public Car(int id) {
        this.id = id;
    }
    
    // constructors - spawn real time car
    public Car(int id, ArrayList<int[]> comChannel , ArrayList<int[]> comChannel2){
		this.id = id;
		this.comChannel =comChannel;
		this.comChannel2 =comChannel2;
		comch.add(comChannel);
		comch.add(comChannel2);
		this.carCat = "rtCar";
	}
    
 // constructors - spawn real time car that want to book a time slot
    public Car(int id, ArrayList<int[]> comChannel , ArrayList<int[]> comChannel2, int bookingTime, int bookingStation){
		this.id = id;
		this.comChannel =comChannel;
		this.comChannel2 =comChannel2;
		comch.add(comChannel);
		comch.add(comChannel2);
		this.carCat = "bookingCar";
		this.bookingStation  = bookingStation;
		this.bookingTime = bookingTime;
	}
    
    // constructors - used for charger to store basic information needed if the car book a time slot
	public Car(int id, int timeSlot) {
		this.id = id;
		this.bookingTime = timeSlot;
	}

	public void run() {
		if(carCat == "rtCar") {
			for (int i =0 ; i < comch.size()&& !done; i ++) {  // try for every channel/charging station to charge
				start = System.currentTimeMillis();
				request(i);
		    	try {
					wait_(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(carCat == "bookingCar"){
			book(bookingStation);
	    	try {
	    		start = System.currentTimeMillis();
				wait_(bookingStation);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    // functionalities
    void request(int i) {
    	
    	//request
    	int[] message = {this.id,Standard.REQUEST};
		Standard.messageTransmitReceiveSimulationGuard.lock();
		comch.get(i).add(message);
		Standard.messageTransmitReceiveSimulationGuard.unlock();
    	//wait 
    	
    }
    
    void wait_(int i) throws InterruptedException {
    	/*
    	 * wait for maximum duration
    	 * wait for done message at the same time
    	 * if timeout, try another charging station/ communication channel
    	 */
		while(!done) {
    		// if done
			Standard.messageTransmitReceiveSimulationGuard.lock();
			Iterator<int[]> iterator = comch.get(i).iterator();
    		while (iterator.hasNext()) {
				int[] element = iterator.next();
    			if (element[0] == id) {
    				if(element[1] == Standard.DONE) {
    					done = true;
    					break;
    				}
    			}
    		}
			Standard.messageTransmitReceiveSimulationGuard.unlock();
    		// if time up
    		if(!done &&((System.currentTimeMillis() - start) > Standard.MAX_WAIT) ) {
    			leave(i);
    			break;
    		}
    	}
    }
    
    void book(int i) {
    	//book
    	int[] message = {this.id,Standard.BOOK, bookingTime};
    	Standard.messageTransmitReceiveSimulationGuard.lock();
    	comch.get(i).add(message);
    	Standard.messageTransmitReceiveSimulationGuard.unlock();
    	try {
			Thread.sleep(bookingTime*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	
    }
    
    void leave(int i) {
    	int[] message = {this.id,Standard.LEAVE};
		Standard.messageTransmitReceiveSimulationGuard.lock();
		comch.get(i).add(message);
		Standard.messageTransmitReceiveSimulationGuard.unlock();
    }
    
    public int getId_() {
    	return this.id;
    }
    
    //test
}
