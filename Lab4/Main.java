package com.company;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Scanner;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String regex = "^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$";
        Pattern pattern = Pattern.compile(regex);
        boolean autoMode = false;
        for(int i = 0;i < args.length;i++){
            if(args[i].equals("-auto")){
                autoMode = true;
            }
        }
        LinkedList<Plan> plan=new LinkedList<>();
        Locale.setDefault(new Locale("uk", "UA"));
        Scanner in = new Scanner(System.in);
        if(autoMode){
            try {
                in = new Scanner(new BufferedInputStream(new FileInputStream("automode.txt")));
            }catch(FileNotFoundException ex){
                System.err.println("Автоматичний режим не запущено!");
                in = new Scanner(System.in);
            }
        }


        while(true)
        {
            System.out.println("1.Add event.");
            System.out.println("2.Fill event.");
            System.out.println("3.Delete event.");
            System.out.println("4.Event menu.");
            System.out.println("5.Show info.");
            System.out.println("6.Clear container.");
            System.out.println("7.Serialize all event to XML file.");
            System.out.println("8.Deserialize all event from XML file.");
            System.out.println("9.Exit");
            int index1=in.nextInt();
            switch(index1) {
                case 1:
                    System.out.println("Event name:");
                    in.nextLine();
                    plan.addNode(new Plan());
                    plan.get(plan.size() - 1).setName(in.nextLine());
                    System.out.printf("Event %s successfully added (id - %d)%n",
                            plan.get(plan.size() - 1).getName(),
                            plan.get(plan.size() - 1).getId());
                    break;
                case 2:
                    System.out.println("Enter event's name: ");
                    in.nextLine();
                    String index = in.nextLine();
                    int index3=-1;
                    for (int i = 0; i < plan.size(); i++)
                    {
                        if(plan.get(i).getName().equals(index))
                        {
                            index3=i;
                            break;
                        }
                    }
                    if(index3==-1)
                    {
                        System.out.println("Name is not found.");
                        break;
                    }

                    System.out.println("Fill the event.");

                    if(plan.get(index3).getDate()==null)
                    {

                        System.out.println("Enter date in format dd/mm/yyyy");

                        while(true)
                        {
                            String  date=in.nextLine();
                            Matcher matcher = pattern.matcher(date);
                            if(matcher.matches()) {
                                plan.get(index3).setDate(date);
                                break;
                            }
                            System.out.println("Wrong.Try again.Enter date in format dd/mm/yyyy");
                        }


                    }
                    System.out.println("Date:"+plan.get(index3).getDate());

                    if(plan.get(index3).getTime()==null)
                    {
                        while (true) {
                            System.out.println("Enter time in format HH:MM");

                            try {
                                plan.get(index3).setTime(new Time(new
                                        SimpleDateFormat("HH:mm")
                                        .parse(in.nextLine()).getTime()));
                                break;
                            } catch (ParseException e) {
                                System.err.println("wrong format! try again");
                            }
                        }
                    }
                    System.out.println("Time:"+plan.get(index3).getTime());


                    if(plan.get(index3).getPlace()==null)
                    {
                        System.out.println("Enter place:");
                        plan.get(index3).setPlace(in.nextLine());
                    }
                    System.out.println("Place:"+plan.get(index3).getPlace());

                    if(plan.get(index3).getDescribe()==null)
                    {
                        System.out.println("Enter description:");
                        plan.get(index3).setDescribe(in.nextLine());
                    }
                    System.out.println("Description:"+plan.get(index3).getDescribe());

                    if(plan.get(index3).getPeople()==null)
                    {
                        System.out.println("Enter the number of people:");
                        int count=in.nextInt();
                        String[] people = new String[count];
                        System.out.println("Enter "+count+" people.");
                        in.nextLine();
                        for(int i=0;i<count;i++)
                        {
                            people[i]=in.nextLine();
                        }
                        plan.get(index3).setPeople(people);
                    }
                    String a[]=plan.get(index3).getPeople();
                    System.out.println("People:");
                    for(String i:a)
                    {
                        System.out.println(i);
                    }
                    break;
                case 3:
                    System.out.println("Enter name:");
                    in.nextLine();
                    String delName = in.nextLine();
                    boolean check1=false;
                    for (int i = 0; i < plan.size(); i++) {
                        if (plan.get(i).getName().equals(delName)) {
                            plan.remove(i);
                            System.out.println("Done.");
                            check1=true;
                            break;
                        }
                    }
                    if(!check1)
                    {
                        System.out.println("Not found.");
                    }
                    break;
                case 4:
                    System.out.println("Enter the name of event:");
                    in.nextLine();
                    String name=in.nextLine();
                    boolean check3=false;
                    for (int i = 0; i < plan.size(); i++) {
                        if (plan.get(i).getName().equals(name)) {
                            check3=true;
                            System.out.println("1.Change name:");
                            System.out.println("2.Change date");
                            System.out.println("3.Change time");
                            System.out.println("4.Change place");
                            System.out.println("5.Change description");
                            System.out.println("6.Add person");
                            System.out.println("7.Delete person");
                            System.out.println("8.Return");
                            int index2=in.nextInt();
                            switch(index2)
                            {
                                case 1:
                                    System.out.println("Enter name:");
                                    in.nextLine();
                                    plan.get(i).setName(in.nextLine());
                                    System.out.println("Done.");
                                    break;
                                case 2:
                                    System.out.println("Enter date in format dd/mm/yyyy");
                                    boolean check4=true;
                                    in.nextLine();
                                    while(check4)
                                    {
                                        String  date=in.nextLine();
                                        Matcher matcher = pattern.matcher(date);
                                        if(matcher.matches()) {
                                            plan.get(i).setDate(date);
                                            check4=false;
                                        }
                                        if(check4) {
                                            System.out.println("Wrong.Try again.Enter date in format dd/mm/yyyy");
                                        }
                                    }
                                    break;
                                case 3:

                                    while (true) {
                                        System.out.println("Enter time in format HH:MM");
                                        in.nextLine();
                                        try {
                                            plan.get(i).setTime(new Time(new
                                                    SimpleDateFormat("HH:mm")
                                                    .parse(in.nextLine()).getTime()));
                                            break;
                                        } catch (ParseException e) {
                                            System.err.println("wrong format! try again");
                                        }
                                    }
                                    System.out.println("Done.");
                                    break;
                                case 4:
                                    System.out.println("Enter place:");
                                    in.nextLine();
                                    plan.get(i).setDate(in.nextLine());
                                    System.out.println("Done.");
                                    break;
                                case 5:
                                    System.out.println("Enter description:");
                                    in.nextLine();
                                    plan.get(i).setDescribe(in.nextLine());
                                    System.out.println("Done.");
                                    break;
                                case 6:
                                    System.out.println("New person:");
                                    in.nextLine();
                                    plan.get(i).addPerson(in.nextLine());
                                    System.out.println("Done.");
                                    break;
                                case 7:
                                    System.out.println("Delete person:");
                                    in.nextLine();
                                    plan.get(i).deletePerson(in.nextLine());
                                    break;
                                case 8:
                                    break;
                                default:
                                    System.out.println("Menu don`t exist");
                                    break;
                            }
                            break;

                        }


                    }
                    if(!check3)
                        System.out.println("Not found.");
                    break;
                case 5:
                    System.out.println("Enter event's name: ");
                    in.nextLine();
                    String index5 = in.nextLine();
                    int index4=-1;
                    for (int i = 0; i < plan.size(); i++)
                    {
                        if(plan.get(i).getName().equals(index5))
                        {
                            System.out.println(plan.get(i).toString());
                            index4=0;
                            break;
                        }
                    }
                    if(index4 == -1)
                    {
                        System.out.println("Name is not found.");
                        break;
                    }
                    break;
                case 6:
                    plan.clear();
                    System.out.println("Container is empty");
                    break;
                case 7:
                    if(plan.size() == 0)
                    {
                        System.out.println("List is empty");
                    }

                    try (
                            XMLEncoder encoder = new XMLEncoder(
                                    new BufferedOutputStream(
                                            new FileOutputStream(PathGetter.getPath(in).toString())))
                    ) {
                        Integer size = plan.size();
                        encoder.writeObject(size);
                        for(Plan i:plan)
                        {
                            encoder.writeObject(i);
                        }
                    } catch (Exception e) {
                        System.err.println("Error");
                        break;
                    }
                    break;
                case 8:
                    try (
                            XMLDecoder decoder = new XMLDecoder(
                                    new BufferedInputStream(
                                            new FileInputStream(PathGetter.getPath(in).toString())))
                    ) {
                        Integer size = (Integer) decoder.readObject();
                        plan = new LinkedList<>() ;
                        for (int i = 0; i < size; i++) {
                            plan.addNode((Plan) decoder.readObject());
                        }
                    } catch (Exception e) {
                        System.err.println("Error");
                        break;
                    }
                    break;

                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error.Wrong input.Try again.");
            }

        }
    }
}
