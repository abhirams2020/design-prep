package ChessGame.model;

import ChessGame.enums.MoveType;
import ChessGame.services.MoveValidator;

public class Move {
  Player player;
  Board board;
  Cell start;
  Cell end;

  public Move(Player player, Board board, Cell start, Cell end) {
    this.player = player;
    this.start = start;
    this.end = end;
    this.board = board;
  }

  public MoveType isValid() {
    return MoveValidator.validate(this);
  }

  public Player getPlayer() {
    return player;
  }

  public Board getBoard() {
    return board;
  }

  public Cell getStart() {
    return start;
  }

  public Cell getEnd() {
    return end;
  }
}
