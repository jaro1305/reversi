package com.gary.reversi.strategy;

import com.rd.game.common.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 26/08/12
 * Time: 23:38
 * To change this template use File | Settings | File Templates.
 */
public class Move {
    short x;
    short y;
    Player player;

    public Move(short x, short y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    @Override
    public String toString() {
        return String.format("(%s: %d, %d)", player, x, y);
    }
}
