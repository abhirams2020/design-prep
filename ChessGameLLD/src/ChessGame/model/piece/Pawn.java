package ChessGame.model.piece;

import ChessGame.enums.Color;
import ChessGame.enums.PieceType;
import ChessGame.model.Board;
import ChessGame.model.Move;
import ChessGame.model.Player;

public class Pawn extends Piece{
  Color color;
  Player player;
  PieceType pieceType = PieceType.PAWN;

  public Pawn(Color color, Player player) {
    super(color, player);
  }

  @Override
  public PieceType getPieceType() {
    return pieceType;
  }
}
