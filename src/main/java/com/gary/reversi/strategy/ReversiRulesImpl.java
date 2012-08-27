package com.gary.reversi.strategy;

import com.rd.game.common.Player;
import com.rd.reversi.client.Position;
import com.rd.reversi.client.ReversiBoard;
import com.rd.reversi.client.ReversiBoardUtils;

import java.util.ArrayList;
import java.util.List;

import static com.gary.reversi.strategy.Utils.getPiece;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 21/08/12
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class ReversiRulesImpl {

    public static void applyMove(ReversiBoardImpl board, Player player, int x, int y) {
        applyMove(board, player, new Position((short)x, (short)y));
    }

    public static void applyMove(ReversiBoardImpl board, Player player, Position position) {
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


    private static void flip(ReversiBoardImpl board, Position start, Player player, int xOffset, int yOffset) {
        int count = countFlippableStonesLine(board, start.getX(), start.getY(), player, xOffset, yOffset);
        int x = start.getX();
        int y = start.getY();
        while (count -- > 0) {
            x += xOffset;
            y += yOffset;
            board.setPiece(x, y, player);
        }
        board.setPiece(start.getX(), start.getY(), player);
    }

    private static int countFlippableStonesLine(ReversiBoardImpl board, short x, short y, Player player, int xOffset, int yOffset) {
        int count = 0;
        Player opponent = Utils.getOpponent(player);
        do {
            x += xOffset;
            y += yOffset;
            count++;
        } while (foo(board, x, y, opponent));

        if (foo(board, x, y, player)) {
            return count - 1;
        } else {
            return 0;
        }

    }

    private static boolean foo(ReversiBoardImpl board, short x, short y, Player player) {
        return x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize()
                && getPiece(board, x, y) == player;
    }

    public static boolean isValidMove(ReversiBoardImpl board, Player player, short x, short y) {
        return
                (countFlippableStonesLine(board, x, y, player, 1, -1) > 0) ||
                (countFlippableStonesLine(board, x, y, player, 1, 0) > 0) ||
                (countFlippableStonesLine(board, x, y, player, 1, 1) > 0) ||
                (countFlippableStonesLine(board, x, y, player, 0, 1) > 0) ||
                (countFlippableStonesLine(board, x, y, player, 0, -1) > 0) ||
                (countFlippableStonesLine(board, x, y, player, -1, 1) > 0) ||
                (countFlippableStonesLine(board, x, y, player, -1, 0) > 0) ||
                (countFlippableStonesLine(board, x, y, player, -1, -1) > 0);
    }


    public static List<Position> getValidMoves(ReversiBoard board, Player player) {
//        List<Position> moves = ReversiBoardUtils.getValidMoves(board, player);
        List<Position> moves = getValidMoves2((ReversiBoardImpl) board, player);
        return moves;
    }

    public static List<Position> getValidMoves2(ReversiBoardImpl board, Player player) {
        List<Position> list = new ArrayList<Position>();
        short size = board.getSize();
        for (short x=0; x < size; x++) {
            for (short y=0; y < size; y++) {
                if (board.getPiece(x, y) == null) {
                    if (isValidMove(board, player, x, y)) {
                        list.add(new Position(x, y));
                    }
                }
            }
        }
        return list;
    }

}
