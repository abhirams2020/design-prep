package ChessGame.services;
import ChessGame.enums.MoveType;
import ChessGame.model.Cell;
import ChessGame.model.Move;
import ChessGame.model.piece.Piece;
import ChessGame.services.validateStrategy.BishopValidatorStrategy;
import ChessGame.services.validateStrategy.KingValidatorStrategy;
import ChessGame.services.validateStrategy.KnightValidatorStrategy;
import ChessGame.services.validateStrategy.PawnValidatorStrategy;
import ChessGame.services.validateStrategy.QueenValidatorStrategy;
import ChessGame.services.validateStrategy.RookValidatorStrategy;

public class MoveValidator {
  public static MoveType validate(Move move) {
//    Board board = move.getBoard();
    Cell start = move.getStart();
    Piece startPiece = start.getPiece();

    if(startPiece == null) {
      return MoveType.INVALID;
    }

    return switch (startPiece.getPieceType()) {
      case PAWN -> new PawnValidatorStrategy().validate(move);
      case KING -> new KingValidatorStrategy().validate(move);
      case QUEEN -> new QueenValidatorStrategy().validate(move);
      case KNIGHT -> new KnightValidatorStrategy().validate(move);
      case BISHOP -> new BishopValidatorStrategy().validate(move);
      case ROOK -> new RookValidatorStrategy().validate(move);
      default -> MoveType.INVALID;
    };
  }
}
