package com.example.haushaltsappversion2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

public class Activity_Diagram extends AppCompatActivity {

    // Create the object of TextView
    // and PieChart class
    TextView tvWohnen, tvLebensmittel, tvGesundheit, tvVerkehrsmittel, tvFreizeit, tvSonstiges;
    PieChart pieChart;
    BarChart mBarChart;
    ValueLineChart LineChart;

    //Datenbank
    MySQLiteCategory dbcategory = new MySQLiteCategory(this, null, null, 0);
    MySQLiteIntake dbintake = new MySQLiteIntake(this, null, null, 0);
    MySQLiteOutgo dboutgo = new MySQLiteOutgo(this, null, null, 0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram);
        // Link those objects with their
        // respective id's that
        // we have given in .XML file

        tvWohnen =findViewById(R.id.tvWohnen);
        tvLebensmittel = findViewById(R.id.tvLebensmittel);
        tvVerkehrsmittel = findViewById(R.id.tvVerkehrsmittel);
        tvGesundheit = findViewById(R.id.tvGesundheit);
        tvFreizeit = findViewById(R.id.tvFreizeit);
        tvSonstiges = findViewById(R.id.tvSonstiges);
        //Setzen der Texte im textview und pie Chart und Barchart
        pieChart = findViewById(R.id.piechart);
        mBarChart = findViewById(R.id.barchart);
        LineChart= findViewById(R.id.linechart);

