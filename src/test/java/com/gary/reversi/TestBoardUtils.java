package com.gary.reversi;

import com.gary.reversi.engine.ReversiBoardImpl;
import com.rd.game.common.Player;
import com.rd.reversi.client.ReversiBoard;
import com.rd.reversi.client.ReversiBoardUtils;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 27/08/12
 * Time: 08:13
 * To change this template use File | Settings | File Templates.
 */
public class TestBoardUtils {

    @Test
    public void testValidMoves() throws Exception {
        String boardStr =
            "- O - - - - O -\n" +
            "- - - - - - - -\n" +
            "- - - - - - - -\n" +
            "- - - - - - - -\n" +
            "- - - - - - - -\n" +
            "- X - - - - - -\n" +
            "- - - - - - - -\n" +
            "- O - - - - - -\n";
        ReversiBoard board = new ReversiBoardImpl(boardStr);
        System.out.println(ReversiBoardUtils.getValidMoves(board, Player.PLAYER_ONE));
    }
}
