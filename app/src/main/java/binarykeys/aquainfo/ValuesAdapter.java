package binarykeys.aquainfo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

/**
 * Created by ACER on 10/6/2017.
 */

public class ValuesAdapter extends RecyclerView.Adapter<ValuesAdapter.ViewHolder> {

    private Context context;
    private List<Values> values;
    private int lastPosition = -1;

    public ValuesAdapter(Context context, List<Values> values) {
        this.context = context;
        this.values = values;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ValuesAdapter.ViewHolder holder, final int position) {
        String PH = values.get(position).getPh().trim();
        String d_o = values.get(position).getD_o().trim();
        final String id = "    " + values.get(position).getId().trim();
        String temperature = values.get(position).getTemperature().trim() + "°C";
        String turbiidty = values.get(position).getTurbidity().trim();
        String time = values.get(position).getTime().trim();
        time = time.substring(time.indexOf(" "), time.length());
        final String latitude=values.get(position).getLatitude().trim();
        final String longitude=values.get(position).getLongitude().trim();
        String latitude2="latitude: "+latitude;
        String longitude2= "longitude: "+longitude;
        String orp = values.get(position).getOrp().trim() + "mV";
        final String conductivity = values.get(position).getConductivity().trim();
        String salinity = values.get(position).getSalinity().trim();
        String rChlorine = values.get(position).getrChlorine().trim();
        String eColi = values.get(position).geteColi().trim();
        String flouride = values.get(position).getFluoride().trim();
        String arsenic = values.get(position).getArsenic().trim();

        holder.id.setText(id);
        holder.temperature.setText(temperature);
        holder.d_o.setText(d_o);
        holder.turbidity.setText(turbiidty);
        holder.conductivity.setText(conductivity);
        holder.ph.setText(PH);
        holder.time.setText(time);
        holder.orp.setText(orp);
        holder.salinity.setText(salinity);
        holder.rCl.setText(rChlorine);
        holder.Ecoli.setText(eColi);
        holder.fl.setText(flouride);
        holder.as.setText(arsenic);
        holder.cardofgrid.setVisibility(View.GONE);
        holder.latitude.setText(latitude2);
        holder.longitude.setText(longitude2);
        holder.viewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, MapsActivity.class);
                i.putExtra("latitude",latitude);
                i.putExtra("longitude",longitude);
                i.putExtra("id",id);
                try{
                    context.startActivity(i);
                }catch (Exception e){
                    Toast.makeText(context,"Some Error Occured",Toast.LENGTH_LONG).show();
                }
            }
        });

        float mn = Float.parseFloat(PH);

        if (mn < 6 || mn > 8) {
            holder.id.setBackgroundColor(ContextCompat.getColor(this.context, R.color.Red));
        } else {
            holder.id.setBackgroundColor(ContextCompat.getColor(this.context, R.color.green));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.cardofgrid.getVisibility() == View.GONE) {

                    holder.cardView.animate().translationX(30).setDuration(500).setInterpolator(new AnticipateInterpolator()).start();
                    holder.cardofgrid.setVisibility(View.VISIBLE);
                } else {
                    holder.cardView.animate().translationX(-30).setDuration(500).setInterpolator(new AnticipateInterpolator()).start();
                    holder.cardofgrid.setVisibility(View.GONE);
                }
            }
        });

      //  setAnimation(holder.itemView,position);


    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView id;
        TextView temperature;
        TextView d_o;
        TextView turbidity;
        TextView conductivity;
        TextView orp;
        TextView time;
        TextView ph;
        TextView salinity;
        TextView rCl;
        TextView Ecoli;
        TextView fl;
        TextView as;
        LinearLayout drinkable;
        GridLayout grid;
        CardView cardView;
        TextView latitude;
        TextView longitude;
        Button viewMap;
        CardView cardofgrid;
        public ViewHolder(View itemView) {
            super(itemView);
            cardofgrid=itemView.findViewById(R.id.carder);
            salinity = itemView.findViewById(R.id.salinity);
            rCl = itemView.findViewById(R.id.rcl);
            Ecoli = itemView.findViewById(R.id.ecoli);
            fl = itemView.findViewById(R.id.fl);
            as = itemView.findViewById(R.id.as);
            cardView = itemView.findViewById(R.id.cardHeading);
            grid = itemView.findViewById(R.id.grid);
            ph = itemView.findViewById(R.id.ph);
            temperature = itemView.findViewById(R.id.temperature);
            id = itemView.findViewById(R.id.id);
            turbidity = itemView.findViewById(R.id.turbidity);
            d_o = itemView.findViewById(R.id.d_o);
            time = itemView.findViewById(R.id.time_stamp);
            drinkable = itemView.findViewById(R.id.drinkable_color);
            conductivity = itemView.findViewById(R.id.conductivity);
            orp = itemView.findViewById(R.id.orp);
            latitude=itemView.findViewById(R.id.latitude);
            longitude=itemView.findViewById(R.id.longitude);
            viewMap=itemView.findViewById(R.id.viewmap);

        }

        @Override
        public void onClick(View view) {

        }
    }

//    private void setAnimation(View viewToAnimate, int position)
//    {
//        // If the bound view wasn't previously displayed on screen, it's animated
//        if (position > lastPosition)
//        {
//            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
//            viewToAnimate.startAnimation(animation);
//            lastPosition = position;
//        }
//    }
}
