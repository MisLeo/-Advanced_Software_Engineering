package com.example.haushaltsplaner;
//https://github.com/mitchtabian/ListViews
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.ArrayAdapter;
        import android.widget.ListAdapter;
        import android.widget.ListView;

        import java.util.ArrayList;
        import java.util.List;
        import android.os.Bundle;

public class Tabelle extends  AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.listView);

        //Erzeugen die AUsgabe objects
        //Hier die Daten später aus Datenbank holen
        //hier alle Daten als String
        class_Ausgabe T1 = new class_Ausgabe("Tanken","54","11.11.2021");
        class_Ausgabe T2 = new class_Ausgabe("Einkaufen Lidl","24","13.11.2021");
        class_Ausgabe T3 = new class_Ausgabe("Handy","3.99","01.11.2021");
        class_Ausgabe T4 = new class_Ausgabe("Penny","10","20.11.2021");
        class_Ausgabe T5 = new class_Ausgabe("Penny EInk.","34.87","21.11.2021");


        //Add the Person objects to an ArrayList
        //kann hier die Liste vielleicht gleich aus der Datenbank geholt werden???
        ArrayList<class_Ausgabe> AusgabeList = new ArrayList<>();
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

        AusgabeListAdapter adapter = new AusgabeListAdapter(this, R.layout.activity_adapter_list_view, AusgabeList);
        //mListView.setAdapter((ListAdapter) adapter);
        mListView.setAdapter(adapter);
    }
}















