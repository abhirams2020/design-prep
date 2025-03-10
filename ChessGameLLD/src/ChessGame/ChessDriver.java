package ChessGame;

import ChessGame.enums.Color;
import ChessGame.model.Game;
import ChessGame.model.Move;
import ChessGame.model.Player;
import ChessGame.model.piece.Piece;
import ChessGame.services.ChessService;
import java.util.List;

public class ChessDriver {
  public static void main(String[] args) {
    ChessService chessService = ChessService.getInstance();

    Game game = chessService.startGame(List.of(
        new Player("ABHIRAM"),
        new Player("SHIBUMON")
    ));

    System.out.println("Winner is : " + game.getWinner().getName());

    System.out.println("Move history : ");

    List<Move> moveList = game.getMoves();

    for(Move move:moveList) {
      Piece curPiece = move.getStart().getPiece();
      System.out.println(move.getPlayer().getName() + " moved " +
          curPiece.getPieceType().name() + " from " +
          move.getStart().getPosition().getX() + "," + move.getStart().getPosition().getY() + " to " +
          move.getEnd().getPosition().getX() + "," + move.getEnd().getPosition().getY());
    }
  }
}
