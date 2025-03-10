package snakeLadder.model;

import snakeLadder.enums.MoveType;

public class Ladder implements Move{
  Cell start, end;

  public Ladder(Cell start, Cell end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.LADDER;
  }

  @Override
  public Cell getNextCell() {
    return end;
  }
}