        // Creating a method setData()
        setData();
    }

    private void setData()
    {
        //Daten aus Datenbank:
        //die Daten müssen hier später aus der Datenbank geholt werden als float
        //Datum muss noch als zeitraum und nicht als Datum angegeben werden, sonst kann nur ein Tag geholt werden
        float Kosten_Wohnen = dboutgo.getValuesOutgosCategory(1,11,2021,"Wohnen"); //Hier noch zugriff auf Kategorie über sting realisieren
        float Kosten_Lebensmittel = dboutgo.getValuesOutgosCategory(1,11,2021,  "Lebensmittel");
        float Kosten_Verkehrsmittel = dboutgo.getValuesOutgosCategory(1,11,2021,  "Verkehrsmittel");
        float Kosten_Gesundheit = dboutgo.getValuesOutgosCategory(1,11,2021,  "Gesundheit");
        float Kosten_Freizeit = dboutgo.getValuesOutgosCategory(1,11,2021,  "Freizeit");
        float Kosten_Sonstiges = dboutgo.getValuesOutgosCategory(1,11,2021,  "Sonstiges");

        //Testdaten
       /* float Kosten_Wohnen = (float) 500.99;
        float Kosten_Lebensmittel = 150;
        float Kosten_Verkehrsmittel = 120;
        float Kosten_Gesundheit = 30;
        float Kosten_Freizeit = 75;
        float Kosten_Sonstiges = 50;
        */
        //Geldwert muss als Sting übergeben werden, da in Textview dargestellt wird
        //Direktes Einbinden von Geldwert in TV
        tvWohnen.setText(Float.toString(Kosten_Wohnen));
        tvLebensmittel.setText(Float.toString(Kosten_Lebensmittel));
        tvVerkehrsmittel.setText(Float.toString(Kosten_Verkehrsmittel));
        tvGesundheit.setText(Float.toString(Kosten_Gesundheit));
        tvFreizeit.setText(Float.toString(Kosten_Freizeit));
        tvSonstiges.setText(Float.toString(Kosten_Sonstiges));

        //zum Testen bei direkter EIngabe von Geldwerten
        /*tvWohnen.setText(Integer.toString(500));
        tvLebensmittel.setText(Integer.toString(150));
        tvVerkehrsmittel.setText(Integer.toString(120));
        tvGesundheit.setText(Integer.toString(30));
        tvFreizeit.setText(Integer.toString(75));
        tvSonstiges.setText(Integer.toString(100));*/

        //Daten und Farben dem PieChart zuordnen
        //es geht noch nicht die Farbe aus colors.xml zu übernehmen
        pieChart.addPieSlice(
                new PieModel(
                        "Wohnen",
                        Float.parseFloat(tvWohnen.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Lebensmittel",
                        Float.parseFloat(tvLebensmittel.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Verkehrsmittel",
                        Float.parseFloat(tvVerkehrsmittel.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Gesundheit",
                        Float.parseFloat(tvGesundheit.getText().toString()),
                        Color.parseColor("#29B6F6")));
        pieChart.addPieSlice(
                new PieModel(
                        "Freizeit",
                        Float.parseFloat(tvFreizeit.getText().toString()),
                        Color.parseColor("#A5B6DF")));
        pieChart.addPieSlice(
                new PieModel(
                        "Sonstiges",
                        Float.parseFloat(tvSonstiges.getText().toString()),
                        Color.parseColor("#FF3AFA")));
        pieChart.setInnerPaddingOutline(5);
        pieChart.setInnerPaddingOutline(5);

        // To animate the pie chart
        pieChart.startAnimation();
        pieChart.setBackgroundColor(0);


        //BarGraph
        BarChart mBarChart = (BarChart) findViewById(R.id.barchart);

        //float anlegen aus Datenbank holen
       /* mBarChart.addBar(new BarModel(Float.parseFloat(tvWohnen.getText().toString()),  Color.parseColor("#66BB6A")));
        mBarChart.addBar(new BarModel(Float.parseFloat(tvLebensmittel.getText().toString()), Color.parseColor("#FFA726")));
        mBarChart.addBar(new BarModel(Float.parseFloat(tvVerkehrsmittel.getText().toString()), Color.parseColor("#EF5350")));
        mBarChart.addBar(new BarModel(Float.parseFloat(tvGesundheit.getText().toString()), Color.parseColor("#29B6F6")));
        mBarChart.addBar(new BarModel(Float.parseFloat(tvFreizeit.getText().toString()), Color.parseColor("#A5B6DF")));
        mBarChart.addBar(new BarModel(Float.parseFloat(tvSonstiges.getText().toString()),Color.parseColor("#FF3AFA")));
        */
        mBarChart.addBar(new BarModel(Kosten_Wohnen,  Color.parseColor("#66BB6A")));
        mBarChart.addBar(new BarModel(Kosten_Lebensmittel, Color.parseColor("#FFA726")));
        mBarChart.addBar(new BarModel(Kosten_Verkehrsmittel, Color.parseColor("#EF5350")));
        mBarChart.addBar(new BarModel(Kosten_Gesundheit, Color.parseColor("#29B6F6")));
        mBarChart.addBar(new BarModel(Kosten_Freizeit, Color.parseColor("#A5B6DF")));
        mBarChart.addBar(new BarModel(Kosten_Sonstiges,Color.parseColor("#FF3AFA")));
        //letzter Balken hat keine Beschriftung??? Breite von Darstellung in XML
        //EIn Balken dann ohne Wert und Farbe einfügen?

        //mBarChart.callOnClick();
        mBarChart.startAnimation();
        mBarChart.setShowValues(true);  //keine Kommazahl darzustellen
        //mBarChart.setAccessibilityHeading(true);
        mBarChart.setActivated(false);

        //LineChart
        //für Monatsvergleich später verwenden
        //Benötigt Monat als Sting und Geldwert als Float

        ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);

        series.addPoint(new ValueLinePoint("Jan", Kosten_Wohnen));
        series.addPoint(new ValueLinePoint("Feb", Kosten_Lebensmittel));
        series.addPoint(new ValueLinePoint("Mar", Kosten_Verkehrsmittel));
        series.addPoint(new ValueLinePoint("Apr", Kosten_Gesundheit));
        series.addPoint(new ValueLinePoint("Mai", Kosten_Freizeit));
        series.addPoint(new ValueLinePoint("Jun", Kosten_Sonstiges));
        /*series.addPoint(new ValueLinePoint("Jul", 3.5f));
        series.addPoint(new ValueLinePoint("Aug", 2.4f));
        series.addPoint(new ValueLinePoint("Sep", 2.4f));
        series.addPoint(new ValueLinePoint("Oct", 3.4f));
        series.addPoint(new ValueLinePoint("Nov", .4f));
        series.addPoint(new ValueLinePoint("Dec", 1.3f));*/

        LineChart.addSeries(series);
        LineChart.startAnimation();

    }
}