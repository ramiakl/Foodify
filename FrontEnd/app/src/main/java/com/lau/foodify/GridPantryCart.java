package com.lau.foodify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// This is responsible of creating a grid adapter for the cart
public class GridPantryCart extends BaseAdapter {
    Context context;
    String[] food;
    String[] weight;
    String[] price;

    LayoutInflater inflater;

    public GridPantryCart(Context context, String[] food, String[] weight,String[] price) {
        this.context = context;
        this.food = food;
        this.weight = weight;
        this.price = price;
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
        exp_txt.setText("Price: " + price[i]);
        location_txt.setVisibility(View.GONE);

        return view;
    }
}
