package com.company;
import java.sql.Time;


public class Plan {
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


}
