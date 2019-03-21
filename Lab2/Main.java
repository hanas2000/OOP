package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        StringBuilder sb= new StringBuilder();
        Scanner in=new Scanner(System.in);
        ArrayList<Container> list=new ArrayList<>();

        while(true)
        {
            System.out.println("1 - make container");
            System.out.println("2 - fill container");
            System.out.println("3 - clear container");
            System.out.println("4 - show container");
            System.out.println("5 - containers' list");
            System.out.println("6 - container menu");
            System.out.println("7 - exit");

            int command=in.nextInt();
            switch(command)
            {
                case 1:
                    System.out.print("Enter container's max length: ");
                    list.add(new Container(in.nextInt()));
                    System.out.println("Success! Your container's index is [" + list.size() + "]");
                    break;
                case 2:
                    System.out.println("enter container's index: ");
                    int index=in.nextInt();
                    if (index > list.size() || index < 0) {
                        System.out.println("error! not found");
                        break;
                    }
                    System.out.println("Enter your strings:");
                    in.nextLine();
                    for(int i=0;i<list.get(index-1).maxSize();i++)
                    {
                        list.get(index-1).add(in.nextLine());
                    }
                    break;
                case 3:
                    System.out.println("enter container's index: ");
                    int del=in.nextInt();
                    if (del > list.size() || del < 0) {
                        System.out.println("error! not found");
                        break;
                    }
                    list.get(del-1).clear();
                    System.out.println("Done");
                    break;
                case 4:
                    System.out.println("enter container's index: ");
                    int show=in.nextInt();
                    if (show > list.size() || show < 0) {
                        System.out.println("error! not found");
                        break;
                    }
                    System.out.println(list.get(show-1).toString());
                    break;
                case 5:
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("[" + (i+1) + "] - data: [" + list.get(i).size() +
                                "/" + list.get(i).maxSize() + "]");
                    }
                    break;
                    case 6:
                        System.out.print("Enter container's index: ");
                        int index2=in.nextInt();
                        if (index2 > list.size() || index2 < 0) {
                            System.out.println("error! not found");
                            break;
                        }

                        System.out.println("1 - add element");
                        System.out.println("2 - remove element");
                        System.out.println("3 - convert to array and iterate through");
                        System.out.println("4 - current size");
                        System.out.println("5 - max size");
                        System.out.println("6 - check string");
                        System.out.println("7 - check sub container");
                        System.out.println("8 - write to file (serialize)");
                        System.out.println("9 - read from file (deserialize)");
                        System.out.println("10 - get element by index");
                        System.out.println("11 - get element's index");
                        System.out.println("12 - sort");
                        System.out.println("13 - iterate through container (foreach)");
                        System.out.println("14 - iterate through container (while)");
                        System.out.println("15 - previous task");
                        System.out.println("16 - return");
                        int command2 = in.nextInt();
                        switch (command2) {
                            case 1:
                                System.out.println("Enter string: ");
                                String a=in.nextLine();
                                if(list.get(index2-1).maxSize()>list.get(index2-1).size())
                                list.get(index2-1).add(in.nextLine());
                                else {
                                System.out.println("You can`t");
                                break;
                                }
                                System.out.println("Done.");
                                break;
                            case 2:
                                System.out.println("Enter string: ");
                                list.get(index2-1).remove(in.nextLine());
                                System.out.println("Done.");
                                break;
                            case 3:
                                list.get(index2-1).toArray();
                                System.out.println("Done.");
                                break;
                            case 4:
                                System.out.println(list.get(index2-1).size());
                                System.out.println("Done.");
                                break;
                            case 5:
                                System.out.println(list.get(index2-1).maxSize());
                                System.out.println("Done.");
                                break;
                            case 6:
                                System.out.println("enter string to check if it exist in container:");
                                in.nextLine();
                                if(list.get(index2-1).contains(in.nextLine())) System.out.println("Exist");
                                else System.out.println("Not exist");
                                break;
                            case 7:
                                System.out.print("enter index: ");
                                int Index = in.nextInt();
                                if (Index > list.size() || Index < 1) {
                                    System.out.println("not found!");
                                    break;
                                }
                                System.out.println(list.get(index2-1).containsAll(list.get(Index-1)));
                                break;
                            case 8:
                                System.out.println("Enter the name of the file <filename.type of date>");
                                in.nextLine();
                                list.get(index2-1).serialize(in.nextLine());
                                System.out.println("Done");
                                break;
                            case 9:
                                System.out.println("Enter the name of the file <filename.type of date>");
                                in.nextLine();
                                list.get(index2-1).deserialize(in.nextLine());
                                System.out.println("Done");
                                break;
                            case 10:
                                System.out.println("Enter index:");
                                System.out.println(list.get(index2-1).get(in.nextInt()));
                                break;
                            case 11:
                                System.out.println("Enter String:");
                                System.out.println(list.get(index2-1).indexOf(in.nextLine()));
                                break;
                            case 12:
                                list.get(index2-1).sort();

                                System.out.println("Done");
                                break;
                            case 13:
                                for(String string:list.get(index2-1))
                                {
                                    System.out.println(string);
                                }
                                break;
                            case 14:
                                MyIterator i = (MyIterator) list.get(index2-1).iterator();
                                while (i.hasNext()) {
                                    System.out.println(i.next());
                                };
                                break;
                            case 15:
                                in.nextLine();
                                System.out.println("Char:");
                                char ch = in.nextLine().charAt(0);

                                System.out.println("Add String:");
                                String st=in.nextLine();
                                list.get(index2-1).task(ch,st);
                            case 16:
                                break;
                            default:
                                System.out.println("Menu don`t exist");
                                break;
                        }
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error.Wrong input.Try again.");
            }
        }
    }
}
