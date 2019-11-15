package com.example.sensorimplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SetTime {
    private LocalDate currentDate;
    private LocalDateTime currentTime;

    public SetTime() {
        currentDate = LocalDate.now();
    }

    public String getTime() {
        currentTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
        return currentTime.format(myFormatObj);
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        formatter.format(currentDate);
        return formatter.format(currentDate);
    }
}
