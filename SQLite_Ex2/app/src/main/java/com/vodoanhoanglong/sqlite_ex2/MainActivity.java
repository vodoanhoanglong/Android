package com.vodoanhoanglong.sqlite_ex2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapters.ProductAdapter;
import Models.Product;

public class MainActivity extends AppCompatActivity {

    Databases db;
    ListView lvProduct;
    ProductAdapter adapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        prepareDb();
        loadData();
    }

    private void linkViews() {
        lvProduct = findViewById(R.id.lvProduct);
    }

    private void loadData() {
        products = new ArrayList<>();
        Cursor cursor = db.getData("SELECT * FROM " + Databases.TB_NAME);
        products.clear();
        while (cursor.moveToNext()){
            products.add(new Product(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2)));
        }
        cursor.close();
        adapter = new ProductAdapter(MainActivity.this, R.layout.item_list, products);
        lvProduct.setAdapter(adapter);
    }

    private void prepareDb() {
        db = new Databases(this);
        db.createSomeDefaultProduct();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnAdd){
            openAddProductDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openAddProductDialog() {
        EditText edtName, edtPrice;
        Button btnOk, btnCancel;

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_add_dialog);
        edtName = dialog.findViewById(R.id.edtName);
        edtPrice = dialog.findViewById(R.id.edtPrice);

        btnOk = dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String price = edtPrice.getText().toString();
                if(name.equals("") || price.equals("")){
                    Toast.makeText(MainActivity.this, "Field not empty", Toast.LENGTH_SHORT).show();
                }else{
                    String sql = "INSERT INTO " + Databases.TB_NAME + " VALUES(null, '" + name + "', " + Double.parseDouble(price) + ")";
                    db.execQuery(sql);
                    dialog.dismiss();
                    loadData();
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void openEditProductDialog(Product p){
        EditText edtName, edtPrice;
        Button btnOk, btnCancel;

        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_edit_dialog);

        edtName = dialog.findViewById(R.id.edtName);
        edtName.setText(p.getProductName());
        edtPrice = dialog.findViewById(R.id.edtPrice);
        edtPrice.setText(String.valueOf(p.getProductPrice()));

        btnOk = dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                double price = Double.parseDouble(edtPrice.getText().toString());

                String sql = "UPDATE " + Databases.TB_NAME + " SET " + Databases.COL_NAME + " = '" + name + "', " +
                        Databases.COL_PRICE + " = " + price + " WHERE " + Databases.COL_ID + " = " + p.getProductId();
                db.execQuery(sql);
                dialog.dismiss();
                loadData();
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void openDeleteProductDialog(int productId){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Confirm delete product");
        builder.setMessage("Are u sure want to delete this product");
        builder.setIcon(android.R.drawable.ic_delete);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String sql = "DELETE FROM " + Databases.TB_NAME + " WHERE ProductId = " + productId;
                db.execQuery(sql);
                loadData();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.create().show();
    }
}