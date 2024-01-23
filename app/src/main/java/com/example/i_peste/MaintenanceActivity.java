package com.example.i_peste;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;


public class MaintenanceActivity extends AppCompatActivity {

    private String[] classes = {"Black bug", "Zigzag leafhopper", "Rice Whorl Maggot", "Mole Cricket", "Rice Bug", "Planthopper", "Rice Caseworm", "Stem Borer", "Leaffolder", "Short-horned grasshopper", "Wolf spider", "Orb spider", "Dragonfly", "Ground beetle"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, classes);

        ListView listView = findViewById(R.id.listViewMaintenance);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Open the EditActivity when an item is clicked
            String selectedInsect = classes[position];
            Intent intent = new Intent(MaintenanceActivity.this, EditActivity.class);
            intent.putExtra("insectName", selectedInsect);
            startActivity(intent);
        });
    }
}