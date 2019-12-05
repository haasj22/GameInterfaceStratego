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

public class GameStateUnitTest {
    //John Haas wrote this test
    @Test
    public void canAddFlagToTheGame() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.FLAG), 6, 7);
        assertEquals(true, testGameState.getIsRedTeamHasFlag());
    }

    //John Haas wrote this test
    @Test
    public void gameCheckOver() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.NINE), 6, 9);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.FLAG), 3, 9);
        testGameState.transitionPhases();
        assertEquals(0, testGameState.isGameOver());
        testGameState.tapOnSquare(6, 9);
        testGameState.tapOnSquare(3,9);
        assertEquals(1, testGameState.isGameOver());
    }

    //John Haas wrote this
    @Test
    public void visibleWorking() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.NINE), 6, 9);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.ONE), 3, 9);
        testGameState.transitionPhases();
        assertEquals(false, testGameState.getPieceAt(3, 9).getIsVisible());
        assertEquals(Rank.NINE, testGameState.getPieceAt(6,9).getPieceRank());
        assertEquals(Rank.ONE, testGameState.getPieceAt(3,9).getPieceRank());
        testGameState.tapOnSquare(6, 9);
        testGameState.tapOnSquare(3,9);
        assertEquals(true, testGameState.getPieceAt(3, 9).getIsVisible());
        assertEquals(Rank.ONE, testGameState.getPieceAt(3,9).getPieceRank());
        assertEquals(null, testGameState.getPieceAt(6,9));
    }

    //John Haas wrote this unit test
    @Test
    public void bombWorking() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.EIGHT), 6, 1);
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.BOMB), 6, 9);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.BOMB), 3, 1);
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SEVEN), 3, 9);
        testGameState.transitionPhases();
        testGameState.tapOnSquare(6,1);
        testGameState.tapOnSquare(5,1);
        testGameState.tapOnSquare(3,9);
        testGameState.tapOnSquare(4, 9);
        testGameState.tapOnSquare(5,1);
        testGameState.tapOnSquare(4,1);
        testGameState.tapOnSquare(4,9);
        testGameState.tapOnSquare(5,9);
        testGameState.tapOnSquare(4,1);
        testGameState.tapOnSquare(3,1);
        testGameState.tapOnSquare(5,9);
        testGameState.tapOnSquare(6,9);
        assertEquals(testGameState.getPieceAt(6,9), null);
        assertEquals(testGameState.getPieceAt(3, 1).getPieceRank(), Rank.EIGHT);
    }

    //John Haas wrote this test
    @Test
    public void forfeitWorks(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.transitionPhases();
        testGameState.transitionPhases();
        testGameState.forfeitGame();
        assertEquals(-1, testGameState.isGameOver());
    }

    //John Haas wrote this test
    @Test
    public void spyAttacks() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SPY), 6, 9);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.ONE), 3, 9);
        testGameState.transitionPhases();
        assertEquals(Rank.SPY, testGameState.getPieceAt(6,9).getPieceRank());
        assertEquals(Rank.ONE, testGameState.getPieceAt(3,9).getPieceRank());
        testGameState.tapOnSquare(6, 9);
        testGameState.tapOnSquare(5, 9);
        testGameState.tapOnSquare(3,9);
        testGameState.tapOnSquare(4,9);
        testGameState.tapOnSquare(5,9);
        testGameState.tapOnSquare(4,9);
        assertEquals(Rank.SPY, testGameState.getPieceAt(4, 9).getPieceRank());
    }

    //John Haas wrote this test
    @Test
    public void pieceMovable() {
        Piece testPiece1 = new Piece(Team.RED_TEAM, Rank.BOMB);
        Piece testPiece2 = new Piece(Team.RED_TEAM, Rank.FLAG);
        Piece testPiece3 = new Piece(Team.RED_TEAM, Rank.EIGHT);

        assertEquals(false, testPiece1.getPieceRank().isPieceMovable());
        assertEquals(false, testPiece2.getPieceRank().isPieceMovable());
        assertEquals(true, testPiece3.getPieceRank().isPieceMovable());
    }

    //John Haas wrote this test
    @Test
    public void scoutHighlightsWork() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.NINE), 6, 1);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SEVEN), 3, 9);
        testGameState.transitionPhases();
        testGameState.tapOnSquare(6,1);
        assertEquals(false, testGameState.getBoard()[6][2].isHighLighted());
        assertEquals(false, testGameState.getBoard()[6][0].isHighLighted());
        assertEquals(false, testGameState.getBoard()[7][1].isHighLighted());
        assertEquals(true, testGameState.getBoard()[5][1].isHighLighted());
        assertEquals(true, testGameState.getBoard()[4][1].isHighLighted());
        assertEquals(true, testGameState.getBoard()[3][1].isHighLighted());
        assertEquals(false, testGameState.getBoard()[2][1].isHighLighted());
        testGameState.tapOnSquare(4,1);
        testGameState.tapOnSquare(3,9);
        testGameState.tapOnSquare(4,9);
        testGameState.tapOnSquare(4,1);
        assertEquals(false, testGameState.getBoard()[4][1].isHighLighted());
        assertEquals(true, testGameState.getBoard()[5][1].isHighLighted());
        assertEquals(true, testGameState.getBoard()[6][1].isHighLighted());
        assertEquals(false, testGameState.getBoard()[7][1].isHighLighted());
        assertEquals(true, testGameState.getBoard()[3][1].isHighLighted());
        assertEquals(false, testGameState.getBoard()[2][1].isHighLighted());
        assertEquals(false, testGameState.getBoard()[4][2].isHighLighted());
        assertEquals(true, testGameState.getBoard()[4][0].isHighLighted());
        assertEquals(false, testGameState.getBoard()[2][1].isHighLighted());
    }

    //John Haas wrote this test
    @Test
    public void normalHighlightWorks() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SEVEN), 6, 1);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SIX), 3, 8);
        testGameState.transitionPhases();
        testGameState.tapOnSquare(6,1);
        assertEquals(true, testGameState.getBoard()[5][1].isHighLighted());
        assertEquals(false, testGameState.getBoard()[4][1].isHighLighted());
        assertEquals(false, testGameState.getBoard()[7][1].isHighLighted());
        assertEquals(false, testGameState.getBoard()[6][2].isHighLighted());
        assertEquals(false, testGameState.getBoard()[6][0].isHighLighted());
        testGameState.tapOnSquare(5, 1);
        testGameState.tapOnSquare(3,8);
        testGameState.tapOnSquare(4, 8);
        testGameState.tapOnSquare(5, 1);
        assertEquals(true, testGameState.getBoard()[4][1].isHighLighted());
        assertEquals(true, testGameState.getBoard()[6][1].isHighLighted());
        assertEquals(true, testGameState.getBoard()[5][0].isHighLighted());
        assertEquals(false, testGameState.getBoard()[5][2].isHighLighted());
        assertEquals(false, testGameState.getBoard()[3][1].isHighLighted());
    }

    //John Haas wrote this test
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



    //written by Kavya Mandla
    @Test
    public void copyConstructor(){
        StrategoGameState testGameState = new StrategoGameState();
        StrategoGameState testGameState2 = new StrategoGameState();
        assertEquals(testGameState.toString(), testGameState2.toString());
    }


    //written by Kavya Mandla
    @Test
    public void playTapping(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.setLastTappedPieceButton(Rank.NINE);
        assertEquals(Rank.NINE, testGameState.getLastTappedPieceButton());
        testGameState.tapOnSquareSETUP(6,7);
        assertEquals(6,testGameState.getLastTappedRow());
        assertEquals(7,testGameState.getLastTappedCol());

    }



    //written by Kavya Mandla
    @Test
    public void isRedBoardFull(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.randomizeRemainingPieces();
        testGameState.transitionPhases();
        assertTrue(testGameState.isBoardFull(Team.RED_TEAM));
    }


    //written by Kavya Mandla
    //checks to see if lastTappedPieceButton is correct
    @Test
    public void getLastTappedButton(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.ONE), 6,7);
        testGameState.tapOnSquareSETUP(6,7);
        testGameState.setLastTappedPieceButton(Rank.ONE);
        assertEquals(Rank.ONE, testGameState.getLastTappedPieceButton());

        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.NINE), 8,7);
        testGameState.tapOnSquareSETUP(8,7);
        testGameState.setLastTappedPieceButton(Rank.NINE);
        assertEquals(Rank.NINE, testGameState.getLastTappedPieceButton());

        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.BOMB), 4,7);
        testGameState.tapOnSquareSETUP(4,7);
        testGameState.setLastTappedPieceButton(Rank.BOMB);
        assertEquals(Rank.BOMB, testGameState.getLastTappedPieceButton());

        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.FLAG), 3,2);
        testGameState.tapOnSquareSETUP(3,2);
        testGameState.setLastTappedPieceButton(Rank.FLAG);
        assertEquals(Rank.FLAG, testGameState.getLastTappedPieceButton());
    }

    //written by Kavya Mandla
    //checking to see if pieces that are placed remain when remaining are randomized
    @Test
    public void randomizeRemainingPieces(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.ONE),6,7);
        testGameState.randomizeRemainingPieces();
        testGameState.transitionPhases();
        assertEquals(Rank.ONE, testGameState.getPieceAt(6,7).getPieceRank());
    }

    //written by Kavya
    //if marshall attacks spy, spy loses and marshall remains
    @Test
    public void marshallAttacks(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.ONE), 6, 8);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.SPY), 3, 8);
        testGameState.transitionPhases();
        assertEquals(Rank.ONE, testGameState.getPieceAt(6, 8).getPieceRank());
        assertEquals(Rank.SPY, testGameState.getPieceAt(3, 8).getPieceRank());
        testGameState.tapOnSquarePLAY(6, 8);
        testGameState.tapOnSquarePLAY(5, 8);
        testGameState.tapOnSquarePLAY(3, 8);
        testGameState.tapOnSquarePLAY(4, 8);
        testGameState.tapOnSquarePLAY(5, 8);
        testGameState.tapOnSquarePLAY(4, 8);
        assertEquals(Rank.ONE, testGameState.getPieceAt(4, 8).getPieceRank());
    }
    //wriiten by Kavya Mandla
    //teste rank One to 3
    @Test
    public void attackOneToThree() {
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.ONE), 6, 8);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.THREE), 3, 8);
        testGameState.transitionPhases();
        assertEquals(Rank.ONE, testGameState.getPieceAt(6, 8).getPieceRank());
        assertEquals(Rank.THREE, testGameState.getPieceAt(3, 8).getPieceRank());
        testGameState.tapOnSquarePLAY(6, 8);
        testGameState.tapOnSquarePLAY(5, 8);
        testGameState.tapOnSquarePLAY(3, 8);
        testGameState.tapOnSquarePLAY(4, 8);
        testGameState.tapOnSquarePLAY(5, 8);
        testGameState.tapOnSquarePLAY(4, 8);
        assertEquals(Rank.ONE, testGameState.getPieceAt(4, 8).getPieceRank());
    }


    //written by Kavya Mandla
    @Test
    public void attackFiveToThree(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.FIVE), 6,8);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.THREE),3,8);
        assertEquals(Rank.FIVE, testGameState.getPieceAt(6,8).getPieceRank());
        assertEquals(Rank.THREE, testGameState.getPieceAt(3,8).getPieceRank());
        testGameState.tapOnSquarePLAY(6, 8);
        testGameState.tapOnSquarePLAY(5, 8);
        testGameState.tapOnSquarePLAY(3, 8);
        testGameState.tapOnSquarePLAY(4, 8);
        testGameState.tapOnSquarePLAY(5, 8);
        testGameState.tapOnSquarePLAY(4, 8);
        assertEquals(Rank.THREE, testGameState.getPieceAt(4,8).getPieceRank());

    }
    //written by Kavya Mandla
    //checks to see if 5 loses to 4
    @Test
    public void attackFiveToFour(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.FIVE),6,8);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.FOUR), 3,8);
        testGameState.transitionPhases();
        assertEquals(Rank.FIVE,testGameState.getPieceAt(6,8).getPieceRank());
        assertEquals(Rank.FOUR,testGameState.getPieceAt(3,8).getPieceRank());
        testGameState.tapOnSquarePLAY(6, 8);
        testGameState.tapOnSquarePLAY(5, 8);
        testGameState.tapOnSquarePLAY(3, 8);
        testGameState.tapOnSquarePLAY(4, 8);
        testGameState.tapOnSquarePLAY(5, 8);
        testGameState.tapOnSquarePLAY(4, 8);
        assertEquals(Rank.FOUR, testGameState.getPieceAt(4,8).getPieceRank());

    }


    // written by Jordan Ho
    @Test
    public void movePiece(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.ONE),
                6,7);
        testGameState.tapOnSquare(6,7);
        testGameState.tapOnSquare(6,6);
        assertNull(testGameState.getPieceAt(6, 7));
        assertEquals(testGameState.getPieceAt(6,6).getPieceRank(), Rank.ONE);
    }

    // written by Jordan Ho
    @Test
    public void attackPiece(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.EIGHT),
                6,8);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.TWO),
                3,8);
        testGameState.transitionPhases();
        assertEquals(Rank.EIGHT, testGameState.getPieceAt(6,8).getPieceRank());
        assertEquals(Rank.TWO, testGameState.getPieceAt(3,8).getPieceRank());
        testGameState.tapOnSquare(6,8);
        testGameState.tapOnSquare(5,8);
        testGameState.tapOnSquare(3,8);
        testGameState.tapOnSquare(4,8);
        testGameState.tapOnSquare(5,8);
        testGameState.tapOnSquare(4,8);
        assertEquals(Rank.TWO, testGameState.getPieceAt(4,8).getPieceRank());

    }

    // written by Jordan Ho
    @Test
    public void scoutAttack(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.NINE),
                6,8);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.SPY),
                3,8);
        testGameState.transitionPhases();
        assertEquals(Rank.NINE, testGameState.getPieceAt(6,8).getPieceRank());
        assertEquals(Rank.SPY, testGameState.getPieceAt(3,8).getPieceRank());
        testGameState.tapOnSquare(6,8);
        testGameState.tapOnSquare(5,8);
        testGameState.tapOnSquare(3,8);
        testGameState.tapOnSquare(4,8);
        testGameState.tapOnSquare(5,8);
        testGameState.tapOnSquare(4,8);
        assertEquals(Rank.NINE, testGameState.getPieceAt(4,8).getPieceRank());
    }

    // written by Jordan Ho
    @Test
    public void spyAttack(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.SPY),
                6,8);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.ONE),
                3,8);
        testGameState.transitionPhases();
        assertEquals(Rank.SPY, testGameState.getPieceAt(6,8).getPieceRank());
        assertEquals(Rank.ONE, testGameState.getPieceAt(3,8).getPieceRank());
        testGameState.tapOnSquare(6,8);
        testGameState.tapOnSquare(5,8);
        testGameState.tapOnSquare(3,8);
        testGameState.tapOnSquare(4,8);
        testGameState.tapOnSquare(5,8);
        testGameState.tapOnSquare(4,8);
        assertEquals(Rank.SPY, testGameState.getPieceAt(4,8).getPieceRank());
    }

    // written by Jordan Ho
    @Test
    public void attackBomb(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.SIX),
                6,7);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.BOMB),
                3,7);
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SPY),
                2, 8);
        testGameState.transitionPhases();
        assertEquals(Rank.SIX, testGameState.getPieceAt(6,7).getPieceRank());
        assertEquals(Rank.BOMB, testGameState.getPieceAt(3,7).getPieceRank());
        assertEquals(Rank.SPY, testGameState.getPieceAt(2,8).getPieceRank());
        testGameState.tapOnSquare(6,7);
        testGameState.tapOnSquare(5,7);
        testGameState.tapOnSquare(2,8);
        testGameState.tapOnSquare(2,7);
        testGameState.tapOnSquare(5,7);
        testGameState.tapOnSquare(4,7);
        testGameState.tapOnSquare(2,7);
        testGameState.tapOnSquare(3,7);
        testGameState.tapOnSquare(4,7);
        testGameState.tapOnSquare(3,7);
        assertEquals(Rank.BOMB, testGameState.getPieceAt(3,7).getPieceRank());
    }

    // written by Jordan Ho
    @Test
    public void minerBomb(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.EIGHT),
                6,8);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.BOMB),
                3,8);
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SPY),
                3, 1);
        testGameState.transitionPhases();
        assertEquals(Rank.EIGHT, testGameState.getPieceAt(6,8).getPieceRank());
        assertEquals(Rank.BOMB, testGameState.getPieceAt(3,8).getPieceRank());
        testGameState.tapOnSquare(6,8);
        testGameState.tapOnSquare(5,8);
        testGameState.tapOnSquare(3,1);
        testGameState.tapOnSquare(4,1);
        testGameState.tapOnSquare(5,8);
        testGameState.tapOnSquare(4,8);
        testGameState.tapOnSquare(4,1);
        testGameState.tapOnSquare(5,1);
        testGameState.tapOnSquare(4,8);
        testGameState.tapOnSquare(3,8);
        assertEquals(Rank.EIGHT, testGameState.getPieceAt(3,8).getPieceRank());
    }

    // written by Jordan Ho
    @Test
    public void captureFlag(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.SIX),
                6,8);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.FLAG),
                3,8);
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SPY),
                3, 1);
        testGameState.transitionPhases();
        assertEquals(Rank.SIX, testGameState.getPieceAt(6,8).getPieceRank());
        assertEquals(Rank.FLAG, testGameState.getPieceAt(3,8).getPieceRank());
        assertEquals(Rank.SPY, testGameState.getPieceAt(3,1).getPieceRank());
        testGameState.tapOnSquare(6,8);
        testGameState.tapOnSquare(5,8);
        testGameState.tapOnSquare(3,1);
        testGameState.tapOnSquare(4,1);
        testGameState.tapOnSquare(5,8);
        testGameState.tapOnSquare(4,8);
        testGameState.tapOnSquare(4,1);
        testGameState.tapOnSquare(5,1);
        testGameState.tapOnSquare(4,8);
        testGameState.tapOnSquare(3,8);
        assertEquals(false, testGameState.getIsBlueTeamHasFlag());
    }


}