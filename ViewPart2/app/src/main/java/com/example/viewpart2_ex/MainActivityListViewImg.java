package com.example.viewpart2_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.model.BeerAdapter;
import com.example.model.Beers;

import java.util.ArrayList;

public class MainActivityListViewImg extends AppCompatActivity {
    ListView lvBeers;
    BeerAdapter adapter;
    ArrayList<Beers> beers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view_img);

        linkView();
        loadData();
        addEvents();
    }

    private void addEvents() {
        lvBeers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Beers beer = (Beers) adapter.getItem(i);
                beers.remove(beer);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivityListViewImg.this, "Removed successfull", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void loadData() {
        // Init data
        beers = new ArrayList<Beers>();
        beers.add(new Beers(R.drawable.beer333, "Beer 333", 20000));
        beers.add(new Beers(R.drawable.heineken, "Beer Heineken", 25000));
        beers.add(new Beers(R.drawable.hanoi, "Beer HN", 30000));
        beers.add(new Beers(R.drawable.saigon, "Beer SG", 22000));
        beers.add(new Beers(R.drawable.tiger, "Beer Tiger", 27000));
        beers.add(new Beers(R.drawable.larue, "Beer Laure", 50000));

        adapter = new BeerAdapter(MainActivityListViewImg.this, R.layout.item_list, beers);
        lvBeers.setAdapter(adapter);
    }


    private void linkView() {
        lvBeers = findViewById(R.id.lvBeers);
    }
}