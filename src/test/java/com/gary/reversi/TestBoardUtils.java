package com.gary.reversi;

import com.gary.reversi.strategy.ReversiBoardImpl;
import com.gary.reversi.strategy.ReversiRulesImpl;
import com.rd.game.common.Player;
import com.rd.reversi.client.Position;
import com.rd.reversi.client.ReversiBoard;
import com.rd.reversi.client.ReversiBoardUtils;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 27/08/12
 * Time: 08:13
 * To change this template use File | Settings | File Templates.
 */
public class TestBoardUtils {

    @Ignore
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
        Assert.assertEquals(0, ReversiBoardUtils.getValidMoves(board, Player.PLAYER_ONE));
    }

    @Ignore("broken - Position does not implement hashcode")
    public void testName() throws Exception {
        String boardStr = "- - - - - - - -\n" +
                "- - - - - - - -\n" +
                "- - X - O - - -\n" +
                "- - X X O - - -\n" +
                "- - O O X - - -\n" +
                "- - - O - X - -\n" +
                "- - - - - - - -\n" +
                "- - - - - - - -\n";

        ReversiBoard board = new ReversiBoardImpl(boardStr);

        List<Position> validMoves = ReversiRulesImpl.getValidMoves(board, Player.PLAYER_ONE);
        System.out.println(validMoves);
        List<Position> list = new ArrayList<Position>();
        list.add(new Position((short)1, (short)2));
        list.add(new Position((short)1, (short)3));
        list.add(new Position((short)2, (short)1));
        list.add(new Position((short)3, (short)2));
        list.add(new Position((short)4, (short)5));
        list.add(new Position((short)5, (short)3));
        list.add(new Position((short)5, (short)4));
        Assert.assertEquals(list, validMoves);

    }
}
