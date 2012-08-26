package com.gary.reversi;

import com.gary.reversi.engine.ReversiBoardImpl;
import com.gary.reversi.engine.ReversiRules;
import com.gary.reversi.engine.ReversiRulesImpl;
import com.rd.game.common.Player;
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
        ReversiRules rules = new ReversiRulesImpl();
        ReversiBoardImpl board = new ReversiBoardImpl(8);
        rules.applyMove(board, Player.PLAYER_ONE, 4, 2);
        System.out.println(board);
    }
}
