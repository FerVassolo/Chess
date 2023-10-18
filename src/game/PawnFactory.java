package game;

import rules.*;

public class PawnFactory implements PieceFactory{

    @Override
    public Piece createPiece(int id, Color color) {
        MovementRule[] movementRules = new MovementRule[]{new VerticalMovement()};
        RestrictionRule[] restrictionRules = new RestrictionRule[]{new StraightMaxQuantityRule(1)};
        return new Piece(id, PieceName.PAWN, "P", color, movementRules, restrictionRules);
    }
}
