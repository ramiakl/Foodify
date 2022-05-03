package com.lau.foodify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// This is responsible of creating a grid adapter for the pantry
public class GridAdapterPantry extends BaseAdapter {
    Context context;
    String[] food;
    String[] weight;
    String[] location;
    String[] date;

    LayoutInflater inflater;

    public GridAdapterPantry(Context context, String[] food, String[] weight, String[] location,String[] date) {
        this.context = context;
        this.food = food;
        this.weight = weight;
        this.location = location;
        this.date = date;
    }

    @Override
    public int getCount() {
        return food.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null){
            view = inflater.inflate(R.layout.grid_list,null);

        }

        TextView food_txt = view.findViewById(R.id.food);
        TextView exp_txt = view.findViewById(R.id.exp);
        TextView location_txt = view.findViewById(R.id.location);
        TextView weight_txt = view.findViewById(R.id.weight);
        TextView lbp = view.findViewById(R.id.lbp);

        food_txt.setText(food[i]);
        weight_txt.setText(weight[i]);
        exp_txt.setText(date[i]);
        location_txt.setText(location[i]);
        lbp.setVisibility(View.GONE);

        return view;
    }
}