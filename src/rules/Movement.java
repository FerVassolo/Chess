package rules;

import game.*;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Movement {
    /*Returns a new board or the original board depending on weather it is a valid movement or not*/

    public Board makeMove(Game game, Position oldPos, Position newPos){
        boolean isValid = validateMovement(game, oldPos, newPos);

        Board board = game.getBoard();
        Map<Position, Piece> newPosDisplay = board.getPositions();

        if(isValid){
            Piece movedPiece = newPosDisplay.get(board.getPosByPos(oldPos));
            newPosDisplay.put(board.getPosByPos(oldPos), null);
            newPosDisplay.put(board.getPosByPos(newPos), movedPiece);

            return new Board(newPosDisplay, board.getHeight(), board.getWidth());
        }
        return game.getBoard();
    }

    public boolean validateMovement(Game game, Position currentPos, Position newPos){
        Board board = game.getBoard();
        Piece piece = board.getPiece(currentPos);
        if(piece == null){
            System.out.println("There is no piece on that position");
            return false;
        }
        RestrictionRule[] restrictionRules = appendRestrictionRules(game.getGameRules(), piece.getRestrictionRules());
        MovementRule[] movementRules = piece.getMovementRules();

        if(!selectedPieceColorIsValid(piece, game.currentTurn()))
            return false;

        /*
        * for(specialRule...)
        *   SpecialRules have both restriction rules and movement rules
        *   if happens to be all valid --> return true
        *   else: break and continue, maybe it is valid because of normal rules*/
        for (RestrictionRule resRule: restrictionRules){
            if(!resRule.validateRule(currentPos, newPos, board))
                return false;
        }
        for(MovementRule rule: movementRules){
            if(!rule.validateMovement(currentPos, newPos))
                continue;
            return true;
        }

        return false;
    }
    public boolean isValid(MovementRule[] movementRules, RestrictionRule[] restrictionRules){
        return true;
    }

    public RestrictionRule[] appendRestrictionRules(RestrictionRule[] gameRules, RestrictionRule[] restrictionRules){
        RestrictionRule[] combinedRules = new RestrictionRule[gameRules.length + restrictionRules.length];
        System.arraycopy(gameRules, 0, combinedRules, 0, gameRules.length);
        System.arraycopy(restrictionRules, 0, combinedRules, gameRules.length, restrictionRules.length);
        return combinedRules;
    }

    public boolean selectedPieceColorIsValid(Piece piece, Player player){
        if(piece.getColor() != player.getColor()) {
            System.out.println("Invalid Color");
            return false;
        }
        return true;
    }
}
