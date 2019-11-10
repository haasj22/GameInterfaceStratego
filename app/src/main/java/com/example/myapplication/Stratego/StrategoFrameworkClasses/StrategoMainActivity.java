package com.example.myapplication.Stratego.StrategoFrameworkClasses;

import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.Game.LocalGame;
import com.example.myapplication.Game.gameConfiguration.GameConfig;
import com.example.myapplication.Game.gameConfiguration.GamePlayerType;
import com.example.myapplication.R;
import com.example.myapplication.Stratego.GameState.DumbComputerPlayer;
import com.example.myapplication.Stratego.GameState.SmartComputerPlayer;
import com.example.myapplication.Stratego.Player.StrategoHumanPlayer;

import java.util.ArrayList;

public class StrategoMainActivity extends GameMainActivity {

    //Tag for logging
    private static final String TAG = "Stratego Main Activity";
    public static final int PORT_NUMBER = 5213;

    /**
     * a stratego game for two players. The default is human vs. computer
     *
     * @return the default configuration for a stratego game
     */
    @Override
    public GameConfig createDefaultConfig() {

        //Define the allowed player types
        ArrayList<GamePlayerType> playerTypes= new ArrayList<GamePlayerType>();

        //human player's view
        playerTypes.add(new GamePlayerType("Local Human Player (red-team)") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new StrategoHumanPlayer(name);
            }
        });

        //dumb computer player
        playerTypes.add(new GamePlayerType("ComputerPlayer (dumb)") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new DumbComputerPlayer(name);
            }
        });

        //smart computer player
        playerTypes.add(new GamePlayerType("ComputerPlayer (smart)") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new SmartComputerPlayer(name);
            }
        });

        //create a default two player configuration for stratego
        GameConfig defaultConfig =
                new GameConfig(playerTypes, 2, 2, "Stratego", PORT_NUMBER);

        defaultConfig.addPlayer("Human", 0); //human player
        defaultConfig.addPlayer("Computer", 1); //dumb computer player

        //sets the initial configuration for the remote player
        defaultConfig.setRemoteData("RemotePlayer", "ipCode", 0);

        return defaultConfig;
    }

    /**
     * createLocalGame
     *
     * Creates a new game that runs on the server tablet,
     *
     * @return a new, game-specific instance of the LocalGame class
     */
    @Override
    public LocalGame createLocalGame() { return new StrategoLocalGame();}
}
