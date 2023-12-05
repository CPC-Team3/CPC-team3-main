
package vehicle;

import java.util.ArrayList;
import java.util.Iterator;

import capstone.Standard;

public class Car extends Thread{
	// Attribute
    public int id = -1;
    ArrayList<int[]> comChannel;
    long start = System.currentTimeMillis();
    
    // constructors
    public Car(int id) {
        this.id = id;
        
    }
    
    
    public Car(int id, ArrayList<int[]> comChannel) {
		this.comChannel =comChannel;
		this.id = id;
	}


	public void run() {
    	request();
    	try {
			wait_();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    // functionalities
    void request() {
    	
    	//request
    	int[] message = {this.id,Standard.REQUEST};
		Standard.messageTransmitReceiveSimulationGuard.lock();
    	comChannel.add(message);
		Standard.messageTransmitReceiveSimulationGuard.unlock();
    	//wait 
    	
    }
    
    void wait_() throws InterruptedException {
    	/*
    	 * wait for maximum duration
    	 * wait for done message at the same time
    	 */
    	boolean done = false;
		while(!done) {
    		// if done
			Standard.messageTransmitReceiveSimulationGuard.lock();
			Iterator<int[]> iterator = comChannel.iterator();
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
    			leave();
    			break;
    		}
    		
    		
    	}
    }
    
    void book() {
    	
    }
    
    void leave() {
    	int[] message = {this.id,Standard.LEAVE};
		Standard.messageTransmitReceiveSimulationGuard.lock();
    	comChannel.add(message);
		Standard.messageTransmitReceiveSimulationGuard.unlock();
    }
    
    public int getId_() {
    	return this.id;
    }
    
    //test
}
