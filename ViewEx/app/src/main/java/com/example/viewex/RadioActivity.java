package com.example.viewex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;

public class RadioActivity extends AppCompatActivity {
    RadioButton radio1, radio2, radio3, radio4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        linkViews();
        addEvents();
    }

    private void addEvents() {
    }

    private void linkViews() {

    }
}