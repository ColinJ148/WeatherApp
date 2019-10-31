package com.example.sensorimplementation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.DecimalFormat;
import java.util.concurrent.ExecutionException;

public class Weather {
    private String weatherDescription, humidity, pressure, temp, minTemp, maxTemp, location;
    private JsonObject weatherJson = null;

    public Weather() throws ExecutionException, InterruptedException {
        FetchWeather fetchWeather = new FetchWeather();
        fetchWeather.execute().get();
        String output = fetchWeather.getWeather();
        convertToJson(output);
        setFields(weatherJson);
        convertTemp();
    }

    /*Method to gather information from Jsonobject and save to the fields in weather*/
    private void setFields(JsonObject weatherJson) {
        this.temp = weatherJson.getAsJsonObject("main").get("temp").getAsString();
        this.humidity = weatherJson.getAsJsonObject("main").get("humidity").getAsString();
        this.pressure = weatherJson.getAsJsonObject("main").get("pressure").getAsString();
        this.temp = weatherJson.getAsJsonObject("main").get("temp").getAsString();
        this.minTemp = weatherJson.getAsJsonObject("main").get("temp_min").getAsString();
        this.maxTemp = weatherJson.getAsJsonObject("main").get("temp_max").getAsString();
        this.location = weatherJson.get("name").getAsString();
        //       this.weatherDescription = weatherJson.getAsJsonObject("weather").get("description").getAsString();
    }

    /*Converts temp to fahrenheit from kelvin and formats to one decimal place*/
    private void convertTemp() {
        DecimalFormat df = new DecimalFormat("###.#");
        double fahrenheit = Double.valueOf(temp);
        fahrenheit = (fahrenheit * (1.8)) - 459;
        temp = df.format(fahrenheit);
    }

    /*converts output string to JsonObject using Gson library*/
    private void convertToJson(String str) {
        weatherJson = new Gson().fromJson(str, JsonObject.class);
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getTemp() {
        return temp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getLocation() {
        return location;
    }


}
