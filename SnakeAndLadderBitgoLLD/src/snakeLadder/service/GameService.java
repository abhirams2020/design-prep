package snakeLadder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import snakeLadder.enums.GameStatus;
import snakeLadder.model.Game;
import snakeLadder.model.Player;

public class GameService {
  Game game;
  int diceSize;

  public GameService(List<String> playerIdList, int boardSize) {
    List<Player> players = new ArrayList<>();
    UserService userService = UserService.getInstance();
    for(String id:playerIdList) {
      players.add(userService.getUserById(id));
    }
    game = new Game(players, boardSize);
    diceSize = 6;
  }

  public void startGame() {
    while(game.getStatus() != GameStatus.COMPLETED) {
      System.out.println("player : " + game.getNextPlayer().getName() + " to roll the dice");
      Scanner sc = new Scanner("roll dice ?");
      System.out.println(sc.nextLine());
      int cnt = 1 + new Random().nextInt(diceSize);
      System.out.println("dice rolled to " + cnt);
      game.move(game.getNextPlayer(), cnt);
    }
    System.out.println("congrats " + game.getWinner().getName() + " for winning");
  }
}
