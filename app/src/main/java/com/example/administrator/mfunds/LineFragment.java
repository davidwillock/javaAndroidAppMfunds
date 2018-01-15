package com.example.administrator.mfunds;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.content.Context;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.text.Format;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

import java.util.List;







public class LineFragment extends Fragment {



    private String mParam1;
    private String mParam2;

    private Context context;

    MySQLiteHelper  db;

    private View mChart;
/*
    private String[] mMonth = new String[] {

            "Jan", "Feb" , "Mar", "Apr", "May", "Jun",

            "Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"

    };
*/


    public LineFragment() {
        // Required empty public constructor
    }



    public static LineFragment newInstance(String param1, String param2, String invest, String term) {
        LineFragment fragment = new LineFragment();
        Bundle args = new Bundle();
        args.putString("JSON", param1);
        args.putString("Line", param2);
        args.putString("investment",invest);
        args.putString("duration",term);
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


        View view = (LinearLayout) inflater.inflate(R.layout.fragment_line,
                container, false);




        List<String> FundYears = new ArrayList<String>();
        FundYears.add("2017");
        FundYears.add("2016");
        FundYears.add("2015");


        //Populate the spinner in the fragment
        Spinner spinner = (Spinner) view.findViewById(R.id.LGspinnerYears);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_item,FundYears);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

/*
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selected = parent.getItemAtPosition(position).toString();

                Toast.makeText(getContext(), " Item selected  " + selected  , Toast.LENGTH_SHORT).show();




            }



        });

*/














        TextView investvalue = (TextView) view.findViewById(R.id.txtGLValue);
        TextView growthvalue = (TextView) view.findViewById(R.id.txtGLgrowthValue);
        TextView fundname = (TextView) view.findViewById(R.id.txtLGfund);



        String invest = getArguments().getString("investment");
        String duration = getArguments().getString("duration");

        int iInvest = Integer.valueOf(invest);
        int iTerm = Integer.valueOf(duration);

        String line = getArguments().getString("Line");

        Hdata hdata = new Hdata();
        java.util.List<Hdata> hdatas = new LinkedList<Hdata>();
        java.util.List<Hdata> Index = new LinkedList<Hdata>();
        List<Hdata> IndexList = new LinkedList<Hdata>();





        if (getArguments() != null) {
            mParam1 = getArguments().getString("JSON");

        }
        String strLine = line+"_m";




        String Fund="";

        try {
            JSONObject jsonObj = new JSONObject(mParam1);
            JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");


            JSONArray ja_dataSymbols = ja_obj.getJSONArray("Symbols");

            int size = ja_dataSymbols.length();
            double[] symboltmp = new double[12];
            int cnt=0;
            for(int i=0;i<ja_dataSymbols.length();i++){


                JSONObject rootObj = ja_dataSymbols.getJSONObject(i);


                Fund = rootObj.getString("symbol");




                //  String cleandate = epoch.replaceAll("[\"]", "");


                Fund.trim();
                strLine.trim();
             //   Toast.makeText(getContext(), " strLine " + strLine +"  Fund  "+  Fund + " size of symbols "+size ,Toast.LENGTH_SHORT).show();

                if (strLine.equals(Fund)) {

                    double db = rootObj.getDouble("close_adj");
                    String epoch = rootObj.getString("epoch");

                    double num=db;
                    //    hdata.setAdj_close(db);

                    hdata.setDate(epoch);
                    hdata.setAdj_close(db);
                    hdatas.add(new Hdata(i,Fund,epoch,0.0,0.0,0.0,0.0,db,0));
                    // Toast.makeText(getContext(), " Inside Parse  " +String.valueOf(db)  , Toast.LENGTH_SHORT).show();
                }
            }

        }catch(Exception ex){
            //        Toast.makeText(getContext(), " Inside Parse  " +ex.getMessage() , Toast.LENGTH_SHORT).show();


        }


        for(Hdata test : hdatas ){

            String str = test.getSymbol();
            double db = test.getAdj_close();

            //           Toast.makeText(getContext(), " Inside Parse  "+ str+ " "+String.valueOf(db)  , Toast.LENGTH_SHORT).show();


        }


      //  Index = DummyList();





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




/***********   Calculate Acumulated Growth of a Fund or Index    ******************/

        //  Fund


