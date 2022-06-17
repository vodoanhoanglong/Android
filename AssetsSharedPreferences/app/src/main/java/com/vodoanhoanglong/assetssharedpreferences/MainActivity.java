package com.vodoanhoanglong.assetssharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {
    ListView lvItems;
    TextView txtTitle;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        loadFonts();
        addEvents();
    }

    private void addEvents() {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/" + adapter.getItem(i));

                txtTitle.setTypeface(typeFace);

                playAudio();
            }
        });
    }

    private void playAudio() {

        MediaPlayer player = new MediaPlayer();
        try {
            AssetFileDescriptor descriptor =  getAssets().openFd("music/ting.mp3");
            player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFonts() {
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);

        AssetManager manager = getAssets();
        try {
            String[] fontList = manager.list("fonts");
            adapter.addAll(fontList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lvItems.setAdapter(adapter);
    }

    private void linkViews() {
        txtTitle = findViewById(R.id.txtTitle);
        lvItems = findViewById(R.id.lvFonts);
    }
}