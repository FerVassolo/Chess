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
        SpecialRule[] specialRules = piece.getSpecialRules();

        if(!selectedPieceColorIsValid(piece, game.currentTurn()))
            return false;

        if(isValid(specialRules, game.getGameRules(), currentPos, newPos, game.getHistoryOfBoards(), board))
            return true; // else: we check if with the normal rules we still can.

        return isValid(movementRules, restrictionRules, currentPos, newPos, board);


    }
    public boolean isValid(MovementRule[] movementRules, RestrictionRule[] restrictionRules, Position currentPos, Position newPos, Board board){
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

    public boolean isValid(SpecialRule[] specialRules, RestrictionRule[] gameRules, Position currentPos, Position newPos, ArrayList<Board> historyOfBoards, Board board){
        for(SpecialRule specialRule : specialRules){
            if(specialRule.specialRuleIsActive(currentPos, historyOfBoards)){
                RestrictionRule[] restrictionRules = appendRestrictionRules(gameRules, specialRule.getRestrictionRules());
                if(isValid(specialRule.getMovementRules(), restrictionRules, currentPos, newPos, board))
                    return true;
            }
        }
        return false;
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
