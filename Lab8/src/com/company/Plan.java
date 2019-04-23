package com.company;
import java.sql.Time;


public class Plan {
    private static int count=0;
    private int id;
    private String name;
    private String date;
    private String time;
    private String place;
    private String describe;
    private String people ;

    {
        id=count++;
    }

    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("Name:").append(name).append(".Date:").append(date).append(".Time:").append(time).append(".Place:")
                .append(place).append(".Describe:").append(describe).append(".People:").append(people);

        return new String(sb);
    }


    public int size()
    {
        return count;
    }


    public int getId() {
        return id;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return time;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public String getDescribe() {
        return describe;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getPlace() {
        return place;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getPeople() {
        return people;
    }
}
