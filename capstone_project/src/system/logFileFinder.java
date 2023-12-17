package system;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;


public class logFileFinder {
	
	Pattern pattern = Pattern.compile("[a-z]+");
	Matcher matcher = pattern.matcher("example123");
	boolean matches = matcher.matches();
	
	
	logFileFinder(){
		
	}
	
	String getInput() {
		System.out.println("Log file finder for charging station system");
		Scanner option = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("select power controller (\"type controller\") or station (\"type station\"): ");
	    String selection = option.nextLine();
	    
	    if (selection.equals("station")) {
	    	Scanner station = new Scanner(System.in);
	    	System.out.println("select station id (0,1): ");
		    int station_id = option.nextInt();
		    
		    
	    }
	    
	    if (selection == "controller") {
	    	 
	    }
	    return null;
	    
	}

	public static void main(String[] args) {
		logFileFinder f = new logFileFinder();
		f.getInput();


	}

}
