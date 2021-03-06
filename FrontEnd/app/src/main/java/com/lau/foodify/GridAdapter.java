package com.lau.foodify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// This is responsible of creating a grid adapter for the cookbook
public class GridAdapter extends BaseAdapter {
    Context context;
    String[] recipe;
    String[] cooktime;
    int[] image;

    LayoutInflater inflater;

    public GridAdapter(Context context, String[] recipe,String[] cooktime, int[] image) {
        this.context = context;
        this.recipe = recipe;
        this.image = image;
        this.cooktime = cooktime;
    }

    @Override
    public int getCount() {
        return recipe.length;
    }

    @Override
    public String getItem(int i) {
        return recipe[i];
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

            view = inflater.inflate(R.layout.grid_item,null);

        }

        ImageView imageView = view.findViewById(R.id.grid_image);
        TextView textView = view.findViewById(R.id.item_name);
        TextView time = view.findViewById(R.id.time);

        imageView.setImageResource(image[i]);
        textView.setText(recipe[i]);
        time.setText(cooktime[i]);

        return view;
    }
}
