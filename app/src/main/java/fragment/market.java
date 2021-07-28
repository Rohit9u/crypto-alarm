package fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cryptoalarm.AsyncResponse;
import com.example.cryptoalarm.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import adapters.recyclerViewAdapter;
import database.coin_model;

public class market extends Fragment{
    RecyclerView recyclerView;
    static List<coin_model> listPad=new ArrayList<>();
    recyclerViewAdapter adapter;
    ProgressBar progressBar;
    SearchView sView;
    Boolean ref;
    SwipeRefreshLayout layout;
    static String api="https://api.coingecko.com/api/v3/coins/markets?vs_currency=inr&order=market_cap_desc&per_page=250&page=1&sparkline=false";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_market, container, false);
        recyclerView=v.findViewById(R.id.Recycler);
        progressBar=v.findViewById(R.id.pbar);
        sView=v.findViewById(R.id.search);
        layout=v.findViewById(R.id.swipeandS);
        ref=true;
        getInfo inf0= new getInfo();
        inf0.execute(api);
        ref=false;
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new getInfo().execute(api);
                layout.setRefreshing(false);
            }
        });

        //content();
        return v;
    }
//    public void refresh(int time){
//        Handler handler=new Handler();
//        Runnable runnable=new Runnable() {
//            @Override
//            public void run() {
//              content();
//            }
//        };
//        handler.postDelayed(runnable,time);
//    }
//
//     public void content(){
//          getInfo inf0= new getInfo();
//          inf0.execute(api);
//          refresh(1000);
//     }

    public class getInfo extends AsyncTask<String,Integer,String>{
        public List<coin_model> lists=new ArrayList<>();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(ref==true){
                progressBar.setVisibility(View.VISIBLE);
            }

        }

        @Override
        protected String doInBackground(String... strings) {
            String s=strings[0];
            StringBuilder builder=new StringBuilder();
            HttpURLConnection connection=null;
            URL url= null;
            try {
                url = new URL(s);
                connection=(HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                BufferedReader bdr=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String input;
                while((input=bdr.readLine())!=null){
                    builder.append(input);
                }
            } catch (MalformedURLException e) {
                //Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
               // Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return builder.toString();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
            String sr=s;
            try {

                //JSONObject obxj=new JSONObject(sr);
                JSONArray arr=new JSONArray(sr);
                for(int i=0;i<arr.length();i++){
                    coin_model models=new coin_model();
                    JSONObject obj=arr.getJSONObject(i);
                    models.setName(obj.getString("name"));
                    models.setCurrent_price(obj.getString("current_price"));
                    models.setId(obj.getString("id"));
                    models.setImage(obj.getString("image"));
                    models.setPrice_change_percentage_24h(obj.getString("price_change_24h"));
                    models.setSymbol(obj.getString("symbol"));
                    models.setMarket_cap_rank(obj.getString("market_cap_rank"));
                    models.setLast_updated(obj.getString("last_updated"));
                    lists.add(models);
                   }
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                adapter = new recyclerViewAdapter(getActivity(), lists);
                recyclerView.setLayoutManager(manager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
                sView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        adapter.getFilter().filter(newText);
                        return false;
                    }
                });


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}