        List<PerformanceCalander>CalList = getIndexes(mParam1,"^SPX");

        int yourInvestment = iInvest;
        // hardcoded for month snap shot
        double[] cagr_spx =CAGR_CAL(CalList, 1.0 ) ;
        //accum=0.0;



        double[] CAGR_SPX = new double[13];
        Arrays.fill(CAGR_SPX,0.0);

        double retailer=yourInvestment;


        //Add Growth to Investment

        for(int i=0;i<CAGR_SPX.length;i++){

            double a1 = (cagr_spx[i]*retailer)/100;

            CAGR_SPX[i] = a1+retailer;
            double test = a1+retailer;

        //    Toast.makeText(getContext(), " Calculate growth  in spx "+ String.valueOf(test), Toast.LENGTH_SHORT).show();
        }

    //    double Percent =  cagr_spx[iTerm-1];
    //    double Return = CAGR_SPX[iTerm-1];


      //  double growthAndReturn = Math.round(Return*100.0)/100.0;
      //  double indexGrowthPercent = Math.round(Percent*100.0)/100.0;











/************************************** ************************************************/










        /***********   Calculate Acumulated Growth of a Fund    ******************/




        double[] cagr_fund =CAGR(hdatas, dur ) ;
        double accum=0.0;



        double[] CAGR_INVEST = new double[13];
        Arrays.fill(CAGR_INVEST,0.0);






        //Add Growth to Investment

        for(int i=0;i<CAGR_INVEST.length;i++){

            double a1 = (cagr_fund[i]*retailer)/100;

            CAGR_INVEST[i] = a1+retailer;
            double test = a1+retailer;

            //       Toast.makeText(getContext(), " Calculate growth  "+ String.valueOf(test), Toast.LENGTH_SHORT).show();
        }










        double Percent =  cagr_fund[iTerm-1];
        double Return = CAGR_INVEST[iTerm-1];


        double growthAndReturn = Math.round(Return*100.0)/100.0;
        double indexGrowthPercent = Math.round(Percent*100.0)/100.0;



        investvalue.setText(String.valueOf(growthAndReturn));
        growthvalue.setText(String.valueOf(indexGrowthPercent));














/************************************** ************************************************/



        //   Toast.makeText(getContext(), " Your Investment "+ iInvest, Toast.LENGTH_SHORT).show();
//        investvalue.setText(String.valueOf(iInvest));
//        growthvalue.setText(String.valueOf(percent));
        fundname.setText(line);






        /************************************************************** *************************************************************************/




        /************************* Graph Infrastructure *************************************/

        // Building the month  legend names and controlling the number of items on the legend

        // INPUT !!! Essentially the period of time entered by the investor for investment
        int xy = iTerm;


        // Build the Legend
        String  mMonth[] = buildMonth(12);


        // Build the Y axis hardcoded for  month snap shot

        int x[] = new int[12];
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








/*************************** End of Graph Infracture *******************************/







        openChart(CAGR_SPX,CAGR_INVEST,maxGraph.intValue(),minGraph.intValue(),Fund,"S&P500",view,mMonth,x);

