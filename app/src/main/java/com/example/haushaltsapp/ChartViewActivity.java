package com.example.haushaltsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haushaltsapp.ChartPackage.testChart;
import com.example.haushaltsapp.database.Category;
import com.example.haushaltsapp.database.Intake;
import com.example.haushaltsapp.database.MySQLite;
import com.example.haushaltsapp.database.Outgo;

public class ChartViewActivity extends  AppCompatActivity
{

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

    private final static int[] COLUMN_WIDTHS = new int[]{40, 20, 40}; //Fensterbreite der einzelnen Spalten
    private final static int CONTENT_ROW_HEIGHT = 80;
    private final static int FIXED_HEADER_HEIGHT = 60;

    private ArrayList<Outgo> Outgolist;
    private RecyclerView recyclerView;
    private recyclerAdapter.RecyclerviewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view);
        mySQLite = new MySQLite(this);
        Outgolist = mySQLite.getAllOutgo();


        recyclerView = findViewById(R.id.chartRecyclerView);

        setAddaper();
    }


    private void setAddaper()
    {
        setOnClickListener();

        recyclerAdapter adapter = new recyclerAdapter(Outgolist, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    private void setOnClickListener()
    {
        listener = new recyclerAdapter.RecyclerviewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intenttoedit = new Intent(getApplicationContext(), testChart.class);
                intenttoedit.putExtra("name", Outgolist.get(position).getName());
                startActivity(intenttoedit);
            }
        };
    }

 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view);
        mySQLite = new MySQLite(this);

        //code von Davids TO DO Liste noch anpassen auf Tabellenansicht


        mySQLite.openDatabase();
        ChartRecyclerView = findViewById(R.id.chartRecyclerView);
        ChartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chartAdapter = new ChartAdapter (mySQLite,this,this);
        ChartRecyclerView.setAdapter(chartAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeHandlerChart(chartAdapter));
        itemTouchHelper.attachToRecyclerView(ChartRecyclerView);
        //fabAddChart = findViewById(R.id.fab);

        chartList = mySQLite.getAllOutgo();
            // chartList = mySQLite.getTaskByType(type);
        //Collections.reverse(taskList);
        chartAdapter.setChart(chartList);
        chartAdapter.notifyDataSetChanged();

    }*/

//Alte Version Tabellenansicht
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view);

        Intent intent = getIntent();
        ArrayList<Outgo> ListeOut = (ArrayList<Outgo>) intent.getSerializableExtra("monthlist");


        final HorizontalScrollView tblHeaderhorzScrollView = (HorizontalScrollView) findViewById(R.id.tblHeaderhorzScrollView);
        tblHeaderhorzScrollView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        tblHeaderhorzScrollView.setHorizontalScrollBarEnabled(false);

        final HorizontalScrollView horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        horizontalScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

            @Override
            public void onScrollChanged() {
                tblHeaderhorzScrollView.setScrollX(horizontalScrollView.getScrollX());
            }
        });


        //zeile
        this.fixedTableLayout = (TableLayout) findViewById(R.id.fixed_column);
        //SPalten die zu jeweiligen Zeile gehören
        this.scrollableTableLayout = (TableLayout) findViewById(R.id.scrollable_part);
        //Setzt die Spaltenbreite für die Tabelle.
        setTableHeaderWidth();
        //Befüllt die Tabelle mit Beispieldaten.
        fillTable();
    }

    private void setTableHeaderWidth() {
        TextView textView;
        textView = (TextView) findViewById(R.id.Ausgabe);
        setHeaderWidth(textView, COLUMN_WIDTHS[0]);
        textView = (TextView) findViewById(R.id.Wert);
        setHeaderWidth(textView, COLUMN_WIDTHS[1]);
        textView = (TextView) findViewById(R.id.Datum);
        setHeaderWidth(textView, COLUMN_WIDTHS[2]);

    }

    private void setHeaderWidth(TextView textView, int width) {
        textView.setWidth(width * getScreenWidth() / 100);
        textView.setHeight(FIXED_HEADER_HEIGHT);
    }

    private void fillTable() {

        Intent intent = getIntent();
        ArrayList<Outgo> ListeOut = (ArrayList<Outgo>) intent.getSerializableExtra("monthlist");

        Context ctx = getApplicationContext();
        int lenghtOutgos = ListeOut.size();
        //begrenzung der ABfrage durch lenghte von Datenbank noch einfügen.
        for (int position = 1; position < lenghtOutgos; position++) {
            //Daten aus Datenbank holen
            String outgo = ListeOut.get(position).getName();
            String value = Double.toString(ListeOut.get(position).getValue());
            Integer day = ListeOut.get(position).getDay();
            //hier sind die vergabe von monat und Jahr verdreht
            Integer month =ListeOut.get(position).getMonth();
            Integer year = ListeOut.get(position).getYear();
            String date = day+"."+month+"."+year;

            fixedTableLayout.addView(createTextView(outgo, COLUMN_WIDTHS[0], position));
            TableRow row = new TableRow(ctx);

            for (int col = 1; col < 2; col++) //1=Wert, 2=Datum , 3=Kategorie, 4=Sonstiges
            {
                //Wert
                row.addView(createTextView(value, COLUMN_WIDTHS[col], position));
                //Datum
                row.addView(createTextView(date, COLUMN_WIDTHS[col], position));
                //Kategorie
                //String Kategorie ="alles";
                //row.addView(createTextView(Kategorie, COLUMN_WIDTHS[col], position));
            }
            scrollableTableLayout.addView(row);
        }
    }

    private TextView createTextView(String text, int width, int index) {
        TextView textView = new TextView(getApplicationContext());
        textView.setText(text);
        textView.setWidth(width * getScreenWidth() / 100);
        textView.setHeight(CONTENT_ROW_HEIGHT);
        return textView;
    }

    private int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

