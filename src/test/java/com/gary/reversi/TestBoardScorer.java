package com.gary.reversi;

import com.gary.reversi.engine.BoardScorer;
import com.gary.reversi.engine.ReversiBoardImpl;
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

    BoardScorer boardScorer = new BoardScorer();
    ReversiBoardImpl board = new ReversiBoardImpl(8);

    @Test
    public void testStoneCount() throws Exception {
        Assert.assertEquals(2, boardScorer.countStones(board, Player.PLAYER_ONE));
    }

    @Test
    public void testCornerCount() throws Exception {
        board.setPiece(0, 0, Player.PLAYER_ONE);
        board.setPiece(7, 7, Player.PLAYER_ONE);
        board.setPiece(0, 7, Player.PLAYER_TWO);

        Assert.assertEquals(2, boardScorer.countCornerStones(board, Player.PLAYER_ONE));
        Assert.assertEquals(1, boardScorer.countCornerStones(board, Player.PLAYER_TWO));
    }

    @Test
    public void testEdgeStones() throws Exception {
        board.setPiece(0, 3, Player.PLAYER_ONE);
        board.setPiece(0, 4, Player.PLAYER_TWO);
        board.setPiece(7, 3, Player.PLAYER_TWO);
        board.setPiece(7, 7, Player.PLAYER_TWO);
        board.setPiece(7, 0, Player.PLAYER_TWO);
        System.out.println(board);
        Assert.assertEquals(1, boardScorer.countEdgeStones(board, Player.PLAYER_ONE));
        Assert.assertEquals(4, boardScorer.countEdgeStones(board, Player.PLAYER_TWO));
    }


    @Test
    public void testCountThreatenedStones() throws Exception {
        board.setPiece(3, 2, Player.PLAYER_ONE);
        board.setPiece(3, 1, Player.PLAYER_ONE);
        board.setPiece(4, 5, Player.PLAYER_ONE);
        board.setPiece(4, 6, Player.PLAYER_ONE);
        board.setPiece(4, 7, Player.PLAYER_ONE);
        System.out.println(board);
        Assert.assertEquals(5, boardScorer.countThreatenedStones(board, Player.PLAYER_ONE));
    }

    @Test
    public void testTotalScore() throws Exception {
        board.setPiece(0, 3, Player.PLAYER_ONE);
        board.setPiece(0, 4, Player.PLAYER_TWO);
        board.setPiece(7, 3, Player.PLAYER_TWO);
        board.setPiece(7, 7, Player.PLAYER_TWO);
        board.setPiece(7, 0, Player.PLAYER_TWO);
        Assert.assertEquals(-8, boardScorer.score(board, Player.PLAYER_ONE));

    }
}