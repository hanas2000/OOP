package com.company;

import java.util.Scanner;

public class Main {

    static void report(String text,String add,char b, StringBuffer sb) {
        System.out.println("---dbg main variables---");
        System.out.println("Text:");
        System.out.println(text);
        System.out.println("ADD TEXT:");
        System.out.println(add);
        System.out.println("Letter:");
        System.out.println(b);
        System.out.println("New text:");
        System.out.println(sb);
        System.out.println("------------------------");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String a=null,c=null;
        boolean debug=false;
        char b='\0';
        StringBuffer sb = new StringBuffer();
        if (args != null) {
            boolean help = false;
            for (String i : args) {
                if (i.equals("-h") || i.equals("-help")) {
                    help = true;
                }
                if (i.equals("-d") || i.equals("-debug")) {
                    debug = true;
                }
            }
            if (help) {
                Help.help();
            }
        }
        while(true) {
            System.out.println("1 - input");
            System.out.println("2 - show input");
            System.out.println("3 - calculate");
            System.out.println("4 - show result");
            System.out.println("5 - exit");
            int num = s.nextInt();
            switch (num) {
                case 1:
                    if(debug) report(a, c, b, sb);
                    s.nextLine();
                    System.out.println("Enter string:");
                    a = s.nextLine()+" ";
                    System.out.println("Enter add string:");
                    c = s.nextLine();
                    System.out.println("Enter char element:");
                    b = s.next().charAt(0);
                    if(debug) report(a, c, b, sb);
                    break;
                case 2:
                    if(debug) report(a, c, b, sb);
                    if (a != null && c != null && b != '\0') {
                        System.out.println("TEXT:" + a);
                        System.out.println("ADD TEXT:" + c);
                        System.out.println("Letter:" + b);
                    } else {
                        System.out.println("Error!Input first!");
                    }
                    if(debug) report(a, c, b, sb);
                    break;
                case 3:
                    if(debug) report(a, c, b, sb);
                    if (a != null && c != null && b != '\0') {

                        for (int i = 0; i < a.length()-1; i++) {

                            if (a.charAt(i) == b && a.charAt(i + 1) == ' ') {
                                sb.append(a.charAt(i)).append(c);

                            } else {
                                sb.append(a.charAt(i));
                            }
                        }
                        System.out.println("Done");
                    } else {
                        System.out.println("Error!Input first!");
                    }
                    if(debug) report(a, c, b, sb);
                    break;
                case 4:
                    if(debug) report(a, c, b, sb);
                    if (a != null && c != null && b != '\0') {
                        System.out.println(sb);
                    } else {
                        System.out.println("Error!Input first!");
                    }
                    if(debug) report(a, c, b, sb);
                    break;
                case 5:
                    if(debug) report(a, c, b, sb);
                    System.out.println("bye!");
                    System.exit(0);
                default:
                    System.out.println("unknown command.");
            }


        }
    }
}
