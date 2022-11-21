package com.interviewdot.WeatherApp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewdot.WeatherApp.dto.WeatherAverageDTO;
import com.interviewdot.WeatherApp.dto.WeatherMapDTO;
import com.interviewdot.WeatherApp.dto.WeatherMapTimeDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final String URI = "http://api.openweathermap.org/data/2.5/forecast";
    private final String API_ID = "7cde1336aaafbe40cb99aa605e6152bc";


    // Notification system log messages
    //  city tempreture >  40 then show the notification
    //

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private WeatherAverageDTO average(List<WeatherMapTimeDTO> list) {
        WeatherAverageDTO result = new WeatherAverageDTO();

        for (WeatherMapTimeDTO item : list) {
            result.setDate(item.getDt().toLocalDate());
            result.plusMap(item);
        }

        //result.totalize();

        return result;
    }

    private String url(String city) {
        return String.format(URI.concat("?q=%s").concat("&appid=%s").concat("&units=metric"), city, API_ID);
    }

    @Override
    public ResponseEntity<?> weatherForecastAverage(String city) {
        List<WeatherAverageDTO> result = new ArrayList<WeatherAverageDTO>();
        try {
            WeatherMapDTO weatherMap = this.restTemplate().getForObject(this.url(city), WeatherMapDTO.class);

            for (LocalDate reference = LocalDate.now();
                 reference.isBefore(LocalDate.now().plusDays(4));
                 reference = reference.plusDays(1)) {
                final LocalDate ref = reference;
                List<WeatherMapTimeDTO> collect = weatherMap.getList().stream()
                        .filter(x -> x.getDt().toLocalDate().equals(ref)).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(collect)) {
                    result.add(this.average(collect));
                }
            }
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(new Json(e.getResponseBodyAsString()), e.getStatusCode());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    private double getTemperatureDataFromMockRapidApi() {
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
        Double tempValue = null;
        try {
        //convert JSON file to map
            Map<?, ?> map = objectMapper.readValue(new FileInputStream("src/main/resources/temperature.json"), Map.class);

        //iterate over map entries and print to console
            for (Map.Entry<?, ?> entry : map.entrySet()) {

                System.out.println(entry.getKey() + "=" + entry.getValue());
                 tempValue = (Double) entry.getValue();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempValue != null ? tempValue : 0;
    }

    private Double calcualteMeanTempreature(Double temp1, Double temp2){
        return (temp1 + temp2)/2;
    }

    private void sendNotification(){
       double temperature =  getTemperatureDataFromMockRapidApi();
        if(temperature > 40){
            System.out.println("Notification sent to user");
        }
    }

}
