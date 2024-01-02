package excercise;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("calculator test suite")
@SelectClasses(CalculatorTest.class)
public class calculatorTestSuit {

}
