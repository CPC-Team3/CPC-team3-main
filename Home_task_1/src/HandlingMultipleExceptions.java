/*
 * Fachhochschule Dortmund
 * Sheikh Muhammad Adib Bin Sheikh Abu Bakar
 * Student ID: 7219310
 * ESE student
 * Task: Handling multiple exceptions
 */


public class HandlingMultipleExceptions {
	public static void main(String[] args) {
		// this function is used to simulate runtime error and shows example of handling multiple exception (ArrayStoreException and ClassCastException) 
		try {
			
			// to trigger ArrayStoreException
			if (false) {
				Object x[] = new String[3];
			    x[0] = new Integer(0);
			}
			
		    // to trigger ClassCastException
		    if (true) {
		    	Object x = new Integer(0);
			    System.out.println((String)x); 
		    }
			
		}catch (ClassCastException e ) { 
			System.out.println("ClassCastException: " + e.getMessage());
			
		}catch (ArrayStoreException e) { 
			System.out.println("ArrayStoreException: " + e.getMessage());
		}
	}


}
