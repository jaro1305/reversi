package com.gary.reversi;

import com.gary.reversi.strategy.ReversiBoardImpl;
import com.gary.reversi.strategy.ReversiRulesImpl;
import com.rd.game.common.Player;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 20/08/12
 * Time: 22:14
 * To change this template use File | Settings | File Templates.
 */
public class TestBoardImpl {

    @Test
    public void testBasicBoard() throws Exception {
        ReversiBoardImpl board = new ReversiBoardImpl(8);
        System.out.println(board.toString());
    }

    @Test
    public void testBasicMove() throws Exception {
        ReversiBoardImpl board = new ReversiBoardImpl(8);
        ReversiRulesImpl.applyMove(board, Player.PLAYER_ONE, 4, 2);
        System.out.println(board);
    }

    @Test
    public void testDoubleCaptureMove() throws Exception {
        String state =
                "- - X - - O - -\n" +
                "- - X - O - - -\n" +
                "- - X O - - - -\n" +
                "- - - O O O O X\n" +
                "- - O O O - - -\n" +
                "- - X - O - - -\n" +
                "- - - - - X - -\n" +
                "- - - - - - - -\n";
        ReversiBoardImpl board = new ReversiBoardImpl(state);
        ReversiRulesImpl.applyMove(board, Player.PLAYER_TWO, 2, 3);
        String expected =
                "- - X - - O - -\n" +
                "- - X - O - - -\n" +
                "- - X O - - - -\n" +
                "- - X X X X X X\n" +
                "- - X X O - - -\n" +
                "- - X - X - - -\n" +
                "- - - - - X - -\n" +
                "- - - - - - - -\n";
        Assert.assertEquals(expected, board.toString());
    }
}
