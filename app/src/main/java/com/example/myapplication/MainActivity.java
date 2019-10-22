package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.Stratego.GameState.Piece;
import com.example.myapplication.Stratego.GameState.Rank;
import com.example.myapplication.Stratego.GameState.StrategoGameState;

public class MainActivity extends AppCompatActivity {
    EditText changingTextView;
    int timeClicked=0;
    StrategoGameState firstInstance;
    StrategoGameState secondInstance;
    StrategoGameState thirdInstance;
    StrategoGameState fourthInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changingTextView = (EditText)findViewById(R.id.textEditor);
        changingTextView.setMovementMethod(new ScrollingMovementMethod());

        Button testButton = (Button)findViewById(R.id.testButton);

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeClicked++;
                runTests();
            }
        });
    }

    public void runTests() {
        Log.i("Here", "clicked");
        switch(timeClicked) {
            case 1:
                firstInstance = new StrategoGameState();
                changingTextView.setText(firstInstance.toString(), TextView.BufferType.SPANNABLE);
                break;
            case 2:
                Log.i("Here", "2 Click");
                secondInstance = new StrategoGameState(firstInstance);
                changingTextView.setText(secondInstance.toString(), TextView.BufferType.SPANNABLE);
                break;
            case 3:
                Log.i("Here", "3 Click");
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.ONE), 7, 3);
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.FIVE), 9, 9);
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.SIX), 6, 7);
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.TWO), 8, 2);
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.NINE), 8, 9);
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.FOUR), 0, 0);
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.THREE), 7, 3);
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.SPY), 6, 2);
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.EIGHT), 6, 3);
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.NINE), 7, 3);
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.FIVE), 6, 6);

                changingTextView.setText(firstInstance.toString(), TextView.BufferType.SPANNABLE);
                break;
            case 4:
                firstInstance.removePieceFromGame(7, 3);
                firstInstance.movePieceDuringSetup(6, 0, 8, 9);
                firstInstance.movePieceDuringSetup(9, 9, 6, 7);
                firstInstance.movePieceDuringSetup(8, 2, 8, 4);
                changingTextView.setText(firstInstance.toString(), TextView.BufferType.SPANNABLE);
                break;
            case 5:
                firstInstance.transitionPhases();
                changingTextView.setText(firstInstance.toString(), TextView.BufferType.SPANNABLE);
                break;
            case 6:
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.ONE), 3, 2);
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.BOMB), 3, 3);
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.FLAG), 2, 3);
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.FOUR), 3, 6);
                firstInstance.addPieceToGame(new Piece(firstInstance.getCurrentTeamsTurn(), Rank.NINE), 3, 7);
                firstInstance.transitionPhases();
                changingTextView.setText(firstInstance.toString(), TextView.BufferType.SPANNABLE);
                break;
            case 7:
                firstInstance.tapOnSquare(3, 7)
        }

    }
}
