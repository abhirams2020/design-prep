package ChessGame.services.validateStrategy;

import ChessGame.enums.MoveType;
import ChessGame.model.Move;

public class RookValidatorStrategy implements ValidatorStrategy{

  @Override
  public MoveType validate(Move move) {
    return MoveType.VALID;
  }
}
