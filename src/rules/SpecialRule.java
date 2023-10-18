package rules;

import game.Board;
import game.Position;

import java.util.ArrayList;

public interface SpecialRule {

    // If the conditions are still valid. Like, the pawn hassnt moved yet on the on passant...
    public void specialRuleIsActive(Position currentPosition, Position newPosition, ArrayList<Board> historyOfBoards);
}
