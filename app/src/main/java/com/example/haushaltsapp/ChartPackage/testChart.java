package com.example.haushaltsapp.ChartPackage;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haushaltsapp.R;
import com.example.haushaltsapp.database.MySQLite;

public class testChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charttest);
        TextView nameText = findViewById(R.id.testname);

        String name = "";

        Bundle extras = getIntent().getExtras();
        if (extras!= null)
        {
            name = extras.getString("name");
        }
        nameText.setText(name);
    }
}
