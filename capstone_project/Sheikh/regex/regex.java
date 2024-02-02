package regex;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.regex.Matcher;
 
public class regex {
 
    public static void main(String[] args){
        //Console console = System.console();
        //if (console == null) {
        //    System.err.println("No console.");
        //    System.exit(1);
        //}
    	boolean g = true;
        while (g) {
 
            Pattern pattern = 
            Pattern.compile("INFO: ");//(console.readLine("%nEnter your regex: "));
 
            Matcher matcher = 
            pattern.matcher("Jan 31, 2024 4:22:54 PM capstone.Log init\r\n"
            		+ "INFO: Loging system for Charger 0 started\r\n"
            		+ "Jan 31, 2024 4:22:54 PM capstone.Log info\r\n"
            		+ "INFO: Charger 0 - Stop charging car 0\r\n"
            		+ "Jan 31, 2024 4:22:54 PM capstone.Log init\r\n"
            		+ "INFO: Loging system for Charger 0 started\r\n"
            		+ "Jan 31, 2024 4:22:54 PM capstone.Log info\r\n"
            		+ "INFO: Waiting for new car\r\n"
            		+ "Jan 31, 2024 4:22:54 PM capstone.Log info\r\n"
            		+ "INFO: Car 0 was taken from the waiting queue and will be charged\r\n"
            		+ "Jan 31, 2024 4:22:54 PM capstone.Log init\r\n"
            		+ "INFO: Loging system for Charger 0 started\r\n"
            		+ "Jan 31, 2024 4:22:54 PM capstone.Log info\r\n"
            		+ "INFO: Charger 0 - Start charging car 0\r\n"
            		+ "");//(console.readLine("Enter input string to search: "));
 
            boolean found = false;
            while (matcher.find()) {
                /*console.format*/System.out.println("I found the text " +matcher.group()+
                    " starting at " + 
                    "index "+ matcher.start() +" and ending at index " + matcher.end());
                    //matcher.group(),
                    //matcher.start(),
                    //matcher.end());
                found = true;
            }
            if(!found){
                //console.format("No match found.%n");
            }
            g= false;
            
            
            //////////////////////////////////////////////////////////////////////////
            //try {
			//	String component = System.in.read();
			//} catch (IOException e) {
			//	// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
            Scanner in = new Scanner(System.in);
            
            String component = in.nextLine();
            
            String log_dir = "D:\\OneDrive\\FH Dortmund\\Sem1\\Scientific and Transversal Skills\\Compact Programming Course\\Capestone Project\\CPC-team3-main\\capstone_project\\log\\"+component;
            File folder = new File(log_dir);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
              if (listOfFiles[i].isFile()) {
            	Path path_tmp = Paths.get(listOfFiles[i].getParent()+ "\\" + listOfFiles[i].getName());
            	if (listOfFiles[i].getName().toLowerCase().endsWith(".log")){
            		System.out.println("File " + listOfFiles[i].getName() );
            	}
            	
              } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
              }
            }//
        }
    }
}