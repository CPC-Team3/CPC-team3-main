package unitTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import charging_station.ChargingStation;


public class ChargingStationTest {

    @Test
    public void addChargerTest() {
        // Set up charging station
        int stationId = 0;
        String bookFilePath = "C:\\Users\\GABRUUU\\git\\CPC-team3-main\\capstone_project\\src\\unitTest\\testLogFile.txt";
        ArrayList<int[]> dummyMessageQueue = new ArrayList<>();
        ChargingStation station = new ChargingStation(stationId, bookFilePath, dummyMessageQueue);

        // Add charger
        station.addCharger(0);

        // Assert
        assertEquals(station.chargers.size(), 1);
    }

}

