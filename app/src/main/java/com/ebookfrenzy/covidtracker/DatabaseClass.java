package com.ebookfrenzy.covidtracker;

public class DatabaseClass {
    String Name, age, number, country;

    public DatabaseClass() {
    }

    public DatabaseClass(String name, String age, String number, String country) {
        Name = name;
        this.age = age;
        this.number = number;
        this.country = country;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
