package snakeLadder.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import snakeLadder.enums.GameStatus;

public class Game {
  String id;
  Queue<Player> playerQueue;
  int boardSize;
  Board board;
  Map<Player, Cell> playerCellMap;
  Player winner = null;
  GameStatus gameStatus;

  public Game(List<Player> playerList, int boardSize){
    id = UUID.randomUUID().toString();
    this.boardSize = boardSize;
    playerQueue = new LinkedList<>(playerList);
    board = new Board(boardSize);
    intializeBoard();
    playerCellMap = new HashMap<>();
    for(Player player:playerList) {
      playerCellMap.put(player, board.cells[0][0]);
      player.addGame(this);
    }
    gameStatus = GameStatus.NOT_STARTED;
  }

  private void intializeBoard() {
  }

  public GameStatus move(Player player, int moves) {
    if(gameStatus==GameStatus.NOT_STARTED) {
      gameStatus = GameStatus.ONGOING;
    }
    if(gameStatus==GameStatus.COMPLETED) {
      return gameStatus;
    }
    Player curPlayer = playerQueue.poll();

    if(curPlayer!=null && !curPlayer.equals(player)) {
      return gameStatus;
    }
    Cell nextCell = board.makeMove(playerCellMap.get(player), moves);
    playerCellMap.put(curPlayer, nextCell);
    if(nextCell.getLocation().getSum(boardSize) == boardSize*boardSize-1) {
      winner = curPlayer;
      gameStatus = GameStatus.COMPLETED;
    }
    playerQueue.offer(curPlayer);
    return gameStatus;
  }

  public GameStatus getStatus() {
    return gameStatus;
  }

  public Player getNextPlayer() {
    return playerQueue.peek();
  }

  public Player getWinner() {
    return winner;
  }
}
