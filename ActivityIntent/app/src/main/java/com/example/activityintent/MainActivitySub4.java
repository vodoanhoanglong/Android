package com.example.activityintent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivitySub4 extends AppCompatActivity {
    TextView txtPow;
    Button btnPow;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sub4);

        linkViews();
        getData();
        addEvents();
    }

    private void getData() {
        intent = getIntent();
        int numb = intent.getIntExtra("numb", 0);
        txtPow.setText(String.valueOf(numb));
    }

    private void addEvents() {
        btnPow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numb = Integer.parseInt(txtPow.getText().toString());
                double res = numb*numb;
                intent.putExtra("pow", res);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    private void linkViews() {
        txtPow = findViewById(R.id.txtNumb);
        btnPow = findViewById(R.id.btnPow);
    }
}