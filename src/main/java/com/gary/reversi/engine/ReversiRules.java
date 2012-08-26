package com.gary.reversi.engine;

import com.rd.game.common.Player;
import com.rd.reversi.client.Position;
import com.rd.reversi.client.ReversiBoard;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 21/08/12
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public interface ReversiRules {


    void applyMove(ReversiBoardImpl board, Player player, int x, int y);
    void applyMove(ReversiBoardImpl board, Player player, Position position);

}
