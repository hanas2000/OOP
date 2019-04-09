package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int length =1000000;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the time the program is running:");
        (new Do_Something(in.nextInt())).start();
        ArrayList<Future<double[]>> arr=new ArrayList();
        ArrayList<double[]> arr1=new ArrayList();
        ExecutorService s = Executors.newFixedThreadPool(4);
        long start_time=System.currentTimeMillis();
        long time1,time2;
           for(int i=0;i<2;i++) {
               arr.add(s.submit(new MyCallable(length)));
           }

        s.shutdown();
        try {
            s.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            }
        catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }                                        time1=System.currentTimeMillis()-start_time;
        start_time=System.currentTimeMillis();
            for(int i=0;i<2;i++) {
                arr1.add(new MyCallable(length).call());
            }
        time2=System.currentTimeMillis()-start_time;
        System.out.println("+---------------+---------------+---------------+---------------+");
        System.out.format("|%-15s|%-15s|%-15s|%-15s|%n", "Тест", "Час виконання","К-сть потоків","К-сть даних");
        System.out.println("+---------------+---------------+---------------+---------------+");
        System.out.format("|%-15s|%-15s|%-15s|%-15s|%n", "#1", time1,2,length);
        System.out.format("|%-15s|%-15s|%-15s|%-15s|%n", "#2", time2,2,length);
        System.out.println("+---------------+---------------+---------------+---------------+");
        System.out.println("Результати:");
        System.out.println("+---------------+-------------------------+-------------------------+-------------------------+-------------------------+--------------------+");
        System.out.format("|%-15s|%-25s|%-25s|%-25s|%-25s|%-20s|%n", "Потік", "Min", "Max","Sum","Average","Час виконання(мс)");
        System.out.println("+---------------+-------------------------+-------------------------+-------------------------+-------------------------+--------------------+");
        for (int i = 0; i < 2; i++) {
            try {
                System.out.format("|%-15s|%-25s|%-25s|%-25s|%-25s|%-20s|%n", "Паралельний " + (i+1),arr.get(i).get()[1] , arr.get(i).get()[0],arr.get(i).get()[4],arr.get(i).get()[2],arr.get(i).get()[3]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("+---------------+-------------------------+-------------------------+-------------------------+-------------------------+--------------------+");
        for (int i = 0; i < 2; i++) {
            System.out.format("|%-15s|%-25s|%-25s|%-25s|%-25s|%-20s|%n", "Послідовний " + (i+1),arr1.get(i)[1] , arr1.get(i)[0],arr1.get(i)[4],arr1.get(i)[2],arr1.get(i)[3]);
        }
        System.out.println("+---------------+-------------------------+-------------------------+-------------------------+-------------------------+--------------------+");

        //while(true) {
        //System.out.println("1.Enter array of integer.");
        //System.out.println("2.Find max value.");
        //System.out.println("3.Find min value.");
        //System.out.println("4.Find average value.");
        //System.out.println("5.find sum.");
        //System.out.println("6.Exit");
        //int index=in.nextInt();
        //switch(index) {
        //case 1:
        //System.out.println("Enter number of containers");
        //
        //break;
        //case 2:
        //for(int i=0;i<myList.size();i++) {
        //if (myList.get(i).size() == 0) {
        //System.out.println("Container is empty");
        //} else {
        //System.out.println("Max value:" + myList.get(i).max());
        //}
        //}
        //break;
        //case 3:
        //for(int i=0;i<myList.size();i++) {
        //if (myList.get(i).size() == 0) {
        //System.out.println("Container is empty");
        //} else {
        //System.out.println("Min value:" + myList.get(i).min());
        //}
        //}
        //break;
        //case 4:
        //for(int i=0;i<myList.size();i++) {
        //if (myList.get(i).size() == 0) {
        //System.out.println("Container is empty");
        //} else {
        //System.out.println("Average value:" + myList.get(i).average_value());
        //}
        //}
        //break;
        //case 5:
        //for(int i=0;i<myList.size();i++) {
        //if (myList.get(i).size() == 0) {
        //System.out.println("Container is empty");
        //} else {
        //System.out.println("Sum value:" + myList.get(i).sum());
        //}
        //}
        //break;
        //case 6:
        //System.exit(0);
        //break;
        //default:
        //System.out.println("Error.Wrong input.Try again.");
        //}
        //}
    }
}
