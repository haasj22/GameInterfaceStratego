package com.example.myapplication;

import static org.junit.Assert.*;

import com.example.myapplication.Game.Game;
import com.example.myapplication.Stratego.GameState.Block;
import com.example.myapplication.Stratego.GameState.Piece;
import com.example.myapplication.Stratego.GameState.Rank;
import com.example.myapplication.Stratego.GameState.StrategoGameState;
import com.example.myapplication.Stratego.GameState.Team;


import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

/**
 * Tests the copy constructor for every class in the game state
 *
 * @author Jordan Ho
 * @author Kavya Mandla
 */

public class CopyConstructorTest {

    private StrategoGameState state;

    @BeforeClass
    public static void setup(){



    }


    @Test
    public void isBoardFull(){
        StrategoGameState testGameState =
    }
    @Test
    public void calculateNumberOfPiece(){
        StrategoGameState testGameState = new StrategoGameState();

    }
    //written by Kavya Mandla
    @Test
    public void getLastTappedButton(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.ONE), 6,7);
        testGameState.tapOnSquareSETUP(6,7);
        assertEquals(Rank.ONE,testGameState.getPieceAt(6,7).getPieceRank());
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.FIVE),3,7);
        testGameState.tapOnSquarePLAY(3,7);
        testGameState.getLastTappedPieceButton();
        assertEquals(Rank.ONE,testGameState.getLastTappedPieceButton());




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

    //wriiten by Kavya Mandla
    //teste rank One to 3
    @Test
    public void attackOneToThree(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.ONE), 6, 7);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),
                Rank.THREE),3,7);
        testGameState.transitionPhases();
        assertEquals(Rank.ONE, testGameState.getPieceAt(6,7).getPieceRank());
        assertEquals(Rank.THREE,testGameState.getPieceAt(3,7).getPieceRank());
        testGameState.tapOnSquarePLAY(6,7);
        testGameState.tapOnSquarePLAY(5,7);
        testGameState.tapOnSquarePLAY(3,7);
        testGameState.tapOnSquarePLAY(4,7);
        testGameState.tapOnSquarePLAY(5,7);
        testGameState.tapOnSquarePLAY(4,7);
        assertEquals(Rank.ONE,testGameState.getPieceAt(4,7).getPieceRank());


    }



    //Kavya Mandla
    @Test
    public void copyConstructor(){
        StrategoGameState testGameState = new StrategoGameState();
        StrategoGameState testGameState2 = new StrategoGameState();

        assertEquals(testGameState.toString(), testGameState2.toString());
    }




    // written by Jordan Ho
    @Test
    public void movePiece(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.ONE),
                6,7);
        testGameState.tapOnSquare(6,7);
        testGameState.tapOnSquare(6,6);
        assertEquals(testGameState.getPieceAt(6,7), null);
        assertEquals(testGameState.getPieceAt(6,6).getPieceRank(), Rank.ONE);
    }

    // written by Jordan Ho
    @Test
    public void attackPiece(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.EIGHT),
                6,7);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.TWO),
                3,7);
        testGameState.transitionPhases();
        assertEquals(Rank.EIGHT, testGameState.getPieceAt(6,7).getPieceRank());
        assertEquals(Rank.TWO, testGameState.getPieceAt(3,7).getPieceRank());
        testGameState.tapOnSquare(6,7);
        testGameState.tapOnSquare(5,7);
        testGameState.tapOnSquare(3,7);
        testGameState.tapOnSquare(4,7);
        testGameState.tapOnSquare(5,7);
        testGameState.tapOnSquare(4,7);
        assertEquals(Rank.TWO, testGameState.getPieceAt(4,7).getPieceRank());

    }

    // written by Jordan Ho
    @Test
    public void scoutAttack(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.NINE),
                6,7);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.SPY),
                3,7);
        testGameState.transitionPhases();
        assertEquals(Rank.NINE, testGameState.getPieceAt(6,7).getPieceRank());
        assertEquals(Rank.SPY, testGameState.getPieceAt(3,7).getPieceRank());
        testGameState.tapOnSquare(6,7);
        testGameState.tapOnSquare(5,7);
        testGameState.tapOnSquare(3,7);
        testGameState.tapOnSquare(4,7);
        testGameState.tapOnSquare(5,7);
        testGameState.tapOnSquare(4,7);
        assertEquals(Rank.NINE, testGameState.getPieceAt(4,7).getPieceRank());
    }

    // written by Jordan Ho
    @Test
    public void spyAttack(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.SPY),
                6,7);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.ONE),
                3,7);
        testGameState.transitionPhases();
        assertEquals(Rank.SPY, testGameState.getPieceAt(6,7).getPieceRank());
        assertEquals(Rank.ONE, testGameState.getPieceAt(3,7).getPieceRank());
        testGameState.tapOnSquare(6,7);
        testGameState.tapOnSquare(5,7);
        testGameState.tapOnSquare(3,7);
        testGameState.tapOnSquare(4,7);
        testGameState.tapOnSquare(5,7);
        testGameState.tapOnSquare(4,7);
        assertEquals(Rank.SPY, testGameState.getPieceAt(4,7).getPieceRank());
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
                6,7);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.BOMB),
                3,7);
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SPY),
                2, 8);
        testGameState.transitionPhases();
        assertEquals(Rank.EIGHT, testGameState.getPieceAt(6,7).getPieceRank());
        assertEquals(Rank.BOMB, testGameState.getPieceAt(3,7).getPieceRank());
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
        assertEquals(Rank.EIGHT, testGameState.getPieceAt(3,7).getPieceRank());
    }

    // written by Jordan Ho
    @Test
    public void captureFlag(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.SIX),
                6,7);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.FLAG),
                3,7);
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.SPY),
                2, 8);
        testGameState.transitionPhases();
        assertEquals(Rank.SIX, testGameState.getPieceAt(6,7).getPieceRank());
        assertEquals(Rank.FLAG, testGameState.getPieceAt(3,7).getPieceRank());
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
        assertEquals(Rank.SIX, testGameState.getPieceAt(3,7).getPieceRank());
    }

}
