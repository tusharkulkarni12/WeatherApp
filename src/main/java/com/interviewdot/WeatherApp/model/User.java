package com.interviewdot.WeatherApp.model;

public class User {

    private String userName;
    private String city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userName.equals(user.userName)) return false;
        return city.equals(user.city);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }

    public User(String userName, String city) {
        this.userName = userName;
        this.city = city;
    }
}
