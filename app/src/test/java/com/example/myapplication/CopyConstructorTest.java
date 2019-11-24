package com.example.myapplication;

import static org.junit.Assert.*;

import com.example.myapplication.Stratego.GameState.Piece;
import com.example.myapplication.Stratego.GameState.Rank;
import com.example.myapplication.Stratego.GameState.StrategoGameState;


import org.junit.BeforeClass;
import org.junit.Test;

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
    public void movePiece(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.ONE),3,3);
        testGameState.transitionPhases();
        testGameState.tapOnSquarePLAY(3,3);
        testGameState.tapOnSquarePLAY(4,4);
        testGameState.transitionPhases();
        assertEquals(testGameState.getPieceAt(3,3), null);
        assertEquals(testGameState.getPieceAt(4,4).getPieceRank(), Rank.ONE);
    }

    @Test
    public void attackPiece(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.EIGHT), 4,4);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.TWO),4,3);
        testGameState.transitionPhases();
        testGameState.tapOnSquare(4,4);
        testGameState.tapOnSquare(4,3);
        assertEquals(Rank.TWO, testGameState.getPieceAt(4,3).getPieceRank());
    }

    @Test
    public void scoutAttack(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.EIGHT),4,4);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.SPY),4,3);
        testGameState.transitionPhases();
        testGameState.tapOnSquare(4,4);
        testGameState.tapOnSquare(4,3);


    }

    @Test
    public void spyAttack(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.SPY),4,4);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.ONE),4,3);
        testGameState.transitionPhases();
        testGameState.tapOnSquarePLAY(4,4);
        testGameState.tapOnSquarePLAY(4,3);
        testGameState.transitionPhases();
        assertEquals(testGameState.getPieceAt(4,3).getPieceRank(), Rank.SPY);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.TWO),4,4);
        testGameState.transitionPhases();
        testGameState.tapOnSquare(4,3);
        testGameState.tapOnSquare(4,4);
        assertEquals(Rank.TWO,testGameState.getPieceAt(4,4).getPieceRank());
    }

    @Test
    public void attackBomb(){
        StrategoGameState testGameState = new StrategoGameState();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(),Rank.SIX),4,4);
        testGameState.transitionPhases();
        testGameState.addPieceToGame(new Piece(testGameState.getCurrentTeamsTurn(), Rank.BOMB),4,3);
        testGameState.transitionPhases();
        testGameState.tapOnSquare(4,4);
        testGameState.tapOnSquare(4,3);
        assertEquals(testGameState.getPieceAt(4,3),null);
    }


}
