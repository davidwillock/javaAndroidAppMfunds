package com.example.administrator.mfunds;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import android.widget.TextView;
import android.widget.Button;

import java.util.List;
import android.os.Handler;
import android.widget.ProgressBar;

import android.webkit.WebView;



public class Main2Activity extends AppCompatActivity {



    MySQLiteHelper  db;
    TextView txtviewdata;

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private ProgressDialog progressDialog;
    private ProgressDialog progress;


    public void showMessageDialog(String message){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);

        }


        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void showMessageDialog(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);

        }
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
    }

    public void hideProgressDialog(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        WebView  mywebview;

        ConstraintLayout layouts = (ConstraintLayout) findViewById(R.id.container);

        layouts.setBackgroundColor(Color.WHITE);






        txtviewdata= (TextView) findViewById(R.id.txtm2viewdata);
        mywebview = (WebView) findViewById(R.id.webview_main);

        mywebview.getSettings().setLoadsImagesAutomatically(true);
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mywebview.loadUrl("http://quotes.wsj.com/index/SPX");
        //mywebview.loadUrl("http://www.nasdaq.com/");
        mywebview.setInitialScale(1);
        mywebview.getSettings().setLoadWithOverviewMode(true);
        mywebview.getSettings().setUseWideViewPort(true);

        txtviewdata.setMovementMethod(new ScrollingMovementMethod());
        //progressBar = (ProgressBar) findViewById(R.id.progressBarMain);
        db  = new MySQLiteHelper(Main2Activity.this);

        db.deleteAllHoldings();
        db.deleteAllPerformance();
        db.deleteAllPerformReturn();
        db.deleteAllSymbol();
















    }

























    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {





        int id = item.getItemId();

        if (id ==R.id.id_login){

            Intent intentregistration = new Intent(Main2Activity.this, LoginActivity.class);
            startActivity(intentregistration);


            return true;
        }

        if (id ==R.id.id_registration){

            Intent intentlogin = new Intent(Main2Activity.this,RegisterActivity.class);
            startActivity(intentlogin);


            return true;
        }

        if (id ==R.id.id_screen){

            Intent intent = new Intent(Main2Activity.this, ScreenScrollActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);

            return true;
        }











        return super.onOptionsItemSelected(item);

    }




    protected void getSymbolbyRisk(String url) {


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();


        client.get(url, params, new TextHttpResponseHandler() {


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        // called when response HTTP status is "200 OK"


                        try {



                            //Remove illegal character from api

                            res = res.replaceAll("[\"]", "");



                            String TESTbak ="{FundsByRiskResult:{Returns : [{'Return': 'ARGFX','OneMonth': '1','ThreeMonth': '3','SixMonth': '6','YTD': '6','OneYear': '1','ThreeYear': '3','FiveYear': '16','TenYear': '10'}]},Holdings: [{'Return': 'ARGFX','OneMonth': '1','ThreeMonth': '3','SixMonth': '6','YTD': '6','OneYear': '1','ThreeYear': '3','FiveYear': '16','TenYear': '10'}]}}";



                            JSONObject jsonObj = new JSONObject(res);
                            JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");
                            JSONArray ja_dataReturns = ja_obj.getJSONArray("Returns");



                            for (int i = 0; i < ja_dataReturns.length(); i++) {

                                JSONObject rootObj = ja_dataReturns.getJSONObject(i);

                                String sym = rootObj.getString("Return");
                                double onemonth = rootObj.getDouble("OneMonth");
                                double threemonth = rootObj.getDouble("ThreeMonth");
                                double sixmonth = rootObj.getDouble("SixMonth");
                                double ytdmonth = rootObj.getDouble("YTD");
                                double oneyear = rootObj.getDouble("OneYear");
                                double threeyear = rootObj.getDouble("ThreeYear");
                                double fiveyear = rootObj.getDouble("FiveYear");
                                double tenyear = rootObj.getDouble("TenYear");


                                txtviewdata.setText(sym);

                                db.addPerformanceReturns(new PerformanceReturns(sym, onemonth, threemonth, sixmonth, ytdmonth, oneyear, threeyear, fiveyear,tenyear));
                            }



                            JSONArray ja_dataHoldings = ja_obj.getJSONArray("Holdings");

                            for (int i = 0; i < ja_dataHoldings.length(); i++) {

                                JSONObject rootObj = ja_dataHoldings.getJSONObject(i);

                                String sym = rootObj.getString("SymID");
                                String fundsym = rootObj.getString("FundSymID");
                                String name = rootObj.getString("Name");
                                String asset = rootObj.getString("Asset");
                                String sector = rootObj.getString("Sector");
                                String geograph = rootObj.getString("Geograph");
                                double percentage = rootObj.getDouble("Percentage");



                                txtviewdata.setText(name);

                                db.addHoldings(new Holdings(fundsym, sym , name, asset, sector, geograph, percentage));
                            }



                            JSONArray ja_dataPerformance = ja_obj.getJSONArray("Performance");




                            for (int i = 0; i < ja_dataPerformance.length(); i++) {

                                JSONObject rootObj = ja_dataPerformance.getJSONObject(i);

                                String sym = rootObj.getString("symbol");
                                String inceptdate = rootObj.getString("InceptionDate");
                                double mer = rootObj.getDouble("Mer");
                                double assets = rootObj.getDouble("Mer");
                                double rank = rootObj.getDouble("Rank");
                                double mstarrating= rootObj.getDouble("MstarRating");
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




                                //            Toast.makeText(getApplicationContext(), sym + ", " + inceptdate, Toast.LENGTH_SHORT).show();
                                txtviewdata.setText(sym);


                                db.addPerformance(new PerformanceStats(sym,inceptdate,assets,mer,rank,mstarrating,stddev,volatilerank,mstarrisk,alpha,beta,rsquare,rrspel,load,maxfrontend,maxbackend,salesopen,navps,netasset,yield,dividend,manager,Fees,FullName));


                            }


                            JSONArray ja_dataSymbols = ja_obj.getJSONArray("Symbols");

                            for(int i=0;i<ja_dataSymbols.length();i++) {


                                JSONObject rootObj = ja_dataSymbols.getJSONObject(i);

                                String symbol = rootObj.getString("symbol");
                                String sEpoch = rootObj.getString("epoch");
                                double dopen = rootObj.getDouble("open");
                                double dhigh = rootObj.getDouble("high");
                                double dlow = rootObj.getDouble("low");
                                double dclose = rootObj.getDouble("close");
                                double dcloseadj = rootObj.getDouble("close_adj");
                                int ivolume = rootObj.getInt("volume");


                                db.addMutualFunds(new Mutual(symbol, sEpoch, dopen, dhigh, dlow, dclose, dcloseadj, ivolume));


                            }



                            JSONArray ja_dataChartSector = ja_obj.getJSONArray("SectorChart");

                            for(int i=0;i<ja_dataChartSector.length();i++) {


                                JSONObject rootObj = ja_dataChartSector.getJSONObject(i);

                                String fund = rootObj.getString("Fund");
                                String sector = rootObj.getString("Sector");
                                int count = rootObj.getInt("Count");
                                int percent = rootObj.getInt("Percent");

                                db.addSector(new chartSector(fund,sector,count,percent));
                    //            Toast.makeText(getApplicationContext(), "got fund from chartsector "+fund, Toast.LENGTH_SHORT).show();


                            }

                            JSONArray ja_dataChartAsset = ja_obj.getJSONArray("AssetChart");

                            for(int i=0;i<ja_dataChartAsset.length();i++) {


                                JSONObject rootObj = ja_dataChartAsset.getJSONObject(i);

                                String fund = rootObj.getString("Fund");
                                String asset = rootObj.getString("Asset");
                                int count = rootObj.getInt("Count");
                                int percent = rootObj.getInt("Percent");

                                db.addAsset(new chartAsset(fund,asset,count,percent));
                          //      Toast.makeText(getApplicationContext(), "got fund from chartAsset "+fund, Toast.LENGTH_SHORT).show();


                            }



                            JSONArray ja_dataChartGeograph = ja_obj.getJSONArray("GeographChart");

                            for(int i=0;i<ja_dataChartGeograph.length();i++) {


                                JSONObject rootObj = ja_dataChartGeograph.getJSONObject(i);

                                String fund = rootObj.getString("Fund");
                                String geograph = rootObj.getString("Geograph");
                                int count = rootObj.getInt("Count");
                                int percent = rootObj.getInt("Percent");

                                db.addGeograph(new chartGeograph(fund,geograph,count,percent));
                           //    Toast.makeText(getApplicationContext(), "got fund from chartGeograph "+fund, Toast.LENGTH_SHORT).show();


                            }






                            Toast.makeText(getApplicationContext(), "TESTING NEW INTEFACE", Toast.LENGTH_SHORT).show();




                        } catch (Exception ex) {

                            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
                            txtviewdata.setText(ex.toString());

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


                    @Override
                    public void onFinish() {
                        super.onFinish();


                        Intent intentcharts = new Intent(Main2Activity.this,MListActivity.class);
                        startActivity(intentcharts);

                        hideProgressDialog();

                    }
                }

        );


    }






    protected void getSymbolData(String url) {


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();


        client.get(url, params, new TextHttpResponseHandler() {


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        // called when response HTTP status is "200 OK"


                        try {


                            // Remove illegal character from api

                            res = res.replaceAll("[\"]", "");
                            JSONObject jsonObj = new JSONObject(res);

                            //extracting data array from json string

                            JSONArray ja_data = jsonObj.getJSONArray("FundsBySymbolResult");


                            for(int i=0;i<ja_data.length();i++) {


                                JSONObject rootObj = ja_data.getJSONObject(i);

                                String symbol = rootObj.getString("symbol");
                                String sEpoch = rootObj.getString("epoch");
                                double dopen = rootObj.getDouble("open");
                                double dhigh = rootObj.getDouble("high");
                                double dlow = rootObj.getDouble("low");
                                double dclose = rootObj.getDouble("close");
                                double dcloseadj = rootObj.getDouble("close_adj");
                                int ivolume = rootObj.getInt("volume");


                                db.addMutualFunds(new Mutual(symbol, sEpoch, dopen, dhigh, dlow, dclose, dcloseadj, ivolume));


                            }



                            Toast.makeText(getApplicationContext(), "Appended db records", Toast.LENGTH_SHORT).show();

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



    protected void connectAsync(String url) {

        String result="";

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();




        client.get(url, params, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        // called when response HTTP status is "200 OK"
                        Toast.makeText(getApplicationContext(), "Succesful" , Toast.LENGTH_SHORT).show();



                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        String failure = "Cannot connect via http";
                        Toast.makeText(getApplicationContext(), "failure" , Toast.LENGTH_SHORT).show();




                    }


                    @Override
                    public void onFinish() {



                        super.onFinish();




                    }















                }
        );




    }




























}
