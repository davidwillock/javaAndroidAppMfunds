package com.example.administrator.mfunds;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toolbar;

import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import android.widget.TextView;

public class ScrollActivity extends AppCompatActivity {








    List<chartSector> sectors = new LinkedList<chartSector>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        ViewPager viewPager;
        ViewPager sviewPager;
        PagerCustomAdapter adapter;
        PagerCustomAdapter sadapter;



     //   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);
       MySQLiteHelper db = new MySQLiteHelper(ScrollActivity.this);







        List<Performance> pdata;

        String fund = getIntent().getStringExtra("FUND");
        String jsonObj = getIntent().getStringExtra("JSON");
        String strInvest = getIntent().getStringExtra("LGIINVEST");
        String strTerm = getIntent().getStringExtra("LGITERM");

       // String fund ="TEST";

     //   pdata = db.getPerformancesbySym(fund);

        List<Fragment> items = new ArrayList<>();
        List<Fragment> sitems = new ArrayList<>();

     //   Fragment f = FundsActivity.newInstance("Performance fragment", Color.CYAN);

     //   items.add(f);


    /*********************************************************************************/
       StatsActivity s = StatsActivity.newInstance(jsonObj, Color.WHITE,fund);

        items.add(s);

   //     Stats2Fragment sf = Stats2Fragment.newInstance(jsonObj, Color.WHITE,fund);

   //     items.add(sf);


        Fragment schart = ChartFragSector.newInstance(jsonObj, fund);
        items.add(schart);


        Fragment achart = ChartFragAsset.newInstance(jsonObj, fund);
        items.add(achart);


        ChartFragHolding hchart = ChartFragHolding.newInstance(jsonObj,fund);
        items.add(hchart);


         Fragment gchart = ChartFragGeograph.newInstance(jsonObj, fund);
         items.add(gchart);

/****************************************************************************************/

       //     myf  = ChartsFragment.newInstance("Sector", fund);
      //  items.add(myf);

      //  myf  = ChartsFragment.newInstance("Geograph", "");
     //   items.add(myf);

        LineInvestFragment lIF = LineInvestFragment.newInstance(jsonObj,fund,strInvest,strTerm);
        items.add(lIF);



//*****************************************************************************************************************
        LineFragment lf = LineFragment.newInstance(jsonObj,fund,strInvest,strTerm);
        items.add(lf);


        LineFragmentIndex lfi = LineFragmentIndex.newInstance(jsonObj,fund,strInvest,strTerm);
        items.add(lfi);
//**********************************************************************************************************
    //    f = FundsActivity.newInstance("Asset fragment", Color.GREEN);
        //sitems.add(f);

     //   f = FundsActivity.newInstance("Sector fragment", Color.MAGENTA);
     //   items.add(f);


        adapter = new PagerCustomAdapter(getSupportFragmentManager(), items);
        viewPager = (ViewPager) findViewById(R.id.container);
         viewPager.setAdapter(adapter);

       // sadapter = new PagerCustomAdapter(getSupportFragmentManager(), sitems);
       // sviewPager = (ViewPager) findViewById(R.id.container);
       // sviewPager.setAdapter(sadapter);







/*


        pdata = db.getPerformancesbySym(fund);

        String strSymid;
        String   strDate;

        for(Performance data : pdata ){

            strSymid = data.getSymID();
            strDate = data.getInceptDate();

            Toast.makeText(getApplicationContext(),"FromScroll: "+strSymid + " , "+ strDate, Toast.LENGTH_SHORT).show();

        }
*/





        //  Bundle b = getIntent().getExtras();
        // int index = b.getInt("index");


        //TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
       // tabLayout.setupWithViewPager(viewPager);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
















    }




    protected void getSymbolData(String url) {

        //  compile 'com.fasterxml.jackson.core:jackson-core:2.7.3'
        //  compile 'com.fasterxml.jackson.core:jackson-annotations:2.7.3'

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        //isValid = false;
        // valid="";

        //  txtjson = (TextView) findViewById(R.id.txtJson);

        //final String url = "http://charlie-davidwillock.c9users.io/articles/new?title=mobile&text=testapi";

        client.get(url, params, new TextHttpResponseHandler() {

                    // String valid="";
                    // isTrue getStatus = isTrue.getInstance();

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        // called when response HTTP status is "200 OK"


                        //  MySQLiteHelper db = new MySQLiteHelper(Funds.this);


                        try {


                            // Remove illegal character from api

                            res = res.replaceAll("[\"]", "");
                            JSONObject jsonObj = new JSONObject(res);

                            //extracting data array from json string

                            JSONArray ja_data = jsonObj.getJSONArray("ChartSectorResult");

                            chartSector charts = new chartSector();
                            sectors.clear();

                            for(int i=0;i<ja_data.length();i++) {


                                JSONObject rootObj = ja_data.getJSONObject(i);


                                charts.setSector(rootObj.getString("Sector"));
                                charts.setCount(rootObj.getInt("Count"));
                                charts.setPercent(rootObj.getInt("Percent"));

                                sectors.add(charts);

                                //    db.addMutualFunds(new Mutual(symbol, sEpoch, dopen, dhigh, dlow, dclose, dcloseadj, ivolume));


                            }


                            //List<Mutual>
                            //  allNewsFunds = db.getAllMutualFunds();
                            //  double idx = allNewsFunds.get(4).getAdj_close();
                            //   String sidx = String.valueOf(idx);
                            //txtjson.setText(sidx);
                            //  txtUserName.setText(sidx);






                            Toast.makeText(getApplicationContext(), "Appended records to List", Toast.LENGTH_SHORT).show();


                            //  txtjson.setText(sEpoch);

                        } catch (Exception ex) {
                            Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_SHORT).show();

                            ex.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        String failure = "Cannot connect via http";
                        Toast.makeText(getApplicationContext(),
                                "Connection failure", Toast.LENGTH_SHORT).show();
                        //  getStatus.setValue(false);


                    }
                }

        );


    }





}
