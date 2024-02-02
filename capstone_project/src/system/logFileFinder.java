package system;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


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
	    
	    if (selection.equals("date")) {
	    	
	        Scanner scanner = new Scanner(System.in);

	        // Ask the user for the search date
	        System.out.print("Enter the date to search in the log file: ");
	        String searchString = scanner.nextLine();

	        // Close the scanner after reading the input
	        scanner.close();
	        String filePath = "C:" + File.separator + "Users" + File.separator + "Work pc" + File.separator + "Desktop" + File.separator + "CPC-team3-main-simulation_weeather" + File.separator + "CPC-team3-main-simulation_weeather" + File.separator + "capstone_project" + File.separator + "log" + File.separator + "station" + File.separator + "ChargingStation_0_1_1_2023_log";

	        SearchByDate(searchString,filePath);
	    	 
	    }
	    return null;
	    
	}
	
	public static String SearchByDate(String data, String path){
		
		 //String filePath = "path/to/your/logfile.log";
		
		 String filePath = path;
	     String searchString = data;
	     try {
	            File file = new File(filePath);
	            FileReader fileReader = new FileReader(file);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);

	            String line;
	            while ((line = bufferedReader.readLine()) != null) {
	                if (line.contains(searchString)) {
	                    return line;
	                  
	                }
	            }

	            bufferedReader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return "fail";
	}

	public static void main(String[] args) {
		 //logFileFinder f = new logFileFinder();
		//f.getInput();
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the search date
        System.out.print("Enter the date to search in the log file: ");
        String searchString = scanner.nextLine();

        // Close the scanner after reading the input
        scanner.close();
       
        String filePath = "C:\\Users\\Work pc\\Desktop\\CPC-team3-main-simulation_weeather\\CPC-team3-main-simulation_weeather\\capstone_project\\src\\system\\ChargingStation_0_1_1_2023_log.log";
        
        String data = SearchByDate(searchString,filePath); 
        System.out.println(data);
	
		


	}

}
