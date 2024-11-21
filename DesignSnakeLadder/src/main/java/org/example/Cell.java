package org.example;

public class Cell {
  public Position position;
  public Position nextPosition;

  public Cell(Position position) {
    this.position = position;
    this.nextPosition = position;
  }

  public String getPositionCoord() {
    return position.getCoord();
  }

  public String getNextPositionCoord() {
    return nextPosition.getCoord();
  }
}
