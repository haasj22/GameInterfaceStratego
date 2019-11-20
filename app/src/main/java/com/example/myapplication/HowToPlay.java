package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author Kavya Mandla
 * creates a drop down that allows you to click on different actions
 */
public class HowToPlay extends Activity {

    helpSurfaceView helpSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stratego_rules);



    }
}
