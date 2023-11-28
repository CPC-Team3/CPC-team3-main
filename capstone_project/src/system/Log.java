/**
 * 
 */
package system;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import system.ExceptionHandler.InitException;
import system.ExceptionHandler.TestException;
import java.io.IOException;

public class Log{
	
	public Logger log;
	FileHandler fileHandler;
	SimpleFormatter formatter;
	String logFilelName;
	String componentName;
	
	Log(String logFilelName, String componentName){
		/*
		 * initialize the file name for logging and the component name where the log is used
		 */
		this.logFilelName = logFilelName;
		this.componentName = componentName;
	}
	
	public void init() throws InitException{
		/*
		 * initialize log object, log file and log handler
		 */
		try {
			
			log = Logger.getLogger(componentName);
			fileHandler = new FileHandler( this.logFilelName + "_log_file.log");
			formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			log.addHandler(fileHandler);
			log.info("Loging system for "+ this.componentName +" started");
			
		} catch (IOException e) {
			e.printStackTrace();
			}		
	}
	
    public static void main(String[] args) throws InitException, TestException {
    	
    	/*
    	 * test init function and logging function
    	 */
    	
    	Log emLog= new Log("system1", "System 1");
		emLog.init();
		emLog.log.info("info message");
		emLog.log.warning("warning message");
    }
}

