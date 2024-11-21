package ChessGame;

import java.util.Deque;

public class Game {
  Board board;
  int boardSize;

  Deque<Player> playerList;

  public void initializeGame() {
    Player white = new Player(Color.WHITE);
    Player black = new Player(Color.BLACK);

    playerList.addLast(white);
    playerList.addLast(black);
  }

  public
}
