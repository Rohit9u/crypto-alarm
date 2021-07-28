package adapters;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptoalarm.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import database.coin_model;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder> implements Filterable {
    List<coin_model> list;
    List<coin_model> ListAll;
    Context context;
    public recyclerViewAdapter(Context context,List<coin_model> models){
        this.context=context;
        ListAll=models;
        list=new ArrayList<>(models);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(v,context);
    }

    @Override
    public void onBindViewHolder( recyclerViewAdapter.ViewHolder holder, int position) {
        coin_model cm=list.get(position);
        holder.name.setText(cm.getName()+" ("+cm.getSymbol()+")");
        holder.price.setText("Rs. "+String.valueOf(cm.getCurrent_price()));
        String sdate=cm.getLast_updated();
        String sd=sdate.substring(11,19);
        String ftime=GMTtoIST(sd);
        holder.last_update.setText(ftime);
        holder.rank.setText(cm.getMarket_cap_rank());
        String la24=String.valueOf(cm.getPrice_change_percentage_24h());
        String fl="";
        if(la24.contains(".")){
            int x=la24.indexOf(".");
            fl=la24.substring(0,x);
            String r=la24.substring(x);
            if(r.length()>3){
                fl=fl+r.substring(0,3);
            }
        }
        holder.last24h.setText(fl);
        holder.last24h.setTextColor(String.valueOf(cm.getPrice_change_percentage_24h()).contains("-")?(Color.parseColor("#ff0004")):
                (Color.parseColor("#13d102")));
        Picasso.get().load(cm.getImage()).into(holder.icon);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<coin_model> searchList=new ArrayList<>();
            if(constraint.toString().isEmpty()){
                searchList.addAll(ListAll);
            }
            else{
                for(coin_model m:ListAll){
                    if(m.getName().toLowerCase().contains(constraint.toString().toLowerCase())){
                        searchList.add(m);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=searchList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
          list.clear();
          list.addAll((ArrayList<coin_model>)results.values);
          notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,price,last_update,last24h,rank;
        ImageView icon;
        public ViewHolder(View itemView,Context ctx) {
            super(itemView);
            context=ctx;
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            rank=itemView.findViewById(R.id.rank);
            last_update=itemView.findViewById(R.id.last_update);
            last24h=itemView.findViewById(R.id.xx);
            icon=itemView.findViewById(R.id.icon);
        }
    }

    public static String GMTtoIST(String x){
        String spl[]=x.split(":");
        int m=Integer.parseInt(spl[1]);
        int h=Integer.parseInt(spl[0]);
        String ss=spl[2];
        m+=30;
        if(m>59) {
            m=m-60;
            h++;
        }
        h+=5;
        if(h>23) {
            h=24-h;
        }
        String mm=String.valueOf(m);
        if(mm.length()==1) {
            mm="0"+m;
        }
        String hh=String.valueOf(h);
        if(hh.length()==1) {
            hh="0"+hh;
        }
        String ftime=hh+":"+mm+":"+ss;
        return ftime;
    }




}
