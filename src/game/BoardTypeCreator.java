package game;

import java.util.Map;

public class BoardTypeCreator {


    public Board NormalBoardDisplay(){
        Board emptyBoard = new Board(8, 8);
        return NormalBoardDisplay(emptyBoard);
    }

    private Board NormalBoardDisplay(Board board){
        PawnFactory pawnFactory = new PawnFactory();
        Board whitePawnsRow = fillEntireLineWithOnePiece(board, pawnFactory, 1, 0, 0, Color.WHITE);
        Board blackPawnsRow = fillEntireLineWithOnePiece(whitePawnsRow, pawnFactory, 6, 0, 8, Color.BLACK);
        Board blackPawnIn31 = putPieceAtPos(blackPawnsRow, pawnFactory, 2, 1, 16, Color.BLACK);
        //todo: put remaining pieces.
        return blackPawnIn31;
    }

    private Board fillEntireLineWithOnePiece(Board board, PieceFactory pieceFactory, int row, int currentCol, int id, Color color){
        // Check if conditions are valid
        if (currentCol >= board.getWidth() || row >= board.getHeight() || row < 0){
            return board;
        }
        Map<Position, Piece> newMap = board.getPositions();
        newMap.put(board.getPosByAxis(row, currentCol), pieceFactory.createPieceWithSpecialRules(id, color));
        Board newBoard = new Board(newMap, 8, 8);
        return fillEntireLineWithOnePiece(newBoard, pieceFactory, row, ++currentCol, ++id, color);
    }

    private Board putPieceAtPos(Board board, PieceFactory pieceFactory, int row, int col, int id, Color color){
        Map<Position, Piece> newMap = board.getPositions();
        newMap.put(board.getPosByAxis(row, col), pieceFactory.createPieceWithSpecialRules(id, color));
        return new Board(newMap, 8, 8);
    }



}
