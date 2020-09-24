package lin.swingy.view.game;

import lin.swingy.model.Game;
import lin.swingy.data.Point;

public interface GameView {

    void    start();
    void    update(Game game, boolean[][] map, Point heroCoord);
    void    gameFinished();
    void    showMessage(String message);
    void    getVillainCollisionInput();
    boolean replaceArtifact(String replaceMessage);
    void    switchView();
}