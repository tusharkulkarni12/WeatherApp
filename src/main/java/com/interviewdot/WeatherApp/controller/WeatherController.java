package com.interviewdot.WeatherApp.controller;

import com.interviewdot.WeatherApp.services.WeatherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @ApiOperation("Return a JSON object that gives the weather averages.")
    @GetMapping(value = "/forecast", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> weatherForecastAverage(@ApiParam("City's name") @RequestParam(required = true) String city) {
        return weatherService.weatherForecastAverage(city);
    }
    @GetMapping(value = "/forecastrapid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> weatherForecastUsingRapidApi(@ApiParam("City's name") @RequestParam(required = true) String city) throws URISyntaxException {
        return weatherService.weatherForecastUsingRapidApi(city);
    }

}
