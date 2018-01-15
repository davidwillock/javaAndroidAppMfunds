package com.example.administrator.mfunds;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Color;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.LinkedList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class ChartsFragment extends Fragment {



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



    public ChartsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ChartsFragment newInstance(String param1, String param2) {
        ChartsFragment fragment = new ChartsFragment();
        Bundle args = new Bundle();
    //    args.putString(ARG_PARAM1, param1);
        args.putString("Sector", param2);
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




        MySQLiteHelper db = new MySQLiteHelper(getActivity());



         String sector = getArguments().getString("Sector");
  //      Toast.makeText(getContext(),"Charts !!!: " +sector, Toast.LENGTH_SHORT).show();


        List<chartSector>  cSector   = db.getChartSectorByFund("blank");





          String[] names = new String[cSector.size()];
            double[] values = new double[cSector.size()];

            cSector = db.getChartSectorByFund("ARGFX");

            int size = cSector.size();

            int cnt=0;
            for(chartSector chart : cSector){

                names[cnt]= chart.getSector();
                values[cnt]=chart.getPercent();
                String col1  = String.valueOf(chart.getPercent());
                Toast.makeText(getContext(),"TEST CHART " + col1 + " contents of percent " + String.valueOf(names[cnt]) , Toast.LENGTH_SHORT).show();
                cnt++;

            }












        View view = (LinearLayout) inflater.inflate(R.layout.fragment_charts,
                container, false);

     //   String[] names = new String[] { "Category 1", "Category 2", "Category 3" };
    // double[] values = { 20, 30, 50 };
        int[] colors = { Color.RED, Color.GREEN, Color.BLUE,Color.CYAN, Color.MAGENTA, Color.LTGRAY,Color.YELLOW,Color.BLACK };

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
        }


        LinearLayout layout = (LinearLayout) view.findViewById(R.id.chart);
        mChartView = ChartFactory.getPieChartView(getActivity(), categorySeries, defaultRenderer);
        defaultRenderer.setClickEnabled(true);
        defaultRenderer.setSelectableBuffer(10);
        layout.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT));



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
