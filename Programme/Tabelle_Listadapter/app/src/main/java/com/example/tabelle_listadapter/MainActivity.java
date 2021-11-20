package com.example.tabelle_listadapter;

import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.listView);

        //Create die Person objects
        //Hier die Daten später aus Datenbank holen
        Ausgabe T1 = new Ausgabe("Tanken","54","11.11.2021");
        Ausgabe T2 = new Ausgabe("Einkaufen Lidl","24","13.11.2021");
        Ausgabe T3 = new Ausgabe("Handy","3.99","01.11.2021");
        Ausgabe T4 = new Ausgabe("Penny","10","20.11.2021");
        Ausgabe T5 = new Ausgabe("Penny EInk.","34.87","21.11.2021");


        //Add the Person objects to an ArrayList
        ArrayList<Ausgabe> AusgabeList = new ArrayList<>();
        AusgabeList.add(T1);
        AusgabeList.add(T2);
        AusgabeList.add(T3);
        AusgabeList.add(T4);
        AusgabeList.add(T5);
        AusgabeList.add(T1);
        AusgabeList.add(T2);
        AusgabeList.add(T4);
        AusgabeList.add(T3);
        AusgabeList.add(T5);
        AusgabeList.add(T1);
        AusgabeList.add(T1);
        AusgabeList.add(T3);
        AusgabeList.add(T4);
        AusgabeList.add(T2);

        AusgabeListAdapter adapter = new AusgabeListAdapter(this, R.layout.adapter_view_layout, AusgabeList);
        mListView.setAdapter(adapter);
    }
}















