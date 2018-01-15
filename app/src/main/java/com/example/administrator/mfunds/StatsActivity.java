package com.example.administrator.mfunds;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import android.content.Context;


import java.util.List;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class StatsActivity extends Fragment {

    private Context context;


    private String mParam1;
    private String mParam2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_stats, container, false);


        context = getActivity();
       MySQLiteHelper db = new MySQLiteHelper(getActivity());







       // ConstraintLayout layout = (ConstraintLayout) view.findViewById(R.id.layout);
       // layout.setBackgroundColor(getArguments().getInt("color"));

       // TextView txtdata = (TextView) view.findViewById(R.id.txtfundsdata);
       // String contents = getArguments().getString("content");
       // txtdata.setText(contents);


        TextView txtSymbol = (TextView) view.findViewById(R.id.txtStatsSymbolvalue);
        TextView txtincept = (TextView) view.findViewById(R.id.txtStatinceptvalue);
        TextView txtnavps = (TextView) view.findViewById(R.id.txtStatNavpsvalue);
        TextView txtreturnytd = (TextView) view.findViewById(R.id.txtStatYtdvalue);
        //  TextView txtchange = (TextView) findViewById(R.id.txtS);
        TextView txtmer = (TextView) view.findViewById(R.id.txtStatMervalue);
        TextView txtasset = (TextView) view.findViewById(R.id.txtStatAssetvalue);
        TextView txtrank = (TextView) view.findViewById(R.id.txtStatRankvalue);
        TextView txtvolrank = (TextView) view.findViewById(R.id.txtStatVolatilityRankvalue);
        TextView txtManager = (TextView) view.findViewById(R.id.txtStatManagervalue);
        TextView txtFees= (TextView) view.findViewById(R.id.txtStatFeesvalue);
        TextView txtFullname = (TextView) view.findViewById(R.id.txtStatsFullNamevalue);
        TextView txtMstarRating = (TextView) view.findViewById(R.id.txtStatMstarRatingvalue);
        TextView txtMstarRisk = (TextView) view.findViewById(R.id.txtStatMstarRiskvalue);
        TextView txtStdDev = (TextView) view.findViewById(R.id.txtStatStdvalue);


        String stats = getArguments().getString("Stats");


        if (getArguments() != null) {
            mParam1 = getArguments().getString("JSON");

        }




        String strFund="";
        try {
            JSONObject jsonObj = new JSONObject(mParam1);
            JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");


            JSONArray ja_dataPerformance = ja_obj.getJSONArray("Performance");

            int size = ja_dataPerformance.length();


            for (int i = 0; i < ja_dataPerformance.length(); i++) {


                JSONObject rootObj = ja_dataPerformance.getJSONObject(i);


                String sym = rootObj.getString("symbol");


                if(sym.equals(stats)){





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






                    txtFullname.setText(sym);
                    txtincept.setText(inceptdate);
                    txtnavps.setText(String.valueOf(navps));
                    txtreturnytd.setText(String.valueOf(yield));
                    txtmer.setText(String.valueOf(mer));
                    txtasset.setText(String.valueOf(assets));
                    txtrank.setText(String.valueOf(rank));
                    txtvolrank.setText(String.valueOf(volatilerank));
                    txtManager.setText(manager);
                    txtFees.setText("22.22");
                    txtFullname.setText(FullName);
                    txtMstarRating.setText(String.valueOf(mstarrating));
                    txtMstarRisk.setText(String.valueOf(mstarrisk));
                    txtSymbol.setText(sym);
                    txtStdDev.setText(String.valueOf(stddev));












                }
            }


        }catch(Exception ex){

            Toast.makeText(getContext(),"Stats Activity: " + ex.getMessage() , Toast.LENGTH_SHORT).show();

        }








//    Bundle args = new Bundle();
     //   String fund = args.getString("PERFORMANCE_LIST");

      //  String fund = getArguments().getString("PERFORMANCE_LIST");
/*
        String funds = getArguments().getString("content");
        Toast.makeText(getContext(),"Stats Activity: "+funds, Toast.LENGTH_SHORT).show();

        String tempstr="PGMIX";
        pdata = db.getPerformancesbySym(funds);

        String strSymid;
        String   strDate;

      //  String content = getArguments().getString("PERFORMANCE_LIST");

        for(Performance data : pdata ){

            String test = data.getSymID();
            txtFullname.setText(data.getSymID());
            txtincept.setText(data.getInceptDate());
            txtnavps.setText(String.valueOf(data.getNavPs()));
            txtreturnytd.setText("6.0");
            txtmer.setText(String.valueOf(data.getMER()));
            txtasset.setText(String.valueOf(data.getAssets()));
            txtrank.setText(String.valueOf(data.getRank()));
            txtvolrank.setText(String.valueOf(data.getVolatileRank()));

            Toast.makeText(getContext(),"FromSTATSACTIVITY: "+test , Toast.LENGTH_SHORT).show();

        }

*/




        return view;


    }



  public StatsActivity(){}


    public static StatsActivity newInstance(String mParam1, int color,String mParam2){

       // Toast.makeText(,"FromSTATSACTIVITY: "+fund , Toast.LENGTH_SHORT).show();
        //ArrayList<Performance> listOfStrings = new ArrayList<>(list.size());
        //listOfStrings.addAll(list);

      //  ArrayList<String> test= new ArrayList<>();
        StatsActivity home = new StatsActivity();
        Bundle args = new Bundle();
        args.putString("JSON", mParam1);
        args.putString("Stats", mParam2);
        args.putInt("color", color);

        home.setArguments(args);





        return home;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);






    }





}
