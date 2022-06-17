package com.vodoanhoanglong.multiprocess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText edtNumOfView;
    Button btnDraw;
    TextView txtPercent;
    ProgressBar pbPercent;
    LinearLayout layoutContainer;

    Random random = new Random();
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200,LinearLayout.LayoutParams.WRAP_CONTENT);

    // Main thread / UI thread
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            int percent = message.arg1;
            int randomNumber = (int) message.obj;
            if(percent == 100){
                Toast.makeText(MainActivity.this, "DONE", Toast.LENGTH_SHORT).show();
            }else
            {
                ImageView img = new ImageView(MainActivity.this);
                img.setLayoutParams(params);
                if(randomNumber % 2 == 0){
                    img.setImageResource(R.drawable.ic_android_black_24dp);
                }else{
                    img.setImageResource(R.drawable.ic_baseline_medical_services_24);
                }
                layoutContainer.addView(img);
            }
            txtPercent.setText(String.valueOf(percent) + "%");
            pbPercent.setProgress(percent);
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        addEvents();
    }

    private void addEvents() {
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runBackgroundThread();
            }
        });
    }

    private void runBackgroundThread() {
        int number = Integer.parseInt(edtNumOfView.getText().toString());
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message message;
                for(int i=1; i <= number; i++){
                    message = handler.obtainMessage();
                    message.arg1 = i*100/number;
                    message.obj = random.nextInt(100);
                    handler.sendMessage(message);
                    SystemClock.sleep(100);
                }
            }
        });
        backgroundThread.start();
    }

    private void linkViews() {
        edtNumOfView = findViewById(R.id.edtNumOfView);
        btnDraw = findViewById(R.id.btnDraw);
        txtPercent = findViewById(R.id.txtPercent);
        pbPercent = findViewById(R.id.pbPercent);
        layoutContainer = findViewById(R.id.layoutContainer);
    }
}