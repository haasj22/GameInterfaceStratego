package com.example.myapplication.Stratego;

import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.Game.GameMainActivity;
import com.example.myapplication.Game.GamePlayer;
import com.example.myapplication.R;

public class RulesSwitch extends XMLObject{
    private rulesButtons rulesButtons;
    private ImageView display;
    private GameMainActivity activity;
    private GamePlayer player;

    private static int counter = 0;
    private final static int MAX_SLIDES = 2;

    public RulesSwitch(View v, rulesButtons rulesButtons, ImageView display, GameMainActivity activity, GamePlayer player){
        super(v);
        this.rulesButtons = rulesButtons;
        this.display = display;
        this.activity = activity;
        this.player = player;
        this.getXmlObject().setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(this.rulesButtons.ordinal() == 0){
            if(counter < MAX_SLIDES - 1){
                counter++;
            }
            else{
                counter = 0;
                this.player.setAsGui(activity);
            }
        }
        else if(this.rulesButtons.ordinal() == 1){
            if(counter > 0){
                counter--;
            }
            else{
                counter = 0;
                this.player.setAsGui(activity);
            }
        }
        else{
            counter = 0;
            this.player.setAsGui(activity);
        }
        switch(counter){
            case 0:
                this.display.setImageResource(R.drawable.rules_one);
                break;
            case 1:
                this.display.setImageResource(R.drawable.rules_two);
                break;
        }
    }
}
