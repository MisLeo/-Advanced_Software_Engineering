package com.example.haushaltsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haushaltsapp.database.Outgo;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{

    private ArrayList<Outgo> OutgoList;
    private RecyclerviewClickListener listener;

    public recyclerAdapter(ArrayList<Outgo> outogoList, RecyclerviewClickListener listener){
        this.OutgoList= outogoList;
        this.listener=listener;
    }
    public class MyViewHolder extends RecyclerView implements View.OnClickListener
    {
       private TextView OutText;
       private TextView OutValue;
       private TextView OutDate;
       private TextView OutCategorie;

       public MyViewHolder(final View view)
       {
           super(view);
           OutText =view.findViewById(R.id.Chartname);
           OutValue = view.findViewById(R.id.ChartValue);
           OutDate =view.findViewById(R.id.ChartDate);
           OutCategorie =view.findViewById(R.id.ChartCategorie);

           view.setOnContextClickListener((OnContextClickListener) this);
       }

        @Override
        public void onClick(View view) {

           listener.onClick(view,getAdpterPosition());
        }
    }

    //WIe position abfragen?
  /* private int getAdpterPosition() {
        int position=0;
        return position;
    }*/


    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chart_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {

        String name = OutgoList.get(position).getName();
        holder.OutText.setText(name);

        String value = Double.toString(OutgoList.get(position).getValue());
        holder.OutValue.setText(value);

        String day = Integer.toString(OutgoList.get(position).getDay());
        String month = Integer.toString(OutgoList.get(position).getMonth());
        String year = Integer.toString(OutgoList.get(position).getYear());
        String date = day+"."+month+"."+year;
        holder.OutDate.setText(date);

        String categorie = OutgoList.get(position).getCategory();
        holder.OutCategorie.setText(categorie);
    }

    @Override
    public int getItemCount() {
        return OutgoList.size();
    }

    public interface RecyclerviewClickListener
    {
        void onClick (View v, int position);
    }
}
