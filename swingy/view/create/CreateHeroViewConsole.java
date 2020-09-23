package swingy.view.create;

import swingy.Main;
import swingy.controller.CreateHeroController;
import swingy.view.game.GameViewConsole;

import java.util.Scanner;

public class CreateHeroViewConsole implements CreateHeroView{

    private CreateHeroController controller;

    public void start(){
        controller = new CreateHeroController(this);

        getUserInput();
    }

    public void getUserInput() {
        Scanner scanner = Main.getScanner();

        System.out.println("To create Hero enter a name and class-");
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Classes: attack  defense   hp\n" +
        "Warrior    30      20      100\n" +
        "Shaman     35      20      90\n" +
        "Priest     25      25      100\n" +
        "Paladin    10      30      120\n" +
        "Mage       45      15      80\n" +
        "Ranger     25      20      110\n" +
        "Enter class name: ");
        String heroClass = scanner.nextLine();

        System.out.println("Create - to create hero " + name + " the " + heroClass);
        while (scanner.hasNext()){
            String input = scanner.nextLine();

            if ("create".equalsIgnoreCase(input)){
                controller.onCreateButtonPressed(name, heroClass);
                break;
            } else {
                System.out.println("Unknown command");
            }
        }
    }

    public void showErrorMessage(String message){
        System.out.println("Error: " + message);
    }

    public void openGame(){
        new GameViewConsole().start();
    }

}