package com.example.haushaltsplaner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class ShowOutgosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_outgos);

        TextView tv = (TextView) findViewById(R.id.textViewOutgoes);

        Intent intent = getIntent();
        List<Outgo> list = (List<Outgo>) intent.getSerializableExtra("list");
        String text = "Alle Ausgaben des aktuellen Monats:";

        for (int i = 0; i < list.size(); i++) {
            text = text + " '\n' " + list.get(i).toString();
        }
        tv.setText(text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.showoutgoes_menu, menu);
        return true;
    }

    public void changeEntry(View view) {
        EditText id = (EditText) findViewById(R.id.textViewEditText);

        int valueId = -1;
        if (!TextUtils.isEmpty(id.getText())) {
            valueId = Integer.parseInt(id.getText().toString());
        }
        Intent intent = new Intent();
        intent.putExtra("entry", "outgo");
        intent.putExtra("id", valueId);
        setResult(RESULT_OK, intent);
        super.finish();
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

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
                Intent switchToCalander = new Intent(this, Calendar.class);
                startActivity(switchToCalander);
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