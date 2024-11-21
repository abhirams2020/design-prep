package ChessGame;

import ChessGame.piece.Piece;

public class Cell {
  Piece piece;
  int row;
  int col;
  Color color;

  public Cell(int row, int col) {
    piece = null;
    this.row = row;
    this.col = col;
  }

  public Piece getPiece() {
    return this.piece;
  }

  public boolean setPiece(Piece piece) {
    if(this.piece != null) {
      return false;
    }
    this.piece = piece;
    return true;
  }

  public void display() {
    if(piece != null) {
      System.out.print(piece.getName());
    }
    else {
      System.out.print(" ");
    }
  }
}
