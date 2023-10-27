package game;

import rules.*;

public class BishopFactory implements PieceFactory{
    MovementRule[] movementRules = new MovementRule[]{new DiagonalMovement()};
    @Override
    public Piece createPiece(int id, Color color) {
        RestrictionRule[] restrictionRules = new RestrictionRule[]{new PieceInterposesDiagonallyRestriction()};
        return new Piece(id, PieceName.BISHOP, "B", color, movementRules, restrictionRules);
    }

    @Override
    public Piece createPieceWithSpecialRules(int id, Color color) {
        return null;
    }
}
