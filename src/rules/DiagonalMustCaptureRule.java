package rules;

import game.Board;
import game.Color;
import game.Position;

public class DiagonalMustCaptureRule implements RestrictionRule{
    @Override
    public boolean validateRule(Position pieceOriginalPos, Position pieceNewPos, Board board) {
        if(!isDiagonal(pieceOriginalPos, pieceNewPos)) {
            return true; // If the movement isn't diagonal this restriction doesn't apply.
        }
        Color currentColor = board.getPiece(pieceOriginalPos).getColor();
        Color newColor = board.getPiece(pieceNewPos).getColor();
        if(currentColor != newColor && newColor != null)
            return true;
        return false;
    }

    public boolean isDiagonal(Position pieceOriginalPos, Position pieceNewPos){
        return new DiagonalMovement().validateMovement(pieceOriginalPos, pieceNewPos);
    }

}
