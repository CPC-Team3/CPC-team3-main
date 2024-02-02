package charging_station;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class AddBookingSlot {
    private String logFilePath;

    public AddBookingSlot(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public void logBookedCar(int carId, int timeSlot) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            writer.println("Booked Car - ID: " + carId + ", Time Slot: " + timeSlot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readBookingLog() {
        List<String> bookingRecords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                bookingRecords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookingRecords;
    }
}