package com.interviewdot.WeatherApp.repository;

import com.interviewdot.WeatherApp.model.User;

import java.util.Arrays;
import java.util.List;

public class UserRepository {

    List<User> employeeList = Arrays.asList(
            new User("John", "London"),
            new User("Harry", "Krakow"),
            new User("Ramesh", "Pune"),
            new User("Suresh", "Mumbai"));

}
