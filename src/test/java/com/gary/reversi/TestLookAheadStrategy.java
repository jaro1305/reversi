package com.gary.reversi;

import com.gary.reversi.strategy.LookAheadStrategy;
import com.gary.reversi.strategy.ReversiBoardImpl;
import com.rd.game.common.Player;
import com.rd.reversi.client.Position;
import junit.framework.Assert;
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
                "- - - - X O - -\n" +
                "- - - - O - - O\n" +
                "- - O O O - O -\n" +
                "- O O O O O O O\n" +
                "- O O O O - - -\n" +
                "X O O O - - - -\n" +
                "- - O O O - - -\n" +
                "- O - O X O - -\n";
        ReversiBoardImpl board = new ReversiBoardImpl(boardStr);
        LookAheadStrategy strategy = new LookAheadStrategy(3);
        strategy.onGameInitialised(board, Player.PLAYER_ONE, "", 1, new byte[0]);

        Position move = strategy.getNextMove();
        Assert.assertEquals(3, move.getX());
        Assert.assertEquals(0, move.getY());

    }
}
