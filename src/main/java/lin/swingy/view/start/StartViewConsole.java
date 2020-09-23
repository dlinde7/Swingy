package lin.swingy.view.start;

import lin.swingy.Main;
import lin.swingy.controller.StartController;
import lin.swingy.view.create.CreateHeroViewConsole;
import lin.swingy.view.select.SelectHeroViewConsole;

import java.util.Scanner;

public class StartViewConsole implements StartView {

    private StartController controller;

    @Override
    public void start() {
        controller = new StartController(this);
        System.out.println("You are in the console text RPG, use one of the following commands:");

        Scanner scanner = Main.getScanner();
        System.out.println("Create - to create hero");
        System.out.println("Select - to select already created hero");
       // System.out.println();
       while (scanner.hasNext()) {
        String input = scanner.nextLine();

        if ("create".equalsIgnoreCase(input)) {
            controller.onCreateHeroButtonPressed();
            break;
        } else if ("select".equalsIgnoreCase(input)) {
            controller.onSelectHeroButtonPressed();
            break;
        } else {
            System.out.println("Unknown command");
        }
    }
    }

    @Override
    public void openCreateHero() {
        new CreateHeroViewConsole().start();
    }

    @Override
    public void openSelectHero() {
        new SelectHeroViewConsole().start();
    }
}