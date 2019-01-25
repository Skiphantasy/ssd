package com.skipha.ssdstoreapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int numbersCount;
    private ArrayList<Button> buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        buttons = new ArrayList<>();
        numbersCount = Integer.parseInt(getResources().getString(R.string.menu_buttons));
        fillButtons();
    }

    private void fillButtons() {
        for (int i = 0; i < numbersCount; i++) {
            String buttonid = "button" + (i + numbersCount);
            buttons.add((Button)findViewById(getResources().getIdentifier(buttonid,"id", getPackageName())));
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getId() == buttons.get(0).getId()) {
                        Intent intent = new Intent(v.getContext(), Receipt.class);
                        startActivity(intent);
                    } else if (v.getId() == buttons.get(1).getId()){
                        Intent intent = new Intent(v.getContext(), Buy.class);
                        startActivity(intent);
                    } else if (v.getId() == buttons.get(2).getId()){
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "This is a message displayed in a Toast",
                                Toast.LENGTH_SHORT);

                        toast.show();
                        Intent intent = new Intent(v.getContext(), RefundReceipt.class);
                        startActivity(intent);
                    }
                }
            });

        }
    }
}
