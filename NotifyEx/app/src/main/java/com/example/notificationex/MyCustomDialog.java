package com.example.notificationex;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class MyCustomDialog extends Dialog {
    ImageView imvOK, imvCancel;
    Activity context;

    public MyCustomDialog(@NonNull Context context){
        super(context);
        this.context = (Activity) context;
        setContentView(R.layout.custom_dialog);
        addViews();
        setCanceledOnTouchOutside(false);
        addEvents();
    }

    private void addEvents() {
        imvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.finish();
            }
        });

        imvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void addViews() {
        imvOK = findViewById(R.id.imvCheck);
        imvCancel = findViewById(R.id.imgClose);
    }
}
