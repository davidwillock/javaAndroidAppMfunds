package com.example.administrator.mfunds;

import android.app.ListActivity;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class ScreenQuestionFragment extends Fragment {


    private int cntRisk;

    MySQLiteHelper  db;

    private ProgressDialog progressDialog;
    Context context;

    private String jsonObject;
    private String invest;
    private String term;
    EditText edEnterValue;
    EditText edEnterTerm;


    public ScreenQuestionFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ScreenQuestionFragment newInstance(String param1, String param2) {
        ScreenQuestionFragment fragment = new ScreenQuestionFragment();
   //     Bundle args = new Bundle();
   //     args.putString(ARG_PARAM1, param1);
   //     args.putString(ARG_PARAM2, param2);
   //     fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  if (getArguments() != null) {
      //      mParam1 = getArguments().getString(ARG_PARAM1);
      //      mParam2 = getArguments().getString(ARG_PARAM2);
      //  }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_screen_question,
                container, false);


        context = getActivity();
        db  = new MySQLiteHelper(context);

        edEnterValue = (EditText) view.findViewById(R.id.editTxtQuestInvest);
        Button btnCriteriaOK = (Button) view.findViewById(R.id.btncheckok);
        Button btnCriteriaCancel = (Button) view.findViewById(R.id.btncheckcancel);


        final CheckBox checkHigh = (CheckBox) view.findViewById(R.id.checkHigh);
        final CheckBox checkMedium = (CheckBox) view.findViewById(R.id.checkMedium);
        final CheckBox checkLow = (CheckBox) view.findViewById(R.id.checkLow);

        edEnterTerm = (EditText) view.findViewById(R.id.editScreenterm);

        term = edEnterTerm.getText().toString();
        invest = edEnterValue.getText().toString();



        int result = cntRisk;

        cntRisk=0;


        // CRASHING
        //String strinvestValue = edEnterValue.getText().toString();
        //double dblinvestValue = Double.valueOf(strinvestValue);

   //     db  = new MySQLiteHelper(context);


        btnCriteriaOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showMessageDialog("Downloading data...");

                int cnt=0;
                int param1=1;
                int param2=3;

                if(checkHigh.isChecked()){


                    cntRisk=8;


                }else if(checkMedium.isChecked()){


                    cntRisk=4;


                }else if(checkLow.isChecked()){


                    cntRisk=1;


                }

                if(cntRisk == 8){

                    param1 = 8;
                    param2 = 10;


                }else if(cntRisk == 4){

                    param1  = 4;
                    param2  = 7;
                }else if(cntRisk == 1){

                    param1 = 1;
                    param2 = 3;
                }


              //  String url = "http://charlie3.gear.host/MyService.svc/apiSelectFundsByRisk/{risk:'"+"'}";
            //    String strparam1= String.valueOf(param1);
            //    String strparam2= String.valueOf(param2);


                term = edEnterTerm.getText().toString();
                invest = edEnterValue.getText().toString();


             //   Toast.makeText(getContext(), "Sending call to api by risk and params "+param1+", "+param2, Toast.LENGTH_SHORT).show();
                String url = "http://charlie3.gear.host/MyService.svc/apiSelectFundsByRisk/{risk1:'"+param1+"',risk2:'"+param2+"'}";
                getSymbolbyRiskScreening(url);


            }



        });










        // Inflate the layout for this fragment
        return view;
    }





    protected void getSymbolbyRiskScreening(String url) {

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


                        try {



                            //Remove illegal character from api

                            res = res.replaceAll("[\"]", "");

                        //    Bundle bundle = new Bundle();
                      //      bundle.putString("JSON", res);
                            // set MyFragment Arguments
                      //      ChartFragGeograph myObj = new ChartFragGeograph();
                      //      myObj.setArguments(bundle);



                             jsonObject = res;

                            String TESTbak ="{FundsByRiskResult:{Returns : [{'Return': 'ARGFX','OneMonth': '1','ThreeMonth': '3','SixMonth': '6','YTD': '6','OneYear': '1','ThreeYear': '3','FiveYear': '16','TenYear': '10'}]},Holdings: [{'Return': 'ARGFX','OneMonth': '1','ThreeMonth': '3','SixMonth': '6','YTD': '6','OneYear': '1','ThreeYear': '3','FiveYear': '16','TenYear': '10'}]}}";



                            //      String TEST ="{FundsByRiskResult:{Returns : [{'Return': 'ARGFX','OneMonth': '1','ThreeMonth': '3','SixMonth': '6','YTD': '6','OneYear': '1','ThreeYear': '3','FiveYear': '16','TenYear': '10'}],Holdings: [{'Return': 'ARGFX','OneMonth': '1','ThreeMonth': '3','SixMonth': '6','YTD': '6','OneYear': '1','ThreeYear': '3','FiveYear': '16','TenYear': '10'}]}}";
                            //  String str1 = "{'charlie'       :{ test1:   [{'name':  'Charlie','flowerCount': 69}, {'name': 'Marcus', 'flowerCount': 3}, {'name': 'Norman', 'flowerCount': 2}]                                ,test2:   [{'name': 'Christian','flowerCount': 1}, {'name': 'Marcus', 'flowerCount': 3}, {'name': 'Norman', 'flowerCount': 2}]}}";

                            //       JSONObject jsonObj = new JSONObject(TEST);
                            JSONObject jsonObj = new JSONObject(res);
                            JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");
                            JSONArray ja_dataReturns = ja_obj.getJSONArray("Returns");

                            //    JSONArray ja_dataSymbols = ja_obj.getJSONArray("Symbols");

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


                             //   txtviewdata.setText(sym);

                                db.addPerformanceReturns(new PerformanceReturns(sym, onemonth, threemonth, sixmonth, ytdmonth, oneyear, threeyear, fiveyear,tenyear));
                            }

                            //       JSONArray ja_dataSymbols = ja_obj.getJSONArray("Symbols");

                            //  JSONArray ja_dataHoldings = ja_obj.getJSONArray("Holdings");
                            // JSONArray ja_dataPerformance = ja_obj.getJSONArray("Performance");


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


                                //       Toast.makeText(getApplicationContext(), sym + ", " + name, Toast.LENGTH_SHORT).show();
                           //     txtviewdata.setText(name);

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
                      //          txtviewdata.setText(sym);


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

                                db.addSector(new chartSector(fund,asset,count,percent));
                                //      Toast.makeText(getApplicationContext(), "got fund from chartAsset "+fund, Toast.LENGTH_SHORT).show();


                            }



                            JSONArray ja_dataChartGeograph = ja_obj.getJSONArray("GeographChart");

                            for(int i=0;i<ja_dataChartGeograph.length();i++) {


                                JSONObject rootObj = ja_dataChartGeograph.getJSONObject(i);

                                String fund = rootObj.getString("Fund");
                                String geograph = rootObj.getString("Geograph");
                                int count = rootObj.getInt("Count");
                                int percent = rootObj.getInt("Percent");

                                db.addSector(new chartSector(fund,geograph,count,percent));
                                //    Toast.makeText(getApplicationContext(), "got fund from chartGeograph "+fund, Toast.LENGTH_SHORT).show();


                            }











                        } catch (Exception ex) {

                            Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_SHORT).show();
                          //  txtviewdata.setText(ex.toString());

                        }





                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        String failure = "Cannot connect via http";
                        Toast.makeText(getContext(),
                                "Connection failure", Toast.LENGTH_SHORT).show();
                        //  getStatus.setValue(false);


                    }


            @Override
            public void onFinish() {


          //      Intent intentcharts = new Intent(context,MListActivity.class);
          //      startActivity(intentcharts);


            //   Intent intent = new Intent(context, typeof(MListActivity.class));
            //    startActivity (intent);


                //     hideProgressDialog();




             //   Intent i = new Intent(getContext().getApplicationContext(),MListActivity.class);

              //  Intent i = new Intent(getContext().getApplicationContext(),MListActivity.class);
                Intent i = new Intent(getContext().getApplicationContext(),ListView2Activity.class);

                i.putExtra("THEJSON",jsonObject);
                i.putExtra("invest",invest);
                i.putExtra("term",term);


                // Intent myIntent = new Intent((ScreenScrollActivity)getActivity(), MListActivity.class);
               startActivity(i);

           //     getFragmentManager().beginTransaction()
           //             .replace(R.id.container, new MListActivity())
           //             .commit();


             //   ListFundsFragment()





                hideProgressDialog();




            }





                }

        );






}



    public void showMessageDialog(String message){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(getActivity());

        }


        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void showMessageDialog(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(getActivity());

        }
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Downloading data");
        progressDialog.show();
    }

    public void hideProgressDialog(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }



}
