package com.vodoanhoanglong.midtermtest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog {
    Activity context;

    ImageView imgPhoto;
    TextView txtName, txtRating, txtAddress;

    public CustomDialog(@NonNull Context context, int imgPhoto, String txtName, Double txtRating, String txtAddress) {
        super(context);
        this.context = (Activity) context;
        setContentView(R.layout.active_dialog);
        addViews();
        this.imgPhoto.setImageResource(imgPhoto);
        this.txtName.setText(txtName);
        this.txtRating.setText(String.valueOf(txtRating));
        this.txtAddress.setText(txtAddress);
    }

    private void addViews() {
        imgPhoto = findViewById(R.id.dlPhoto);
        txtName = findViewById(R.id.dlPlaceName);
        txtRating = findViewById(R.id.dlRatingValue);
        txtAddress = findViewById(R.id.dlAddress);
    }
}
