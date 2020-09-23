package swingy;

import swingy.data.DataBase;
import swingy.view.StartViewConsole;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args){
        if (args.length != 1 || !args[0].equals("console")){
            System.out.println("Use: console");
            System.exit(1);

            if (args[0].equals("console")){
                new StartViewConsole().start();
            }
        }
    }

    public static Scanner getScanner(){
        if (scanner == null){
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void closeConnections() {
        if (scanner != null){
            scanner.close();
        }
    }

}