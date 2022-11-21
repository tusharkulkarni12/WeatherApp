package com.interviewdot.WeatherApp;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

@SpringBootApplication
public class WeatherAppApplication {


	public static void main(String[] args) throws IOException {
//
//		//create ObjectMapper instance
//		ObjectMapper objectMapper = new ObjectMapper();
//	//	Path readPath = Paths.get("src/main/resources/input.txt");
//	//	String fileContent = Files.readString(readPath);
////convert JSON file to map
//		Map<?, ?> map = objectMapper.readValue(new FileInputStream("src/main/resources/temperature.json"), Map.class);
//
////iterate over map entries and print to console
//		for (Map.Entry<?, ?> entry : map.entrySet()) {
//			System.out.println(entry.getKey() + "=" + entry.getValue());
//
//		}
		SpringApplication.run(WeatherAppApplication.class, args);

	}

}
