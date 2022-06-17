package com.vodoanhoanglong.broadcastreceiverex;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView imvNetworkState;
    TextView txtNetworkState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
    }

    private void linkViews() {
        imvNetworkState = findViewById(R.id.imvNetwork);
        txtNetworkState = findViewById(R.id.txtNetwork);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myReceiver);
    }

    BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                    imvNetworkState.setImageResource(R.drawable.ic_baseline_wifi);
                    txtNetworkState.setText("Connected via Wifi");
                } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                    imvNetworkState.setImageResource(R.drawable.ic_baseline_signal_cellular_alt_24);
                    txtNetworkState.setText("Connected via Mobile data");
                }
            } else {
                imvNetworkState.setImageResource(R.drawable.ic_baseline_not_interested_24);
                txtNetworkState.setText("No internet connection");
            }
        }
    };
}