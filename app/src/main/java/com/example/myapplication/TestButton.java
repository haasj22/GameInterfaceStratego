package com.example.myapplication;

import android.view.View;
import android.widget.Button;

public class TestButton implements View.OnClickListener {
    int timesClicked;

    public TestButton() {
        timesClicked=0;
    }

    @Override
    public void onClick(View view) {
        timesClicked++;
    }
}
