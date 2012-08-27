package com.gary.reversi;

import com.gary.reversi.engine.LookAheadStrategy;
import com.gary.reversi.engine.ReversiBoardImpl;
import com.rd.game.common.Player;
import com.rd.reversi.client.Position;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 27/08/12
 * Time: 07:43
 * To change this template use File | Settings | File Templates.
 */
public class TestLookAheadStrategy {

    @Test
    public void testFindNextMove() throws Exception {
        String boardStr =
                "X - O O X O O O\n" +
                        "O O O O X O X X\n" +
                        "O O O X X X X O\n" +
                        "O O O X X O O O\n" +
                        "O O O O O O O -\n" +
                        "O O O O O O - O\n" +
                        "O X O O O O O -\n" +
                        "O O O O X X X X";
        ReversiBoardImpl board = new ReversiBoardImpl(boardStr);
        LookAheadStrategy strategy = new LookAheadStrategy(3);
        strategy.onGameInitialised(board, Player.PLAYER_ONE, "", 1, new byte[0]);

        Position move = strategy.getNextMove();
        System.out.println(move);

    }
}
