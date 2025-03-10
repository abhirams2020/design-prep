package ChessGame.model;

import ChessGame.enums.Color;
import ChessGame.enums.PieceType;
import java.util.List;
import java.util.Random;

public class Board {
  int boardSize = 8;
  public Cell[][] cells;
  private static volatile Board board;

  public Board(int boardSize) {
    this.boardSize = boardSize;
    cells = new Cell[boardSize][boardSize];
  }

  public Board getBoard(int boardSize, Player whitePlayer, Player blackPlayer) {
    if(board == null) {
      synchronized (Board.class) {
        if(board == null) {
          board = new Board(boardSize);
          populateBoard(board, whitePlayer, blackPlayer);
        }
      }
    }
    return board;
  }

  public int getBoardSize() { return boardSize; }

  public Cell[][] getCells() { return cells; }

  private void populateBoard(Board board, Player whitePlayer, Player blackPlayer) {
    // initialize board color
    for(int i=0;i<boardSize;i++) {
      for(int j=0;j<boardSize;j++) {
        if((i+j)%2==0) {
          board.cells[i][j] = new Cell(new Position(i,j), Color.WHITE);
        } else {
          board.cells[i][j] = new Cell(new Position(i,j), Color.BLACK);
        }
      }
    }

    // initialize pieces
    populateBlack(blackPlayer);
    populateWhite(whitePlayer);
  }

  private void populateBlack(Player player) {
    for(int i=0;i<boardSize;i++) {
      board.cells[1][i].setPiece(PieceFactory.getPiece(PieceType.PAWN, Color.BLACK, player));
    }
    board.cells[0][0].setPiece(PieceFactory.getPiece(PieceType.ROOK, Color.BLACK, player));
    board.cells[0][7].setPiece(PieceFactory.getPiece(PieceType.ROOK, Color.BLACK, player));
    board.cells[0][1].setPiece(PieceFactory.getPiece(PieceType.KNIGHT, Color.BLACK, player));
    board.cells[0][6].setPiece(PieceFactory.getPiece(PieceType.KNIGHT, Color.BLACK, player));
    board.cells[0][2].setPiece(PieceFactory.getPiece(PieceType.BISHOP, Color.BLACK, player));
    board.cells[0][5].setPiece(PieceFactory.getPiece(PieceType.BISHOP, Color.BLACK, player));
    board.cells[0][4].setPiece(PieceFactory.getPiece(PieceType.KING, Color.BLACK, player));
    board.cells[0][3].setPiece(PieceFactory.getPiece(PieceType.QUEEN, Color.BLACK, player));
  }

  private void populateWhite(Player player) {
    for(int i=0;i<boardSize;i++) {
      board.cells[6][i].setPiece(PieceFactory.getPiece(PieceType.PAWN, Color.WHITE, player));
    }
    board.cells[7][0].setPiece(PieceFactory.getPiece(PieceType.ROOK, Color.WHITE, player));
    board.cells[7][7].setPiece(PieceFactory.getPiece(PieceType.ROOK, Color.WHITE, player));
    board.cells[7][1].setPiece(PieceFactory.getPiece(PieceType.KNIGHT, Color.WHITE, player));
    board.cells[7][6].setPiece(PieceFactory.getPiece(PieceType.KNIGHT, Color.WHITE, player));
    board.cells[7][2].setPiece(PieceFactory.getPiece(PieceType.BISHOP, Color.WHITE, player));
    board.cells[7][5].setPiece(PieceFactory.getPiece(PieceType.BISHOP, Color.WHITE, player));
    board.cells[7][4].setPiece(PieceFactory.getPiece(PieceType.KING, Color.WHITE, player));
    board.cells[7][3].setPiece(PieceFactory.getPiece(PieceType.QUEEN, Color.WHITE, player));
  }
}
