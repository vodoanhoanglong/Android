package com.example.notificationex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnShowToast, btnShowAlert, btnCustomDialog, btnMyCustomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkView();
        addEvents();
    }

    private void addEvents() {
        btnShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, "UTC2", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0 ,0);
                toast.show();
            }
        });
        btnShowAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setTitle("Exit");
                alertBuilder.setIcon(R.drawable.icon_android);
                alertBuilder.setMessage("Do you want to exit app");

                alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = alertBuilder.create();
                // don't touch outside panel
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setCanceledOnTouchOutside(false);

                // vi custom_dialog da dc set trong dialog setContentView
                ImageView imvCheck = dialog.findViewById(R.id.imvCheck);
                imvCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });

                ImageView imvCancel = dialog.findViewById(R.id.imgClose);
                imvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        btnMyCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new MyCustomDialog(MainActivity.this);
                dialog.show();
            }
        });
    }

    private void linkView() {
        btnShowToast = findViewById(R.id.btnShowToast);
        btnShowAlert = findViewById(R.id.btnShowAlert);
        btnCustomDialog = findViewById(R.id.btnCustomDialog);
        btnMyCustomDialog = findViewById(R.id.btnMyCustomDialog);
    }
}