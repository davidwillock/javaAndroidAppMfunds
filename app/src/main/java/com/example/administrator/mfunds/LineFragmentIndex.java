package com.example.administrator.mfunds;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.GraphicalView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.DecimalFormat;


import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import java.text.DecimalFormat;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.math.BigDecimal;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


public class LineFragmentIndex extends Fragment {

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context context;

    MySQLiteHelper db;

    //private View mChart;

    XYSeries benchmarkSeries;
    XYSeries symbolSeries;

    double growthAndReturn;
    double indexGrowthPercent;

    private GraphicalView mChart;



/*
    private String[] mMonth = new String[]{

            "Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug", "Sep", "Oct", "Nov", "Dec"

    };
*/

    public LineFragmentIndex() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LineFragmentIndex newInstance(String param1, String param2, String invest, String term) {
        LineFragmentIndex fragment = new LineFragmentIndex();
        Bundle args = new Bundle();
        args.putString("JSON", param1);
        args.putString("LineIndex", param2);
        args.putString("investment", invest);
        args.putString("duration", term);
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


        final View view = (LinearLayout) inflater.inflate(R.layout.fragment_line_fragment_index,
                container, false);





        TextView investvalue = (TextView) view.findViewById(R.id.txtGLIinvestValue);
        TextView growthvalue = (TextView) view.findViewById(R.id.txtGLIgrowthvalue);









        String line = getArguments().getString("LineIndex");



        String invest = getArguments().getString("investment");
        String duration = getArguments().getString("duration");

        final int fInvest = Integer.valueOf(invest);
        final int fTerm = Integer.valueOf(duration);

        int iInvest= fInvest;
        int iTerm = fTerm;


        if (getArguments() != null) {
            mParam1 = getArguments().getString("JSON");

        }



        List<String> idx = getSpinnerIndexes(mParam1);

        List<String> Indexes = new ArrayList<String>();
    //    Indexes.add("S&P500");
    //    Indexes.add("Dow Jones");
    //    Indexes.add("Russell");


        //Populate the spinner in the fragment
        Spinner spinner = (Spinner) view.findViewById(R.id.SGLIIndexspinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_item,idx);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = parentView.getItemAtPosition(position).toString();




                String invest = getArguments().getString("investment");
                String duration = getArguments().getString("duration");


                final int fInvest = Integer.valueOf(invest);
                final int fTerm = Integer.valueOf(duration);

                int iInvest= fInvest;
                int iTerm = fTerm;


                String line = getArguments().getString("LineIndex");


         //       double[] dowJones={10800.1,10400.2,10900.3,10700.4,10600.5,10700.6,10700.7,10600.8,0,0,0,0 };
                //   int max = 105;
                //   int min = 0;

          //      double[] Russel={10200.1,10300.2,10600.3,10700.4,10700.5,10800.6,10800.7,10700.8,0,0,0,0 };

        //        double[] SPX ={10300.1,10600.2,10800.3,10600.4,10500.5,10400.6,10500.7,10900.8,0,0,0,0 };


        //        double[] Fund ={10100.1,10200.2,10300.3,10400.4,10500.5,10600.6,10700.7,10800.8,0,0,0,0 };


                double dur=0.1;

                if (iTerm == 1) {
                    dur = 1;
                } else if (iTerm == 2) {
                    dur = 0.5;
                } else if (iTerm == 3) {

                    dur = 0.3;
                } else if (iTerm == 4) {

                    dur = 0.25;
                } else if (iTerm == 5) {
                    dur = 0.2;
                } else if (iTerm == 6) {
                    dur = 0.16;
                } else if (iTerm == 7) {
                    dur = 0.14;
                } else if (iTerm == 8) {
                    dur = 0.125;
                } else if (iTerm == 9) {
                    dur = 0.1;
                } else {

                    dur = 0.1;
                }


                int sz =13;


                List<PerformanceCalander>  FundsList = getFunds(mParam1,line) ;




                double[] cagr_funds =CAGR(FundsList, dur ) ;
                double accum=0.0;



                double[] CAGR_FUNDS = new double[13];
                Arrays.fill(CAGR_FUNDS,0.0);

                double retailer_funds=iInvest;


                //Add Growth to Investment

                for(int i=0;i<CAGR_FUNDS.length;i++){

                    double a1 = (cagr_funds[i]*retailer_funds)/100;

                    CAGR_FUNDS[i] = a1+retailer_funds;
                    double test = a1+retailer_funds;

                  //  Toast.makeText(getContext(), " Calculate growth  in spx "+ String.valueOf(test), Toast.LENGTH_SHORT).show();
                }





                if(selected.equals("^SPX")){


                    List<PerformanceCalander>CalList = getIndexes(mParam1,selected);

                    int yourInvestment = iInvest;

                    double[] cagr =CAGR(CalList, dur ) ;
                    //accum=0.0;



                    double[] CAGR_SPX = new double[13];
                    Arrays.fill(CAGR_SPX,0.0);

                    double retailer=yourInvestment;


                    //Add Growth to Investment

                    for(int i=0;i<CAGR_SPX.length;i++){

                        double a1 = (cagr[i]*retailer)/100;

                        CAGR_SPX[i] = a1+retailer;
                        double test = a1+retailer;

                     //   Toast.makeText(getContext(), " Calculate growth  in spx "+ String.valueOf(test), Toast.LENGTH_SHORT).show();
                    }

                    double Percent =  cagr[iTerm-1];
                    double Return = CAGR_SPX[iTerm-1];


                    double growthAndReturn = Math.round(Return*100.0)/100.0;
                    double indexGrowthPercent = Math.round(Percent*100.0)/100.0;




                    /************************* Graph Infrastructure *************************************/

                    // Building the month  legend names and controlling the number of items on the legend

                    // INPUT !!! Essentially the period of time entered by the investor for investment
                    int xy = iTerm;


                    // Build the Legend
                    String  mMonth[] = buildMonth(xy);


                    // Build the Y axis

                    int x[] = new int[xy];
                    for(int i=0;i<xy;i++){

                        x[i] = i;
                    }

                    // Get the min & Maxvalue

                    Double minGraph = getMin(CAGR_SPX);
                    Double maxGraph = getMax(CAGR_SPX);



                    // Decrease and Increase the legends x and y by 5 percent.. provide a better perspective of growth

                    Double decreaseMin = (minGraph / 100) * 5;
                    minGraph-=decreaseMin;

                    Double increaseMax = (maxGraph /100) * 5;
                    maxGraph+= increaseMax;



                    TextView investvalue = (TextView) view.findViewById(R.id.txtGLIinvestValue);
                    TextView growthvalue = (TextView) view.findViewById(R.id.txtGLIgrowthvalue);

                   // growthAndReturn = 10606;
                   // indexGrowthPercent = 6.9;

                    investvalue.setText(String.valueOf(growthAndReturn));
                    growthvalue.setText(String.valueOf(indexGrowthPercent));





/*************************** End of Graph Infracture *******************************/


             openChart(CAGR_SPX,CAGR_FUNDS,maxGraph.intValue(),minGraph.intValue(),"fund","S&P500",view,mMonth,x);



                }else if(selected.equals("^DJI")){




                    List<PerformanceCalander>CalList = getIndexes(mParam1,selected);

                    int yourInvestment = iInvest;

                    double[] cagr =CAGR(CalList, dur ) ;
                    //accum=0.0;



                    double[] CAGR_DJI = new double[13];
                    Arrays.fill(CAGR_DJI,0.0);

                    double retailer=yourInvestment;


                    //Add Growth to Investment

                    for(int i=0;i<CAGR_DJI.length;i++){

                        double a1 = (cagr[i]*retailer)/100;

                        CAGR_DJI[i] = a1+retailer;
                        double test = a1+retailer;

                      //  Toast.makeText(getContext(), " Calculate growth  in dji "+ String.valueOf(test), Toast.LENGTH_SHORT).show();
                    }

                    double Percent =  cagr[iTerm-1];
                    double Return = CAGR_DJI[iTerm-1];


                    double growthAndReturn = Math.round(Return*100.0)/100.0;
                    double indexGrowthPercent = Math.round(Percent*100.0)/100.0;






              //      Toast.makeText(getContext(), " The Dow  ", Toast.LENGTH_SHORT).show();

                    /************************* Graph Infrastructure *************************************/

                    // Building the month  legend names and controlling the number of items on the legend

                    // INPUT !!! Essentially the period of time entered by the investor for investment
                    int xy = iTerm;


                    // Build the Legend
                    String  mMonth[] = buildMonth(xy);


                    // Build the Y axis

                    int x[] = new int[xy];
                    for(int i=0;i<xy;i++){

                        x[i] = i;
                    }

                    // Get the min & Maxvalue

                    Double minGraph = getMin(CAGR_DJI);
                    Double maxGraph = getMax(CAGR_DJI);



                    // Decrease and Increase the legends x and y by 5 percent.. provide a better perspective of growth

                    Double decreaseMin = (minGraph / 100) * 5;
                    minGraph-=decreaseMin;

                    Double increaseMax = (maxGraph /100) * 5;
                    maxGraph+= increaseMax;



                    TextView investvalue = (TextView) view.findViewById(R.id.txtGLIinvestValue);
                    TextView growthvalue = (TextView) view.findViewById(R.id.txtGLIgrowthvalue);

                    // growthAndReturn = 10606;
                    // indexGrowthPercent = 6.9;

                    investvalue.setText(String.valueOf(growthAndReturn));
                    growthvalue.setText(String.valueOf(indexGrowthPercent));






/*************************** End of Graph Infracture *******************************/

                    openChart(CAGR_DJI,CAGR_FUNDS,maxGraph.intValue(),minGraph.intValue(),"PGMIX","Dow Jones",view,mMonth,x);


                }else if(selected.equals("^RUT")){


                 //   Toast.makeText(getContext(), " The Russel  ", Toast.LENGTH_SHORT).show();

                    List<PerformanceCalander>CalList = getIndexes(mParam1,selected);

                    int yourInvestment = iInvest;

                    double[] cagr_rji =CAGR(CalList, dur ) ;
                    //accum=0.0;



                    double[] CAGR_RUT = new double[13];
                    Arrays.fill(CAGR_RUT,0.0);

                    double retailer=yourInvestment;


                    //Add Growth to Investment

                    for(int i=0;i<CAGR_RUT.length;i++){

                        double a1 = (cagr_rji[i]*retailer)/100;

                        CAGR_RUT[i] = a1+retailer;
                        double test = a1+retailer;

                     //   Toast.makeText(getContext(), " Calculate growth  in rji "+ String.valueOf(test), Toast.LENGTH_SHORT).show();
                    }

                    double Percent =  cagr_rji[iTerm-1];
                    double Return = CAGR_RUT[iTerm-1];


                    double growthAndReturn = Math.round(Return*100.0)/100.0;
                    double indexGrowthPercent = Math.round(Percent*100.0)/100.0;












                    /************************* Graph Infrastructure *************************************/

                    // Building the month  legend names and controlling the number of items on the legend

                    // INPUT !!! Essentially the period of time entered by the investor for investment
                    int xy = iTerm;


                    // Build the Legend
                    String  mMonth[] = buildMonth(xy);


                    // Build the Y axis

                    int x[] = new int[xy];
                    for(int i=0;i<xy;i++){

                        x[i] = i;
                    }

                    // Get the min & Maxvalue

                    Double minGraph = getMin(CAGR_RUT);
                    Double maxGraph = getMax(CAGR_RUT);



                    // Decrease and Increase the legends x and y by 5 percent.. provide a better perspective of growth

                    Double decreaseMin = (minGraph / 100) * 5;
                    minGraph-=decreaseMin;

                    Double increaseMax = (maxGraph /100) * 5;
                    maxGraph+= increaseMax;



                    TextView investvalue = (TextView) view.findViewById(R.id.txtGLIinvestValue);
                    TextView growthvalue = (TextView) view.findViewById(R.id.txtGLIgrowthvalue);

                    investvalue.setText(String.valueOf(growthAndReturn));
                    growthvalue.setText(String.valueOf(indexGrowthPercent));







/*************************** End of Graph Infracture *******************************/


                    openChart(CAGR_RUT,CAGR_FUNDS,maxGraph.intValue(),minGraph.intValue(),"PGMIX","Russel",view,mMonth,x);

                }

































            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {




                String line = getArguments().getString("LineIndex");


                double[] symbol = new double[13];//{91.1,92.2,93.3,94.4,95.5,96.6,97.7,98.8,0,0,0,0 };
                Arrays.fill(symbol, 0);
                //     double[] benchmark=null;// {91


                String invest = getArguments().getString("investment");
                String duration = getArguments().getString("duration");
                List<PerformanceCalander> CalList = new LinkedList<>();



               final int fInvest = Integer.valueOf(invest);
               final int fTerm = Integer.valueOf(duration);

                int iInvest= fInvest;
                int iTerm = fTerm;


                if (getArguments() != null) {
                    mParam1 = getArguments().getString("JSON");

                }









                String Fund="";

                try {
                    JSONObject jsonObj = new JSONObject(mParam1);
                    JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");


                    JSONArray ja_dataCalander = ja_obj.getJSONArray("PerformanceCalander");

                    //int size = ja_dataSymbols.length();
                    double[] symboltmp = new double[12];

                    int cnt=0;
                    for(int i=0;i<ja_dataCalander.length();i++){


                        JSONObject rootObj = ja_dataCalander.getJSONObject(i);


                        Fund = rootObj.getString("Symbol");



                        //   String cleandate = epoch.replaceAll("[\"]", "");


                        //  Fund.trim();
                        //  line.trim();

                        //  Toast.makeText(getContext(), "Fund " + Fund+ " Line   "+line+ " size of calander  "+ja_dataCalander.length()+ " i +  " +String.valueOf(i), Toast.LENGTH_SHORT).show();
                        if (Fund.equals(line)) {

                            String sym = rootObj.getString("Symbol");
                            double navps  = rootObj.getDouble("NavPS");
                            String date  = rootObj.getString("Date");
                            PerformanceCalander  Cal = new PerformanceCalander(date,sym,navps);
                            CalList.add(Cal);
                            //        Toast.makeText(getContext(), "Nav  " + String.valueOf(navps)+ "Date   "+date, Toast.LENGTH_SHORT).show();

                        }

                    }

                }catch(Exception ex){
                    Toast.makeText(getContext(), " exception " +ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

                for(PerformanceCalander list : CalList){


                    //   Toast.makeText(getContext(), " Symbols "+ list.getSymbol()+" NavPs   " +String.valueOf(list.getNavps()+"Date "+list.getDate()), Toast.LENGTH_SHORT).show();
                }







                double dur=0.1;

                if (iTerm == 1) {
                    dur = 1;
                } else if (iTerm == 2) {
                    dur = 0.5;
                } else if (iTerm == 3) {

                    dur = 0.3;
                } else if (iTerm == 4) {

                    dur = 0.25;
                } else if (iTerm == 5) {
                    dur = 0.2;
                } else if (iTerm == 6) {
                    dur = 0.16;
                } else if (iTerm == 7) {
                    dur = 0.14;
                } else if (iTerm == 8) {
                    dur = 0.125;
                } else if (iTerm == 9) {
                    dur = 0.1;
                } else {

                    dur = 0.1;
                }


                int sz =13;


/********************************************************** Retailer Investment ************************************************************************/

                int yourInvestment = iInvest;

                double[] cagr =CAGR(CalList, dur ) ;
                double accum=0.0;



                double[] CAGR_INVEST = new double[13];
                Arrays.fill(CAGR_INVEST,0.0);

                double retailer=yourInvestment;


                //Add Growth to Investment

                for(int i=0;i<CAGR_INVEST.length;i++){

                    double a1 = (cagr[i]*retailer)/100;

                    CAGR_INVEST[i] = a1+retailer;
                    double test = a1+retailer;

                   // Toast.makeText(getContext(), " Calculate growth  "+ String.valueOf(test), Toast.LENGTH_SHORT).show();
                }

                double Percent =  cagr[iTerm-1];
                double Return = CAGR_INVEST[iTerm-1];


                double growthAndReturn = Math.round(Return*100.0)/100.0;
                double indexGrowthPercent = Math.round(Percent*100.0)/100.0;

                // double yourInvest = Double.valueOf(iInvest);


                //     double[] symbols={91.1,92.2,93.3,94.4,95.5,96.6,97.7,98.8,0,0,0,0 };
                double[] benchmark={10500.1,10600.2,10700.3,10800.4,10900.5,10800.6,10700.7,10600.8,0,0,0,0 };
                //   int max = 105;
                //   int min = 0;

                double[] fund={10600.1,10700.2,10600.3,10400.4,11000.5,10800.6,10700.7,10600.8,0,0,0,0 };






/************************************************************** *************************************************************************/




                /************************* Graph Infrastructure *************************************/

                // Building the month  legend names and controlling the number of items on the legend

                // INPUT !!! Essentially the period of time entered by the investor for investment
                int xy = iTerm;


                // Build the Legend
                String  mMonth[] = buildMonth(xy);


                // Build the Y axis

                int x[] = new int[xy];
                for(int i=0;i<xy;i++){

                    x[i] = i;
                }

                // Get the min & Maxvalue

                Double minGraph = getMin(fund);
                Double maxGraph = getMax(fund);



                // Decrease and Increase the legends x and y by 5 percent.. provide a better perspective of growth

                Double decreaseMin = (minGraph / 100) * 5;
                minGraph-=decreaseMin;

                Double increaseMax = (maxGraph /100) * 5;
                maxGraph+= increaseMax;








/*************************** End of Graph Infracture *******************************/

                Toast.makeText(getContext(), " On Nothing Selected ", Toast.LENGTH_SHORT).show();

                openChart(benchmark,CAGR_INVEST,maxGraph.intValue(),minGraph.intValue(),"fund","S&P500",view,mMonth,x);


            }
        });





        return view;
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_line, container, false);
    }


    public void openChart(double[] benchmark,double[] symbol,int plustol,int minustol,String fund,String Index,View view,String[] mMonth,int[] x ){




       // int[] x = { 0,1,2,3,4,5,6,7, 8,9,10,11 };



        // Creating an XYSeries for Income

        //XYSeries incomeSeries = new XYSeries("Income");
         benchmarkSeries = new XYSeries(Index);
         symbolSeries = new XYSeries(fund);

        // Creating an XYSeries for Expense

        //XYSeries expenseSeries = new XYSeries("Expense");

        // Adding data to Income and Expense Series

        for(int i=0;i<x.length;i++){

            benchmarkSeries.add(i,benchmark[i]);
            symbolSeries.add(i,symbol[i]);
            //  incomeSeries.add(i,benchmark[i]);

            //  expenseSeries.add(i,mfund[i]);

        }



        // Creating a dataset to hold each series

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

        // Adding Income Series to the dataset

        // dataset.addSeries(incomeSeries);
        dataset.addSeries(benchmarkSeries);
        dataset.addSeries(symbolSeries);

        // Adding Expense Series to dataset

        // dataset.addSeries(expenseSeries);



        // Creating XYSeriesRenderer to customize incomeSeries

        XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();

        incomeRenderer.setColor(Color.CYAN); //color of the graph set to cyan

        incomeRenderer.setFillPoints(true);

        incomeRenderer.setLineWidth(2f);

        incomeRenderer.setDisplayChartValues(true);

        //setting chart value distance

        incomeRenderer.setDisplayChartValuesDistance(10);

        //setting line graph point style to circle

        incomeRenderer.setPointStyle(PointStyle.CIRCLE);

        //setting stroke of the line chart to solid

        incomeRenderer.setStroke(BasicStroke.SOLID);



        // Creating XYSeriesRenderer to customize expenseSeries

        XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();

        expenseRenderer.setColor(Color.MAGENTA);

        expenseRenderer.setFillPoints(true);

        expenseRenderer.setLineWidth(2f);

        expenseRenderer.setDisplayChartValues(true);

        //setting line graph point style to circle

        expenseRenderer.setPointStyle(PointStyle.SQUARE);

        //setting stroke of the line chart to solid

        expenseRenderer.setStroke(BasicStroke.SOLID);



        // Creating a XYMultipleSeriesRenderer to customize the whole chart

        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();

        multiRenderer.setXLabels(0);

        multiRenderer.setChartTitle("Compare Annualized Indexes");
        multiRenderer.setChartTitleTextSize(70f);

        Calendar cal = Calendar.getInstance();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        multiRenderer.setXTitle( dateFormat.format(cal.getTime()));

        multiRenderer.setYTitle("Investment");





        /***
         104
         * Customizing graphs
         105
         */

        // multiRenderer.setApplyBackgroundColor(true);
        // multiRenderer.setBackgroundColor(Color.WHITE);

        //setting text size of the title

        multiRenderer.setChartTitleTextSize(40); //28

        //setting text size of the axis title

        multiRenderer.setAxisTitleTextSize(30); //24

        //setting text size of the graph lable

        multiRenderer.setLabelsTextSize(30); //

        //setting zoom buttons visiblity
        multiRenderer.setZoomButtonsVisible(false);

        //setting pan enablity which uses graph to move on both axis

        multiRenderer.setPanEnabled(false, false);

        //setting click false on graph

        multiRenderer.setClickEnabled(false);

        //setting zoom to false on both axis

        multiRenderer.setZoomEnabled(false, false);

        //setting lines to display on y axis

        multiRenderer.setShowGridY(true);

        //setting lines to display on x axis
        multiRenderer.setShowGridX(true);

        //setting legend to fit the screen size

        multiRenderer.setFitLegend(true);

        //setting displaying line on grid

        multiRenderer.setShowGrid(true);

        //setting zoom to false

        multiRenderer.setZoomEnabled(false);

        //setting external zoom functions to false

        multiRenderer.setExternalZoomEnabled(false);

        //setting displaying lines on graph to be formatted(like using graphics)

        multiRenderer.setAntialiasing(true);

        //setting to in scroll to false

        multiRenderer.setInScroll(false);

        //setting to set legend height of the graph

        multiRenderer.setLegendHeight(30);

        //setting x axis label align

        multiRenderer.setXLabelsAlign(Paint.Align.CENTER);

        //setting y axis label to align

        multiRenderer.setYLabelsAlign(Paint.Align.LEFT);

        //setting text style

        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);

        //setting no of values to display in y axis

        multiRenderer.setYLabels(10);

        // setting y axis max value, Since i'm using static values inside the graph so i'm setting y max value to 4000.

        // if you use dynamic values then get the max y value and set here

        //  multiRenderer.setYAxisMax(11000);
        //  multiRenderer.setYAxisMin(9000);

        multiRenderer.setYAxisMax(plustol);
        multiRenderer.setYAxisMin(minustol);

        //setting used to move the graph on xaxiz to .5 to the right

        multiRenderer.setXAxisMin(-0.5);

        //setting used to move the graph on xaxiz to .5 to the right

        multiRenderer.setXAxisMax(11);

        //setting bar size or space between two bars

        //multiRenderer.setBarSpacing(0.5);

        //Setting background color of the graph to transparent

        multiRenderer.setBackgroundColor(Color.TRANSPARENT);

        //Setting margin color of the graph to transparent

        multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));

        multiRenderer.setApplyBackgroundColor(true);

        multiRenderer.setScale(4f);

        //setting x axis point size

        multiRenderer.setPointSize(6f);

        //setting the margin size for the graph in the order top, left, bottom, right

        multiRenderer.setMargins(new int[]{30, 30, 30, 30});



        for(int i=0; i< x.length;i++){

            multiRenderer.addXTextLabel(i, mMonth[i]);

        }



        // Adding incomeRenderer and expenseRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer

        // should be same

        multiRenderer.addSeriesRenderer(incomeRenderer);

        multiRenderer.addSeriesRenderer(expenseRenderer);



        //this part is used to display graph on the xml

        LinearLayout chartContainer = (LinearLayout) view.findViewById(R.id.chart);

        //remove any views before u paint the chart

        chartContainer.removeAllViews();

        //drawing bar chart

        mChart = ChartFactory.getLineChartView(getActivity(), dataset, multiRenderer);

        //adding the view to the linearlayout

        chartContainer.addView(mChart);

    }






    public double[] CAGR(List<PerformanceCalander> sList, double dur) {

        double[] cagr= new double[13];


        double tmpV1=0;
        double value=0;
        String tPoch="";
        String ePoch="";

        double Rv=0;
        Date d1=null;
        Date d2=null;
        double R2=0;
        double R3=0;



        SimpleDateFormat format = new SimpleDateFormat("m/d/yyyy hh:mm:ss");
      // SimpleDateFormat format = new SimpleDateFormat("m/d/yyyy hh:mm:ss");

        double R1=0;
        double Result;
        int cnt=0;
        Boolean once=true;

        for(PerformanceCalander sym : sList){



            value = sym.getNavps();
            ePoch = sym.getDate();

            if(!tPoch.isEmpty()){


                try {

                    Rv = (	(double)value / (double)tmpV1 );
                    R1 = ((double)dur / (double)1);
                    R2 = (Math.pow(Rv,R1));

                    R2 = R2 -1;
                    R3 = R2 * 100;

                    cagr[cnt]=R3;
                    cnt++;
                    once = false;
                     // Toast.makeText(getContext(), " Inside CAGR SPX  "+ String.valueOf(cagr[cnt]), Toast.LENGTH_SHORT).show();
                } catch (Exception ex){


                }
            }

            if(once){
                tmpV1 = value;
                tPoch = ePoch;
            }





            //System.out.println();



        }

        return cagr;
    }


    public double[] ArrayGrowthtoInvestment(List<PerformanceCalander> cagrIndex,int Investment, double term,int sz){



        // Add the retailers investment to the
        // growth of the index

        double[] CAGRIndex = new double[sz];
        Arrays.fill(CAGRIndex,0);
        double[] CAGRIndxTMP = CAGR(cagrIndex, term ) ;

        for(int i=0;i<CAGRIndxTMP.length;i++){

            double a1 = (CAGRIndxTMP[i]*Investment)/100;

            CAGRIndex[i] = a1+Investment;
        }

        return CAGRIndex;




    }

    public double calculateGrowth(List<PerformanceCalander> hdatas,double term){




        double[] cagr =CAGR(hdatas, term ) ;
        double accum=0.0;


        // Calculate growth
        for(int d = 0; d < cagr.length;d++){

            accum+= cagr[d];
         //   Toast.makeText(getContext(), " Inside CAGR  "+ String.valueOf(cagr[d]), Toast.LENGTH_SHORT).show();
        }

       return accum;

    }




    List<Hdata> DummyList () {

        List<Hdata> Index = new LinkedList<Hdata>();

        Hdata symbol1 = new Hdata(1,"S&P500","1/1/2001 12:00:00 AM",99.0,100.0,101.0,104.0,10.27,70);
        Hdata symbol2 = new Hdata(2,"S&P500","1/2/2001 12:00:00 AM",99.0,100.0,101.0,104.0,15.00,70);
        Hdata symbol3 = new Hdata(3,"S&P500","1/3/2001 12:00:00 AM",99.0,100.0,101.0,104.0,30.96,70);
        Hdata symbol4 = new Hdata(4,"S&P500","1/4/2001 12:00:00 AM",99.0,100.0,101.0,104.0,31.92,70);
        Hdata symbol5 = new Hdata(5,"S&P500","1/5/2001 12:00:00 AM",99.0,100.0,101.0,104.0,40.22,70);
        Hdata symbol6 = new Hdata(6,"S&P500","1/6/2001 12:00:00 AM",99.0,100.0,101.0,104.0,41.35,70);
        Hdata symbol7 = new Hdata(7,"S&P500","1/7/2001 12:00:00 AM",99.0,100.0,101.0,104.0,50.55,70);
        Hdata symbol8 = new Hdata(8,"S&P500","1/8/2001 12:00:00 AM",99.0,100.0,101.0,104.0,40.48,70);
        Hdata symbol9 = new Hdata(9,"S&P500","1/9/2001 12:00:00 AM",99.0,100.0,101.0,104.0,30.60,70);
        Hdata symbol10 = new Hdata(10,"S&P500","1/10/2001 12:00:00 AM",99.0,100.0,101.0,104.0,20.41,70);
        Hdata symbol11 = new Hdata(11,"S&P500","1/11/2001 12:00:00 AM",99.0,100.0,101.0,104.0,24.66,70);
        Hdata symbol12 = new Hdata(12,"S&P500","1/12/2001 12:00:00 AM",99.0,100.0,101.0,104.0,26.90,70);




        Index.add(symbol1);
        Index.add(symbol2);
        Index.add(symbol3);
        Index.add(symbol4);
        Index.add(symbol5);
        Index.add(symbol6);
        Index.add(symbol7);
        Index.add(symbol8);
        Index.add(symbol9);
        Index.add(symbol10);
        Index.add(symbol11);
        Index.add(symbol12);





        return Index;



    }

    List<Hdata> DummyList2 () {

        List<Hdata> Index = new LinkedList<Hdata>();

        Hdata symbol1 = new Hdata(1,"S&P500","1/1/2001 12:00:00 AM",99.0,100.0,101.0,104.0,110.27,70);
        Hdata symbol2 = new Hdata(2,"S&P500","1/2/2001 12:00:00 AM",99.0,100.0,101.0,104.0,120.00,70);
        Hdata symbol3 = new Hdata(3,"S&P500","1/3/2001 12:00:00 AM",99.0,100.0,101.0,104.0,130.96,70);
        Hdata symbol4 = new Hdata(4,"S&P500","1/4/2001 12:00:00 AM",99.0,100.0,101.0,104.0,140.92,70);
        Hdata symbol5 = new Hdata(5,"S&P500","1/5/2001 12:00:00 AM",99.0,100.0,101.0,104.0,150.22,70);
        Hdata symbol6 = new Hdata(6,"S&P500","1/6/2001 12:00:00 AM",99.0,100.0,101.0,104.0,160.35,70);
        Hdata symbol7 = new Hdata(7,"S&P500","1/7/2001 12:00:00 AM",99.0,100.0,101.0,104.0,170.55,70);
        Hdata symbol8 = new Hdata(8,"S&P500","1/8/2001 12:00:00 AM",99.0,100.0,101.0,104.0,180.48,70);
        Hdata symbol9 = new Hdata(9,"S&P500","1/9/2001 12:00:00 AM",99.0,100.0,101.0,104.0,190.60,70);
        Hdata symbol10 = new Hdata(10,"S&P500","1/10/2001 12:00:00 AM",99.0,100.0,101.0,104.0,200.41,70);
        Hdata symbol11 = new Hdata(11,"S&P500","1/11/2001 12:00:00 AM",99.0,100.0,101.0,104.0,120.66,70);
        Hdata symbol12 = new Hdata(12,"S&P500","1/12/2001 12:00:00 AM",99.0,100.0,101.0,104.0,123.90,70);




        Index.add(symbol1);
        Index.add(symbol2);
        Index.add(symbol3);
        Index.add(symbol4);
        Index.add(symbol5);
        Index.add(symbol6);
        Index.add(symbol7);
        Index.add(symbol8);
        Index.add(symbol9);
        Index.add(symbol10);
        Index.add(symbol11);
        Index.add(symbol12);





        return Index;



    }





    public static double[] twodecpl(double[] cagr){


        DecimalFormat twodecpl = new DecimalFormat("#.00");
        double dr=0;

        double newValue=0;
        double accum1=0;
        double[] pass1 = new double[cagr.length];
        double[] pass2 = new double[cagr.length];
        double[] pass3 = new double[cagr.length];

        double accum2=0;
        double accum3=0;


        for(int d = 0; d < cagr.length;d++){

            dr = (cagr[d]);
            double newKB = Math.round(dr*100.0)/100.0;
            double a1=Math.floor(dr*100) / 100;
            accum1+=a1;
            pass1[d] = accum1;

        }

        for(int d = 0; d < cagr.length;d++){

            double	p2 = (pass1[d]);
            double newdb = Math.round(p2*100.0)/100.0;
            //double a1=Math.floor(dr*100) / 100;
            accum2=newdb;
            pass2[d] = accum2;

        }



        for(int d = 0; d < cagr.length;d++){

            //   double	p3 = (pass2[d]);
            BigDecimal bd =	truncateDecimal(pass2[d], 2);
            accum3= bd.doubleValue();
            pass3[d]=accum3;


        }

        return pass3;





    }




    private static BigDecimal truncateDecimal(final double x, final int numberofDecimals) {
        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_DOWN);
    }








    public static String[] buildMonth(int sz) {

        String[] month= new String[sz];

        switch (sz ) {


            case 1:   month = new String[] {"2017"};

                break;

            case 2:
                month = new String[] {"2016","2017"};
                break;

            case 3:
                month = new String[] {"2015", "2016", "2017"};
                break;

            case 4:
                month = new String[] { "2014", "2015", "2016", "2017"};
                break;
            case 5:
                month = new String[] { "2013", "2014", "2015", "2016", "2017"};
                break;

            case 6:
                month = new String[] {  "2012", "2013", "2014", "2015", "2016", "2017"};
                break;


            case 7:   month = new String[] { "2011","2012","2013","2014","2015","2016","2017"};

                break;

            case 8:  month = new String[] { "2010","2011","2012","2013","2014","2015","2016","2017"};
                break;

            case 9: month = new String[] {"2009","2010","2011","2012","2013","2014","2015","2016","2017"};
                break;

            case 10: month = new String[] { "2008","2009","2010","2011","2012","2013","2014","2015","2016","2017"};
                break;
            case 11:

                month = new String[] {  "2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017"};
                break;

            case 12: month = new String[] { "2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017"};
                break;

            default:
                break;
        }




        return month;
    }

