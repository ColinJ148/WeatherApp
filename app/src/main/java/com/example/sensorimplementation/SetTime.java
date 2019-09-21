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
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(myFormatObj);
    }

    public String getDate() {
        return currentDate.toString();
    }
}
