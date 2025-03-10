package snakeLadder.model;

public class Cell {
  Move move;
  Location location;

  public Cell(Location location) {
    this.location = location;
  }

  public Cell(Location location, Move move) {
    this.location = location;
    this.move = move;
  }

  public Location getLocation() {
    return location;
  }

  public Move getMove() {
    return move;
  }

  public void setMove(Move move) {
    if(move != null) {
      System.out.println("move already exists");
    }
    this.move = move;
  }

  public boolean checkMove() {
    return move!=null;
  }

  public Cell getNextCell() {
    if(move==null) {
      return this;
    }
    return move.getNextCell();
  }
}
