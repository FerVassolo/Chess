import game.*;
import rules.RestrictionRule;

import java.sql.Time;

public class Main {

    public static void main(String[] args) {

        Player fer = new Player("fer", 1, new Time(0), Color.WHITE);
        Player leo = new Player("leo", 2, new Time(0), Color.BLACK);
        RestrictionRule[] gameRules = new RestrictionRule[]{};
        Player[] players = new Player[]{fer, leo};

        // Creating normal board
        Board board = new BoardTypeCreator().NormalBoardDisplay();
        Game game = new Game(gameRules, players, board, new Time(0));
        game.startTurnBasedGame(game.getBoard());
    }

}
