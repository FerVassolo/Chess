package rules;

import game.Board;
import game.Position;

import java.util.ArrayList;

public abstract class SpecialRule {

    private MovementRule[] movementRules;
    private RestrictionRule[] restrictionRules;

    // If the conditions are still valid. Like, the pawn hasn't moved yet on the on passant...
    public abstract boolean specialRuleIsActive(Position currentPosition, ArrayList<Board> historyOfBoards);

    public MovementRule[] getMovementRules() {
        return movementRules;
    }

    public RestrictionRule[] getRestrictionRules() {
        return restrictionRules;
    }
}
