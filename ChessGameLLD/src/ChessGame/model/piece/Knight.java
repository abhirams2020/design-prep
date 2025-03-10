package ChessGame.model.piece;

import ChessGame.enums.Color;
import ChessGame.enums.PieceType;
import ChessGame.model.Player;

public class Knight extends Piece{
  Color color;
  Player player;
  PieceType pieceType = PieceType.KNIGHT;

  public Knight(Color color, Player player) {
    super(color, player);
  }

  @Override
  public PieceType getPieceType() {
    return pieceType;
  }
}
