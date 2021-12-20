package com.example.haushaltsapp.ToDoListPackage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haushaltsapp.R;
import com.example.haushaltsapp.ToDoListActivity;
import com.example.haushaltsapp.database.MySQLite;

import java.util.List;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ViewHolder> {
    private List<TaskModel> chartList;
    private MySQLite db;
    private ToDoListActivity activity;
    private static ToDoInterface toDoInterface;

    public ChartAdapter(MySQLite db, ToDoListActivity activity, ToDoInterface toDoInterface) {
        this.db = db;
        this.activity = activity;
        this.toDoInterface = toDoInterface;
    }

    @NonNull
    @Override
    //Anzeigen der Tasks, Aufruf durch RecyclerView wenn neuer ViewHolder kreiert wird
    public ChartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new ChartAdapter.ViewHolder(itemView);
    }

    //Aufruf durch RecyclerView, um Verbindug zwischen ViewHolder und zugehörigen Daten zu generieren
    @Override
    public void onBindViewHolder(@NonNull final ChartAdapter.ViewHolder holder, int position) {
        db.openDatabase();
        final TaskModel item = chartList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        int previousStatus = item.getStatus();
        int adapterPosition = holder.getAdapterPosition();
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (previousStatus!=0){
                        db.updateStatus(item.getId(), 0);
                    }else {
                        db.updateStatus(item.getId(), 1);
                        toDoInterface.onTaskClick(adapterPosition);
                    }
                } else {
                    db.updateStatus(item.getId(), previousStatus);
                }
            }
        });
    }

    //Anzeige aller Elemente bis zum Ende der toDoListe
    @Override
    public int getItemCount() {
        int size = 0;
        if(chartList != null) {
            size = chartList.size();
        }
        return size;
    }

    private boolean toBoolean(int n) {
        return n != 0;
    }

    public Context getContext() {
        return activity;
    }

    public void setTasks(List<TaskModel> todoList) {
        this.chartList = todoList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        TaskModel item = chartList.get(position);
        db.deleteTask(item.getId());
        chartList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        TaskModel item = chartList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
            task.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
