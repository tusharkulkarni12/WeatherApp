package com.interviewdot.WeatherApp.services;

import com.alicp.jetcache.anno.Cached;
import com.interviewdot.WeatherApp.dto.WeatherAverageDTO;
import com.interviewdot.WeatherApp.dto.WeatherMapDTO;
import com.interviewdot.WeatherApp.dto.WeatherMapTimeDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class WeatherServiceRapid {
   // private final String URI = "https://visual-crossing-weather.p.rapidapi.com/forecast";
    //https://visual-crossing-weather.p.rapidapi.com/forecast?aggregateHours=24&location=pune
    private final String API_ID = "526fd346bfmshe9bacfd11b196ebp157b73jsnccf442657502";

    private final RestTemplate restTemplate;

    public WeatherServiceRapid(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Cached(expire = 10, timeUnit = TimeUnit.MINUTES)
    public ResponseEntity<?> weatherForecastUsingRapidApi(String city) throws URISyntaxException {


        String baseUrl = "https://visual-crossing-weather.p.rapidapi.com/forecast?aggregateHours=24&location=pune";
        URI uri = new URI(baseUrl);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


//    private String url(String city) {
//        return String.format(URI.concat("?q=%s").concat("&appid=%s").concat("&units=metric"), city, API_ID);
//    }
}
