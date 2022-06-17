package Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.vodoanhoanglong.sqlite_ex2.MainActivity;
import com.vodoanhoanglong.sqlite_ex2.R;

import org.w3c.dom.Text;

import java.util.List;

import Models.Product;

public class ProductAdapter extends BaseAdapter {
    private MainActivity context;
    private int item_layout;
    private List<Product> products;

    public ProductAdapter(MainActivity context, int item_layout, List<Product> products) {
        this.context = context;
        this.item_layout = item_layout;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.txtProductInfo = view.findViewById(R.id.txtProductInfo);
            holder.imvEdit = view.findViewById(R.id.imvEdit);
            holder.imvDelete = view.findViewById(R.id.imvDelete);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Product p = products.get(i);
        holder.txtProductInfo.setText(p.getProductName() + " >> " + p.getProductPrice());

        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.openEditProductDialog(p);
            }
        });

        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.openDeleteProductDialog(p.getProductId());
            }
        });

        return view;
    }

    private static class ViewHolder{
        TextView txtProductInfo;
        ImageView imvEdit, imvDelete;
    }
}
