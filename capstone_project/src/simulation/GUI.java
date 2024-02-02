package simulation;

import javax.swing.*;

import capstone.Standard;
import system.ChargingSystem;
import vehicle.Car;

import java.awt.*;
import java.util.ArrayList;

public class GUI extends Thread {
	
	String result;
    public JFrame frame;
    
    //clock 
    public JLabel date_label;
    public JLabel clock_label;
    
    // queue
    public JLabel waiting_queue_station1;
    public JLabel waiting_queue_station2;
    public JLabel booking_queue_station1;
    public JLabel booking_queue_station2;
    
    

    // energy source controller
    public JLabel weather_label;
    public JLabel energy_source_label;

    // charging station
    public JLabel[][] charger_label;

    // new car information
    public JTextField new_car_id_field;
    public JTextField new_car_booking_station_field;
    public JTextField new_car_booking_time_field;
    public JButton add_car_button;

    // running system
    ArrayList<int[]> comChannel;
    ArrayList<int[]> comChannel2;
    ChargingSystem system;

    public GUI(ChargingSystem new_system, ArrayList<int[]> new_comChannel, ArrayList<int[]> new_comChannel2) {
        this.system = new_system;
        this.comChannel = new_comChannel;
        this.comChannel2 = new_comChannel2;
    }

    // init GUI
    public void init() {
        // frame setting
        frame = new JFrame("Car Charging Station System Simulation");
        frame.setBounds(100, 100, 600, 3000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(15, 1));
        
        // clock
        JPanel clockPanel = new JPanel(new FlowLayout());
        date_label = new JLabel("Current Date: ");
        clockPanel.add(date_label);
        clock_label = new JLabel("Clock Tick: 0");
        clockPanel.add(clock_label);
        frame.add(clockPanel);
        frame.setVisible(true);

        // energy source controller panel
        JPanel energyPanel = new JPanel(new FlowLayout());
        weather_label = new JLabel("Weather: ");
        energyPanel.add(weather_label);
        energy_source_label = new JLabel("Energy Source: ");
        energyPanel.add(energy_source_label);
        frame.add(energyPanel);
        frame.setVisible(true);

        // charging station panel
        JPanel chargingPanel = new JPanel(new FlowLayout());
        charger_label = new JLabel[2][2];
        for (int station = 0; station < 2; station++) {
            for (int charger_slot = 0; charger_slot < 2; charger_slot++) {
                charger_label[station][charger_slot] = new JLabel(
                        "Station " + (station + 1) + " [Charger " + charger_slot + " ]: Empty");
                chargingPanel.add(charger_label[station][charger_slot]);

            }
        }
        frame.add(chargingPanel);
        frame.setVisible(true);

        // new car panel
        JPanel addCarPanel = new JPanel(new FlowLayout());
        new_car_id_field = new JTextField(10);
        new_car_booking_station_field = new JTextField(10);
        new_car_booking_time_field = new JTextField(10);
        add_car_button = new JButton("Add Car");

        addCarPanel.add(new_car_id_field);
        addCarPanel.add(new_car_booking_station_field);
        addCarPanel.add(new_car_booking_time_field);
        addCarPanel.add(add_car_button);

        frame.add(addCarPanel);
        add_car_button.addActionListener(e -> add_new_car());
        frame.setVisible(true);
        
        
     // queue
        //JPanel queuePanel = new JPanel(new FlowLayout());
        //waiting_queue_station1 = new JLabel("Waiting queue station 1: ");
        //waiting_queue_station2 = new JLabel("Waiting queue station 2: ");
        //booking_queue_station1 = new JLabel("Booking queue station 1: ");
        //booking_queue_station2 = new JLabel("Booking queue station 2: ");
        //queuePanel.add(waiting_queue_station1);
        //queuePanel.add(waiting_queue_station2);
        //queuePanel.add(booking_queue_station1);
        //queuePanel.add(booking_queue_station2);
        //frame.add(queuePanel);
        //frame.setVisible(true);
    }

