package excercise;
		

public class Calculator {
	
	Calculator(){
		
	}
	public double sum(double A, double B) {
		return A+B;
		
	}
	public double multiplication(double A, double B) {
		return A*B;
		
	}
	public double division(double nominator, double denominator) {
		if (denominator != 0) {
            return nominator / denominator;
        } else {
            throw new ArithmeticException("Cannot divide by zero");
        }
	}
	public double factorial(double n) {
	    if (n == 0 || n == 1) {
	        return 1;
	    } else {
	        return n * factorial(n - 1);
	    }
	    
	}
	public double power(double base, double exponent) {
        if (exponent == 0) {
            return 1;
        } else if (exponent > 0) {
            return base * power(base, exponent - 1);
        } else {
            return 1 / (base * power(base, -exponent - 1));
        }
   
		
	}
	
	
	
	
	

	public static void main(String[] args) {
		
		
	}

}
