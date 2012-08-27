package com.gary.reversi.engine;

import com.rd.game.common.Player;
import com.rd.reversi.client.Position;
import com.rd.reversi.client.ReversiBoard;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 20/08/12
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */
public class ReversiBoardImpl implements ReversiBoard {

    private final short size;
    private final Player[][] board;

    public ReversiBoardImpl(Player[][] board) {
        this.size = (short)board.length;
        this.board = new Player[size][];
        for (int i=0; i < size; i++) {
            this.board[i] = board[i].clone();
        }

    }

    public ReversiBoardImpl(String board) {
        String[] rows = board.split("\n");
        size = (short)rows.length;
        Player[][] transposed = new Player[size][size];
        for (int r = 0; r < size; r++) {
            transposed[r] = readRow(rows[r]);
            if (transposed[r].length != size) {
                throw new RuntimeException("invalid row size");
            }
        }
        this.board = new Player[size][size];
        for (int i=0; i < size; i++) {
            for (int j=0; j < size; j++) {
                this.board[i][j] = transposed[j][i];
            }
        }

    }

    private Player[] readRow(String rowString) {
        String[] elements = rowString.split(" ");
        Player[] row = new Player[elements.length];
        for (int i=0; i < elements.length; i++) {
            if (Player.PLAYER_ONE.toString().equalsIgnoreCase(elements[i])) {
                row[i] = Player.PLAYER_ONE;
            } else if (Player.PLAYER_TWO.toString().equalsIgnoreCase(elements[i])) {
                row[i] = Player.PLAYER_TWO;
            } else if ("-".equals(elements[i])) {
                row[i] = null;
            } else {
                throw new RuntimeException(String.format("invalid element [%s]", elements[i]));
            }
        }
        return row;
    }

    public ReversiBoardImpl(int size) {
        this((short)size);
    }

    public ReversiBoardImpl(short size) {
        this.size = size;
        this.board = new Player[size][size];
        initStart();
    }

    public ReversiBoardImpl(ReversiBoard reversiBoard) {
        this.size = reversiBoard.getSize();
        this.board = new Player[size][size];
        for (short i=0; i < size; i++) {
            for (short j=0; j < size; j++) {
                this.board[i][j] = reversiBoard.getPiece(new Position(i, j));
            }
        }
    }

    private void initStart() {
        board[size / 2 - 1][size / 2 - 1] = Player.PLAYER_ONE;
        board[size / 2 - 1][size / 2] = Player.PLAYER_TWO;
        board[size / 2][size / 2] = Player.PLAYER_ONE;
        board[size / 2][size / 2 - 1] = Player.PLAYER_TWO;
    }

    @Override
    public short getSize() {
        return size;
    }

    @Override
    public com.rd.game.common.Player getPiece(Position position) {
        return board[position.getX()][position.getY()];
    }

    public com.rd.game.common.Player getPiece(int x, int y) {
        return board[x][y];
    }

    public void setPiece(int x, int y, Player player) {
        board[x][y] = player;
    }

    public void setPiece(Position position, Player player) {
        board[position.getX()][position.getY()] = player;
    }

    @Override
    public ReversiBoard copy() {
        return new ReversiBoardImpl(board);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++)  {

                if (board[x][y] == null) {
                    builder.append("-");
                } else if (board[x][y] == Player.PLAYER_ONE) {
                    builder.append("O");
                } else if (board[x][y] == Player.PLAYER_TWO) {
                    builder.append("X");
                }
                if (x < size - 1) {
                    builder.append(" ");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
