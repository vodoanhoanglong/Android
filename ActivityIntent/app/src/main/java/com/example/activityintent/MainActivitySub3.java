package com.example.activityintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivitySub3 extends AppCompatActivity {
    TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sub3);

        linkViews();
        getData();
        addEvents();
    }

    private void getData() {
        Intent intent = getIntent();
        int num = intent.getIntExtra("numb", 0);
        float mark = intent.getFloatExtra("mark", 0.0f);
        boolean check = intent.getBooleanExtra("check", false);
        String say = intent.getStringExtra("say");

        Product p = (Product) intent.getSerializableExtra("productInfo");


        txtContent.append("Numb: " + num + "\n");
        txtContent.append("Mark: " + mark + "\n");
        txtContent.append("Check: " + check + "\n");
        txtContent.append("Say: " +say + "\n");
        txtContent.append("Product Info: " + p.toString());
    }

    private void addEvents() {

    }

    private void linkViews() {
        txtContent = findViewById(R.id.txtContent);
    }
}