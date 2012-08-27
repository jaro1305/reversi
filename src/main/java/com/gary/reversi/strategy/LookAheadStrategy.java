package com.gary.reversi.strategy;

import com.rd.game.common.GameLogger;
import com.rd.game.common.Player;
import com.rd.reversi.client.Position;
import com.rd.reversi.client.ReversiBoard;
import com.rd.reversi.client.ReversiBoardUtils;
import com.rd.reversi.client.ReversiClientStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 26/08/12
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public class LookAheadStrategy implements ReversiClientStrategy {

    private final BoardScorer scorer = new BoardScorer();
    private final ReversiRules rules = new ReversiRulesImpl();
    private ReversiBoardImpl board;
    private Player player;
    private int lookAheadLevel = 2;

    public LookAheadStrategy() {
    }

    public LookAheadStrategy(int lookAheadLevel) {
        this.lookAheadLevel = lookAheadLevel;
    }

    public void onGameInitialised(ReversiBoard reversiBoard, Player player, String s, int i, byte[] bytes, GameLogger gameLogger) {
        this.onGameInitialised(reversiBoard, player);
    }

    public void onGameInitialised(ReversiBoard reversiBoard, Player player, String s, int i, byte[] bytes) {
        this.onGameInitialised(reversiBoard, player);
    }

    public void onGameInitialised(ReversiBoard reversiBoard, Player player) {
        this.board = new ReversiBoardImpl(reversiBoard);
        this.player = player;
    }

    @Override
    public Position getNextMove() {
        MoveValuation moveValuation = lookAhead(board, player, lookAheadLevel, new ArrayList<Move>());
        if (moveValuation.two.isEmpty()) {
            throw new RuntimeException("this should not happen");
        }
        Move move = moveValuation.two.get(0);
        return new Position(move.x, move.y);
    }

    public void onMovePlayed(ReversiBoard reversiBoard, Player player) {
        this.board = new ReversiBoardImpl(reversiBoard);
    }

    public void onMovePlayed(ReversiBoard reversiBoard, Player player, Position position) {
        this.board = new ReversiBoardImpl(reversiBoard);
    }

    @Override
    public byte[] onGameEnded(ReversiBoard reversiBoard, Player player) {
        return new byte[0];
    }

    static class MoveValuation extends Pair<Integer, List<Move>> {
        public MoveValuation(Integer one, List<Move> two) {
            super(one, two);
        }
    }

    private MoveValuation lookAhead(ReversiBoard board, Player player, int level, List<Move> history) {
        List<Position> possibleMoves = ReversiBoardUtils.getValidMoves(board, player);
        if (level < 0 || possibleMoves.isEmpty()) {
            return new MoveValuation(scorer.score(board, player), history);
        }

        MoveValuation bestScore = new MoveValuation(Integer.MIN_VALUE, new ArrayList<Move>());

        for (Position position : possibleMoves) {
            ReversiBoardImpl boardCopy = (ReversiBoardImpl)board.copy();
            List<Move> historyCopy = new ArrayList<Move>(history);
            historyCopy.add(new Move(position.getX(), position.getY(), player));
            rules.applyMove(boardCopy, player, position);

            MoveValuation moveScore = lookAhead(boardCopy, Utils.getOpponent(player), level - 1, historyCopy);
            moveScore.one *= -1;
            if (moveScore.one >= bestScore.one) {
                bestScore = moveScore;
            }
        }
        return bestScore;
    }

}
