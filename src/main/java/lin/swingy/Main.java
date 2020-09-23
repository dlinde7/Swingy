package lin.swingy;

import lin.swingy.data.DataBase;
import lin.swingy.view.start.StartViewConsole;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args){
        if (args.length != 1 || !args[0].equals("console")){
            System.out.println("Use: console");
            System.exit(1);
        }

            DataBase.connect();

            if (args[0].equals("console")){
                new StartViewConsole().start();
            }
    }

    public static Scanner getScanner(){
        if (scanner == null){
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void closeConnections() {
        DataBase.close();
        if (scanner != null){
            scanner.close();
        }
    }

}