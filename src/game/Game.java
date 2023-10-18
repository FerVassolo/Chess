package game;

import rules.Movement;
import rules.RestrictionRule;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private RestrictionRule[] gameRules;
    private Player[] players;
    private Board board;
    private Time gameClock;
    private int turn = 0;

    // All the boards used in the game. Useful for checking the on passant, the pawns first double movement or the king castle.
    //It ain't actually inmutable.
    private ArrayList<Board> historyOfBoards = new ArrayList<>();

    public Game(RestrictionRule[] gameRules, Player[] players, Board board, Time gameClock){
        this.gameRules = gameRules;
        this.players = players;
        this.board = board;
        this.gameClock = gameClock;
        historyOfBoards.add(board);
    }

    public void passTurn(){
        if(++turn >= players.length)
            turn = 0;
    }
    public Player currentTurn(){
        return players[turn];
    }

    public Board getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }

    public RestrictionRule[] getGameRules() {
        return gameRules;
    }

    public ArrayList<Position> askForMovement(){
        System.out.println();
        System.out.println("It is " + players[turn].getName() + "'s turn");
        System.out.println("Select a piece to move (row, col): ");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Row: ");
        int currentRow = scanner.nextInt();
        System.out.print("Column: ");
        int currentCol = scanner.nextInt();

        System.out.println("New position (row, col): ");
        System.out.print("Row: ");
        int newRow = scanner.nextInt();
        System.out.print("Column: ");
        int newCol = scanner.nextInt();
        System.out.println();

        Position currentPos = new Position(currentRow, currentCol);
        Position newPos = new Position(newRow, newCol);

        ArrayList<Position> arrayList = new ArrayList<>();
        arrayList.add(currentPos);
        arrayList.add(newPos);

        return arrayList;
    }


    // I should put this method inside another one that checks if the game ends
    public void startTurnBasedGame(Board board){
        board.display();
        ArrayList<Position> positions = askForMovement();
        Board newBoard = new Movement().makeMove(this, positions.get(0), positions.get(1));
        if (board == newBoard){
            System.out.println("Movement is not valid, try again");
            startTurnBasedGame(board);
        }
        passTurn();
        // here should check if the game ends and stuff.

        // if it doesn't:
        historyOfBoards.add(newBoard);
        startTurnBasedGame(newBoard);
    }

    public ArrayList<Board> getHistoryOfBoards() {
        return historyOfBoards;
    }
}
