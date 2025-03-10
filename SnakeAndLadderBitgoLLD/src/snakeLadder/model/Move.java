package snakeLadder.model;

import snakeLadder.enums.MoveType;

public interface Move {
  MoveType  getMoveType();
  Cell getNextCell();
}
