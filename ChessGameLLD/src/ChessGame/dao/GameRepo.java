package ChessGame.dao;

import ChessGame.model.Game;
import ChessGame.services.ChessService;
import java.util.HashMap;
import java.util.Map;

public class GameRepo {
  public static volatile GameRepo instance;

  Map<String, Game> gameMap = new HashMap<>();

  private GameRepo() {
  }

  public static GameRepo getInstance() {
    if(instance == null) {
      synchronized (GameRepo.class) {
        if(instance == null) {
          instance = new GameRepo();
        }
      }
    }
    return instance;
  }

  public void addGame(String id, Game game) {
    gameMap.put(id, game);
  }

  public Game getGame(String id) {
    if(!gameMap.containsKey(id)) {
      System.out.println("game not found");
      return null;
    }
    return gameMap.get(id);
  }
}
