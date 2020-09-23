package swingy.view.game;

import swingy.model.Game;
import swingy.data.Point;

public interface GameView {

    void    start();
    void    printMap(boolean[][] map, Point heroCoord);
    void    update(Game game);
    void    gameFinished();
    void    showMessage(String message);
    void    getVillainCollisionInput();
    boolean replaceArtifact(String replaceMessage);
    void    switchView();
}