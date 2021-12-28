package com.example.haushaltsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haushaltsapp.ChartPackage.RecyclerAdapter;
import com.example.haushaltsapp.DeleteCategoryPackage.deleteCategorieAdapter;
import com.example.haushaltsapp.ToDoListPackage.SwipeHandler;
import com.example.haushaltsapp.database.Category;
import com.example.haushaltsapp.database.Intake;
import com.example.haushaltsapp.database.MySQLite;
import com.example.haushaltsapp.database.Outgo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class DeleteCategoryActivity extends AppCompatActivity {

    ////Variabeln zur Menünavigation
    private MySQLite mySQLite;
    private final int REQUESTCODE_ADD = 12; //AddEntryActivity
    private final int REQUESTCODE_SHOW = 13; //ShowEntryActivity
    private final int REQUESTCODE_EDIT = 14; //EditEntryActivity
    private final int REQUESTCODE_ADD_CATEGORY = 15; //AddCategoryActivity

    private int day;
    private int month;
    private int year;
    ///////////////////////////////

    private RecyclerView recyclerView;
    private ArrayList<Category> CategorieList;
    private deleteCategorieAdapter.deleteCategorieClickListener listener;
    private deleteCategorieAdapter deleteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_categorie);
        mySQLite = new MySQLite(this);
        CategorieList = mySQLite.getAllCategory();
        recyclerView = findViewById(R.id.deleteCategorieRecyclerView);

        setAdapter();
    }

    private void setAdapter()
    {
        setOnClickListner();

        deleteCategorieAdapter adapter = new deleteCategorieAdapter(CategorieList,listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        deleteAdapter = new deleteCategorieAdapter(CategorieList,listener);
        recyclerView.setAdapter(deleteAdapter);
        //ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeHandlerdelete(deleteAdapter));

        //itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private void setOnClickListner(){
        listener = new deleteCategorieAdapter.deleteCategorieClickListener() {
            @Override
            public void onClick(View v, int position) {

                String Categorie = CategorieList.get(position).getName_PK();


                if (Categorie.equals("Sonstiges"))
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Sonstiges kann nicht gelöscht werden",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {

                    Toast toast = Toast.makeText(getApplicationContext(),Categorie+" Wird gelöscht",Toast.LENGTH_SHORT);
                    toast.show();
                    //suchen nach allen einträgen mit Categorie änderen der Categorie zu sonstiges
                    mySQLite.ChangeCategorietoSonstiges(Categorie);
                    //Löschen der Kategorie
                    mySQLite.deleteCategoryByName(Categorie);
                }
            }
        };
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        MenuItem item = menu.findItem(R.id.itemTableView);
        item.setEnabled(false);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemMainPage:
                Intent switchToMain = new Intent(this, MainActivity.class);
                startActivity(switchToMain);
                return true;

            case R.id.itemAddIntakesOutgoes:
                mySQLite = new MySQLite(this);
                ArrayList<Category> categories = mySQLite.getAllCategory();
                Intent switchToAddEntry = new Intent(this, AddEntryActivity.class);
                switchToAddEntry.putExtra("list", categories);
                mySQLite.close();
                startActivityForResult(switchToAddEntry, REQUESTCODE_ADD);
                return true;

            case R.id.subitemIntakes:
                mySQLite = new MySQLite(this);
                ArrayList<Intake> intakes = mySQLite.getMonthIntakes(day, month, year);
                Intent getIntakes = new Intent(this, ShowEntriesActivity.class);
                getIntakes.putExtra("list", (Serializable) intakes);
                getIntakes.putExtra("entry", "Intake");
                mySQLite.close();
                startActivityForResult(getIntakes, REQUESTCODE_SHOW);
                return true;

            case R.id.subitemOutgoes:
                mySQLite = new MySQLite(this);
                ArrayList<Outgo> outgoes = mySQLite.getMonthOutgos(day, month, year);
                Intent getOutgoes = new Intent(this, ShowEntriesActivity.class);
                getOutgoes.putExtra("list", (Serializable) outgoes);
                getOutgoes.putExtra("entry", "Outgo");
                mySQLite.close();
                startActivityForResult(getOutgoes, REQUESTCODE_SHOW);
                return true;

            case R.id.itemBudgetLimit:
                Intent switchToBudgetLimit = new Intent(this, BudgetLimitActivity.class);
                startActivity(switchToBudgetLimit);
                return true;

            case R.id.itemDiagramView:
                mySQLite = new MySQLite(this);
                Intent switchToDiagramView = new Intent(this, DiagramViewActivity.class);
                //Alle Ausgaben in Datenbank
                ArrayList<Outgo> AlloutgoD = mySQLite.getAllOutgo();
                switchToDiagramView.putExtra("dataOut", AlloutgoD);
                //Alle Einnahmen in Datenbank
                ArrayList<Intake> AllIntakeD = mySQLite.getAllIntakes();
                switchToDiagramView.putExtra("dataIn", AllIntakeD);
                mySQLite.close();
                startActivity(switchToDiagramView);
                return true;

            case R.id.itemTableView:
                mySQLite = new MySQLite(this);
                Intent switchToChartView = new Intent(this, ChartViewActivity.class);
                //Alle Ausgaben in Datenbank
                ArrayList<Outgo> AlloutgoT = mySQLite.getAllOutgo();
                switchToChartView.putExtra("dataOut", AlloutgoT);
                //Ausgaben von aktuellem Monat
                ArrayList<Outgo> outgoesT = mySQLite.getMonthOutgos(day, month, year);
                switchToChartView.putExtra("monthlist", outgoesT);
                //Alle Einnahmen in Datenbank
                ArrayList<Outgo> AllintakeT = mySQLite.getAllOutgo();
                switchToChartView.putExtra("dataIn", AllintakeT);
                mySQLite.close();
                startActivity(switchToChartView);
                return true;

            case R.id.itemCalendar:
                Intent switchToCalender = new Intent(this, CalendarEventActivity.class);
                startActivity(switchToCalender);
                return true;

            case R.id.itemToDoListe:
                Intent switchToToDoList = new Intent(this, ToDoListActivity.class);
                startActivity(switchToToDoList);
                return true;

            case R.id.itemAddCategory:
                mySQLite = new MySQLite(this);
                Intent switchToAddCategory = new Intent(this, AddCategoryActivity.class);
                ArrayList<Category> categories1 = mySQLite.getAllCategory();
                switchToAddCategory.putExtra("list", (Serializable) categories1);
                mySQLite.close();
                startActivityForResult(switchToAddCategory, REQUESTCODE_ADD_CATEGORY);
                return true;

            case R.id.itemDeleteCategory:
                mySQLite = new MySQLite(this);
                Intent switchToDeleteCategory = new Intent(this, DeleteCategoryActivity.class);
                startActivity(switchToDeleteCategory);
                return true;

            case R.id.itemPdfCreator:
                Intent switchToPdfCreator = new Intent(this, PDFCreatorActivity.class);
                startActivity(switchToPdfCreator);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}