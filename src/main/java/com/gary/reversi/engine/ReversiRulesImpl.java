package com.gary.reversi.engine;

import com.rd.game.common.Player;
import com.rd.reversi.client.Position;

import static com.gary.reversi.engine.Utils.getPiece;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 21/08/12
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class ReversiRulesImpl implements ReversiRules {

//    private final ReversiBoardImpl board = new ReversiBoardImpl(8);

    @Override
    public void applyMove(ReversiBoardImpl board, Player player, int x, int y) {
        applyMove(board, player, new Position((short)x, (short)y));
    }

    @Override
    public void applyMove(ReversiBoardImpl board, Player player, Position position) {
        if (board.getPiece(position) == null) {
            for (int xOffset = -1; xOffset <= 1; xOffset++) {
                for (int yOffset = -1; yOffset <= 1; yOffset++) {
                    if (xOffset != 0 || yOffset != 0) {
                        flip(board, position, player, xOffset, yOffset);
                    }
                }
            }
        }
    }


    private void flip(ReversiBoardImpl board, Position start, Player player, int xOffset, int yOffset) {
        int x = start.getX();
        int y = start.getY();
        Player opponent = Utils.getOpponent(player);
        int count = 0;
        do {
            x += xOffset;
            y += yOffset;
            count++;
        } while (x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize()
                && board.getPiece(x, y) == opponent);

        if (x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize() && board.getPiece(x, y) == player && (x != start.getX() || y != start.getY())) {
            x = start.getX();
            y = start.getY();
            do {
                x += xOffset;
                y += yOffset;
                board.setPiece(x, y, player);
            } while (--count > 0);
        }
        board.setPiece(start.getX(), start.getY(), player);
    }

}
