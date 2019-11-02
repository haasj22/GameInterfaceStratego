package com.example.myapplication;

import com.example.myapplication.Stratego.GameState.Piece;
import com.example.myapplication.Stratego.GameState.Rank;
import com.example.myapplication.Stratego.GameState.StrategoGameState;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void canAddFlagToTheGame() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.FLAG), 6, 7);
        assertEquals(true, testGameState.getIsRedTeamHasFlag());
    }

    @Test
    public void gameCheckOver() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.NINE), 6, 7);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.FLAG), 3, 7);
        testGameState.transitionPhases();
        assertEquals(0, testGameState.isGameOver());
        testGameState.tapOnSquarePLAY(6, 7);
        testGameState.tapOnSquarePLAY(3,7);
        assertEquals(1, testGameState.isGameOver());
    }

    @Test
    public void visibleWorking() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.NINE), 6, 7 );
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.ONE), 3, 7);
        testGameState.transitionPhases();
        assertEquals(false, testGameState.getPieceAt(3, 7).getIsVisible());
        assertEquals(Rank.NINE, testGameState.getPieceAt(6,7).getPieceRank());
        assertEquals(Rank.ONE, testGameState.getPieceAt(3,7).getPieceRank());
        testGameState.tapOnSquarePLAY(6, 7);
        testGameState.tapOnSquarePLAY(3,7);
        assertEquals(true, testGameState.getPieceAt(3, 7).getIsVisible());
        assertEquals(Rank.ONE, testGameState.getPieceAt(3,7).getPieceRank());
        assertEquals(null, testGameState.getPieceAt(6,7));
    }

    @Test
    public void bombWorking() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.EIGHT), 6, 3);
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.BOMB), 6, 7);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.BOMB), 3, 3);
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SEVEN), 3, 7);
        testGameState.transitionPhases();
        testGameState.tapOnSquarePLAY(6,3);
        testGameState.tapOnSquarePLAY(5,3);
        testGameState.tapOnSquarePLAY(3,7);
        testGameState.tapOnSquarePLAY(4, 7);
        testGameState.tapOnSquarePLAY(5,3);
        testGameState.tapOnSquarePLAY(4,3);
        testGameState.tapOnSquarePLAY(4,7);
        testGameState.tapOnSquarePLAY(5,7);
        testGameState.tapOnSquarePLAY(4,3);
        testGameState.tapOnSquarePLAY(3,3);
        testGameState.tapOnSquarePLAY(5,7);
        testGameState.tapOnSquarePLAY(6,7);
        assertEquals(testGameState.getPieceAt(6,7), null);
        assertEquals(testGameState.getPieceAt(3, 3).getPieceRank(), Rank.EIGHT);
    }

    @Test
    public void forfeitWorks(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.transitionPhases();
        testGameState.transitionPhases();
        testGameState.forfeitGame();
        assertEquals(-1, testGameState.isGameOver());
    }

    @Test
    public void spyAttacks() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SPY), 6, 7);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.ONE), 3, 7);
        testGameState.transitionPhases();
        assertEquals(Rank.SPY, testGameState.getPieceAt(6,7).getPieceRank());
        assertEquals(Rank.ONE, testGameState.getPieceAt(3,7).getPieceRank());
        testGameState.tapOnSquarePLAY(6, 7);
        testGameState.tapOnSquarePLAY(5, 7);
        testGameState.tapOnSquarePLAY(3,7);
        testGameState.tapOnSquarePLAY(4,7);
        testGameState.tapOnSquarePLAY(5,7);
        testGameState.tapOnSquarePLAY(4,7);
        assertEquals(Rank.SPY, testGameState.getPieceAt(4, 7).getPieceRank());
    }
}