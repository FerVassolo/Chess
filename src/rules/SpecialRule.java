package rules;

import game.Board;
import game.Position;

import java.util.ArrayList;

public interface SpecialRule {

    public boolean specialRuleIsActive(Position currentPosition, ArrayList<Board> historyOfBoards);

    public MovementRule[] getMovementRules();

    public RestrictionRule[] getRestrictionRules();

}
