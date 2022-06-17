package com.vodoanhoanglong.sqlite_ex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Models.Product;

public class MainActivity extends AppCompatActivity {
    public static final String DB_NAME = "product_db.db";
    public static final String DB_PATH_SUFFIX = "/databases/";
    public static String TB_NAME = "Product";
    public static SQLiteDatabase database = null;

    ListView lvProduct;
    ArrayAdapter<Product> adapter;

    Product selectedProduct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyDatabase();
        linkViews();

        // contextMenu for list item
        registerForContextMenu(lvProduct);
        // addEvents for edit product
        addEvents();
//        loadData();
    }

    private void addEvents() {
        lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProduct = adapter.getItem(i);
                return false;
            }
        });
    }

    private void loadData() {
        adapter = new ArrayAdapter<Product>(MainActivity.this, android.R.layout.simple_list_item_1);

        database = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM " + TB_NAME, null);

        while(cursor.moveToNext()){
            adapter.add(new Product(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2)));
        }

        lvProduct.setAdapter(adapter);
    }

    private void linkViews() {
        lvProduct = findViewById(R.id.lvProduct);
    }

    private void copyDatabase(){
        try {
            File dbFile = getDatabasePath(DB_NAME);
            if(!dbFile.exists()){
                if(CopyDBFromAsset())
                    Toast.makeText(MainActivity.this, "Copy DB successful", Toast.LENGTH_LONG).show();
                else Toast.makeText(MainActivity.this, "Copy DB fail", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Log.e("Error: ", e.toString());
        }
    }

    private boolean CopyDBFromAsset() {
        String dbPath = getApplicationInfo().dataDir + DB_PATH_SUFFIX + DB_NAME;
        try {
            InputStream inputStream = getAssets().open(DB_NAME);
            File file = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!file.exists()) {
                boolean flag = file.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024];
            int length;
            while((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onResume() {
        loadData();
        super.onResume();
    }

    // Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnAdd){
            Intent intent = new Intent(MainActivity.this, Add_Product.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnEdit){
            //Open edit screen
            Intent intent = new Intent(MainActivity.this, Edit_Product.class);

            if(selectedProduct != null)
            {
                intent.putExtra("productInfo", selectedProduct);
                startActivity(intent);
            }
        }else if(item.getItemId() == R.id.mnDel){
            //Delete
            if(selectedProduct != null)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirm delete product");
                builder.setMessage("Are you sure want to delete this product: " + selectedProduct.getProductName());
                builder.setIcon(android.R.drawable.ic_delete);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int flag = database.delete(TB_NAME, "ProductId = ?",
                                new String[] {selectedProduct.getProductId() + ""});

                        if(flag > 0){
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            loadData();
                        }else{
                            Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
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
        return super.onContextItemSelected(item);
    }
}