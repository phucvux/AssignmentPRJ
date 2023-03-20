/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package date;

/**
 *
 * @author CucLe
 */
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class getWeek {
    
    public static DayOfWeek[] getWeekDays(LocalDate date) {
        List<DayOfWeek> weekDays = new ArrayList<>();
        
        // Find the first day of the week that includes the specified date (Monday)
        LocalDate monday = date.with(DayOfWeek.MONDAY);
        
        // Add the days of the week to the list
        for (int i = 0; i < 7; i++) {
            weekDays.add(monday.plusDays(i).getDayOfWeek());
        }
        
        // Convert the list to an array and return it
        return weekDays.toArray(new DayOfWeek[0]);
    }
    
    public static void main(String[] args) {
        LocalDate randomDate = LocalDate.of(2023, 3, 17); // Example random date (St. Patrick's Day)
        DayOfWeek[] weekDays = getWeekDays(randomDate);
        System.out.println("Days of the week including " + randomDate + ":");
        for (DayOfWeek day : weekDays) {
            System.out.println(day);
        }
    }
}
