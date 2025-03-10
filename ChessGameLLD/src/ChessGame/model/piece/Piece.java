package ChessGame.model.piece;

import ChessGame.enums.Color;
import ChessGame.enums.PieceType;
import ChessGame.model.Player;

public abstract class Piece {
  private Color color;
  private Player player;

  public Piece(Color color, Player player) {
    this.color = color;
    this.player = player;
  }

  public Color getColor() {
    return color;
  }

  public Player getPlayer() {
    return player;
  }

  public abstract PieceType getPieceType();
}
