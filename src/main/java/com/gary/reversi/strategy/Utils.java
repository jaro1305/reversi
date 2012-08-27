package com.gary.reversi.strategy;

import com.rd.game.common.Player;
import com.rd.reversi.client.Position;
import com.rd.reversi.client.ReversiBoard;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 26/08/12
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    public static Player getOpponent(Player player) {
        return player == Player.PLAYER_ONE ? Player.PLAYER_TWO : Player.PLAYER_ONE;
    }

    public static Player getPiece(ReversiBoardImpl board, short x, short y) {
        return board.getPiece(x, y);
    }

    public static Player getPiece(ReversiBoard board, short x, short y) {
        return board.getPiece(new Position(x, y));
    }

    public static Player getPiece(ReversiBoard board, int x, int y) {
        return board.getPiece(new Position((short)x, (short)y));
    }
}