        return view;
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_line, container, false);
    }


    //  public void openChart(double[] benchmark,double[] symbol,int plustol,int minustol,String fund,String Index,View view){

    public void openChart(double[] benchmark,double[] symbol,int plustol,int minustol,String fund,String Index,View view,String[] mMonth,int[] x ){


// REFERENCE ACHART ENGINE WEBSITE

        //  int[] x = { 0,1,2,3,4,5,6,7, 8,9,10,11 };



        // Creating an XYSeries for Income

        //XYSeries incomeSeries = new XYSeries("Income");
        XYSeries benchmarkSeries = new XYSeries(Index);
        XYSeries symbolSeries = new XYSeries(fund);

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

        expenseRenderer.setColor(Color.GREEN);

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

        multiRenderer.setChartTitle("1 Year Investment");
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

        multiRenderer.setXLabelsAlign(Align.CENTER);

        //setting y axis label to align

        multiRenderer.setYLabelsAlign(Align.LEFT);

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






    public double[] CAGR(List<Hdata> sList, double dur) {

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

        for(Hdata sym : sList){



            value = sym.getAdj_close();
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



    public List<Hdata> parseJsonObject(String json,String line){


        Hdata hdata = new Hdata();
        List<Hdata> hdatas = new LinkedList<>();


        line+="_m";
        String Fund="";

        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONObject ja_obj = jsonObj.getJSONObject("FundsByRiskResult");


            JSONArray ja_dataSymbols = ja_obj.getJSONArray("Symbols");

            //int size = ja_dataSymbols.length();
            double[] symboltmp = new double[12];
            int cnt=0;
            for(int i=0;i<ja_dataSymbols.length();i++){


                JSONObject rootObj = ja_dataSymbols.getJSONObject(i);


                Fund = rootObj.getString("symbol");



                //   String cleandate = epoch.replaceAll("[\"]", "");


                Fund.trim();


                if (Fund.equals(line)) {

                    double db = rootObj.getDouble("close_adj");
                    String epoch = rootObj.getString("epoch");

                    double num=db;
                    //    hdata.setAdj_close(db);

                    hdata.setDate(epoch);
                    hdata.setAdj_close(db);
                    hdatas.add(new Hdata(i,Fund,epoch,0.0,0.0,0.0,0.0,db,0));
                    //    Toast.makeText(getContext(), " Inside Parse  " +String.valueOf(db)  , Toast.LENGTH_SHORT).show();
                }
            }

        }catch(Exception ex){



        }

        return hdatas;
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





















    public double[] ArrayGrowthtoInvestment(List<Hdata> cagrIndex,int Investment, double term){



        // Add the retailers investment to the
        // growth of the index

        double[] CAGRIndex = new double[13];
        Arrays.fill(CAGRIndex,0);
        double[] CAGRIndxTMP = CAGR(cagrIndex, term ) ;

        for(int i=0;i<CAGRIndxTMP.length;i++){

            double a1 = (CAGRIndxTMP[i]*Investment)/100;

            CAGRIndex[i] = a1+Investment;
        }

        return CAGRIndex;




    }

    public double calculateGrowth(List<Hdata> hdatas,double term){




        double[] cagr =CAGR(hdatas, term ) ;
        double accum=0.0;


        // Calculate growth
        for(int d = 0; d < cagr.length;d++){

            accum+= cagr[d];
            //     Toast.makeText(getContext(), " Inside CAGR  "+ String.valueOf(cagr[d]), Toast.LENGTH_SHORT).show();
        }

        return accum;

    }







    public static String[] buildMonth(int sz) {

        String[] month= new String[sz];

        switch (sz ) {


            case 1:   month = new String[] { "Jan"};

                break;

            case 2:
                month = new String[] { "Jan", "Feb" };
                break;

            case 3:
                month = new String[] { "Jan", "Feb" , "Mar" };
                break;

            case 4:
                month = new String[] { "Jan", "Feb" , "Mar", "Apr" };
                break;
            case 5:
                month = new String[] {  "Jan", "Feb" , "Mar", "Apr", "May" };
                break;

            case 6:
                month = new String[] {  "Jan", "Feb" , "Mar", "Apr", "May", "Jun"};
                break;


            case 7:   month = new String[] {  "Jan", "Feb" , "Mar", "Apr", "May", "Jun", "Jul" };

                break;

            case 8:  month = new String[] {  "Jan", "Feb" , "Mar", "Apr", "May", "Jun", "Jul", "Aug"  };
                break;

            case 9: month = new String[] { "Jan", "Feb" , "Mar", "Apr", "May", "Jun", "Jul", "Aug" , "Sep" };
                break;

            case 10: month = new String[] {  "Jan", "Feb" , "Mar", "Apr", "May", "Jun", "Jul", "Aug" , "Sep", "Oct" };
                break;
            case 11:

                month = new String[] {   "Jan", "Feb" , "Mar", "Apr", "May", "Jun", "Jul", "Aug" , "Sep", "Oct", "Nov"};
                break;

            case 12: month = new String[] {  "Jan", "Feb" , "Mar", "Apr", "May", "Jun", "Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"};
                break;

            default:
                break;
        }




        return month;
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
        Hdata symbol10 = new Hdata(10,"S&P500","1/10/2001 12:00:00 AM",99.0,100.0,101.0,104.0,60.41,70);
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


    public double[] CAGR_CAL(List<PerformanceCalander> sList, double dur) {

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



}
