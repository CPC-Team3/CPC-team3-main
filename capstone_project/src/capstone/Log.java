/**
 * 
 */
package capstone;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import capstone.exception_handler.InitException;
import capstone.exception_handler.TestException;

public class Log{
	
	// Attribute
	Logger log;
	FileHandler fileHandler;
	SimpleFormatter formatter;
	String logFilelName;
	String componentName;
	
	// Constructor and initializer
	public Log(String logFilelName, String componentName){
		/*
		 * initialize the file name for logging and the component name where the log is used
		 */
		this.logFilelName = logFilelName;
		this.componentName = componentName;
		init();
	}
	
	public void init(){
		/*
		 * initialize log object, log file and log handler
		 */
		try {
			
			log = Logger.getLogger(componentName);
			fileHandler = new FileHandler( "log\\" + this.logFilelName + "_log_file.log");
			formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			log.addHandler(fileHandler);
			log.info("Loging system for "+ this.componentName +" started");
			
		} catch (IOException e) {
			e.printStackTrace();
			}		
	}
	
	
	// Functionalities
	public void info(String info) {
		log.info(info);
	}
	
	public void warning(String warning) {
		log.warning(warning);
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

