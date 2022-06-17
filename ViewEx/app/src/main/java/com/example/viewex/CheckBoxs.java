package com.example.viewex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class CheckBoxs extends AppCompatActivity {

    CheckBox chkFilm, chkFPT, chkClip;
    Button btnConfirm;
    TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        linkViews();
        addEvents();
    }

    private void addEvents() {
        btnConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String text = "Your choice: ";
                text += chkFilm.isChecked() ? chkFilm.getText() + "; " : " ";
                text += chkFPT.isChecked() ? chkFPT.getText() + "; " : " ";
                text += chkClip.isChecked() ? chkClip.getText() + "; " : " ";

                txtShow.setText(text);
            }
        });
    }

    private void linkViews() {
        chkFilm = findViewById(R.id.chkFilm);
        chkFPT = findViewById(R.id.chkFPT);
        chkClip = findViewById(R.id.chkClip);
        btnConfirm = findViewById(R.id.btnConfirm);
        txtShow = findViewById(R.id.tvYourChoice);
    }



}