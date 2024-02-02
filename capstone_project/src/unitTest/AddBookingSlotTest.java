package unitTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import org.junit.jupiter.api.Test;

import charging_station.AddBookingSlot;

public class AddBookingSlotTest {
    private static final String TEST_LOG_FILE_PATH = "C:\\Users\\GABRUUU\\git\\CPC-team3-main\\capstone_project\\src\\unitTest\\testLogFile.txt";

    @Test
    public void testLogBookedCar() throws IOException {
        // Create an instance of AddBookingSlot
        AddBookingSlot bookingSlot = new AddBookingSlot(TEST_LOG_FILE_PATH);

        // Call logBookedCar method
        bookingSlot.logBookedCar(1, 10);

        // Read the content of the log file
        String logContent = readLogFile(TEST_LOG_FILE_PATH);

        // Assert that the log contains the expected entry
        assertEquals("Booked Car - ID: 1, Time Slot: 10", logContent.trim());
    }

    private String readLogFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
