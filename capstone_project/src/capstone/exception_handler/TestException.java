package capstone.exception_handler;

@SuppressWarnings("serial")
public	class TestException extends Exception {
	public TestException(String message) {
        super(message);
        System.out.println("Test Failed");
	}
}