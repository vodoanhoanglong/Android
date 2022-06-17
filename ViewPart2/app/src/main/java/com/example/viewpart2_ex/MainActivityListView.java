package com.example.viewpart2_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.model.Product;

public class MainActivityListView extends AppCompatActivity {

    EditText edtName, edtColor;
    Button btnAdd;
    ListView lvProduct;
    ArrayAdapter<Product> arrProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view);

        linkViews();
        initAdapter();
        addEvents();
    }

    private void initAdapter() {
        arrProduct = new ArrayAdapter<Product>(MainActivityListView.this, android.R.layout.simple_list_item_1);
        lvProduct.setAdapter(arrProduct);
    }

    private void addEvents() {

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product p = arrProduct.getItem(i);
                Toast.makeText(MainActivityListView.this, p.getProductname(), Toast.LENGTH_SHORT).show();
            }
        });

        lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product p = arrProduct.getItem(i);
                arrProduct.remove(p);
                return true;
            }
        });
    }

    private void addProduct() {
        String name = edtName.getText().toString();
        String color = edtColor.getText().toString();
        arrProduct.add(new Product(name, color));

    }

    private void linkViews() {
        edtColor = findViewById(R.id.editProductColor);
        edtName = findViewById(R.id.editProductName);
        btnAdd = findViewById(R.id.btnAdd);
        lvProduct = findViewById(R.id.lvProduct);
    }
}