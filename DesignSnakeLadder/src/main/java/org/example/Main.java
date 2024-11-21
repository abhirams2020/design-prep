package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

  public static void main(String[] args) {
    Game game= new Game(10, 2);
    game.addPlayer(new Player("A"));
    game.addPlayer(new Player("B"));
    game.addPlayer(new Player("C"));
    game.launch();
  }
}