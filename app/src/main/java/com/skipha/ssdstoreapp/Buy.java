package com.skipha.ssdstoreapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Buy extends AppCompatActivity {
    private static final String TABLE_SSD = "ssd";
    private static int amount;
    private static final String TABLE_RECEIPTS = "receipts";
    private static final String TABLE_ITEMS = "items";
    private  ArrayList<Items> items;
    private ArrayList<String> types;
    private ArrayList<String> prices;
    private ArrayList<Button> buttons;
    private ArrayList<Spinner> spinners;
    private int spinnersCount;
    private ArrayList <String> numbers;
    private ArrayAdapter adapter;
    private SQLiteDatabase db;
    private SQLiteHelper helper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new  SQLiteHelper(this, "SSDStore.db", null, 1);
        db = helper.getWritableDatabase();
        items = new ArrayList<>();
        spinners = new ArrayList<>();
        amount = Integer.parseInt(getResources().getString(R.string.amount));
        types = new ArrayList<>();
        prices = new ArrayList<>();
        numbers = new ArrayList<>();
        spinnersCount = Integer.parseInt(getResources().getString(R.string.menu_buttons));
        insertSSD();

        for (int i = 0; i < spinnersCount; i++) {
            String spner = "spinner" + (i + 1);
            spinners.add((Spinner) findViewById(getResources().getIdentifier(spner,"id", getPackageName())));
        }

        spinners.get(0).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = parent.getSelectedItem().toString();
                numbers.clear();
                helper = new  SQLiteHelper(getBaseContext(), "SSDStore.db", null, 1);
                db = helper.getReadableDatabase();
                String query = "SELECT *+ FROM " + TABLE_SSD + " WHERE nombre LIKE '" + itemSelected + "'";
                Cursor cursor = db.rawQuery(query, null);
                cursor.moveToNext();

                for (int i = 0; i < Integer.parseInt(cursor.getString(cursor.getColumnIndex("cantidad"))); i++) {
                    numbers.add("" + (i + 1));
                }

                adapter = new ArrayAdapter(getBaseContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        numbers);
                spinners.get(1).setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void insertSSD() {
        if(db != null)
        {
            types = new ArrayList<String> (Arrays.asList(getResources().getStringArray(R.array.ssdtypes)));
            prices = new ArrayList<String> (Arrays.asList(getResources().getStringArray(R.array.ssdprices)));
            Toast toast = Toast.makeText(getApplicationContext(),
                    "This is a message displayed in a Toast",
                    Toast.LENGTH_SHORT);

            toast.show();
            for(int i = 0; i < types.size(); i++)
            {
                db.execSQL("INSERT INTO " + TABLE_SSD +
                        " VALUES ('" + types.get(i) + "', " + Integer.parseInt(prices.get(i)) + ", " + amount + ")");
            }
            db.close();
        }
    }
}
