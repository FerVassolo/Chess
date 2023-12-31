package rules;

/**
 * cannot move if the new position leaves the player in check.
 */

import game.*;

public class CannotMoveIfInCheck implements RestrictionRule{

    @Override
    public boolean validateRule(Position pieceOriginalPos, Position pieceNewPos, Board board) {
        Board movedPieceBoard = new Movement().movePiece(board, pieceOriginalPos, pieceNewPos);
        Color color =  board.getPiece(pieceOriginalPos).getColor();
        Check check = new Check();
        return check.verifyBoardIsNotInCheck(movedPieceBoard, color);
    }

}
