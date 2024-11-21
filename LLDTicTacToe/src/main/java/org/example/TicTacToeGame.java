package org.example;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import org.example.board.Board;
import org.example.board.Player;
import org.example.pieces.Piece;
import org.example.pieces.PlayingPiece;
import org.example.pieces.PlayingPieceO;
import org.example.pieces.PlayingPieceX;
import java.util.Scanner;

public class TicTacToeGame {
  Board board;
  List<Player> players;

  int index;
  int playerSize;

  int rem;

  public void initializeGame() {
    board = new Board(3);
    players = new ArrayList<>();

    index = 0;
    playerSize = 2;
    rem = 3*3;

    PlayingPiece noughtPiece = new PlayingPieceO();
    Player player1 = new Player("player1", noughtPiece);

    PlayingPiece crossPiece = new PlayingPieceX();
    Player player2 = new Player("player2", crossPiece);

    players = List.of(player1, player2);
  }

  public String getWinner() {
    while(rem>0) {
      board.printBoard();
      System.out.println("Enter coordinates for player " + players.get(index).getName() + " with piece " + players.get(index).getPlayingPiece().getPiece().name());

      Scanner inputScanner = new Scanner(System.in);
      String s = inputScanner.nextLine();
      String[] values = s.split(",");

      int row = Integer.valueOf(values[0]);
      int col = Integer.valueOf(values[1]);

      boolean isPieceAdded = board.addPiece(row, col, players.get(index).getPlayingPiece());

      if(isPieceAdded) {
        if(isThereWinner(row, col, players.get(index).getPlayingPiece().getPiece())) {
          return players.get(index).getName();
        }
        index++;
        index = index % playerSize;
        rem--;
      }
    }

    return "tie";
  }

  public boolean isThereWinner(int row, int column, Piece pieceType) {
    boolean rowMatch = true;
    boolean columnMatch = true;
    boolean diagonalMatch = true;
    boolean antiDiagonalMatch = true;

    //need to check in row
    for(int i=0;i<board.boardSize;i++) {

      if(board.board[row][i] == null || board.board[row][i].getPiece() != pieceType) {
        rowMatch = false;
      }
    }

    //need to check in column
    for(int i=0;i<board.boardSize;i++) {

      if(board.board[i][column] == null || board.board[i][column].getPiece() != pieceType) {
        columnMatch = false;
      }
    }

    //need to check diagonals
    for(int i=0, j=0; i<board.boardSize;i++,j++) {
      if (board.board[i][j] == null || board.board[i][j].getPiece() != pieceType) {
        diagonalMatch = false;
      }
    }

    //need to check anti-diagonals
    for(int i=0, j=board.boardSize-1; i<board.boardSize;i++,j--) {
      if (board.board[i][j] == null || board.board[i][j].getPiece() != pieceType) {
        antiDiagonalMatch = false;
      }
    }

    return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
  }

}
