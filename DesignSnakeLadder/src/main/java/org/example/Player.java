package org.example;

public class Player {
  public Cell cell;
  public String name;

  public Player(String name) {
    this.name = name;
    this.cell = new Cell(new Position(0,0));
  }
}
