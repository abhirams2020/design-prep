package ChessGame;

import ChessGame.piece.Piece;

public class Board {
  int boardSize;

  Cell[][] board;

  public Board() {
    this.boardSize = 8;
    board = new Cell[boardSize][boardSize];
  }

  public Board(int boardSize) {
    this.boardSize = boardSize;
    board = new Cell[boardSize][boardSize];
  }

  public boolean addPiece(PieceType pieceType, Color color, int r, int c) {
    if(r<0 || r>=boardSize || c<0 || c>=boardSize) {
      System.out.println("enter valid cell");
      return false;
    }
    if(board[r][c].setPiece(new Piece(pieceType, color))) {
      System.out.println("piece " + pieceType.name() + " set");
      return true;
    }
    System.out.println("cell already occupied");
    return false;
  }

  public void displayBoard() {
    for(int i=0;i<boardSize;i++) {
      for(int j=0;j<boardSize;j++) {
        board[i][j].display();
      }
    }
  }
}
