// mit ScollView
//https://www.geeksforgeeks.org/how-to-add-a-pie-chart-into-an-android-application/

package com.example.piechart_geeksforgeeks;

import androidx.appcompat.app.AppCompatActivity;
// Import the required libraries
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // Create the object of TextView
    // and PieChart class
    TextView tvWohnen, tvLebensmittel, tvGesundheit, tvVerkehrsmittel, tvFreizeit, tvSonstiges;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Link those objects with their
        // respective id's that
        // we have given in .XML file

        tvWohnen =findViewById(R.id.tvWohnen);
        tvLebensmittel = findViewById(R.id.tvLebensmittel);
        tvVerkehrsmittel = findViewById(R.id.tvVerkehrsmittel);
        tvGesundheit = findViewById(R.id.tvGesundheit);
        tvFreizeit = findViewById(R.id.tvFreizeit);
        tvSonstiges = findViewById(R.id.tvSonstiges);

        pieChart = findViewById(R.id.piechart);

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData();
    }

    private void setData()
    {

        // Set the percentage of language used
        tvWohnen.setText(Integer.toString(500));
        tvLebensmittel.setText(Integer.toString(150));
        tvVerkehrsmittel.setText(Integer.toString(120));
        tvGesundheit.setText(Integer.toString(30));
        tvFreizeit.setText(Integer.toString(75));
        tvSonstiges.setText(Integer.toString(100));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Wohnen",
                        Integer.parseInt(tvWohnen.getText().toString()),
                        Color.parseColor("#66BB6A")));
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