package com.example.haushaltsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.haushaltsapp.database.Category;
import com.example.haushaltsapp.database.Intake;
import com.example.haushaltsapp.database.MySQLite;
import com.example.haushaltsapp.database.Outgo;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DiagramViewActivity extends AppCompatActivity {

    ////Variabeln zur Menünavigation
    private MySQLite mySQLite;
    private final int REQUESTCODE_ADD = 12; //AddEntryActivity
    private final int REQUESTCODE_SHOW = 13; //ShowEntryActivity
    private final int REQUESTCODE_EDIT = 14; //EditEntryActivity
    private final int REQUESTCODE_ADD_CATEGORY = 15; //AddCategoryActivity
    ///////////////////////////////

    private MySQLite db;

    private Button changeToAnnual;

    //noch erweitern um tv+, bei zufügen von weiteren Kategorien
    private TextView tvWohnen, tvLebensmittel, tvGesundheit, tvVerkehrsmittel, tvFreizeit, tvSonstiges;
    private PieChart pieChart;
    private BarChart mBarChart;

    //aktuelles Datum
    private int day;
    private int month;
    private int year;

    private EditText editTextDate; //Datum
    private String dates;

    //zur Jahresansicht
    //private static final int REQUESTCODE = 30;


    private void getDate(){
        java.util.Calendar calender = java.util.Calendar.getInstance();
        SimpleDateFormat datumsformat = new SimpleDateFormat("dd.MM.yyyy");
        String dates = datumsformat.format(calender.getTime());
        day = Integer.parseInt(dates.substring(0,2));
        month = Integer.parseInt(dates.substring(3,5));
        year = Integer.parseInt(dates.substring(6,10));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram_view);
        changeToAnnual = findViewById(R.id.changeView);

        db = new MySQLite(this);
        db.openDatabase();
        //Erhalte das aktuelle Datum
        getDate();

        //Aktuelles Datum anzeigen
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        java.util.Calendar calender = Calendar.getInstance();
        SimpleDateFormat datumsformat = new SimpleDateFormat("dd.MM.yyyy");
        editTextDate.setText(datumsformat.format(calender.getTime()));

        setData();
    }

    private void setData() {

        //Datum von Textfeld
        dates = editTextDate.getText().toString();
        day = Integer.parseInt(dates.substring(0,2));
        month = Integer.parseInt(dates.substring(3,5));
        year = Integer.parseInt(dates.substring(6,10));

        //Setzen der Texte im textview und pie Chart und Barchart
        tvWohnen =findViewById(R.id.tvWohnen);
        tvLebensmittel = findViewById(R.id.tvLebensmittel);
        tvVerkehrsmittel = findViewById(R.id.tvVerkehrsmittel);
        tvGesundheit = findViewById(R.id.tvGesundheit);
        tvFreizeit = findViewById(R.id.tvFreizeit);
        tvSonstiges = findViewById(R.id.tvSonstiges);

        pieChart = findViewById(R.id.piechart);
        mBarChart = findViewById(R.id.barchart);

        //hier noch durch Schleife ergänzen um hinzugefügte Kategorien auch abzudecken
        /*ArrayList<Category> Categories= db.getAllCategory();
        int numberCat =Categories.size();
        int n =0;
        float Kosten_Wohnen =0, Kosten_Lebensmittel =0,Kosten_Verkehrsmittel =0,Kosten_Gesundheit=0,Kosten_Freizeit=0,Kosten_Sonstiges=0;
        float Kosten_etc =0;

        int Catcolor =0;
        String Cat ="";

        if ( n < numberCat)
        {
            switch (n)
            {
                //Zugriff auf COlor und Kategorie funktioniert noch nicht!!!
                //Catcolor = Categories.get(n).getColor();
                //Cat = Categories.get(n).getName_PK();
                //zuweisen der Categorie in der Datenbank geht nicht über die obere Abfrage
                case 0:
                    Kosten_Wohnen = db.getCategorieOutgosMonth(day,month,year,"Wohnen" );
                    break;
                case 1:
                    Kosten_Lebensmittel = db.getCategorieOutgosMonth(day,month,year,"Lebensmittel" );
                    break;
                case 2:
                    Kosten_Verkehrsmittel = db.getCategorieOutgosMonth(day,month,year,"Verkehrsmittel" );
                    break;
                case 3:
                    Kosten_Gesundheit = db.getCategorieOutgosMonth(day,month,year,"Gesundheit" );
                    break;
                case 4:
                    Kosten_Freizeit = db.getCategorieOutgosMonth(day,month,year,"Freizeit" );
                    break;
                case 5:
                    Kosten_Sonstiges = db.getCategorieOutgosMonth(day,month,year, "Sonatiges" );
                    break;
                //case 6:
                  //  Kosten_etc= db.getCategorieOutgosMonth(day,month,year,Cat);
                    //break;
            }
         n=n+1;
        }*/

        float Kosten_Wohnen = (float) db.getCategorieOutgosMonth(day,month,year,"Wohnen");
        float Kosten_Lebensmittel = (float)db.getCategorieOutgosMonth(day,month,year,"Lebensmittel");
        float Kosten_Verkehrsmittel = (float)db.getCategorieOutgosMonth(day,month,year,"Verkehrsmittel");
        float Kosten_Gesundheit = (float)db.getCategorieOutgosMonth(day,month,year,"Gesundheit");
        float Kosten_Freizeit = (float) db.getCategorieOutgosMonth(day,month,year,"Freitzeit");
        float Kosten_Sonstiges = (float) db.getCategorieOutgosMonth(day,month,year,"Sonstiges");



        //Direktes Einbinden von Geldwert in TV
        tvWohnen.setText(Float.toString(Kosten_Wohnen));
        tvLebensmittel.setText(Float.toString(Kosten_Lebensmittel));
        tvVerkehrsmittel.setText(Float.toString(Kosten_Verkehrsmittel));
        tvGesundheit.setText(Float.toString(Kosten_Gesundheit));
        tvFreizeit.setText(Float.toString(Kosten_Freizeit));
        tvSonstiges.setText(Float.toString(Kosten_Sonstiges));

        //Diagramme zurücksetzten
        pieChart.clearChart();
        mBarChart.clearChart();
        //Diagram Methoden aufrufen
        PieChartKat(Kosten_Wohnen, Kosten_Lebensmittel, Kosten_Verkehrsmittel, Kosten_Gesundheit, Kosten_Freizeit, Kosten_Sonstiges);
        BarGraphKat(Kosten_Wohnen, Kosten_Lebensmittel, Kosten_Verkehrsmittel, Kosten_Gesundheit, Kosten_Freizeit, Kosten_Sonstiges);

    }
    public void PieChartKat (float Wohnen,float Lebensmittel, float Verkehrsmittel, float Gesundheit, float Freizeit,float Sonstiges)
    {
        //Daten und Farben dem PieChart zuordnen
        //es geht noch nicht die Farbe aus colors.xml zu übernehmen

        pieChart.addPieSlice(
                new PieModel(
                        "Wohnen",
                        Wohnen,
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Lebensmittel",
                        Lebensmittel,
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Verkehrsmittel",
                        Verkehrsmittel,
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Gesundheit",
                        Gesundheit,
                        Color.parseColor("#29B6F6")));
        pieChart.addPieSlice(
                new PieModel(
                        "Freizeit",
                        Freizeit,
                        Color.parseColor("#A5B6DF")));
        pieChart.addPieSlice(
                new PieModel(
                        "Sonstiges",
                        Sonstiges,
                        Color.parseColor("#FF3AFA")));
        pieChart.setInnerPaddingOutline(5);
        pieChart.setInnerPaddingOutline(5);

        // To animate the pie chart
        pieChart.startAnimation();
        pieChart.setBackgroundColor(0);
    }

    public void BarGraphKat(float Wohnen,float Lebensmittel, float Verkehrsmittel, float Gesundheit, float Freizeit,float Sonstiges)
    {
        mBarChart.addBar(new BarModel(
                "Wohnen",//Über weite in activity einstellen, welcher Text lesbar ist
                Wohnen,
                Color.parseColor("#66BB6A")));
        mBarChart.addBar(new BarModel(
                "Lebensmittel",
                Lebensmittel,
                Color.parseColor("#FFA726")));
        mBarChart.addBar(new BarModel(
                "Verkehrsmittel",
                Verkehrsmittel,
                Color.parseColor("#EF5350")));
        mBarChart.addBar(new BarModel(
                "Gesundheit",
                Gesundheit,
                Color.parseColor("#29B6F6")));
        mBarChart.addBar(new BarModel(
                "Freizeit",
                Freizeit,
                Color.parseColor("#A5B6DF")));
        mBarChart.addBar(new BarModel(
                "Sonstiges",
                Sonstiges,
                Color.parseColor("#FF3AFA")));
        //letzter Balken hat keine Beschriftung??? Breite von Darstellung in XML
        //EIn Balken dann ohne Wert und Farbe einfügen?

        //mBarChart.callOnClick();
        mBarChart.startAnimation();
        mBarChart.setShowValues(true);  //keine Kommazahl darzustellen
        //mBarChart.setAccessibilityHeading(true);
        mBarChart.setActivated(false);
    }


    public void changeMonth(View view)
    {
        setData();
    }
    //Link zu Jahresansicht
    //Platzierung noch ändern
    public void changeToAnnual(View view) {

        Intent intent = getIntent();
        ArrayList<Outgo> Data = (ArrayList<Outgo>) intent.getSerializableExtra("dataOut");
        ArrayList<Intake> DataIn = (ArrayList<Intake>) intent.getSerializableExtra("dataIn");

        Intent switchToAnnualView= new Intent(this, AnnualViewActivity.class);
        ArrayList<Outgo> AlloutgoD =Data;
        switchToAnnualView.putExtra("dataOut",AlloutgoD);
        ArrayList<Intake> AllIntakes =DataIn;
        switchToAnnualView.putExtra("dataIn",AllIntakes);
        startActivity(switchToAnnualView);
        //noch Datenbank mitgeben
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);

        //Die aktuelle Activity im Menü ausblenden
        MenuItem item = menu.findItem(R.id.itemDiagramView);
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

}