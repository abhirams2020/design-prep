package ChessGame.services;

import ChessGame.enums.Color;
import ChessGame.model.Board;
import ChessGame.model.Game;
import ChessGame.model.Move;
import ChessGame.model.Player;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class ChessService {
  public static volatile ChessService instance;

  private ChessService() {
  }

  public static ChessService getInstance() {
    if(instance == null) {
      synchronized (ChessService.class) {
        if(instance == null) {
          instance = new ChessService();
        }
      }
    }
    return instance;
  }

  public Game startGame(List<Player> playerList) {
    Queue<Player> q = new ArrayDeque<>();
    Game game;
    Player p1=playerList.getFirst();
    Player p2=playerList.getLast();

    if(new Random().nextInt() % 2 == 0) {
      p1.setColor(Color.WHITE);
      p2.setColor(Color.BLACK);
      q.add(p1);
      q.add(p2);
      game =  new Game(p1,p2);
    } else {
      p1.setColor(Color.BLACK);
      p2.setColor(Color.WHITE);
      q.add(p2);
      q.add(p1);
      game =  new Game(p2,p1);
    }

    Board board = game.getBoard();

    while(!q.isEmpty() && game.getWinner() == null) {
      Player cur = q.poll();
      Scanner sc = new Scanner(System.in);
      printBoard(board);
      System.out.println(cur.getName() + ", enter next move. eg 1,2:3,4");
      String mv = sc.nextLine();

      int sx = mv.charAt(0) - '0';
      int sy = mv.charAt(2) - '0';
      int ex = mv.charAt(4) - '0';
      int ey = mv.charAt(6) - '0';

      game.makeMove(new Move(cur, board, board.cells[sx][sy], board.cells[ex][ey]));
      q.add(cur);
    }

    return game;
  }

  private void printBoard(Board board) {
    int boardSize = board.getBoardSize();

    for(int i=0;i<boardSize;i++) {
      for(int j=0;j<boardSize;j++) {
        if(board.getCells()[i][j].getPiece() == null) {
          System.out.print("..\t");
        } else {
        System.out.print(
          "" +
          board.getCells()[i][j].getPiece().getColor().toString().charAt(0) +
          board.getCells()[i][j].getPiece().getPieceType().toString().charAt(0) +
          "\t"
        );
        }
      }
      System.out.println();
    }
  }
}
