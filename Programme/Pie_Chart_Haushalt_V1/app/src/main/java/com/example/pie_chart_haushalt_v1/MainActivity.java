//https://www.geeksforgeeks.org/how-to-add-a-pie-chart-into-an-android-application/
//ohne Scrollview
//Übergabe von Double Werten geht noch nicht
package com.example.pie_chart_haushalt_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;


public class MainActivity extends AppCompatActivity {

    // Create the object of TextView
    TextView tvWohnen, tvLebensmittel, tvGesundheit, tvVerkehrsmittel, tvFreizeit, tvSonstiges;
    // create PieChart class
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //zuordnen zu objekten  id´s Textview in XML
            tvWohnen =findViewById(R.id.tvWohnen);
            tvLebensmittel = findViewById(R.id.tvLebensmittel);
            tvVerkehrsmittel = findViewById(R.id.tvVerkehrsmittel);
            tvGesundheit = findViewById(R.id.tvGesundheit);
            tvFreizeit = findViewById(R.id.tvFreizeit);
            tvSonstiges = findViewById(R.id.tvSonstiges);
            pieChart = findViewById(R.id.piechart);
            //Setzen der Texte im textview und pie Chart
            setData();


        }

    private void setData()
    {
        //Daten aus Datenbank:
        //die Daten müssen hier später aus der Datenbank geholt werden
        double Kosten_Wohnen = 500.99;
        double Kosten_Lebensmittel = 150;
        double Kosten_Verkehrsmittel = 120;
        double Kosten_Gesundheit = 30;
        double Kosten_Freizeit = 75;
        double Kosten_Sonstiges = 50;

        //Geldwert muss als Sting übergeben werden, da in Textview dargestellt wird
        //Direktes Einbinden von Geldwert in TV
        /*tvWohnen.setText(Double.toString(Kosten_Wohnen));
        tvLebensmittel.setText(Double.toString(Kosten_Lebensmittel));
        tvVerkehrsmittel.setText(Double.toString(Kosten_Verkehrsmittel));
        tvGesundheit.setText(Double.toString(Kosten_Gesundheit));
        tvFreizeit.setText(Double.toString(Kosten_Freizeit));
        tvSonstiges.setText(Double.toString(Kosten_Sonstiges));*/

        //zum Testen bei direkter EIngabe von Geldwerten
        tvWohnen.setText(Integer.toString((int) 1.99));
        tvLebensmittel.setText(Integer.toString(1));
        tvVerkehrsmittel.setText(Integer.toString(1));
        tvGesundheit.setText(Integer.toString(1));
        tvFreizeit.setText(Integer.toString(1));
        tvSonstiges.setText(Integer.toString(1));

        //Daten und Farben dem PieChart zuordnen
        //es geht noch nicht die Farbe aus colors.xml zu übernehmen
        pieChart.addPieSlice(
                new PieModel(
                        "Wohnen",
                        Integer.parseInt(tvWohnen.getText().toString()),
                        Color.parseColor("#66BB6A")));
                        //Color.parseColor("@color/Wohnen")));
        pieChart.addPieSlice(
                new PieModel(
                        "Lebensmittel",
                        Integer.parseInt(tvLebensmittel.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Verkehrsmittel",
                        Integer.parseInt(tvVerkehrsmittel.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Gesundheit",
                        Integer.parseInt(tvGesundheit.getText().toString()),
                        Color.parseColor("#29B6F6")));
        pieChart.addPieSlice(
                new PieModel(
                        "Freizeit",
                        Integer.parseInt(tvFreizeit.getText().toString()),
                        Color.parseColor("#A5B6DF")));
        pieChart.addPieSlice(
                new PieModel(
                        "Sonstiges",
                        Integer.parseInt(tvSonstiges.getText().toString()),
                        Color.parseColor("#FF3AFA")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}
