package com.gary.reversi.engine;

import com.rd.game.common.Player;
import com.rd.reversi.client.Position;
import com.rd.reversi.client.ReversiBoard;
import com.rd.reversi.client.ReversiBoardUtils;
import com.rd.reversi.client.ReversiClientStrategy;

import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 27/08/12
 * Time: 07:37
 * To change this template use File | Settings | File Templates.
 */
public class RandomStrategy implements ReversiClientStrategy {

    ReversiBoard board;
    Player player;
    Random random = new Random(333);

    @Override
    public void onGameInitialised(ReversiBoard reversiBoard, Player player, String s, int i, byte[] bytes) {
        this.board = reversiBoard;
        this.player = player;
    }

    @Override
    public Position getNextMove() {
        System.out.println("[[[ " + board + " ]]]");
        List<Position> validMoves = ReversiBoardUtils.getValidMoves(board, player);
        return validMoves.get((int)Math.floor(random.nextDouble() * validMoves.size()));
    }

    @Override
    public void onMovePlayed(ReversiBoard reversiBoard, Player player) {
        this.board = reversiBoard;
    }

    @Override
    public byte[] onGameEnded(ReversiBoard reversiBoard, Player player) {
        return new byte[0];
    }
}
