package org.example.pieces;

public class PlayingPieceO implements PlayingPiece {
  Piece pieceType;

  public PlayingPieceO() {
    this.pieceType = Piece.O;
  }

  @Override
  public Piece getPiece() {
    return this.pieceType;
  }
}
