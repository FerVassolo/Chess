package game;

import rules.*;

import java.util.Arrays;

public class PawnFactory implements PieceFactory{

    // Weather has or not special rules, the movement rules are always the same. Only the restrictions change.
    MovementRule[] movementRules = new MovementRule[]{new VerticalMovement()};
    @Override
    public Piece createPiece(int id, Color color) {
        RestrictionRule[] restrictionRules = new RestrictionRule[]{new StraightMaxQuantityRule(1), new CannotCaptureVertically()};
        return new Piece(id, PieceName.PAWN, "P", color, movementRules, restrictionRules);
    }

    public Piece createPieceWithSpecialRules(int id, Color color){
        Piece piece = createPiece(id, color);
        SpecialRule[] specialRules = new SpecialRule[]{new OnPassant(movementRules)};
        return new Piece(id, PieceName.PAWN, "P", color, movementRules, piece.getRestrictionRules(), specialRules);
    }
}
