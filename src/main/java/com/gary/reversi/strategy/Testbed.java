package com.gary.reversi.strategy;

import com.rd.game.common.Player;
import com.rd.reversi.client.Position;
import com.rd.reversi.client.ReversiBoardUtils;
import com.rd.reversi.client.ReversiClientStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 27/08/12
 * Time: 00:40
 * To change this template use File | Settings | File Templates.
 */
public class Testbed {

//    private final Logger LOG = LoggerFac

    public static void main(String[] args) {
        ReversiClientStrategy strategyOne = new LookAheadStrategy(4);
        ReversiClientStrategy strategyTwo = new LookAheadStrategy(0);
        new Testbed().play(strategyOne, strategyTwo);
    }

    public Player play(ReversiClientStrategy strategyOne, ReversiClientStrategy strategyTwo) {
        ReversiRules rules = new ReversiRulesImpl();
        ReversiBoardImpl board = new ReversiBoardImpl(8);

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
                System.out.printf("player %s plays %d, %d\n", player, position.getX(), position.getY());
                board = (ReversiBoardImpl)board.copy();
                rules.applyMove(board, player, position);
                strategyOne.onMovePlayed(board, player);
                strategyTwo.onMovePlayed(board, player);
            } else {
                System.out.printf("player %s doesn't have a valid move\n", player);
                if (!ReversiBoardUtils.getValidMoves(board, Utils.getOpponent(player)).isEmpty() ||
                        ReversiBoardUtils.getPieceCount(board, Player.PLAYER_ONE) == 0 ||
                        ReversiBoardUtils.getPieceCount(board, Player.PLAYER_TWO) == 0)
                    break;


            }
            player = Utils.getOpponent(player);
            turn ++;
            System.out.println(board);
        }

        System.out.printf("game finished. score: %d / %d\n",
                ReversiBoardUtils.getPieceCount(board, Player.PLAYER_ONE),
                ReversiBoardUtils.getPieceCount(board, Player.PLAYER_TWO));
        System.out.println(board);
        if (ReversiBoardUtils.getPieceCount(board, Player.PLAYER_ONE) > ReversiBoardUtils.getPieceCount(board, Player.PLAYER_TWO)) {
            return Player.PLAYER_ONE;
        } else {
            return Player.PLAYER_TWO;
        }

    }

}