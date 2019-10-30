package com.example.myapplication.Stratego;

import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.Game.Game;
import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.R;
import com.example.myapplication.Stratego.rulesButtons;

/**
 * TODO: COMMENT
 */
public class RulesHelp extends XMLObject{
    private final GameMainActivity activity;
    private GamePlayer player;
    private Game game;

    public RulesHelp(View v, GamePlayer player, Game game, GameMainActivity activity){
        super(v);
        this.player = player;
        this.game = game;
        this.getXmlObject().setOnClickListener(this);
        this.activity = activity;
    }

    /**
     * TODO: COMMENT
     * @param v
     */
    @Override
    public void onClick(View v){
        this.activity.setContentView(R.layout.stratego_rules);

        RulesSwitch previous = new RulesSwitch(
                this.activity.findViewById(R.id.rules_previous),
                rulesButtons.PREVIOUS,
                (ImageView)this.activity.findViewById(R.id.rules_slide),
                activity, player);
        RulesSwitch next = new RulesSwitch(
                this.activity.findViewById(R.id.rules_next),
                rulesButtons.NEXT,
                (ImageView)this.activity.findViewById(R.id.rules_slide),
                activity,player);

        RulesSwitch title = new RulesSwitch(
                this.activity.findViewById(R.id.rules_next),
                rulesButtons.EXIT,
                (ImageView)this.activity.findViewById(R.id.rules_slide),
                activity,player);

        RulesSwitch exit = new RulesSwitch(
                this.activity.findViewById(R.id.rules_next),
                rulesButtons.EXIT,
                (ImageView)this.activity.findViewById(R.id.rules_slide),
                activity,player);
    }

    /**
     * TODO: COMMENT
     * @param game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * TODO: COMMENT
     * @return
     */
    public Game getGame(){
        return this.game;
    }
}
