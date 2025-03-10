package ChessGame.model;

import ChessGame.enums.Color;
import ChessGame.model.piece.Piece;

public class Cell {
  Position position;
  Piece piece;
  Color color;

  public Cell(Position position, Color color) {
    this.position = position;
    this.color = color;
  }

  public Position getPosition() {
    return position;
  }

  public Piece getPiece() {
    return piece;
  }

  public Color getColor() {
    return color;
  }

  public void setPiece(Piece piece) {
    this.piece = piece;
  }
}
