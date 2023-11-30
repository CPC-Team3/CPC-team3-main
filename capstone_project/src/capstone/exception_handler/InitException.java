package capstone.exception_handler;

@SuppressWarnings("serial")
public class InitException extends Exception {
	public InitException(String message) {
        super(message);
        System.out.println("Unable to initiate the component");
    }
}