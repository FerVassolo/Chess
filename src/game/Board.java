package game;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private Map<Position, Piece> positions;
    private int height;
    private int width;
    private String displayBoard;
    /*Creating an empty Board*/

    public Board(int height, int width){
        this.height = height;
        this.width = width;
        this.positions = createEmptyBoard();
    }

    /*Updating the board*/
    public Board(Map<Position, Piece> positions, int height, int width){
        this.positions = positions;
        this.height = height;
        this.width = width;
    }

    public Map<Position, Piece> createEmptyBoard(){
        Map<Position, Piece> positions = new HashMap<>();
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Position position = new Position(i, j);
                positions.put(position, null);
                //positions.put(position, new Piece(i+j, PieceName.PAWN, "P", Color.BLACK, new MovementRule[]{new StraightMovement(1)}, new RestrictionRule[]{new StraightMaxQuantityRule(1)}));
            }
        }
        return positions;
    }

    /*At the beggining all displays will be squares*/
    public void display(){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                if(positions.get(getPosByAxis(i, j)) != null){
                    String pieceName = positions.get(getPosByAxis(i, j)).getNameAbbreviation();
                    System.out.print("| " + pieceName + " ");
                }
                else
                    System.out.print("|   ");
                if(j == height-1)
                    System.out.print("|");
            }
            System.out.println();
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Map<Position, Piece> getPositions() {
        return positions;
    }
    public Position getPositionByRowCol(int row, int col){
        Position[] pos = positions.keySet().toArray(new Position[0]);
        for(Position p : pos){
            if(row == p.getRow() && col == p.getCol()){
                return p;
            }
        }
        return null;
    }

    public Position getPosByPos(Position pos){
        return getPositionByRowCol(pos.getRow(), pos.getCol());
    }

    public Position getPosByAxis(int row, int col){
        for(Position pos : positions.keySet()){
            if (pos.getRow() == row && pos.getCol() == col){
                return pos;
            }
        }
        return null;
    }

    // Put any position, it doesn't matter if it doesn't exist it will change it into an existing one.
    public Piece getPiece(Position position){
        Position pos = getPositionByRowCol(position.getRow(), position.getCol());
        return positions.get(pos);
    }



}
