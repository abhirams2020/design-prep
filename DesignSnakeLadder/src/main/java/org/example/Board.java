package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
  int size;
  Cell[][] board;
  int diceCount;
  int numSnakes;
  int numLadders;

  public Board(int size, int diceCount, int numSnakes, int numLadders) {
    this.size = size;
    board = new Cell[size][size];
    this.diceCount = diceCount;
    this.numSnakes = numSnakes;
    this.numLadders = numLadders;
    initializeBoard();
  }

  public void initializeBoard() {
    System.out.println("board initialized");
    for(int i=0;i<size;i++) {
      for(int j=0;j<size;j++) {
        Position position = new Position(i,j);
        board[i][j] = new Cell(position);
      }
    }
    addSnakes();
    addLadders();
    printBoard();
  }

  public void addSnakes() {
    int count = numSnakes;
    while(count > 0) {
      int startPos = ThreadLocalRandom.current().nextInt(0, size*size);
      int endPos = ThreadLocalRandom.current().nextInt(0, startPos);
      if(startPos > endPos) {
        int start_x = startPos / size;
        int start_y = startPos % size;
        int end_x = endPos / size;
        int end_y = endPos % size;
        board[start_x][start_y].nextPosition = new Position(end_x, end_y);
        count--;
        System.out.println("added snake from " + board[start_x][start_y].getPositionCoord() + " to " + board[start_x][start_y].getNextPositionCoord());
      }
    }
    System.out.println("Snakes added");
  }

  public void addLadders() {
    int count = numSnakes;
    while(count > 0) {
      int startPos = ThreadLocalRandom.current().nextInt(0, size*size);
      int endPos = ThreadLocalRandom.current().nextInt(startPos+1, size*size);
      if(startPos < endPos) {
        int start_x = startPos / size;
        int start_y = startPos % size;
        int end_x = endPos / size;
        int end_y = endPos % size;
        board[start_x][start_y].nextPosition = new Position(end_x, end_y);
        count--;
        System.out.println("added ladder from " + board[start_x][start_y].getPositionCoord() + " to " + board[start_x][start_y].getNextPositionCoord());
      }
    }
    System.out.println("Ladders added");
  }

  public void printBoard() {
    for(Cell[] cells:board) {
      for(Cell cell:cells) {
        System.out.print(cell.getPositionCoord() + "\t");
      }
      System.out.println();
    }
  }
}
