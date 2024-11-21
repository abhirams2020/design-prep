package ChessGame.piece;

import ChessGame.Cell;
import ChessGame.Color;
import ChessGame.Move;
import ChessGame.PieceType;

public abstract class Piece {
  private boolean isKill = false;
  private Color color;
  private PieceType pieceType;
  private boolean hasMoved = false;
  private Cell cell;

  public Piece(PieceType pieceType, Color color, Cell cell) {
    this.pieceType = pieceType;
    this.color = color;
    this.cell = cell;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public void killPiece() {
    isKill = true;
  }

  public PieceType getPieceType() {
    return this.pieceType;
  }

  public void setPieceType(PieceType pieceType) {
    this.pieceType = pieceType;
  }

  public boolean checkHasMoved() {
    return this.hasMoved;
  }

  public void setHasMoved() {
    this.hasMoved = true;
  }

  public Cell getCell() {
    return this.cell;
  }

  public void setCell(Cell cell) {
    this.cell = cell;
  }
}
