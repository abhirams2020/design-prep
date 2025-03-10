package snakeLadder.model;

public class Board {
  Cell[][] cells;
  int boardSize;

  public Board(int boardSize) {
    cells = new Cell[boardSize][boardSize];
    for(int i=0;i<boardSize;i++) {
      for(int j=0;j<boardSize;j++) {
        cells[i][j] = new Cell(new Location(i,j));
      }
    }
    this.boardSize = boardSize;
  }

  public void addSnake(int sx, int sy, int ex, int ey) {
    Cell start = cells[sx][sy], end = cells[ex][ey];
    if(start.getLocation().getSum(boardSize) > end.getLocation().getSum(boardSize)) {
      Move move = new Snake(start, end);
      start.setMove(move);
      end.setMove(move);
    }
  }

  public void addLadder(int sx, int sy, int ex, int ey) {
    Cell start = cells[sx][sy], end = cells[ex][ey];
    if(start.getLocation().getSum(boardSize) > end.getLocation().getSum(boardSize)) {
      Move move = new Ladder(start, end);
      start.setMove(move);
      end.setMove(move);
    }
  }

  public Cell makeMove(Cell curr, int diceCnt) {
    int curTotal = curr.getLocation().getSum(boardSize);
    int nextTotal = Math.min(boardSize*boardSize-1, curTotal + diceCnt);
    int nx=nextTotal/boardSize;
    int ny=nextTotal%boardSize;
    if(nx==boardSize-1 && ny==boardSize-1) {
      return cells[nx][ny];
    }
    return cells[nx][ny].getNextCell();
  }
}
