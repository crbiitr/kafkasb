package com.kafka.sb.kafkasb.model;

import java.time.LocalDateTime;

/**
 * Created by chetan on 23/1/20.
 */
public class User {
    private String id;
    private String name;
    private int age;
    private double salary;
    private String address;
    private long timestamp;

    public User() {
        super();
    }
    public User(String id, String name, int age, double salary, String address, long timestamp) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.address = address;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", address='" + address + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
