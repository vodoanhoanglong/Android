package com.example.viewpart2_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lvDrinks;
    String[] drinks = {"Coca", "Pepsi", "Sting", "Juice Orange", "Coffee", "Tiger", "Naruto", "Madara", "Milk", "Tea"};
    ArrayAdapter<String> adapter;
    String[] foods;
    EditText edtName, edtColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        loadData();
        addEvents();
    }

    private void addEvents() {
        lvDrinks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MainActivity.this, foods[i], Toast.LENGTH_SHORT).show();
            }
        });
        lvDrinks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void loadData() {
        foods = getResources().getStringArray(R.array.food_list);
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, foods);
        lvDrinks.setAdapter(adapter);
    }

    private void linkViews() {
        lvDrinks = findViewById(R.id.lvDrinks);
    }
}