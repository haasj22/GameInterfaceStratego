package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;

public class notepadActivity extends Activity {

    notepadSurfaceView notepadSurfaceView;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            notepadSurfaceView = new notepadSurfaceView(this, null);
            setContentView(notepadSurfaceView);

        }

}
