package com.example.diagram_ohne_kat;

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


public class MainActivity extends AppCompatActivity {

    // Create the object of TextView
    // and PieChart class
    TextView tvAusgaben, tvEinnahmen, tvRestbudget;
    PieChart pieChart;
    BarChart mBarChart;
    ValueLineChart LineChart;

    //Datenbank
    //musst du dann noch selbst auf die richtige verweisen
    //MySQLiteCategory dbcategory = new MySQLiteCategory(this, null, null, 0);
    //MySQLiteIntake dbintake = new MySQLiteIntake(this, null, null, 0);
    //MySQLiteOutgo dboutgo = new MySQLiteOutgo(this, null, null, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setzen der Texte im textview und pie Chart und Barchart
        tvEinnahmen=findViewById(R.id.tvEinnahmen);
        tvAusgaben=findViewById(R.id.tvAusgaben);
        tvRestbudget=findViewById(R.id.tvRestbudget);
        pieChart = findViewById(R.id.piechart);
        mBarChart = findViewById(R.id.barchart);
        LineChart= findViewById(R.id.linechart);

        // Creating a method setData()
        setData();
    }

    private void setData()
    {
        //Daten aus Datenbank:
        //Datum muss noch als zeitraum und nicht als Datum angegeben werden, sonst kann nur ein Tag geholt werden

        //MEthode noch alte Methode von Kategorie, neue noch ergänzen
        //float Ausgaben =dboutgo.getValuesOutgosCategory(1,11,2021,  "Sonstiges");
        //float Einnahmen =dboutgo.getValuesOutgosCategory(1,11,2021,  "Sonstiges");
        //Testdaten
        float Ausgaben =888.0f;
        float Einnahmen =1000.0f;

        //Eventuell noch differenz für Restbudget
        float Restbudget = Einnahmen-Ausgaben;

        //Setzen von EInnahmen und Ausgaben als Stirng in Textview
        tvEinnahmen.setText(Float.toString(Einnahmen));
        tvAusgaben.setText(Float.toString(Ausgaben));
        tvRestbudget.setText(Float.toString(Restbudget));
        //zum Testen bei direkter EIngabe von Geldwerten
        //tvEinnahmen.setText(Integer.toString(1000));
        //tvAusgaben.setText(Integer.toString(888));
        //tvRestbudget.setText(Integer.toString(112));

        //Daten und Farben dem PieChart zuordnen
        //es geht noch nicht die Farbe aus colors.xml zu übernehmen
        pieChart.addPieSlice(

                new PieModel(
                        "Einnahmen",
                        Float.parseFloat(tvEinnahmen.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Ausgaben",
                        Float.parseFloat(tvAusgaben.getText().toString()),
                        Color.parseColor("#EF5350")));

        pieChart.setInnerPaddingOutline(5);
        pieChart.setInnerPaddingOutline(5);

        // To animate the pie chart
        pieChart.startAnimation();
        pieChart.setBackgroundColor(0);


        //BarGraph
        BarChart mBarChart = (BarChart) findViewById(R.id.barchart);

        mBarChart.addBar(new BarModel(Einnahmen,  Color.parseColor("#66BB6A")));
        mBarChart.addBar(new BarModel(Ausgaben, Color.parseColor("#EF5350")));
        mBarChart.addBar(new BarModel(Restbudget, Color.parseColor("#FFA726")));

        mBarChart.startAnimation();
        mBarChart.setShowValues(true);  //keine Kommazahl darzustellen auf Balken
        mBarChart.setActivated(false);

        //LineChart
        //für Monatsvergleich später verwenden
        //Benötigt Monat als Sting und Geldwert als Float

        ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);

        //Beschreibung
        //series.addPoint(new ValueLinePoint("Achsenbeschriftung", floatwert Übergeben));

        //Hier kannst du in den Werten noch die einzelenen Monate im Vergleich zueinander darstellen.
        //als float wert den Monatswert übergeben

        series.addPoint(new ValueLinePoint("Jan", 2.0f));
        series.addPoint(new ValueLinePoint("Feb", 1.0f));
        series.addPoint(new ValueLinePoint("Mar", 1.5f));
        series.addPoint(new ValueLinePoint("Apr", 2.0f));
        series.addPoint(new ValueLinePoint("Mai", 0.5f));
        series.addPoint(new ValueLinePoint("Jun", 4.0f));
        series.addPoint(new ValueLinePoint("Jul", 3.5f));
        series.addPoint(new ValueLinePoint("Aug", 2.4f));
        series.addPoint(new ValueLinePoint("Sep", 2.4f));
        series.addPoint(new ValueLinePoint("Oct", 3.4f));
        series.addPoint(new ValueLinePoint("Nov", .4f));
        series.addPoint(new ValueLinePoint("Dec", 1.3f));

        LineChart.addSeries(series);
        LineChart.startAnimation();

    }
}
