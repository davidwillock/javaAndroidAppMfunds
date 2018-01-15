package com.example.administrator.mfunds;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import android.app.Activity;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;


public class MListActivity extends Activity {

    private static String jsonRelayontoScroll;
    private static String strTerm;
    private static String strInvest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mlist);


        MySQLiteHelper db  = new MySQLiteHelper(MListActivity.this);


        jsonRelayontoScroll = getIntent().getExtras().getString("THEJSON");
        strTerm = getIntent().getExtras().getString("term");
        strInvest = getIntent().getExtras().getString("invest");




      List<PerformanceStats> plist = db.getPerformanceDataList();

        String res =jsonRelayontoScroll;

        PerformanceStats stats = new PerformanceStats();

try {


    JSONObject jsonObj = new JSONObject(res);
    JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");
    JSONArray ja_dataReturns = ja_obj.getJSONArray("Returns");


    JSONArray ja_dataPerformance = ja_obj.getJSONArray("Performance");


    for (int i = 0; i < ja_dataPerformance.length(); i++) {

        JSONObject rootObj = ja_dataPerformance.getJSONObject(i);

        String sym = rootObj.getString("symbol");
        String inceptdate = rootObj.getString("InceptionDate");
        double mer = rootObj.getDouble("Mer");
        double assets = rootObj.getDouble("Mer");
        double rank = rootObj.getDouble("Rank");
        double mstarrating = rootObj.getDouble("MstarRating");
        double stddev = rootObj.getDouble("StdDev");
        double volatilerank = rootObj.getDouble("VolatileRank");
        double mstarrisk = rootObj.getDouble("MstarRisk");
        double alpha = rootObj.getDouble("Alpha");
        double beta = rootObj.getDouble("Beta");
        double rsquare = rootObj.getDouble("Rsquare");
        String rrspel = rootObj.getString("RRSPEligibility");
        String load = rootObj.getString("Load");
        double maxfrontend = rootObj.getDouble("MaxFrontEnd");
        double maxbackend = rootObj.getDouble("MaxBackEnd");
        String salesopen = rootObj.getString("SalesOpen");
        double navps = rootObj.getDouble("NavPs");
        double netasset = rootObj.getDouble("NetAsset");
        double yield = rootObj.getDouble("Yield");
        double dividend = rootObj.getDouble("Dividend");
        String manager = rootObj.getString("Manager");
        double Fees = rootObj.getDouble("Fees");
        String FullName = rootObj.getString("FundName");





        stats.getSymID();


        stats = new PerformanceStats(sym,inceptdate,assets,mer,rank,mstarrating,stddev,volatilerank,mstarrisk,alpha,beta,rsquare,rrspel,load,maxfrontend,maxbackend,salesopen,navps,netasset,yield,dividend,manager,Fees,FullName);



    }


}catch(Exception ex){




}






         plist = db.getPerformanceDataList();







        String[] slist = new String[plist.size()];




        int i=0;
        for(PerformanceStats list : plist) {

            slist[i] = list.getSymID();




            i++;
        }




       final ListView lv;


        lv=(ListView) findViewById(R.id.lvfunds);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, slist);



        lv.setAdapter(adapter);




        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int itemPosition     = position;
                String  itemValue    = (String) lv.getItemAtPosition(position);






                Bundle bundle = new Bundle();
                bundle.putString("FUND",itemValue);
                bundle.putString("JSON",jsonRelayontoScroll);
                bundle.putString("LGITERM",strTerm);
                bundle.putString("LGIINVEST",strInvest);

                Intent intent = new Intent(MListActivity.this,ScrollActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);




            }
        } );














    }

    private void populateFundList() {


        // Construct the data source
        ArrayList<Lfund> arrayOfUsers=null;

        arrayOfUsers.clear();

        arrayOfUsers= Lfund.getFunds();


        for(Lfund item : arrayOfUsers){

            String sitem = item.toString();


        }

        // Create the adapter to convert the array to views
        CustomFundsAdapter adapter = new CustomFundsAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView

         ListView listView = (ListView) findViewById(R.id.lvfunds);
         adapter.clear();
         adapter.setNotifyOnChange(true);
         listView.setAdapter(adapter);
    }





}
