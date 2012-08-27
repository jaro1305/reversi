package com.gary.reversi.trainer;

import com.gary.reversi.strategy.*;
import com.rd.game.common.Player;
import com.rd.reversi.client.Position;
import com.rd.reversi.client.ReversiBoardUtils;
import com.rd.reversi.client.ReversiClientStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Trainer {

    private final Logger LOG = LoggerFactory.getLogger(Trainer.class);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i=0; i < 10; i++) {
            LookAheadStrategy strategyOne = new LookAheadStrategy(3);
            LookAheadStrategy strategyTwo = new LookAheadStrategy(2);
            new Trainer().play(strategyOne, strategyTwo);

        }
        System.out.println("time seconds " + (int)((System.currentTimeMillis() - start)/1000F));
    }

    public Player play(ReversiClientStrategy strategyOne, ReversiClientStrategy strategyTwo) {
        ReversiBoardImpl board = new ReversiBoardImpl(11);

        ReversiClientStrategy[] strategies = new ReversiClientStrategy[]{
            strategyOne, strategyTwo
        };
        Player[] players = new Player[] {Player.PLAYER_ONE, Player.PLAYER_TWO};

        strategyOne.onGameInitialised(board, Player.PLAYER_ONE, "o", 1, new byte[0]);
        strategyTwo.onGameInitialised(board, Player.PLAYER_TWO, "x", 1, new byte[0]);

        int turn = 0;
        Player player = players[turn];
        while (ReversiBoardUtils.getPieceCount(board, null) != 0) {
            if (!ReversiBoardUtils.getValidMoves(board, player).isEmpty()) {
                Position position = strategies[turn % 2].getNextMove();
                LOG.info("player {} plays {}, {}", new Object[]{player, position.getX(), position.getY()});
                board = (ReversiBoardImpl)board.copy();
                ReversiRulesImpl.applyMove(board, player, position);
                strategyOne.onMovePlayed(board, player);
                strategyTwo.onMovePlayed(board, player);
            } else {
                LOG.info("player {} doesn't have a valid move", player);
                if (!ReversiBoardUtils.getValidMoves(board, Utils.getOpponent(player)).isEmpty() ||
                        ReversiBoardUtils.getPieceCount(board, Player.PLAYER_ONE) == 0 ||
                        ReversiBoardUtils.getPieceCount(board, Player.PLAYER_TWO) == 0)
                    break;


            }
            player = Utils.getOpponent(player);
            turn ++;
            LOG.info(board.toString());
        }

        LOG.info("game finished. score: {} / {}",
                ReversiBoardUtils.getPieceCount(board, Player.PLAYER_ONE),
                ReversiBoardUtils.getPieceCount(board, Player.PLAYER_TWO));
        LOG.info(board.toString());
        if (ReversiBoardUtils.getPieceCount(board, Player.PLAYER_ONE) > ReversiBoardUtils.getPieceCount(board, Player.PLAYER_TWO)) {
            return Player.PLAYER_ONE;
        } else {
            return Player.PLAYER_TWO;
        }

    }

}
