package com.example.myapplication;

import com.example.myapplication.Game.infoMsg.GameState;
import com.example.myapplication.Stratego.GameState.Piece;
import com.example.myapplication.Stratego.GameState.Rank;
import com.example.myapplication.Stratego.GameState.StrategoGameState;
import com.example.myapplication.Stratego.GameState.Team;
import com.example.myapplication.Stratego.StrategoFrameworkClasses.StrategoMainActivity;

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

    @Test
    public void pieceMovable() {
        Piece testPiece1 = new Piece(Team.RED_TEAM, Rank.BOMB);
        Piece testPiece2 = new Piece(Team.RED_TEAM, Rank.FLAG);
        Piece testPiece3 = new Piece(Team.RED_TEAM, Rank.EIGHT);

        assertEquals(false, testPiece1.getPieceRank().isPieceMovable());
        assertEquals(false, testPiece2.getPieceRank().isPieceMovable());
        assertEquals(true, testPiece3.getPieceRank().isPieceMovable());
    }

    @Test
    public void scoutHighlightsWork() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.NINE), 6, 3);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SEVEN), 3, 7);
        testGameState.transitionPhases();
        testGameState.tapOnSquare(6,3);
        assertEquals(false, testGameState.getBoard()[6][4].isHighLighted());
        assertEquals(false, testGameState.getBoard()[6][2].isHighLighted());
        assertEquals(false, testGameState.getBoard()[7][4].isHighLighted());
        assertEquals(true, testGameState.getBoard()[5][3].isHighLighted());
        assertEquals(true, testGameState.getBoard()[4][3].isHighLighted());
        assertEquals(true, testGameState.getBoard()[3][3].isHighLighted());
        assertEquals(false, testGameState.getBoard()[2][3].isHighLighted());
        testGameState.tapOnSquare(4,3);
        testGameState.tapOnSquare(3,7);
        testGameState.tapOnSquare(4,7);
        testGameState.tapOnSquare(4,3);
        assertEquals(false, testGameState.getBoard()[4][3].isHighLighted());
        assertEquals(true, testGameState.getBoard()[5][3].isHighLighted());
        assertEquals(true, testGameState.getBoard()[6][3].isHighLighted());
        assertEquals(false, testGameState.getBoard()[7][3].isHighLighted());
        assertEquals(true, testGameState.getBoard()[3][3].isHighLighted());
        assertEquals(false, testGameState.getBoard()[2][3].isHighLighted());
        assertEquals(false, testGameState.getBoard()[4][4].isHighLighted());
        assertEquals(true, testGameState.getBoard()[4][2].isHighLighted());
        assertEquals(false, testGameState.getBoard()[2][3].isHighLighted());
    }

    @Test
    public void normalHighlightWorks() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SEVEN), 6, 3);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SIX), 3, 7);
        testGameState.transitionPhases();
        testGameState.tapOnSquare(6,3);
        assertEquals(true, testGameState.getBoard()[5][3].isHighLighted());
        assertEquals(false, testGameState.getBoard()[4][3].isHighLighted());
        assertEquals(false, testGameState.getBoard()[7][3].isHighLighted());
        assertEquals(false, testGameState.getBoard()[6][4].isHighLighted());
        assertEquals(false, testGameState.getBoard()[6][2].isHighLighted());
        testGameState.tapOnSquare(5, 3);
        testGameState.tapOnSquare(3,7);
        testGameState.tapOnSquare(4, 7);
        testGameState.tapOnSquare(5, 3);
        assertEquals(true, testGameState.getBoard()[4][3].isHighLighted());
        assertEquals(true, testGameState.getBoard()[6][3].isHighLighted());
        assertEquals(true, testGameState.getBoard()[5][2].isHighLighted());
        assertEquals(false, testGameState.getBoard()[5][4].isHighLighted());
        assertEquals(false, testGameState.getBoard()[3][3].isHighLighted());
    }

    @Test
    public void setupTapping() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.setLastTappedPieceButton(Rank.BOMB);
        assertEquals(Rank.BOMB, testGameState.getLastTappedPieceButton());
        assertEquals(-1, testGameState.getLastTappedRow());
        assertEquals(-1, testGameState.getLastTappedCol());
        assertEquals(false, testGameState.isDidLastBlockContainPiece());
        testGameState.tapOnSquare(8,8);
        assertEquals(8, testGameState.getLastTappedRow());
        assertEquals(8, testGameState.getLastTappedCol());
        assertEquals(false, testGameState.isDidLastBlockContainPiece());
        testGameState.tapOnSquare(8,8);
        assertEquals(true, testGameState.getBoard()[8][8].getContainedPiece() != null
                && testGameState.getBoard()[8][8].getContainedPiece().getPieceRank() == Rank.BOMB);
    }


}