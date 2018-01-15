package com.example.administrator.mfunds;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;


public class ListView2Activity extends Activity implements OnItemClickListener {



    ListView lview;
    ListViewCustomAdapter lviewAdapter;

    private static String jsonRelayontoScroll;
    private static String strTerm;
    private static String strInvest;





    //   private final static String month[] = {"January","February","March","April","May",
   //         "June","July","August","September","October","November","December"};

  //  private final static String number[] = {"Month - 1", "Month - 2","Month - 3",
    //        "Month - 4","Month - 5","Month - 6",
    //        "Month - 7","Month - 8","Month - 9",
    //        "Month - 10","Month - 11","Month - 12"};

   // private final static String symbol[] ;

    String[] symbolList=null;
    String[] symbolFullName=null;
    double[] symbolNavPs = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);



        MySQLiteHelper db  = new MySQLiteHelper(ListView2Activity.this);


        jsonRelayontoScroll = getIntent().getExtras().getString("THEJSON");
        strTerm = getIntent().getExtras().getString("term");
        strInvest = getIntent().getExtras().getString("invest");

        int size=0;


        java.util.List<PerformanceStats> plist = db.getPerformanceDataList();
        java.util.List<PerformanceStats> slist = new LinkedList<>();

        String res =jsonRelayontoScroll;

        PerformanceStats stats = new PerformanceStats();

        try {


            JSONObject jsonObj = new JSONObject(res);
            JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");
            //JSONArray ja_dataReturns = ja_obj.getJSONArray("Returns");


            JSONArray ja_dataPerformance = ja_obj.getJSONArray("Performance");
            size = ja_dataPerformance.length();

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
                slist.add(stats);

              //  Toast.makeText(this,"size of performance stats "+String.valueOf(ja_dataPerformance.length())+ "  count"+String.valueOf(i), Toast.LENGTH_SHORT).show();
            }


        }catch(Exception ex){




        }






        plist = db.getPerformanceDataList();




       // int sz = ja_dataPerformance.length();


        symbolList = new String[slist.size()];
         symbolFullName = new String[slist.size()];
         symbolNavPs = new double[slist.size()];
/*

        int cnt=0;
        for(PerformanceStats items : slist){

       //     symbolFullName[cnt] = items.getFullName();
       //     symbolNavPs[cnt] = items.getNavPs();

            cnt++;
        }

*/

        int i=0;
        for(PerformanceStats list : slist) {

            symbolList[i] = list.getSymID();
            symbolFullName[i] = list.getFullName();
            symbolNavPs[i] = list.getNavPs();
            i++;

          //  Toast.makeText(this,"FullName => "+ list.getFullName()+ " "+String.valueOf(i), Toast.LENGTH_SHORT).show();
        }

        lview = (ListView) findViewById(R.id.listView2);
        lviewAdapter = new ListViewCustomAdapter(this, symbolList, symbolFullName,symbolNavPs);

      //  System.out.println("adapter => "+lviewAdapter.getCount());

        lview.setAdapter(lviewAdapter);

        lview.setOnItemClickListener(this);

       }


    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        // TODO Auto-generated method stub
       // Toast.makeText(this,"Title => "+symbolList[position]+"=> n Description"+symbolFullName[position], Toast.LENGTH_SHORT).show();



        String  itemValue    = (String) symbolList[position];
     //  Toast.makeText(this,"Fund => "+ itemValue, Toast.LENGTH_SHORT).show();





        Bundle bundle = new Bundle();
        bundle.putString("FUND",itemValue);
        bundle.putString("JSON",jsonRelayontoScroll);
        bundle.putString("LGITERM",strTerm);
        bundle.putString("LGIINVEST",strInvest);

        Intent intent = new Intent(ListView2Activity.this,ScrollActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);









    }





}
