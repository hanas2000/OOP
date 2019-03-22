package com.company;
import java.io.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Plan implements Comparable<Plan>,Serializable{
    private static int count=0;
    private int id;
    private String name;
    private String date;
    private Time time;
    private String place;
    private String describe;
    private String[] people ;

    {
        id=count++;
    }

    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("Name:").append(name).append(".Date:").append(date).append(".Time:").append(time).append(".Place:")
                .append(place).append(".Describe:").append(describe).append(".People:");
        for(String i:people)
        {
            sb.append(i).append(" ");
        }

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

    public void setTime(Time time) {
        this.time = time;
    }
    public Time getTime() {
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

    public void setPeople(String[] people) {
        this.people = people;
    }

    public String[] getPeople() {
        return people;
    }

    public String[] deletePerson(String name)
    {
        String[] newArray=new String[people.length-1];
        for(int i=0;i<people.length;i++)
        {
            if(people[i].equals(name))
            {
                System.arraycopy(people,0, newArray, 0, people.length-1 );
                people=newArray;
                System.out.println("Done.");
                System.out.println("People:");
                for(String q:people)
                {
                    System.out.println(q);
                }
                return people;
            }
        }
        System.out.println("Name is not in massive.");
        return people;
    }

    public String[] addPerson(String name)
    {
        String[] newArray=new String[people.length+1];
        System.arraycopy(people,0, newArray, 0, people.length );
        newArray[newArray.length-1]=name;
        people=newArray;
        System.out.println("People:");
        for(String i:people)
        {
            System.out.println(i);
        }
        return people;
    }
    public String[] toArray()
    {
        if(count==0)
        {
            System.out.println("Error.Array is empty");
            return null;
        }
        String[] newArray = new String[5+people.length];
        DateFormat df=new SimpleDateFormat("HH:mm");
        newArray[0]=name;
        newArray[1]=date;
        newArray[2]=df.format(time);
        newArray[3]=describe;
        for(int i=0;i<people.length;i++)
        {
            newArray[4+i]=people[i];
        }

        return newArray;
    }

    public int compareTo(Plan other) {
        return date.compareTo(other.date);
    }


}