public Double getMin(double[] fund) {


    double min=0;
    double low=0;
    min=fund[0];
    for(int i=0; i< fund.length;i++){

        low=fund[i];
        if(low <= min  && low != 0){
            min = low;
        }
    }

    return min;
}



public Double getMax(double[] fund){


    //	 Get the Max

    double max=0;
    double high=0;
    max=fund[0];
    for(int i =0; i< fund.length;i++){

        high=fund[i];
        if(high >= max){
            max = high;
        }

    }

        return max;
}


List<Hdata> getSPX(){


    Hdata hdata = new Hdata();
    java.util.List<Hdata> hdatas= new LinkedList<>();
    java.util.List<Hdata> Index = new LinkedList<Hdata>();

    Hdata symbol1 = new Hdata(1,"S&P500","1/1/2001 12:00:00 AM",99.0,100.0,101.0,104.0,10.27,70);
    Hdata symbol2 = new Hdata(2,"S&P500","1/2/2001 12:00:00 AM",99.0,100.0,101.0,104.0,10.22,70);
    Hdata symbol3 = new Hdata(3,"S&P500","1/3/2001 12:00:00 AM",99.0,100.0,101.0,104.0,9.96,70);
    Hdata symbol4 = new Hdata(4,"S&P500","1/4/2001 12:00:00 AM",99.0,100.0,101.0,104.0,10.92,70);
    Hdata symbol5 = new Hdata(5,"S&P500","1/5/2001 12:00:00 AM",99.0,100.0,101.0,104.0,10.22,70);
    Hdata symbol6 = new Hdata(6,"S&P500","1/6/2001 12:00:00 AM",99.0,100.0,101.0,104.0,11.35,70);
    Hdata symbol7 = new Hdata(7,"S&P500","1/7/2001 12:00:00 AM",99.0,100.0,101.0,104.0,11.55,70);
    Hdata symbol8 = new Hdata(8,"S&P500","1/8/2001 12:00:00 AM",99.0,100.0,101.0,104.0,11.48,70);
    Hdata symbol9 = new Hdata(9,"S&P500","1/9/2001 12:00:00 AM",99.0,100.0,101.0,104.0,11.60,70);
    Hdata symbol10 = new Hdata(10,"S&P500","1/10/2001 12:00:00 AM",99.0,100.0,101.0,104.0,11.41,70);
    Hdata symbol11 = new Hdata(11,"S&P500","1/11/2001 12:00:00 AM",99.0,100.0,101.0,104.0,11.66,70);
    Hdata symbol12 = new Hdata(12,"S&P500","1/12/2001 12:00:00 AM",99.0,100.0,101.0,104.0,11.90,70);




    Index.add(symbol1);
    Index.add(symbol2);
    Index.add(symbol3);
    Index.add(symbol4);
    Index.add(symbol5);
    Index.add(symbol6);
    Index.add(symbol7);
    Index.add(symbol8);
    Index.add(symbol9);
    Index.add(symbol10);
    Index.add(symbol11);
    Index.add(symbol12);



    return Index;





    }

    List<String>getSpinnerIndexes(String json) {


        String Fund="";
        List<String> Index = new ArrayList<String>();

        try {
            JSONObject jsonObj = new JSONObject(mParam1);
            JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");


            JSONArray ja_dataCalander = ja_obj.getJSONArray("SpinnerIDX");

            int cnt=0;
            for(int i=0;i<ja_dataCalander.length();i++){


                JSONObject rootObj = ja_dataCalander.getJSONObject(i);


                Fund = rootObj.getString("Symbol");
                String sym = rootObj.getString("Symbol");
                Index.add(sym);
                }

        }catch(Exception ex){
            Toast.makeText(getContext(), " exception " +ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


        return Index;
    }


    List<PerformanceCalander>getIndexes(String json, String line) {


        String Fund="";
        List<PerformanceCalander> CalList = new LinkedList<PerformanceCalander>();

        try {
            JSONObject jsonObj = new JSONObject(mParam1);
            JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");


            JSONArray ja_dataCalander = ja_obj.getJSONArray("PerformanceCalanderIDX");

            int cnt=0;
            for(int i=0;i<ja_dataCalander.length();i++){


                JSONObject rootObj = ja_dataCalander.getJSONObject(i);


                Fund = rootObj.getString("Symbol");

               // Toast.makeText(getContext(), "Fund  " + Fund+ "Line   "+ line, Toast.LENGTH_SHORT).show();

               if (Fund.equals(line)) {

                    String sym = rootObj.getString("Symbol");
                    double navps  = rootObj.getDouble("NavPS");
                    String date  = rootObj.getString("Date");
                    PerformanceCalander  Cal = new PerformanceCalander(date,sym,navps);
                    CalList.add(Cal);

                  // Toast.makeText(getContext(), "Nav  " + String.valueOf(navps)+ "Date   "+date, Toast.LENGTH_SHORT).show();

                }


            }

        }catch(Exception ex){
            Toast.makeText(getContext(), " Exception in getIndex" +ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


        return CalList;
    }



    List<PerformanceCalander>getFunds(String json, String line) {


        String Fund="";
        List<PerformanceCalander> CalList = new LinkedList<PerformanceCalander>();

        try {
            JSONObject jsonObj = new JSONObject(mParam1);
            JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");


            JSONArray ja_dataCalander = ja_obj.getJSONArray("PerformanceCalander");

            int cnt=0;
            for(int i=0;i<ja_dataCalander.length();i++){


                JSONObject rootObj = ja_dataCalander.getJSONObject(i);


                Fund = rootObj.getString("Symbol");

            //    Toast.makeText(getContext(), "Fund  " + Fund+ "Line   "+ line, Toast.LENGTH_SHORT).show();

                if (Fund.equals(line)) {

                    String sym = rootObj.getString("Symbol");
                    double navps  = rootObj.getDouble("NavPS");
                    String date  = rootObj.getString("Date");
                    PerformanceCalander  Cal = new PerformanceCalander(date,sym,navps);
                    CalList.add(Cal);

                   // Toast.makeText(getContext(), "Nav  " + String.valueOf(navps)+ "Date   "+date, Toast.LENGTH_SHORT).show();

                }


            }

        }catch(Exception ex){
            Toast.makeText(getContext(), " Exception in getIndex" +ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


        return CalList;
    }





}












