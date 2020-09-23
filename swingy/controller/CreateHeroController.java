package swingy.controller;

import swingy.exception.HeroValidationException;
import swingy.model.Game;
import swingy.model.character.Hero;
import swingy.model.character.HeroFactory;
import swingy.data.DataBase;
import swingy.view.create.CreateHeroView;

public class CreateHeroController {

    private CreateHeroView view;
    private Game game;

    public CreateHeroController(CreateHeroView view) {
        this.view = view;
        game = Game.getInstance();
    }

    public void onCreateButtonPressed(String name, String heroClass) {
        Hero hero;
        try {
            hero = HeroFactory.newHero(name, heroClass);
            hero.validateHero();
        } catch (IllegalArgumentException | HeroValidationException e) {
            view.showErrorMessage(e.getMessage());
            view.getUserInput();
            return;
        }

        int id = DataBase.insert(hero.getName(), hero.getHeroClass(), hero.getLevel(), hero.getExperience(), hero.getAttack(), hero.getDefense(), hero.getHitPoints());
        hero.setId(id);
        game.initGame(hero);
        view.openGame();
    }
}