    public void add_new_car() {
        String id_s = new_car_id_field.getText().trim();
        String station_id_s = new_car_booking_station_field.getText().trim();
        String booking_time_s = new_car_booking_time_field.getText().trim();

        if (!id_s.isEmpty() && (station_id_s.isEmpty() || booking_time_s.isEmpty())) {
            int id = Integer.valueOf(id_s);
            Car new_car = new Car(id, comChannel, comChannel2);
            new_car.setDaemon(true);
            new_car.start();
        }

        else if (!id_s.isEmpty() && !station_id_s.isEmpty() && !booking_time_s.isEmpty()) {
            int id = Integer.valueOf(id_s);
            int station_id = Integer.valueOf(station_id_s);
            int booking_time = Integer.valueOf(booking_time_s);
            Car new_car = new Car(id, comChannel, comChannel2, booking_time, (station_id-1));
            new_car.setDaemon(true);
            new_car.start();
        }

        new_car_id_field.setText(""); // Clear the text field
        new_car_booking_station_field.setText(""); // Clear the text field
        new_car_booking_time_field.setText(""); // Clear the text field
    }

    // hard coded for 2 station
    public void update() {
    	
        SwingUtilities.invokeLater(() -> date_label.setText("Current Date:  " + Standard.date));
        SwingUtilities.invokeLater(() -> clock_label.setText("Clock Tick:  " + (System.currentTimeMillis() - Standard.T0) ));
    	
        SwingUtilities.invokeLater(() -> charger_label[0][0].setText("Station 1, [Charger " + 1 + " ]: car " + system.station1.chargers.get(0).handledCar.id));
        SwingUtilities.invokeLater(() -> charger_label[0][1].setText("Station 1, [Charger " + 2 + " ]: car " + system.station1.chargers.get(1).handledCar.id));
        //SwingUtilities.invokeLater(() -> charger_label[0][2] = new JLabel("Station 1, [Charger " + 3 + " ]: car " + system.station1.chargers.get(2).handledCar.id));
        //SwingUtilities.invokeLater(() -> charger_label[0][3] = new JLabel("Station 1, [Charger " + 4 + " ]: car " + system.station1.chargers.get(3).handledCar.id));

        SwingUtilities.invokeLater(() -> charger_label[1][0].setText("Station 2, [Charger " + 1 + " ]: car " + system.station2.chargers.get(0).handledCar.id));
        SwingUtilities.invokeLater(() -> charger_label[1][1].setText("Station 2, [Charger " + 2 + " ]: car " + system.station2.chargers.get(1).handledCar.id));
        //SwingUtilities.invokeLater(() -> charger_label[1][2] = new JLabel("Station 2, [Charger " + 3 + " ]: car " + system.station1.chargers.get(2).handledCar.id));
        //SwingUtilities.invokeLater(() -> charger_label[1][3] = new JLabel("Station 2, [Charger " + 4 + " ]: car " + system.station1.chargers.get(3).handledCar.id));

        SwingUtilities.invokeLater(() -> weather_label.setText("Weather: " + system.energyController1.currentWeather));
        SwingUtilities.invokeLater(() -> energy_source_label.setText("Energy Source:" + system.energyController1.currentEnergySource));
        
        
        
        //result = concatenateCars(system.station1.waitingCars);
        //SwingUtilities.invokeLater(() -> waiting_queue_station1.setText("Waiting queue station 1: " + result));

        //result = concatenateCars(system.station2.waitingCars);
        //SwingUtilities.invokeLater(() -> waiting_queue_station2.setText("Waiting queue station 2:" + result));

        //result = concatenateCars(system.station1.bookingCars);
        //SwingUtilities.invokeLater(() -> booking_queue_station1.setText("Booking queue station 1: " + result));

        //result = concatenateCars(system.station2.bookingCars);
        //SwingUtilities.invokeLater(() -> booking_queue_station2.setText("Booking queue station 2:" + result));
        
        
        
        
        

        try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public void run() {
        init();
        while (true) {
            update();
        }
    }
    
    private static String concatenateCars(ArrayList<Car> carList) {
        StringBuilder result = new StringBuilder();

        for (Car car : carList) {
            result.append(car.toString()).append(", ");
        }

        // Remove the trailing comma and space
        if (result.length() > 2) {
            result.setLength(result.length() - 2);
        }

        return result.toString();
    }
}