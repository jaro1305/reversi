package com.gary.reversi.strategy;

import com.rd.game.common.Player;
import com.rd.reversi.client.ReversiBoard;

import static com.gary.reversi.strategy.Utils.getPiece;

public class ReversiScorer {
//    float[] weights = new float[]{1, -1, 1, -1, 1, -1, 1, -1};
    float[] weights = new float[]{2, -2, 6, -6, 10, -10, 1, -1};

    public void initGreedyWeights() {
        float[] weights = new float[]{1, 0, 0, 0, 0, 0, 0, 0};
    }

    public int score(ReversiBoard board, Player player) {
        Player opponent = Utils.getOpponent(player);

        int playerStones = countStones(board, player);
        int opponentStones = countStones(board, opponent);
        int result;
        if (playerStones == 0) {
            result = Integer.MIN_VALUE + 1;
        } else if (opponentStones == 0) {
            result = Integer.MAX_VALUE - 1;
        } else {
            result = (int)(
                playerStones * weights[0] +
                opponentStones * weights[1] +
                countEdgeStones(board, player) * weights[2] +
                countEdgeStones(board, opponent) * weights[3] +
                countCornerStones(board, player) * weights[4] +
                countCornerStones(board, opponent) * weights[5] +
                countThreatenedStones(board, player) * weights[6] +
                countThreatenedStones(board, opponent) * weights[7]);
        }
        return result;
    }

    public int countStones(ReversiBoard board, Player player) {
        int count = 0;
        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                if (getPiece(board, x, y) == player) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countEdgeStones(ReversiBoard board, Player player) {
        int count = 0;
        for (short x = 0; x < board.getSize(); x++) {
            if (getPiece(board, 0, x) == player)
                count++;
            if (getPiece(board, board.getSize()-1, x) == player)
                count++;
        }
        for (short x = 1; x < board.getSize()-1; x++) {
            if (getPiece(board, x, 0) == player)
                count++;
            if (getPiece(board, x, board.getSize()-1) == player)
                count++;
        }
        return count;
    }

    public int countCornerStones(ReversiBoard board, Player player) {
        int count = 0;
        if (getPiece(board, 0, 0) == player)
            count++;
        if (getPiece(board, 0, board.getSize() - 1) == player)
            count++;
        if (getPiece(board, board.getSize() - 1, 0) == player)
            count++;
        if (getPiece(board, board.getSize() - 1, board.getSize() - 1) == player)
            count++;
        return count;
    }

    public int countThreatenedStones(ReversiBoard board, Player player) {
        int count = 0;
        for (short x = 0; x < board.getSize(); x++) {
            for (short y = 0; y < board.getSize(); y++) {
                if (getPiece(board, x, y) == player && isThreatened(board, x, y))
                    count ++;
            }

        }
        return count;
    }

    public boolean isThreatened(ReversiBoard board, int x, int y) {
        Player player = getPiece(board, x, y);
        if (getPiece(board, x, y) == player &&
                (isThreatened(board, x, y, 0, 1) ||
                isThreatened(board, x, y, 1, 1) ||
                isThreatened(board, x, y, 1, 0))) {
            return true;
        }
        return false;
    }

    public boolean isThreatened(ReversiBoard board, int x, int y, int xOffset, int yOffset) {
        Player player = getPiece(board, x, y);
        Player opponent = Utils.getOpponent(player);
        Player positiveEnd = getStoneAtLinesEnd(board, x, y, xOffset, yOffset, player);
        Player negativeEnd = getStoneAtLinesEnd(board, x, y, -xOffset, -yOffset, player);

        return ((positiveEnd == null && negativeEnd == opponent) ||
                (positiveEnd == opponent && negativeEnd == null));
    }

    private Player getStoneAtLinesEnd(ReversiBoard board, int x, int y, int xOffset, int yOffset, Player player) {
        while (x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize() &&
                getPiece(board, x, y) == player) {
            x += xOffset;
            y += yOffset;
        }
        if (x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize())
            return getPiece(board, x, y);
        else
            return player;
    }


}
