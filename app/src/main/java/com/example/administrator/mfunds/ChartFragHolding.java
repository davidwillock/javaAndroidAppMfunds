package com.example.administrator.mfunds;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONObject;
import android.widget.Toast;

import java.util.*;



public class ChartFragHolding extends Fragment {

    int[] pieChartValues={25,15,20,40};
    java.util.List<chartSector> sectors = new LinkedList<chartSector>();

    public static final String TYPE = "type";
    private static int[] COLORS = new int[] { Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN };
    private CategorySeries mSeries = new CategorySeries("");
    private DefaultRenderer mRenderer = new DefaultRenderer();
    private GraphicalView mChartView;

    private Context context;
    private String mParam1;
    private String mParam2;


    public ChartFragHolding() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ChartFragHolding newInstance(String param1, String param2) {
        ChartFragHolding fragment = new ChartFragHolding();
        Bundle args = new Bundle();
        args.putString("JSON" , param1);
        args.putString("Holding", param2);
        fragment.setArguments(args);
        //    fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String holding = getArguments().getString("Holding");

        if (getArguments() != null) {
            mParam1 = getArguments().getString("JSON");

        }

        int size = countJsonObject(mParam1,holding);

        String strFund="";

        String[] names = new String[9];
        Arrays.fill(names,"-");

        double[] values = new double[9];
        Arrays.fill(values,0);

      //  Toast.makeText(getContext(), "Fund Holdings "  , Toast.LENGTH_SHORT).show();

        try {
            JSONObject jsonObj = new JSONObject(mParam1);
            JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");


            JSONArray ja_dataHoldings = ja_obj.getJSONArray("Holdings");



            int cnt=0;
            for (int i = 0; i < ja_dataHoldings.length(); i++) {


                JSONObject rootObj = ja_dataHoldings.getJSONObject(i);

                strFund = rootObj.getString("FundSymID");


                if(strFund.equals(holding)  ){

                    String sym = rootObj.getString("SymID");
                    String fundsym = rootObj.getString("FundSymID");
                    String name = rootObj.getString("Name");
                    String asset = rootObj.getString("Asset");
                    String sector = rootObj.getString("Sector");
                    String geograph = rootObj.getString("Geograph");
                    double percentage = rootObj.getDouble("Percentage");


                    if( cnt < size) {

                        names[cnt]=name;
                        values[cnt]=percentage;
                        cnt++;



                    }

                }

            }


        }catch(Exception ex){



        }

        // REFERENCE ACHARTENGINE  WEBSITE


        View view = (LinearLayout) inflater.inflate(R.layout.fragment_charts,
                container, false);

        //     String[] names = new String[] { "Holding 1", "Holding 2", "Holding 3","Holding 4","Holding 5","Holding 6","Holding 7","Holding 8","Holding 9"};//"Holding 9"};
        //    double[] values = { 10, 20, 30,40,50,60,70,80,90 };
        int[] colors = { Color.RED, Color.GREEN, Color.BLUE,Color.CYAN, Color.MAGENTA, Color.LTGRAY,Color.YELLOW,Color.BLACK,Color.DKGRAY };







        CategorySeries categorySeries = new CategorySeries("Pie Chart");
        for (int i = 0; i <names.length; i++) {
            categorySeries.add(names[i], values[i]);
        }

        DefaultRenderer defaultRenderer = new DefaultRenderer();
        for (int i = 0; i < values.length; i++) {
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            defaultRenderer.setBackgroundColor(Color.WHITE);
            defaultRenderer.setLabelsTextSize(20f);
            defaultRenderer.setLegendTextSize(20f);
            defaultRenderer.setApplyBackgroundColor(true);
            defaultRenderer.addSeriesRenderer(seriesRenderer);
            defaultRenderer.setChartTitle("Holdings ");
            defaultRenderer.setChartTitleTextSize(70f);


        }


        LinearLayout layout = (LinearLayout) view.findViewById(R.id.chart);
        mChartView = ChartFactory.getPieChartView(getActivity(), categorySeries, defaultRenderer);
        defaultRenderer.setClickEnabled(true);
        defaultRenderer.setSelectableBuffer(10);
        layout.addView(mChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT));



        return view;

        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_charts, container, false);
    }


    public void fillPieChart(){
        for(int i=0;i<pieChartValues.length;i++)
        {
            mSeries.add(" Share Holder " + (mSeries.getItemCount() + 1), pieChartValues[i]);
            SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
            renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
            mRenderer.addSeriesRenderer(renderer);
            if (mChartView != null)
                mChartView.repaint();
        }
    }


    public int countJsonObject(String mParam1, String fund) {

        int cnt=0;

        try {
            JSONObject jsonObj = new JSONObject(mParam1);
            JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");


            JSONArray ja_dataHoldings = ja_obj.getJSONArray("Holdings");

            int size = ja_dataHoldings.length();

            for (int i = 0; i < ja_dataHoldings.length(); i++) {


                JSONObject rootObj = ja_dataHoldings.getJSONObject(i);

                String strFund = rootObj.getString("FundSymID");

                if (strFund.equals(fund)) {


                   cnt++;
                }


            }

        }catch(Exception ex){



        }

       return cnt;
    }











}
