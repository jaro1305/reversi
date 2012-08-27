package com.gary.reversi;

import com.gary.reversi.strategy.ReversiScorer;
import com.gary.reversi.strategy.ReversiBoardImpl;
import com.rd.game.common.Player;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 26/08/12
 * Time: 21:55
 * To change this template use File | Settings | File Templates.
 */
public class TestBoardScorer {

    ReversiScorer reversiScorer = new ReversiScorer();
    ReversiBoardImpl board = new ReversiBoardImpl(8);

    @Test
    public void testStoneCount() throws Exception {
        Assert.assertEquals(2, reversiScorer.countStones(board, Player.PLAYER_ONE));
    }

    @Test
    public void testCornerCount() throws Exception {
        board.setPiece(0, 0, Player.PLAYER_ONE);
        board.setPiece(7, 7, Player.PLAYER_ONE);
        board.setPiece(0, 7, Player.PLAYER_TWO);

        Assert.assertEquals(2, reversiScorer.countCornerStones(board, Player.PLAYER_ONE));
        Assert.assertEquals(1, reversiScorer.countCornerStones(board, Player.PLAYER_TWO));
    }

    @Test
    public void testEdgeStones() throws Exception {
        board.setPiece(0, 3, Player.PLAYER_ONE);
        board.setPiece(0, 4, Player.PLAYER_TWO);
        board.setPiece(7, 3, Player.PLAYER_TWO);
        board.setPiece(7, 7, Player.PLAYER_TWO);
        board.setPiece(7, 0, Player.PLAYER_TWO);
        System.out.println(board);
        Assert.assertEquals(1, reversiScorer.countEdgeStones(board, Player.PLAYER_ONE));
        Assert.assertEquals(4, reversiScorer.countEdgeStones(board, Player.PLAYER_TWO));
    }


    @Test
    public void testCountThreatenedStones() throws Exception {
        board.setPiece(3, 2, Player.PLAYER_ONE);
        board.setPiece(3, 1, Player.PLAYER_ONE);
        board.setPiece(4, 5, Player.PLAYER_ONE);
        board.setPiece(4, 6, Player.PLAYER_ONE);
        board.setPiece(4, 7, Player.PLAYER_ONE);
        System.out.println(board);
        Assert.assertEquals(5, reversiScorer.countThreatenedStones(board, Player.PLAYER_ONE));
    }

    @Test
    public void testTotalScore() throws Exception {
        board.setPiece(0, 3, Player.PLAYER_ONE);
        board.setPiece(0, 4, Player.PLAYER_TWO);
        board.setPiece(7, 3, Player.PLAYER_TWO);
        board.setPiece(7, 7, Player.PLAYER_TWO);
        board.setPiece(7, 0, Player.PLAYER_TWO);
        Assert.assertEquals(-44, reversiScorer.score(board, Player.PLAYER_ONE));
    }

    @Test
    public void testIsThreatened() throws Exception {
        ReversiBoardImpl board = new ReversiBoardImpl(
              // 0 1 2 3 4 5 6 7
                "- - - - O - - -\n" + // 0
                "- - - - O - - -\n" + // 1
                "- - X - O - - -\n" + // 2
                "- - X X O - - -\n" + // 3
                "- - O O X - - -\n" + // 4
                "- - - O - X - O\n" + // 5
                "- - - O - - - X\n" + // 6
                "- - - - - - - X\n"); // 7
        ReversiScorer scorer = new ReversiScorer();
        Assert.assertTrue(scorer.isThreatened(board, 2, 4));
        Assert.assertFalse(scorer.isThreatened(board, 5, 5));
        Assert.assertFalse(scorer.isThreatened(board, 7, 7));
        Assert.assertTrue(scorer.isThreatened(board, 7, 5));
        Assert.assertFalse(scorer.isThreatened(board, 4, 1));
    }
}