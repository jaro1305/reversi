package com.gary.reversi.engine;

import com.rd.game.common.Player;
import com.rd.reversi.client.Position;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 21/08/12
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class ReversiGameImpl implements ReversiGame {

    private final ReversiBoardImpl board = new ReversiBoardImpl(8);

    @Override
    public void applyMove(Player player, int x, int y) {
        applyMove(player, new Position((short)x, (short)y));
    }

    @Override
    public void applyMove(Player player, Position position) {
        if (board.getPiece(position) == null) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x != 0 || y != 0) {
                        flip(position, player, x, y);
                    }
                }
            }
        }
    }

    @Override
    public void printBoard() {
        System.out.println(board.toString());
    }

    private void flip(Position start, Player player, int xOffset, int yOffset) {
        int x = start.getX();
        int y = start.getY();
        Player opponent = player == Player.PLAYER_ONE ? Player.PLAYER_TWO : Player.PLAYER_ONE;

        System.out.println("offset is " + xOffset + ", " + yOffset);
        do {
            x += xOffset;
            y += yOffset;
            System.out.println("checking " + x + ", " + y);
        } while (x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize()
                && board.getPiece(x, y) == opponent);

        if (x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize() && board.getPiece(x, y) == player && (x != start.getX() || y != start.getY())) {
            x = start.getX();
            y = start.getY();
            do {
                x += xOffset;
                y += yOffset;
                board.setPiece(x, y, player);
            } while (x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize()
                    && board.getPiece(x, y) == opponent);
        }
        board.setPiece(start.getX(), start.getY(), player);
    }

}
