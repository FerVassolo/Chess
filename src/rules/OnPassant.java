package rules;

import game.Board;
import game.Position;

import java.util.ArrayList;

public class OnPassant extends SpecialRule{

    private MovementRule[] movementRules;
    private RestrictionRule[] restrictionRules;

    // Lo hago estatico, ¿total siempre son las mismas reglas?
    public OnPassant(MovementRule[] movementRules){
        //todo
        this.movementRules = movementRules;
        this.restrictionRules = new RestrictionRule[]{new StraightMaxQuantityRule(1), new CannotCaptureVertically()};
    }

    // The pawn had to be on the same position all the game through;
    @Override
    public boolean specialRuleIsActive(Position currentPosition, ArrayList<Board> historyOfBoards) {
        boolean pieceNeverMoved = pieceNeverMoved(currentPosition, historyOfBoards);
        // there is actually another rule that states that the on passant is valid only if the pawn to be captured
        // had just been moved. Else it is not valid anymore.
        return pieceNeverMoved;
    }

    public boolean pieceNeverMoved(Position currentPosition, ArrayList<Board> historyOfBoards){
        for(int i = 0; i < historyOfBoards.size() -1; i++){
            Board prevBoard = historyOfBoards.get(i);
            Board newBoard = historyOfBoards.get(i+1);
            Position oldPos = prevBoard.getPosByPos(currentPosition);
            Position newPos = newBoard.getPosByPos(currentPosition);
            if(prevBoard.getPiece(oldPos) != newBoard.getPiece(newPos)){
                return false;
            }
        }
        return true;
    }

    @Override
    public MovementRule[] getMovementRules() {
        return movementRules;
    }

    @Override
    public RestrictionRule[] getRestrictionRules() {
        return restrictionRules;
    }
}