*/


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

        switch (item.getItemId()){
            case R.id.itemMainPage:
                Intent switchToMain = new Intent(this, MainActivity.class);
                startActivity(switchToMain);
                return true;

            case R.id.itemAddIntakesOutgoes:
                mySQLite = new MySQLite(this);
                ArrayList<Category> categories = mySQLite.getAllCategory();
                Intent switchToAddEntry = new Intent(this, AddEntryActivity.class);
                switchToAddEntry.putExtra("list",categories);
                mySQLite.close();
                startActivityForResult(switchToAddEntry,REQUESTCODE_ADD);
                return true;

            case R.id.subitemIntakes:
                mySQLite = new MySQLite(this);
                ArrayList<Intake> intakes = mySQLite.getMonthIntakes(day,month,year);
                Intent getIntakes = new Intent(this, ShowEntriesActivity.class);
                getIntakes.putExtra("list",(Serializable) intakes);
                getIntakes.putExtra("entry","Intake");
                mySQLite.close();
                startActivityForResult(getIntakes, REQUESTCODE_SHOW);
                return true;

            case R.id.subitemOutgoes:
                mySQLite = new MySQLite(this);
                ArrayList<Outgo> outgoes = mySQLite.getMonthOutgos(day, month, year);
                Intent getOutgoes = new Intent(this, ShowEntriesActivity.class);
                getOutgoes.putExtra("list",(Serializable) outgoes);
                getOutgoes.putExtra("entry","Outgo");
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
                ArrayList<Outgo> AlloutgoD =mySQLite.getAllOutgo();
                switchToDiagramView.putExtra("dataOut",AlloutgoD);
                //Alle Einnahmen in Datenbank
                ArrayList<Intake> AllIntakeD =mySQLite.getAllIntakes();
                switchToDiagramView.putExtra("dataIn",AllIntakeD);
                mySQLite.close();
                startActivity(switchToDiagramView);
                return true;

            case R.id.itemTableView:
                mySQLite = new MySQLite(this);
                Intent switchToChartView = new Intent(this, ChartViewActivity.class);
                //Alle Ausgaben in Datenbank
                ArrayList<Outgo> AlloutgoT =mySQLite.getAllOutgo();
                switchToChartView.putExtra("dataOut",AlloutgoT);
                //Ausgaben von aktuellem Monat
                ArrayList<Outgo> outgoesT = mySQLite.getMonthOutgos(day,month,year);
                switchToChartView.putExtra("monthlist",outgoesT);
                //Alle Einnahmen in Datenbank
                ArrayList<Outgo> AllintakeT =mySQLite.getAllOutgo();
                switchToChartView.putExtra("dataIn",AllintakeT);
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
                switchToAddCategory.putExtra("list",(Serializable) categories1);
                mySQLite.close();
                startActivityForResult(switchToAddCategory, REQUESTCODE_ADD_CATEGORY);
                return true;

            case R.id.itemPdfCreator:
                Intent switchToPdfCreator = new Intent(this, PDFCreatorActivity.class);
                startActivity(switchToPdfCreator);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

  /*  @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        type = String.valueOf(spinner.getSelectedItem());
        AddNewEntry.setNewType(type);
        chartList = mySQLite.getAllOutgo();
        chartAdapter.setChart(chartList);
        chartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        chartList = mySQLite.getAllOutgo();
        Collections.reverse(chartList);
        chartAdapter.setChart(chartList);
        chartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTaskClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(chartAdapter.getContext());
        builder.setMessage("Haben Sie diese Aufgabe erledigt?");
        builder.setPositiveButton("Ja",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Glückwunsch! Mach weiter so!", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton("Abbruch", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chartAdapter.notifyItemChanged(position);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }*/
}

