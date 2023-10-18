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
        //todo: put remaining pieces.
        return blackPawnsRow;
    }

    private Board fillEntireLineWithOnePiece(Board board, PieceFactory pieceFactory, int row, int currentCol, int id, Color color){
        // Check if conditions are valid
        if (currentCol >= board.getWidth() || row >= board.getHeight() || row < 0){
            return board;
        }
        Map<Position, Piece> newMap = board.getPositions();
        newMap.put(board.getPositionByRowCol(row, currentCol), pieceFactory.createPiece(id, color));
        Board newBoard = new Board(newMap, 8, 8);
        return fillEntireLineWithOnePiece(newBoard, pieceFactory, row, ++currentCol, ++id, color);
    }



}
