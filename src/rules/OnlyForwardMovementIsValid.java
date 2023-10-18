package rules;

import game.Board;
import game.Color;
import game.Piece;
import game.Position;

public class OnlyForwardMovementIsValid implements RestrictionRule {

    @Override
    public boolean validateRule(Position pieceOriginalPos, Position pieceNewPos, Board board) throws IllegalArgumentException {
        Piece piece = board.getPiece(pieceOriginalPos);
        Color pieceColor = piece.getColor();

        if (pieceColor == Color.WHITE)
            return pieceNewPos.getCol() - pieceOriginalPos.getCol() > 0;
        else if (pieceColor == Color.BLACK)
            return pieceNewPos.getCol() - pieceOriginalPos.getCol() < 0;
        else{
            System.out.println("Color is not yet implemented");
            return false;
        }

    }

}
