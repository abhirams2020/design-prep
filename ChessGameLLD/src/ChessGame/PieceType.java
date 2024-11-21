package ChessGame;

public enum PieceType {
  KING("Ki"),
  QUEEN("Q"),
  PAWN("P"),
  BISHOP("B"),
  KNIGHT("Kn"),
  ROOK("R");

  private final String shortType;

  PieceType(String shortType) {
    this.shortType = shortType;
  }

  public String getShortType() {
    return this.shortType;
  }
}
