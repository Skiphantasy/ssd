package com.skipha.ssdstoreapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectFragment extends Fragment {
    ArrayList<Items> items;
    ArrayList<Spinner> spinners;
    int spinnersCount;
    ArrayList <String> numbers;
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        items = new ArrayList<>();
        spinners = new ArrayList<>();
        numbers = new ArrayList<>();
        spinnersCount = Integer.parseInt(getResources().getString(R.string.menu_buttons));

        for (int i = 0; i < spinnersCount; i++) {
            String spner = "spinner" + (i + 1);
            spinners.add((Spinner) container.findViewById(getResources().getIdentifier(spner,"id", getActivity().getPackageName())));
        }

        spinners.get(0).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = parent.getSelectedItem().toString();
                connectFirebase(itemSelected);
            }
        });
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    private void connectFirebase(String itemSelected) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ssdTypes = database.getReference("SSDTypes");
        Query query = ssdTypes.orderByChild("Name")
                .equalTo(itemSelected);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int number =Integer.parseInt(dataSnapshot.child("Name").getValue(String.class));

                for (int i = 0; i < number; i++) {
                    numbers.add("" + (i + 1));
                }

                spinners.get(1).setAdapter(new ArrayAdapter(getContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        numbers));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
