package com.example.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.viewpart2_ex.R;

import java.util.List;

public class BeerAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<Beers> beers;

    public BeerAdapter(Activity context, int item_layout, List<Beers> beers) {
        this.context = context;
        this.item_layout = item_layout;
        this.beers = beers;
    }

    @Override
    public int getCount() {
        return beers.size();
    }

    @Override
    public Object getItem(int i) {
        return beers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.imvPhoto = view.findViewById(R.id.imageBeer);
            holder.txtName = view.findViewById(R.id.beerName);
            holder.txtPrice = view.findViewById(R.id.beerPrice);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        // Biding data
        Beers beer = beers.get(i);
        holder.txtName.setText(beer.getName());
        holder.txtPrice.setText(String.valueOf(beer.getPrice() + "VND"));
        holder.imvPhoto.setImageResource(beer.getProductThumb());

        return view;
    }

    public static class ViewHolder{
        ImageView imvPhoto;
        TextView txtName, txtPrice;
    }
}
