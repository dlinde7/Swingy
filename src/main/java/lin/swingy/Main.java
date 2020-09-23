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
            if (DataBase.isEmpty()) {
                DataBase.insert("Wolf", "Ranger", 3, 3400, 70, 50, 185);
                DataBase.insert("Raven", "Shaman", 2, 1654, 85, 50, 110);
                DataBase.insert("Steven", "Paladin", 4, 5628, 50, 110, 200);
                DataBase.insert("Bolgen", "Mage", 1, 576, 75, 25, 90);
                DataBase.insert("John", "Priest", 1, 576, 40, 40, 120);
            }

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