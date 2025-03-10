package ChessGame.model;

import ChessGame.enums.Color;
import ChessGame.enums.PieceType;
import ChessGame.model.piece.Bishop;
import ChessGame.model.piece.King;
import ChessGame.model.piece.Knight;
import ChessGame.model.piece.Pawn;
import ChessGame.model.piece.Piece;
import ChessGame.model.piece.Queen;
import ChessGame.model.piece.Rook;

public class PieceFactory {
  public static Piece getPiece(PieceType pieceType, Color color, Player player) {
    return switch (pieceType) {
      case PAWN -> new Pawn(color, player);
      case KING -> new King(color, player);
      case QUEEN -> new Queen(color, player);
      case KNIGHT -> new Knight(color, player);
      case BISHOP -> new Bishop(color, player);
      case ROOK -> new Rook(color, player);
    };
  }
}
