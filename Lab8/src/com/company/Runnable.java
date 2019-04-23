package com.company;

public class Runnable extends Thread {

    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
        System.exit(0);
    }

}
