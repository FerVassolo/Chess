package rules;

import game.Board;
import game.Game;

public interface EndGameRule {

    // If any endgae rule is true, then the game ends
    public boolean checkEndRule(Game game, Board board);

}
