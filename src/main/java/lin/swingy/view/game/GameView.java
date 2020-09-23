package lin.swingy.view.game;

import lin.swingy.model.Game;
import lin.swingy.data.Point;

public interface GameView {

    void    start();
    void    printMap(boolean[][] map, Point heroCoord);
    void    update(Game game);
    void    gameFinished();
    void    showMessage(String message);
    void    getVillainCollisionInput();
    boolean replaceArtifact(String replaceMessage);
    //void    switchView();
}