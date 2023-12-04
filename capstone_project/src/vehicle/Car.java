
package vehicle;

import java.util.ArrayList;

import capstone.Standard;

public class Car extends Thread{
	// Attribute
    public int id;
    ArrayList<int[]> comChannel;
    long start = System.currentTimeMillis();
    
    // constructors
    public Car(int id) {
        this.id = id;
    }
    
    
    public Car(int id2, ArrayList<int[]> comChannel) {
		this.comChannel =comChannel;
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
    	comChannel.add(message);
    	//wait 
    	
    }
    
    void wait_() throws InterruptedException {
    	boolean done = false;
		long counter = 0;
    	while(!done) {
    		counter++;
    		
    		// if done
    		for (int[] message : comChannel) {
    			if (message[0] == id) {
    				if(message[1] == Standard.DONE) {
    					done = true;
    					break;
    				}
    			}
    			
    		}
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
    	comChannel.add(message);
    }
    
    public int getId_() {
    	return this.id;
    }
    
    //test
}
