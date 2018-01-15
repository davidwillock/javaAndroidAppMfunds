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
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class ChartFragAsset extends Fragment {

    int[] pieChartValues={25,15,20,40};
    List<chartSector> sectors = new LinkedList<chartSector>();

    public static final String TYPE = "type";
    private static int[] COLORS = new int[] { Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN };
    private CategorySeries mSeries = new CategorySeries("");
    private DefaultRenderer mRenderer = new DefaultRenderer();
    private GraphicalView mChartView;

    private Context context;
    private String mParam1;
    private String mParam2;

    public ChartFragAsset() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ChartFragAsset newInstance(String json, String funds) {
        ChartFragAsset fragment = new ChartFragAsset();
        Bundle args = new Bundle();
        args.putString("JSON", json);
        args.putString("Asset", funds);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = (LinearLayout) inflater.inflate(R.layout.fragment_charts,
                container, false);



   //     Toast.makeText(getContext(), "Fund Assets "  , Toast.LENGTH_SHORT).show();



        String asset = getArguments().getString("Asset");
        if (getArguments() != null) {
            mParam1 = getArguments().getString("JSON");
        }

        int cnt=0;
        try {
            JSONObject jsonObj = new JSONObject(mParam1);
            JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");


            JSONArray ja_dataChartGeograph = ja_obj.getJSONArray("AssetChart");

            int size = ja_dataChartGeograph.length();
            String strFund="";

            for (int i = 0; i < ja_dataChartGeograph.length(); i++) {
                JSONObject rootObj = ja_dataChartGeograph.getJSONObject(i);
                strFund = rootObj.getString("Fund");
                if (strFund.equals(asset)) {
                    cnt++;
                }
            }

        }catch(Exception ex){


        }


        Double[] values= new Double[cnt];

        String[] names= new String[cnt];

        int idx=0;
        String strFund="";
        try {
            JSONObject jsonObj = new JSONObject(mParam1);
            JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");


            JSONArray ja_dataChartAsset = ja_obj.getJSONArray("AssetChart");

            int size = ja_dataChartAsset.length();

            for (int i = 0; i < ja_dataChartAsset.length(); i++) {


                JSONObject rootObj = ja_dataChartAsset.getJSONObject(i);

                strFund = rootObj.getString("Fund");


                if(strFund.equals(asset)){

                    String strGeograph = rootObj.getString("Asset");
                    double percent = rootObj.getDouble("Percent");
                    values[idx]=percent;
                    names[idx]=strGeograph;
                    idx++;

               }

            }


        }catch(Exception ex){
        }


        int[] colors = { Color.RED, Color.GREEN, Color.BLUE,Color.CYAN, Color.MAGENTA, Color.LTGRAY,Color.YELLOW,Color.BLACK };



        // REFERENCE ACHARTENGINE  WEBSITE

        CategorySeries categorySeries = new CategorySeries("Pie Chart");
        for (int i = 0; i < names.length; i++) {
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
            defaultRenderer.setChartTitle("Assets ");
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



}
