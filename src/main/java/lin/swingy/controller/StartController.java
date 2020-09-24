package lin.swingy.controller;

import lin.swingy.view.start.StartView;

public class StartController {

    private StartView view;

    public StartController(StartView view) {
        this.view = view;
    }

    public void onCreateHeroButtonPressed() {
        view.openCreateHero();
    }

    public void onSwitchButtonPressed() {
        view.switchView();
    }

    public void onSelectHeroButtonPressed() {
        view.openSelectHero();
    }
}