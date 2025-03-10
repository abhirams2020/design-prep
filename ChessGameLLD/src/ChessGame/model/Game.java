package ChessGame.model;

import ChessGame.enums.MoveType;
import ChessGame.services.MoveValidator;
import java.util.ArrayList;
import java.util.List;

public class Game {
  String id;
  Player whitePlayer, blackPlayer;
  Player winner;
  List<Move> moves;
  Board board;

  public Game(Player whitePlayer, Player blackPlayer) {
    this.whitePlayer = whitePlayer;
    this.blackPlayer = blackPlayer;
    board = new Board(8).getBoard(8, whitePlayer, blackPlayer);
    moves = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public List<Player> getPlayerList() {
    return List.of(whitePlayer, blackPlayer);
  }

  public Player getWinner() {
    return winner;
  }

  public List<Move> getMoves() {
    return moves;
  }

  public Board getBoard() {
    return board;
  }

  public void makeMove(Move move) {
    if(winner != null) return;

    if(MoveValidator.validate(move) == MoveType.VALID) {
      moves.add(move);
      if(moves.size() > 3)
        winner = move.getStart().getPiece().getPlayer(); // for testing. remove later
    } else if (MoveValidator.validate(move) == MoveType.WINNER) {
      moves.add(move);
      winner = move.getStart().getPiece().getPlayer();
    } else {
      throw new IllegalArgumentException("invalid move");
    }
  }
}
