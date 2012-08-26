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

    public ReversiBoardImpl(int size) {
        this((short)size);
    }

    public ReversiBoardImpl(short size) {
        this.size = size;
        this.board = new Player[size][size];
        initStart();
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
