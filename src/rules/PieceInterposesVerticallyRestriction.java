package rules;

import game.Board;
import game.Position;

public class PieceInterposesVerticallyRestriction implements RestrictionRule {

    // doesn't verify the newPiecePos
    @Override
    public boolean validateRule(Position pieceOriginalPos, Position pieceNewPos, Board board) {
        int originalRow = pieceOriginalPos.getRow();
        int newRow = pieceNewPos.getRow();
        int col = pieceNewPos.getRow();
        if(originalRow < newRow) {
            for (int i = originalRow+1; i < newRow; i++) {
                Position pos = board.getPosByAxis(i, col);
                if(board.getPiece(pos) != null){
                    return false;
                }
            }
        }
        else if(originalRow > newRow) {
            for (int i = newRow+1; i < originalRow; i++) {
                Position pos = board.getPosByAxis(i, col);
                if(board.getPiece(pos) != null){
                    return false;
                }
            }
        }
        return true;
    }
}
