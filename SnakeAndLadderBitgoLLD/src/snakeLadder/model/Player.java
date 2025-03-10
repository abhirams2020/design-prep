package snakeLadder.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {
  String id, name;
  List<Game> gameList;

  public Player(String name) {
    this.name = name;
    this.id = UUID.randomUUID().toString();
    gameList=new ArrayList<>();
  }

  public void addGame(Game game) {
    gameList.add(game);
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }
}
