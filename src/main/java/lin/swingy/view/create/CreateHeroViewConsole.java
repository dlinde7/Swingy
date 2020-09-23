package lin.swingy.view.create;

import lin.swingy.Main;
import lin.swingy.controller.CreateHeroController;
import lin.swingy.view.select.SelectHeroViewConsole;
import lin.swingy.view.game.GameViewConsole;

import java.util.Scanner;

public class CreateHeroViewConsole implements CreateHeroView{

    private CreateHeroController controller;

    @Override
    public void start(){
        controller = new CreateHeroController(this);

        getUserInput();
    }

    @Override
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

        System.out.println("Select - to select an existing hero");
        System.out.println("Redo - to restart create a hero");
        System.out.println("Create - to create hero " + name + " the " + heroClass);
        while (scanner.hasNext()){
            String input = scanner.nextLine();

            if ("create".equalsIgnoreCase(input)){
                controller.onCreateButtonPressed(name, heroClass);
                break;
            } else if ("select".equalsIgnoreCase(input)){
                controller.onSelectButtonPressed();
                break;
            } else if ("redo".equalsIgnoreCase(input)){
                controller.onRedoButtonPressed();
                break;
            } else {
                System.out.println("Unknown command");
            }
        }
    }

    @Override
    public void showErrorMessage(String message){
        System.out.println("Error: " + message);
    }

    @Override
    public void openGame(){
        new GameViewConsole().start();
    }

    @Override
    public void openCreateHero(){
        new CreateHeroViewConsole().start();
    }

    @Override
    public void openSelectHero(){
        new SelectHeroViewConsole().start();
    }

}