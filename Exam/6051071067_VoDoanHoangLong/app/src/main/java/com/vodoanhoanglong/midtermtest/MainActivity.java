package com.vodoanhoanglong.midtermtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton btnLunch, btnNoodle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        addEvents();
    }

    private void addEvents() {
        btnLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListViewLunch.class);
                intent.putExtra("type", "price");

                startActivity(intent);
            }
        });

        btnNoodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListViewLunch.class);
                intent.putExtra("type", "noodle");

                startActivity(intent);
            }
        });
    }

    private void linkViews() {
        btnLunch = findViewById(R.id.btnLunch);
        btnNoodle = findViewById(R.id.btnNoodle);
    }
}