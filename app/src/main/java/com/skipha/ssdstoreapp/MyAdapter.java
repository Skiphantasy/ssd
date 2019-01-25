package com.skipha.ssdstoreapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {
    private Activity context;
    private String[] data;
    private ArrayList<String> types;
    private ArrayList<Integer> prices;
    private ArrayList<Integer> amount;
   // ArrayList<TextView> textViews;

    public MyAdapter(Activity context, String[] data, ArrayList<String> types, ArrayList<Integer> prices, ArrayList<Integer> amount) {
        super(context, R.layout.listview, data);
        this.types = types;
        this.context = context;
        this.amount = amount;
        this.prices = prices;
        this.data = data;
        //textViews = new ArrayList<>();
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.listview, null);

       /* textViews.add((TextView) context.findViewById(R.id.textView3));
        textViews.add((TextView) context.findViewById(R.id.textView4));
        textViews.add((TextView) context.findViewById(R.id.textView5));
        textViews.get(0).setText(types.get(position));
        textViews.get(1).setText("" + prices.get(position));
        textViews.get(2).setText("" + types.get(position));*/
        TextView type = item.findViewById(R.id.textView3);
        TextView price = item.findViewById(R.id.textView4);
        TextView amountText = item.findViewById(R.id.textView5);
        type.setText(types.get(position));
        price.setText("" + prices.get(position));
        amountText.setText("" + amount.get(position));

        return(item);
    }
}

