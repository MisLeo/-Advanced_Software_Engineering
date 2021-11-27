package com.example.haushaltsplaner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebHistoryItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Tabelle extends  AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabelle);

        ListView mListView = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent();
        ArrayList<Outgo> ListeOut = (ArrayList<Outgo>) intent.getSerializableExtra("list");
        //zum TEsten ohne Datenbankzugriff
        //Erzeugen die AUsgabe objects
        class_Ausgabe T1 = new class_Ausgabe("Tanken", "54", "11.11.2021");
        class_Ausgabe T2 = new class_Ausgabe("Einkaufen Lidl", "24", "13.11.2021");
        class_Ausgabe T3 = new class_Ausgabe("Handy", "3.99", "01.11.2021");
        class_Ausgabe T4 = new class_Ausgabe("Penny", "10", "20.11.2021");
        class_Ausgabe T5 = new class_Ausgabe("Penny EInk.", "34.87", "21.11.2021");

        //füllen der Array List
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

        //Zum Test ohne Datenbank
        AusgabeListAdapter adapter = new AusgabeListAdapter(this, R.layout.activity_adapter_list_view, AusgabeList);
        mListView.setAdapter(adapter);

        /*OutgoListAdapter adapter = new OutgoListAdapter(this,R.layout.activity_adapter_list_view,ListeOut);
        mListView.setAdapter(adapter);

        Intent switchOutgoListAdapter =new Intent(this, OutgoListAdapter.A.class);
        ArrayList<Outgo> outgoes1 = ListeOut;
        switchOutgoListAdapter.putExtra("list",(Serializable) outgoes1);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tabelle_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.itemStartseite:
                Intent switchToMain = new Intent(this, MainActivity.class);
                startActivity(switchToMain);
                return true;

            case R.id.itemEinnahmenAusgaben:
                Intent switchToAddEntry = new Intent(this, AddEntryActivity.class);
                startActivity(switchToAddEntry);
                return true;

            case R.id.itemBudgetLimit:
                Intent switchToBudgetLimit = new Intent(this, BudgetLimit.class);
                startActivity(switchToBudgetLimit);
                return true;

            case R.id.itemDiagrammansicht:
                Intent switchToEditDiagramView = new Intent(this, EditDiagramView.class);
                startActivity(switchToEditDiagramView);
                return true;

            case R.id.itemKalender:
                Intent switchToCalendar = new Intent(this, Calendar.class);
                startActivity(switchToCalendar);
                return true;

            case R.id.itemTodoListe:
                Intent switchToDoList = new Intent(this, ToDoList.class);
                startActivity(switchToDoList);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}