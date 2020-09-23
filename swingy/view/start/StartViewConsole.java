package swingy.view.start;

import swingy.Main;
import swingy.controller.StartController;
import swingy.view.create.CreateHeroViewConsole;
import swingy.view.select.SelectHeroViewConsole;

import java.util.Scanner;

public class StartViewConsole implements StartView {

    private StartController controller;

    public void start() {
        controller = new StartController(this);
        System.out.println("You are in the console text RPG, use one of the following commands:");

        Scanner scanner = Main.getScanner();
        System.out.println();
        System.out.println("Create - to create hero");
        System.out.println("Select - to select already created hero");
       // System.out.println();
       while(scanner.hasNexr()) {
           String input = scanner.nextLine();

           if ("create".equalsIgnoreCase(input)){
               controller.onCreateHeroButtonPressed();
               break;
            } else if ("select".equalsIgnoreCase(input)){
                controller.onSelectHeroButtonPressed();
                break;
            } /*else if ("switch".equalsIgnoreCase(input)){
                controller.onSwitchButtonPressed();
                break;
            }*/ else {
                System.out.println("Unknown command");
            }
       }
    }

    public void openCreateHero() {
        new CreateHeroViewConsole().start();
    }

    public void openSelectHero() {
        new SelectHeroViewConsole().start();
    }
}