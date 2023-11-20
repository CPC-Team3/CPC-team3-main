/*
 * Fachhochschule Dortmund
 * Mykyta Konakh
 * Student ID: 7219011
 * ESE student
 * Task: Resource Management
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


public class ResourceManagement {
    public static void main(String[] args) {
        // Initialize resource in try statement
        try (Scanner scanner = new Scanner (new FileInputStream("input.txt"))) {
            while(scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
        // error handling with close
        } catch (IOException e) {
            System.err.println("Cannot open a file " + e.getMessage());
        }
    }
}
