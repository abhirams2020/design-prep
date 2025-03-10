package ChessGame.services.validateStrategy;

import ChessGame.enums.MoveType;
import ChessGame.model.Move;

public interface ValidatorStrategy {
  // cur king should not be under check
  // cur player move should be valid for the piece
  // cur player move should not make his king under check
  MoveType validate(Move move);
}
