package unitTest;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("pop a car from waiting list test")
@SelectClasses(chargingCarTest.class)
public class testSuit {

}

