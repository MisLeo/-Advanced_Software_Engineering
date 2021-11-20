//https://www.bing.com/videos/search?q=android+studio+table+layout+scrollview&&view=detail&mid=BE4C1BBF0D76E49950FBBE4C1BBF0D76E49950FB&&FORM=VRDGAR&ru=%2Fvideos%2Fsearch%3Fq%3Dandroid%2520studio%2520table%2520layout%2520scrollview%26qs%3Dn%26form%3DQBVR%26sp%3D-1%26pq%3Dandroid%2520studio%2520table%2520layout%2520scrollview%26sc%3D0-38%26sk%3D%26cvid%3DD9E6E18991064E72B1C577E5CB1A8BA9
//https://draeger-it.blog/android-scrollbare-tabelle-mit-festem-tabellenkopf/#

package com.example.haushaltsappversion2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//noch kein zugriff auf Datenbank
//Einteilung von Spalten noch vornehmen
public class Activity_Tabelle extends AppCompatActivity {

    private final static int[] COLUMN_WIDTHS = new int[]{30, 20, 20, 20, 10, 10};//Spaltenbreite
    //private final static float[] COLUMN_WIDTHS = new float[][]{30, 20, 20, 20, 10, 10};//Spaltenbreite
    private final static int CONTENT_ROW_HEIGHT = 80;
    private final static int FIXED_HEADER_HEIGHT = 60;

    private TableLayout fixedTableLayout;
    private TableLayout scrollableTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabelle);

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


        this.fixedTableLayout = (TableLayout) findViewById(R.id.fixed_column);
        this.scrollableTableLayout = (TableLayout) findViewById(R.id.scrollable_part);
        //Setzt die Spaltenbreite für die Tabelle.
        setTableHeaderWidth();
        //Befüllt die Tabelle mit Beispieldaten.
        fillTable();
    }

    private void setTableHeaderWidth() {
        TextView textView;
        textView = (TextView) findViewById(R.id.col0);
        setHeaderWidth(textView, COLUMN_WIDTHS[0]);
        textView = (TextView) findViewById(R.id.col1);
        setHeaderWidth(textView, COLUMN_WIDTHS[1]);
        textView = (TextView) findViewById(R.id.col2);
        setHeaderWidth(textView, COLUMN_WIDTHS[2]);
        textView = (TextView) findViewById(R.id.col3);
        setHeaderWidth(textView, COLUMN_WIDTHS[3]);
        textView = (TextView) findViewById(R.id.col4);
        setHeaderWidth(textView, COLUMN_WIDTHS[4]);
        textView = (TextView) findViewById(R.id.col5);
        setHeaderWidth(textView, COLUMN_WIDTHS[5]);
    }

    private void setHeaderWidth(TextView textView, int width) {
        textView.setWidth(width * getScreenWidth() / 100);
        textView.setHeight(FIXED_HEADER_HEIGHT);
    }

    private void fillTable() {
        Context ctx = getApplicationContext();
        for (int i = 1; i <= 100; i++) {
            fixedTableLayout.addView(createTextView(String.valueOf(i), COLUMN_WIDTHS[1], i));
            TableRow row = new TableRow(ctx);
            for (int col = 1; col <= 5; col++) {
                row.addView(createTextView(String.valueOf(col), COLUMN_WIDTHS[col], i));
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
}
