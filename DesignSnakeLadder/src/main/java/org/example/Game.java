package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Game {
  Deque<Player> playerList = new ArrayDeque<>();
  Board board;
  List<Dice> diceList = new ArrayList<>();

  int boardSize, diceCount;

  public Game(int boardSize, int diceCount) {
    this.boardSize = boardSize;
    this.diceCount = diceCount;
  }

  public void addPlayer(Player player) {
    playerList.addLast(player);
  }

  private int rollDice() {
    int total = 0;
    for(Dice dice : diceList) {
      total += dice.rollDice();
    }
    return total;
  }

  private Player movePlayer(Player curPlayer, int toMove) {
    Position startPos = curPlayer.cell.position;
    int start = boardSize*curPlayer.cell.position.x + curPlayer.cell.position.y;
    int end = Math.min(boardSize*boardSize-1, start + toMove);

    curPlayer.cell = board.board[end/boardSize][end%boardSize];

    while(curPlayer.cell.position != curPlayer.cell.nextPosition) {
      curPlayer.cell = board.board[curPlayer.cell.nextPosition.x][curPlayer.cell.nextPosition.y];
    }

    Position endPos = curPlayer.cell.position;
    System.out.println("moved player " + curPlayer.name + " from " + startPos.getCoord() + " to " + endPos.getCoord());
    return curPlayer;
  }

  private boolean checkWinner(Player player) {
    return player.cell.position.x==boardSize-1 && player.cell.position.y==boardSize-1;
  }

  public void launch() {
    board = new Board(boardSize, 1, 2, 2);
    diceList.add(new NormalDice(6));
    diceList.add(new NormalDice(6));
    System.out.println("Game launched");
    for(Player player:playerList) {
      System.out.println(player.name);
    }

    while(true) {
      Player curPlayer = playerList.getFirst();
      System.out.println("Player " + curPlayer.name + " turn");
      playerList.removeFirst();

      int toMove = rollDice();
      System.out.println("Dice rolled and got : " + toMove);

      curPlayer = movePlayer(curPlayer, toMove);

      playerList.addLast(curPlayer);

      if(checkWinner(curPlayer)) {
        System.out.println(curPlayer.name + " is winner");
        return;
      }
    }
  }
}
