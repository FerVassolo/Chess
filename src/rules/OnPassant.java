package rules;

import game.Board;
import game.Position;

import java.util.ArrayList;

public class OnPassant implements SpecialRule{

    private MovementRule[] movementRules;
    private RestrictionRule[] restrictionRules;

    public OnPassant(MovementRule[] movementRules, RestrictionRule[] restrictionRules){
        this.movementRules = movementRules;
        this.restrictionRules = restrictionRules;
    }

    // The pawn had to be on the same position all the game through;
    @Override
    public void specialRuleIsActive(Position currentPosition, Position newPosition, ArrayList<Board> historyOfBoards) {
        for(Board board : historyOfBoards){
            if
        }
    }
}
