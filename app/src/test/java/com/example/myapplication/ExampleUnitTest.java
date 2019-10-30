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
}