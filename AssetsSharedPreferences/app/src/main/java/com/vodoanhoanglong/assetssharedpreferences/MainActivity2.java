package com.vodoanhoanglong.assetssharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    public static final String PREFERENCE_NAME = "temp_data";

    TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        linkViews();
    }

    private void linkViews() {
        txtContent = findViewById(R.id.txtContent);
    }

    public  void save(View view){
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("numb", 1);
        editor.putFloat("grades", 8.9f);
        editor.putBoolean("checked", true);
        editor.putString("say", "hello");

        editor.apply();
    }

    public void loadData(View view) {
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);

        int numb = preferences.getInt("numb", 0);
        float grades = preferences.getFloat("grades", 0.0f);
        boolean checked = preferences.getBoolean("checked", false);
        String say = preferences.getString("say", "");
        txtContent.append("Numb: " + numb);
        txtContent.append("\nGrades: " + grades);
        txtContent.append("\nChecked: " + checked);
        txtContent.append("\nSay: " + say);

    }
}