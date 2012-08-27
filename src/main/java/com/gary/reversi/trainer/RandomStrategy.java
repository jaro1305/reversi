package com.gary.reversi.trainer;

import com.gary.reversi.strategy.ReversiRulesImpl;
import com.rd.game.common.Player;
import com.rd.reversi.client.Position;
import com.rd.reversi.client.ReversiBoard;
import com.rd.reversi.client.ReversiClientStrategy;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements ReversiClientStrategy {

    ReversiBoard board;
    Player player;
    Random random = new Random(338);

    @Override
    public void onGameInitialised(ReversiBoard reversiBoard, Player player, String s, int i, byte[] bytes) {
        this.board = reversiBoard;
        this.player = player;
    }

    @Override
    public Position getNextMove() {
        List<Position> validMoves = ReversiRulesImpl.getValidMoves(board, player);
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
