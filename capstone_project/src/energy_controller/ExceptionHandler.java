package energy_controller;

public class ExceptionHandler {

	@SuppressWarnings("serial")
	class TestException extends Exception {
		public TestException(String message) {
	        super(message);
	        System.out.println("Test Failed");
	    }
	}
	
	@SuppressWarnings("serial")
	class InitException extends Exception {
		public InitException(String message) {
	        super(message);
	        System.out.println("Unable to initiate the component");
	    }
	}
	
	@SuppressWarnings("serial")
	class assigningException extends Exception {
		public assigningException(String message) {
	        super(message);
	    }
	}

	@SuppressWarnings("serial")
	class listeningException extends Exception {
		public listeningException(String message) {
	        super(message);
	    }
	}
	
	@SuppressWarnings("serial")
	class energySwitchException extends Exception {
		public energySwitchException(String message) {
	        super(message);
	    }
	}

}
