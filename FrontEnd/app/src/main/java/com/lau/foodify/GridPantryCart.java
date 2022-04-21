package com.lau.foodify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridPantryCart extends BaseAdapter {
    Context context;
    String[] food;
    String[] weight;
    String[] date;

    LayoutInflater inflater;

    public GridPantryCart(Context context, String[] food, String[] weight,String[] date) {
        this.context = context;
        this.food = food;
        this.weight = weight;
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
        TextView weight_txt = view.findViewById(R.id.weight);
        TextView location_txt = view.findViewById(R.id.location);

        food_txt.setText(food[i]);
        weight_txt.setText(weight[i]);
        exp_txt.setText("price: " + date[i]);
        location_txt.setVisibility(View.GONE);

        return view;
    }
}
