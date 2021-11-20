package com.example.tabelle_listadapter;

import android.content.Context;
//import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AusgabeListAdapter extends ArrayList<Ausgabe> {

    private static final String TAG = "AugabeListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    private static class ViewHolder {
        TextView Ausgabe;
        TextView Wert;
        TextView Datum;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public AusgabeListAdapter(Context context, int resource, ArrayList<Ausgabe> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the Ausgabe information
        //getItem wir nicht erkannt
       /*String ausgabe = getItem(position).getAUsgabe();
        String wert = getItem(position).getWert();
        String datum = getItem(position).getDatum();
*/
        String ausgabe = "T1";
        String wert = "100";
        String datum = "11.11.2021";

        //Create the person object with the information
        Ausgabe ausgabeA = new Ausgabe(ausgabe, wert, datum);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvAusgabe = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvWert = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvDatum = (TextView) convertView.findViewById(R.id.textView3);

        tvAusgabe.setText(ausgabe);
        tvWert.setText(wert);
        tvDatum.setText(datum);

        return convertView;
    }}

    /*
        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.Ausgabe = (TextView) convertView.findViewById(R.id.textView1);
            holder.Wert = (TextView) convertView.findViewById(R.id.textView2);
            holder.Datum = (TextView) convertView.findViewById(R.id.textView3);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.Ausgabe.setText(Ausgabe.getAusgabe());
        holder.Wert.setText(Ausgabe.getWert());
        holder.Datum.setText(Ausgabe.getDatum());


        return convertView;
    }
}*/