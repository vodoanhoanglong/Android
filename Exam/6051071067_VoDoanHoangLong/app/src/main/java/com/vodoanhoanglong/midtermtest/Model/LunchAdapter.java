package com.vodoanhoanglong.midtermtest.Model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vodoanhoanglong.midtermtest.R;

import java.util.List;

public class LunchAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<Lunch> lunches;

    public LunchAdapter(Activity context, int item_layout, List<Lunch> lunches) {
        this.context = context;
        this.item_layout = item_layout;
        this.lunches = lunches;
    }

    @Override
    public int getCount(){
        return lunches.size();
    }

    @Override
    public Object getItem(int i) {
        return lunches.get(i);
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
            holder.imgPhoto = view.findViewById(R.id.lunch_img);
            holder.txtPlaceName = view.findViewById(R.id.lunch_name);
            holder.txtDishName = view.findViewById(R.id.lunch_des);
            holder.txtRatingValue = view.findViewById(R.id.lunch_vote);
            holder.txtRatingCount = view.findViewById(R.id.lunch_count);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        Lunch lunch = lunches.get(i);
        holder.imgPhoto.setImageResource(lunch.getPhoto());
        holder.txtPlaceName.setText(lunch.getPlaceName());
        holder.txtDishName.setText(lunch.getDishName());
        holder.txtRatingValue.setText(String.valueOf(lunch.getRatingValue()));
        holder.txtRatingCount.setText(lunch.getRatingCount());


        return view;
    }

    public static class ViewHolder{
        ImageView imgPhoto;
        TextView txtPlaceName, txtDishName, txtRatingValue, txtRatingCount, txtAddress;
    }

}
