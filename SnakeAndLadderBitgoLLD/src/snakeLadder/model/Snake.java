package snakeLadder.model;

import snakeLadder.enums.MoveType;

public class Snake implements Move{
  Cell start, end;

  public Snake(Cell start, Cell end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.SNAKE;
  }

  @Override
  public Cell getNextCell() {
    return end;
  }
}
