package org.example.board;

import org.example.pieces.PlayingPiece;

public class Board {
  public PlayingPiece[][] board;
  public int boardSize;

  public Board() {
    board = new PlayingPiece[3][3];
    boardSize = 3;
  }

  public Board(int boardSize) {
    board = new PlayingPiece[boardSize][boardSize];
    this.boardSize = boardSize;
  }

  public boolean addPiece(int row, int col, PlayingPiece playingPiece) {
    if(row<0 || row>=boardSize || col<0 || col>=boardSize) {
      System.out.println("coordinates out of range");
      return false;
    }
    if(board[row][col] != null) {
      System.out.println("square is already occupied");
      return false;
    }
    board[row][col] = playingPiece;
    return true;
  }

  public void printBoard() {
    for(int i=0;i<boardSize;i++) {
      for(int j=0;j<boardSize;j++) {
        if(board[i][j]==null) {
          System.out.print("  |");
        } else {
          System.out.print(board[i][j].getPiece().toString() + " |");
        }
      }
      System.out.println();
    }
  }
}
