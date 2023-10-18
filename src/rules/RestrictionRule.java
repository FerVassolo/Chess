package rules;

import game.Board;
import game.Position;

public interface RestrictionRule {

    /*Validates if the new position is valid*/
    /*If false, it is invalid, if true it is valid*/
    public boolean validateRule(Position pieceOriginalPos, Position pieceNewPos, Board board);

}
