package rules;

import game.Board;
import game.Position;

// Rule is the same for horizontal than it is for vertical
public class StraightMaxQuantityRule implements RestrictionRule{

    int maxQty;

    public StraightMaxQuantityRule(int maxQty){
        this.maxQty = maxQty;
    }

    @Override
    public boolean validateRule(Position pieceOriginalPos, Position pieceNewPos, Board board) {
        int verticalDistance = Math.abs(pieceNewPos.getRow() - pieceOriginalPos.getRow());
        int horizontalDistance = Math.abs(pieceNewPos.getCol() - pieceOriginalPos.getCol());
        if(verticalDistance > maxQty || horizontalDistance > maxQty) return false;
        return true;
    }



}
