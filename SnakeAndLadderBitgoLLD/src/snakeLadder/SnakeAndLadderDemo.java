package snakeLadder;

import java.util.List;
import snakeLadder.service.GameService;
import snakeLadder.service.UserService;

public class SnakeAndLadderDemo {
  public static void main(String[] args) {
    UserService userService = UserService.getInstance();
    String id1 = userService.createUser("abhiram");
    String id2 = userService.createUser("shaji");
    int boardSize = 4;
    GameService game = new GameService(List.of(id2, id1), boardSize);
    game.startGame();
  }
}
