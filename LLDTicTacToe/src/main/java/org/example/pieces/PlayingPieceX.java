package org.example.pieces;

public class PlayingPieceX implements PlayingPiece{
  Piece pieceType;

  public PlayingPieceX() {
    this.pieceType = Piece.X;
  }

  @Override
  public Piece getPiece() {
    return this.pieceType;
  }
}
