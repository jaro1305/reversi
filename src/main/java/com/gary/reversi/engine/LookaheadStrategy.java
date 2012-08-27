package com.gary.reversi.engine;

import com.rd.game.common.Player;
import com.rd.reversi.client.Position;
import com.rd.reversi.client.ReversiBoard;
import com.rd.reversi.client.ReversiBoardUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alutek
 * Date: 26/08/12
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public class LookaheadStrategy {

    public static void main(String[] args) {
        new LookaheadStrategy().foo();
    }

    private final BoardScorer scorer = new BoardScorer();
    private final ReversiRules rules = new ReversiRulesImpl();

    public void foo() {
        ReversiBoard board = new ReversiBoardImpl(8);
        Player player = Player.PLAYER_ONE;
        MoveValuation bestScore = bar(board, player, 4, new ArrayList<Move>());
        System.out.println("--------------------------------------------");
        System.out.println("--------------------------------------------");
        System.out.println("--------------------------------------------");
        System.out.println(bestScore.one + ", " + bestScore.two);
    }

    private void postLeaf(ReversiBoard board, List<Move> moveHistory) {
        int score = scorer.score(board, Player.PLAYER_ONE);
        System.out.printf("found leaf score = %d, board=\n%s\n", score, board.toString());

        for (Move move : moveHistory) {
            System.out.printf("\tmove : %s %d, %d\n", move.player, move.x, move.y);
        }
    }

    static class MoveValuation extends Pair<Integer, List<Move>> {
        public MoveValuation(Integer one, List<Move> two) {
            super(one, two);
        }
    }

    private MoveValuation bar(ReversiBoard board, Player player, int level, List<Move> history) {
        List<Position> possibleMoves = ReversiBoardUtils.getValidMoves(board, player);
        if (level < 0 || possibleMoves.isEmpty()) {
            System.out.printf("leaf found\n");
            postLeaf(board, history);
            return new MoveValuation(scorer.score(board, player), history);
        }

        System.out.printf("----------------------------\n");
        System.out.printf("inspecting board for player %s\n", player);

        MoveValuation bestScore = new MoveValuation(Integer.MIN_VALUE, new ArrayList<Move>());

        for (Position position : possibleMoves) {
            ReversiBoardImpl boardCopy = (ReversiBoardImpl)board.copy();
            List<Move> historyCopy = new ArrayList<Move>(history);
            historyCopy.add(new Move(position.getX(), position.getY(), player));
            rules.applyMove(boardCopy, player, position);

            MoveValuation moveScore = bar(boardCopy, Utils.getOpponent(player), level - 1, historyCopy);
            System.out.printf("checking move : %d, %d\n%s\n", position.getX(), position.getY(), boardCopy);
            moveScore.one *= -1;
            if (moveScore.one > bestScore.one) {
                bestScore = moveScore;
            }
        }
        return bestScore;
    }

}