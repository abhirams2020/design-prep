package ChessGame.model;

import ChessGame.enums.Color;
import java.util.UUID;

public class Player {
  String id;
  String name;
  Color color;

  public Player(String name, Color color) {
    this.name = name;
    this.color = color;
    this.id = UUID.randomUUID().toString();
  }

  public Player(String name) {
    this.name = name;
    this.id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }
}
