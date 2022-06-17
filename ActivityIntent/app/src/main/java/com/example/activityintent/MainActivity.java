package com.example.activityintent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn, btn2, btn3, btn4, btnOpenCamera, btnCall, btnDial;
    TextView txtRes;
    ImageView imgCamera;

//    public static final int REQUEST_CODE = 1;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("MainActivity Intent", "Hello");

        linkViews();
        addEvents();

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK && result.getData() != null)
                {
                    double numb = result.getData().getDoubleExtra("pow", 0);
                    Bitmap img = (Bitmap) result.getData().getExtras().get("data");
                    if(numb != 0)
                        txtRes.setText(String.valueOf(numb));
                    if(img != null)
                        imgCamera.setImageBitmap(img);
                }
            }
        });
    }

    private void addEvents() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivitySub.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivitySub2.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivitySub3.class);

                // Attach data
                intent.putExtra("numb", 99);
                intent.putExtra("mark", 8.9);
                intent.putExtra("check", true);
                intent.putExtra("say", "Hello");

                Product p = new Product(1, "heniken", 1990);
                intent.putExtra("productInfo", p);

                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivitySub4.class);

                intent.putExtra("numb", 8);
//                startActivityForResult(intent, REQUEST_CODE);

                activityResultLauncher.launch(intent);

            }
        });

        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher.launch(intent);
            }

        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:0932765080");
                intent.setData(uri);
                startActivity(intent);
            }
        });

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:0123456789");
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }

    private void linkViews() {
        btn = findViewById(R.id.btnSub1);
        btn2 = findViewById(R.id.btnSub2);
        btn3 = findViewById(R.id.btnSub3);
        btn4 = findViewById(R.id.btnSub4);
        txtRes = findViewById(R.id.txtResolve);
        btnOpenCamera = findViewById(R.id.btnOpenCamera);
        imgCamera = findViewById(R.id.imvPhoto);
        btnCall = findViewById(R.id.btnCall);
        btnDial = findViewById(R.id.btnDial);
    }

    // Receive data
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null)
//        {
//            txtRes.setText(String.valueOf(data.getDoubleExtra("pow", 0)));
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity Intent", "Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity Intent", "Resume");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity Intent", "Stop");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity Intent", "Pause");

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity Intent", "Restart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity Intent", "Destroy");

    }
}