package system;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LogFileFinderTest {

    @Test
    public void testSearchByDateSuccess() {
        String result = logFileFinder.SearchByDate("Dec 15", "C:\\Users\\Work pc\\Desktop\\CPC-team3-main-simulation_weeather\\CPC-team3-main-simulation_weeather\\capstone_project\\src\\system\\ChargingStation_0_1_1_2023_log.log");
        assertEquals("found", result);
    }

    @Test
    public void testSearchByDateFail() {
        String result = logFileFinder.SearchByDate("Feb 15", "C:\\Users\\Work pc\\Desktop\\CPC-team3-main-simulation_weeather\\CPC-team3-main-simulation_weeather\\capstone_project\\src\\system\\ChargingStation_0_1_1_2023_log.log");
        assertEquals("fail", result);
    }
}
