package com.vodoanhoanglong.sqlite_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Models.Product;

public class Edit_Product extends AppCompatActivity {

    EditText edtName, edtPrice;
    Button btnOk, btnCancel;
    Product p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        linkViews();
        getData();
        addEvents();
    }

    private void getData() {
        Intent intent = getIntent();
         p = (Product) intent.getSerializableExtra("productInfo");
        edtName.setText(p.getProductName());
        edtPrice.setText(String.valueOf(p.getProductPrice()));
    }

    private void linkViews() {
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);
    }

    private void addEvents() {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("ProductName", edtName.getText().toString());
                values.put("ProductPrice", Double.parseDouble(edtPrice.getText().toString()));

                int flag = MainActivity.database.update(MainActivity.TB_NAME, values, "ProductId = ?",
                        new String[] {String.valueOf(p.getProductId())});
                if(flag > 0){
                    Toast.makeText(Edit_Product.this, "Success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Edit_Product.this, "Fail", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}