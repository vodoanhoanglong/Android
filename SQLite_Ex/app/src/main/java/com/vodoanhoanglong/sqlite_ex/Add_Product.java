package com.vodoanhoanglong.sqlite_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Product extends AppCompatActivity {

    EditText edtName, edtPrice;
    Button btnOk, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        linkViews();
        addEvents();
    }

    private void addEvents() {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Insert Data to DB
                ContentValues values = new ContentValues();
                values.put("ProductName", edtName.getText().toString());
                values.put("ProductPrice", Double.parseDouble(edtPrice.getText().toString()));

                long flag = MainActivity.database.insert(MainActivity.TB_NAME, null, values);
                if(flag > 0) {
                    Toast.makeText(Add_Product.this, "Success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Add_Product.this, "Fail", Toast.LENGTH_SHORT).show();
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

    private void linkViews() {
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        btnOk  = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);
    }
}