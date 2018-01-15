package com.example.administrator.mfunds;

import java.util.LinkedList;
import java.util.List;
import android.widget.Toast;
import java.util.ArrayList;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {



// Reference from SQLite Web Site on how to use..


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "MUTUALFUNDS";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create student records table
        String CREATE_SYMBOL_TABLE = "CREATE TABLE IF NOT EXISTS SYMBOLS ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "symbol TEXT ,  "+
                "date TEXT, "+
                "open REAL, "+
                "high REAL, "+
                "low REAL, "+
                "close REAL, "+
                "adj_close REAL, "+
                "key TEXT ) ";


        String CREATE_SECTORALLOCATION_TABLE = "CREATE TABLE IF NOT EXISTS SectorAllocation ( " +
                "SectorID INT ,  "+
                "Sector TEXT ) ";




        String CREATE_HOLDINGS_TABLE = "CREATE TABLE IF NOT EXISTS HOLDINGS ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FundSymID TEXT ,  "+
                "SymID TEXT, "+
                "Name TEXT, "+
                "Asset TEXT, "+
                "Sector TEXT, "+
                "Geograph TEXT, "+
                "Percentage REAL ) ";

        String CREATE_PERFORMANCEDATA_TABLE = "CREATE TABLE IF NOT EXISTS PERFORMANCEDATA ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "SymID TEXT ,  "+
                "InceptionDate TEXT, "+
                "MER REAL, "+
                "Assets REAL, "+
                "Rank REAL, "+
                "MstarRating REAL, "+
                "StdDev REAL, "+
                "VolatileRank REAL, "+
                "MstarRisk REAL, "+
                "Alpha REAL, "+
                "Beta REAL, "+
                "Rsquared REAL, "+
                "RRSPEligibility TEXT, "+
                "Load TEXT, "+
                "MaxFrontEnd REAL, "+
                "MaxBackEnd REAL, "+
                "SaleOpen TEXT, "+
                "NavPS REAL, "+
                "NetAsset REAL, "+
                "Yield REAL, "+
                "Dividend REAL ) ";



        String CREATE_PERFORMANCERETURNS_TABLE = "CREATE TABLE IF NOT EXISTS PERFORMANCERETURNS ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Return TEXT ,  "+
                "OneMonth REAL, "+
                "ThreeMonth REAL, "+
                "SixMonth REAL, "+
                "YTD REAL, "+
                "OneYear REAL, "+
                "ThreeYear REAL, "+
                "FiveYear REAL, "+
                "TenYear TEXT ) ";

        String CREATE_CHARTSECTOR_TABLE = "CREATE TABLE IF NOT EXISTS CHARTSECTOR ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FUND TEXT ,  "+
                "SECTOR TEXT, "+
                "COUNT INT, "+
                "PERCENT INT ) ";


        String CREATE_CHARTASSET_TABLE = "CREATE TABLE IF NOT EXISTS CHARTASSET ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FUND TEXT ,  "+
                "ASSET TEXT, "+
                "COUNT INT, "+
                "PERCENT INT ) ";

        String CREATE_CHARTGEOGRAPH_TABLE = "CREATE TABLE IF NOT EXISTS CHARTGEOGRAPH ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FUND TEXT ,  "+
                "GEOGRAPH TEXT, "+
                "COUNT INT, "+
                "PERCENT INT ) ";


        String CREATE_TEST_TABLE = "CREATE TABLE IF NOT EXISTS TEST ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FUND INT ,  "+
                "GEOGRAPH TEXT, "+
                "COUNT INT, "+
                "PERCENT INT ) ";







        // create symbol records table
        db.execSQL(CREATE_SYMBOL_TABLE);
        db.execSQL(CREATE_HOLDINGS_TABLE);
        db.execSQL(CREATE_PERFORMANCEDATA_TABLE);
        db.execSQL(CREATE_PERFORMANCERETURNS_TABLE);
        db.execSQL(CREATE_SECTORALLOCATION_TABLE);
        db.execSQL(CREATE_CHARTSECTOR_TABLE);
        db.execSQL(CREATE_CHARTASSET_TABLE);
        db.execSQL(CREATE_CHARTGEOGRAPH_TABLE);
        db.execSQL(CREATE_TEST_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older  records table if existed
        db.execSQL("DROP TABLE IF EXISTS SYMBOLS");
        // create fresh  records table
        this.onCreate(db);
    }

    /**
     * CRUD operations (create "add", read "get", update, delete) student record + get all symbol records + delete all symbol records
     */



    //  Table Columns names
    private static final String KEY_sID = "ID";
    private static final String KEY_sFUND= "Fund";
    private static final String KEY_sSECTOR= "Sector";
    private static final String KEY_sCOUNT = "Count";
    private static final String KEY_sPERCENT = "Percent";

    private static final String[] TABLE_CHARTSECTOR_COLUMNS = {KEY_sID, KEY_sFUND, KEY_sSECTOR, KEY_sCOUNT, KEY_sPERCENT};

    private static final String TABLE_CHARTSECTOR = "CHARTSECTOR";

    public void addSector(chartSector sectors) {
        Log.d("addSectorFund", sectors.toString());
        //  get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();

        values.put(KEY_sFUND, sectors.getFund());
        values.put(KEY_sSECTOR, sectors.getSector());
        values.put(KEY_sCOUNT, sectors.getCount());
        values.put(KEY_sPERCENT, sectors.getPercent());

        // 3. insert
        db.insert(TABLE_CHARTSECTOR, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        // 4. close



    }



    //  Table Columns names
    private static final String KEY_aID = "ID";
    private static final String KEY_aFUND= "Fund";
    private static final String KEY_aAsset= "Asset";
    private static final String KEY_aCOUNT = "Count";
    private static final String KEY_aPERCENT = "Percent";

    private static final String[] TABLE_CHARTASSET_COLUMNS = {KEY_aID, KEY_aFUND, KEY_aAsset, KEY_aCOUNT, KEY_aPERCENT};


    private static final String TABLE_CHARTASSET = "CHARTASSET";

    public void addAsset(chartAsset sectors) {
        Log.d("addAssetFund", sectors.toString());
        //  get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_aFUND, sectors.getFund());
        values.put(KEY_aAsset, sectors.getAsset());
        values.put(KEY_aCOUNT, sectors.getCount());
        values.put(KEY_aPERCENT, sectors.getPercent());

        // 3. insert
        db.insert(TABLE_CHARTASSET, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        // 4. close



    }


    //private static final String KEYID = "ID";
    private static final String KEYFUND= "FUND";
    private static final String KEYGEOGRAPH= "GEOGRAPH";
    private static final String KEYCOUNT = "COUNT";
    private static final String KEYPERCENT = "PERCENT";

    private static final String TABLE_TEST = "TEST";








    //  Table Columns names
    private static final String KEY_gID = "ID";
    private static final String KEY_gFUND= "Fund";
    private static final String KEY_gGeograph= "Geograph";
    private static final String KEY_gCOUNT = "Count";
    private static final String KEY_gPERCENT = "Percent";

    private static final String[] TABLE_CHARTGEOGRAPH_COLUMNS = {KEY_gID, KEY_gFUND, KEY_gGeograph, KEY_gCOUNT, KEY_gPERCENT};


    private static final String TABLE_CHARTGEOGRAPH = "CHARTGEOGRAPH";

    public void addGeograph(chartGeograph sectors) {
        Log.d("addGeographFund", sectors.toString());
        //  get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_gFUND, sectors.getFund());
        values.put(KEY_gGeograph, sectors.getGeograph());
        values.put(KEY_gCOUNT, sectors.getCount());
        values.put(KEY_gPERCENT, sectors.getPercent());

        // 3. insert
        db.insert(TABLE_CHARTGEOGRAPH, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        // 4. close



    }


















    // Symbol table name
    private static final String TABLE_SYMBOL = "SYMBOLS";

    //  Table Columns names
    private static final String symKEY_ID = "id";
    private static final String KEY_SYMBOL = "symbol";
    private static final String KEY_DATETIME = "date";
    private static final String KEY_OPEN = "open";
    private static final String KEY_HIGH = "high";
    private static final String KEY_LOW = "low";
    private static final String KEY_CLOSE = "close";
    private static final String KEY_ADJ_CLOSE = "adj_close";
    private static final String symKEY_STATUS = "key_status";

    private static final String[] SYMBOL_COLUMNS = {symKEY_ID, KEY_SYMBOL, KEY_DATETIME, KEY_OPEN, KEY_HIGH, KEY_LOW,KEY_CLOSE ,KEY_ADJ_CLOSE,symKEY_STATUS};


    public void addMutualFunds(Mutual mutual){
        Log.d("addMutualFund", mutual.toString());
        //  get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_SYMBOL, mutual.getSymbol());
        values.put(KEY_DATETIME, mutual.getDate());
        values.put(KEY_OPEN, mutual.getOpen());
        values.put(KEY_HIGH, mutual.getHigh());
        values.put(KEY_LOW, mutual.getLow());
        values.put(KEY_CLOSE, mutual.getClose());
        values.put(KEY_ADJ_CLOSE, mutual.getAdj_close());
        // values.put(KEY_STATUS, "NO");




        // 3. insert
        db.insert(TABLE_SYMBOL, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        // 4. close



        db.close();
    }










    // Symbol table name
    private static final String TABLE_HOLDINGS = "HOLDINGS";

    //  Table Columns names
    private static final String holdKEY_ID = "id";
    private static final String KEY_FUNDSYMBOL = "FundSymID";
    private static final String holdKEY_SYMID = "SymID";
    private static final String KEY_NAME = "Name";
    private static final String KEY_ASSET = "Asset";
    private static final String KEY_SECTOR = "Sector";
    private static final String KEY_GEOGRAPH = "Geograph";
    private static final String KEY_PERCENTAGE = "Percentage";
   private static final String holdKEY_STATUS = "key_status";

    private static final String[] HOLDINGS_COLUMNS = {holdKEY_ID, KEY_FUNDSYMBOL, holdKEY_SYMID, KEY_NAME, KEY_ASSET, KEY_SECTOR,KEY_GEOGRAPH ,KEY_PERCENTAGE,holdKEY_STATUS};





    public void addHoldings(Holdings holdings){
        Log.d("addHoldingsFund", holdings.toString());
        //  get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_FUNDSYMBOL, holdings.getFundSymbol());
        values.put(KEY_SYMID, holdings.getSymID());
        values.put(KEY_NAME, holdings.getName());
        values.put(KEY_ASSET, holdings.getAsset());
        values.put(KEY_SECTOR, holdings.getSector());
        values.put(KEY_GEOGRAPH, holdings.getGeograph());
        values.put(KEY_PERCENTAGE, holdings.getPercentage());
        // values.put(KEY_STATUS, "NO");




        // 3. insert
        db.insert(TABLE_HOLDINGS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        // 4. close



        db.close();
    }


    // Symbol table name
    private static final String TABLE_PERFORMANCEDATA= "PERFORMANCEDATA";

    //  Table Columns names
    private static final String perfKEY_ID = "id";
    private static final String KEY_SYMID = "SymID";
    private static final String KEY_INCEPTIONDATE = "InceptionDate";
    private static final String KEY_MER = "MER";
    private static final String KEY_ASSETS = "Assets";
    private static final String KEY_RANK = "Rank";
    private static final String KEY_MSTARRATING = "MstarRating";
    private static final String KEY_STDDEV = "StdDev";
    private static final String KEY_VOLATILERANK = "VolatileRank";
    private static final String KEY_MSTARRISK = "MstarRisk";
    private static final String KEY_ALPHA = "Alpha";
    private static final String KEY_BETA = "Beta";
    private static final String KEY_RSQUARED = "Rsquared";
    private static final String KEY_RRSPELIGILIBILITY = "RRSPEligibility";
    private static final String KEY_LOAD = "Load";
    private static final String KEY_MAXFRONTEND = "MaxFrontEnd";
    private static final String KEY_MAXBACKEND = "MaxBackEnd";
    private static final String KEY_SALEOPEN = "SaleOpen";
    private static final String KEY_NAVPS = "NavPS";
    private static final String KEY_NETASSET = "NetAsset";
    private static final String KEY_YIELD = "Yield";
    private static final String KEY_DIVIDEND = "Dividend";


     private static final String perfKEY_STATUS = "key_status";

    private static final String[] PERFORMANCEDATA_COLUMNS = {perfKEY_ID, KEY_SYMID, KEY_INCEPTIONDATE, KEY_MER, KEY_ASSETS,KEY_RANK ,KEY_MSTARRATING,KEY_STDDEV,KEY_VOLATILERANK,KEY_MSTARRISK,KEY_ALPHA,KEY_BETA,KEY_RSQUARED,KEY_RRSPELIGILIBILITY,KEY_LOAD,KEY_MAXFRONTEND,KEY_MAXBACKEND,KEY_SALEOPEN,KEY_NAVPS,KEY_NETASSET,KEY_YIELD,KEY_DIVIDEND,perfKEY_STATUS};

    public void addPerformance(PerformanceStats performance){
        Log.d("addHoldingsFund", performance.toString());
        //  get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_SYMID, performance.getSymID());
        values.put(KEY_INCEPTIONDATE, performance.getInceptDate());
        values.put(KEY_MER, performance.getMER());
        values.put(KEY_ASSETS, performance.getAssets());
        values.put(KEY_RANK, performance.getRank());
        values.put(KEY_MSTARRATING, performance.getMstarRating());
        values.put(KEY_STDDEV , performance.getStdDev());
        values.put(KEY_VOLATILERANK, performance.getVolatileRank());
        values.put(KEY_MSTARRISK, performance.getMstarRisk());
        values.put(KEY_ALPHA, performance.getAlpha());
        values.put(KEY_BETA, performance.getBeta());
        values.put(KEY_RSQUARED, performance.getRsquared());
        values.put(KEY_RRSPELIGILIBILITY, performance.getRRSPElilibility());
        values.put(KEY_LOAD, performance.getLoad());
        values.put(KEY_MAXFRONTEND, performance.getMaxFrontEnd());
        values.put(KEY_MAXBACKEND, performance.getMaxBackEnd());
        values.put(KEY_SALEOPEN, performance.getSaleOpen());
        values.put(KEY_NAVPS, performance.getNavPs());
        values.put(KEY_NETASSET, performance.getNetAsset());
        values.put(KEY_YIELD, performance.getYield());
        values.put(KEY_DIVIDEND, performance.getDividend());









        // values.put(KEY_STATUS, "NO");

       // 3. insert
        db.insert(TABLE_PERFORMANCEDATA, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        // 4. close

        db.close();
    }



    // Symbol table name
    private static final String TABLE_PERFORMANCERETURNS = "PERFORMANCERETURNS";

    //  Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_RETURN = "Return";
    private static final String KEY_ONEMONTH = "OneMonth";
    private static final String KEY_THREEMONTH = "ThreeMonth";
    private static final String KEY_SIXMONTH = "SixMonth";
    private static final String KEY_YTD = "YTD";
    private static final String KEY_ONEYEAR = "OneYear";
    private static final String KEY_THREEYEAR = "ThreeYear";
    private static final String KEY_FIVE = "FiveYear";
    private static final String KEY_TENYEAR = "TenYear";
    private static final String KEY_STATUS = "key_status";

    private static final String[] RETURNS_COLUMNS = {KEY_ID, KEY_RETURN, KEY_ONEMONTH, KEY_THREEMONTH, KEY_SIXMONTH, KEY_YTD,KEY_ONEYEAR ,KEY_THREEYEAR,KEY_FIVE,KEY_TENYEAR,KEY_STATUS};









    public void addPerformanceReturns(PerformanceReturns returns){
        Log.d("addHoldingsFund", returns.toString());
        //  get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        //  create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_RETURN, returns.getReturn());
        values.put(KEY_ONEMONTH, returns.getOneMonth());
        values.put(KEY_THREEMONTH, returns.getThreeMonth());
        values.put(KEY_SIXMONTH, returns.getSixMonth());
        values.put(KEY_YTD, returns.getYTD());
        values.put(KEY_ONEYEAR, returns.getOneYear());
        values.put(KEY_THREEYEAR, returns.getThreeYear());
        values.put(KEY_FIVE, returns.getFiveYear());
        values.put(KEY_TENYEAR, returns.getTenYear());

        // values.put(KEY_STATUS, "NO");




        // 3. insert
        db.insert(TABLE_PERFORMANCERETURNS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        // 4. close



        db.close();
    }


    String CREATE_PERFORMANCERETURNS_TABLE = "CREATE TABLE IF NOT EXISTS PERFORMANCERETURNS ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Return TEXT ,  "+
            "OneMonth REAL, "+
            "ThreeMonth REAL, "+
            "SixMonth REAL, "+
            "YTD REAL, "+
            "OneYear REAL, "+
            "ThreeYear REAL, "+
            "FiveYear REAL, "+
            "TenYear TEXT ) ";




    public Mutual getMutualFunds(int id){
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        // 2. build query
        Cursor cursor =
                db.query(TABLE_SYMBOL, // a. table
                        SYMBOL_COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
        // 4. build student object
        Mutual mutual = new Mutual();
        mutual.setId(Integer.parseInt(cursor.getString(0)));
        mutual.setSymbol(cursor.getString(1));
        mutual.setDate(cursor.getString(2));
        mutual.setOpen(cursor.getDouble(3));
        mutual.setHigh(cursor.getDouble(4));
        mutual.setLow(cursor.getDouble(5));
        mutual.setClose(cursor.getDouble(6));
        mutual.setAdj_close(cursor.getDouble(7));
     //   mutual.setAdj_close(cursor.getDouble(8));
        Log.d("getMutual("+id+")", mutual.toString());
        // 5. return Symbol
        return mutual;
    }

    public List<Mutual> getMutualFundsbySym(String sym) {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        List<Mutual> mutuals = new LinkedList<Mutual>();
        // 2. build query

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_SYMBOL + " where Symbol ='" + sym + "'";
        // 2. get reference to writable DB

        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build symbols and add it to list
        Mutual mutual = null;

        if (cursor.moveToFirst()) {


            do {
                mutual = new Mutual();
                mutual.setId(Integer.parseInt(cursor.getString(0)));
                mutual.setSymbol(cursor.getString(1));
                mutual.setDate(cursor.getString(2));
                mutual.setOpen(cursor.getDouble(3));
                mutual.setHigh(cursor.getDouble(4));
                mutual.setLow(cursor.getDouble(5));
                mutual.setClose(cursor.getDouble(6));
                mutual.setAdj_close(cursor.getDouble(7));
                // Add mutual to mutuals
                mutuals.add(mutual);
            } while (cursor.moveToNext());
        }
            Log.d("getMutualbySymbol(" + sym + ")", mutual.toString());
            // 5. return Symbol
            return mutuals;

    }

    private static final String TABLE_SECTOR = "SECTORALLOCATION";

    // Get All Charts
    public List<Sector> getAllChartSector() {
        List<Sector> sectors = new LinkedList<Sector>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_SECTOR;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build symbols and add it to list
        Sector sector = null;
        if (cursor.moveToFirst()) {


            do {
                sector = new Sector();
                sector.setSectorID(cursor.getInt(0));
                sector.setSector(cursor.getString(1));

                // Add mutual to mutuals
                sectors.add(sector);
            } while (cursor.moveToNext());
        }
        Log.d("getAllChartSectors", sector.toString());


        // return symbol
        return sectors;
    }








    // Get All Mutuals
    public List<Mutual> getAllMutualFunds() {
        List<Mutual> mutuals = new LinkedList<Mutual>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_SYMBOL;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build symbols and add it to list
        Mutual mutual = null;
        if (cursor.moveToFirst()) {


            do {
                mutual = new Mutual();
                mutual.setId(Integer.parseInt(cursor.getString(0)));
                mutual.setSymbol(cursor.getString(1));
                mutual.setDate(cursor.getString(2));
                mutual.setOpen(cursor.getDouble(3));
                mutual.setHigh(cursor.getDouble(4));
                mutual.setLow(cursor.getDouble(5));
                mutual.setClose(cursor.getDouble(6));
                mutual.setAdj_close(cursor.getDouble(7));
                // Add mutual to mutuals
                mutuals.add(mutual);
            } while (cursor.moveToNext());
        }
        Log.d("getAllMutuals", mutual.toString());


        // return symbol
        return mutuals;
    }



    public List<Holdings> getHoldingsbySym(String sym) {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        List<Holdings> holdings = new LinkedList<Holdings>();
        // 2. build query

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_HOLDINGS + " where FundSymID ='" + sym + "'";
        // 2. get reference to writable DB

        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build symbols and add it to list
        Holdings holding = null;

        if (cursor.moveToFirst()) {


            do {
                holding = new Holdings();
                //  holding.setId(Integer.parseInt(cursor.getString(0)));
                holding.setFundSymbol(cursor.getString(1));
                holding.setSymID(cursor.getString(2));
                holding.setName(cursor.getString(3));
                holding.setAsset(cursor.getString(4));
                holding.setSector(cursor.getString(5));
                holding.setGeograph(cursor.getString(6));
                holding.setPercentage(cursor.getDouble(7));
                // Add mutual to mutuals
                holdings.add(holding);
            } while (cursor.moveToNext());
        }
        Log.d("getHoldingsbySymbol(" + sym + ")", holding.toString());
        // 5. return Symbol
        return holdings;

    }

    public List<Holdings> getAllHoldings() {
        List<Holdings> holdings = new LinkedList<Holdings>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_HOLDINGS;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build symbols and add it to list
        Holdings holding = null;
        if (cursor.moveToFirst()) {


            do {
                holding = new Holdings();
              //  holding.setId(Integer.parseInt(cursor.getString(0)));
                holding.setFundSymbol(cursor.getString(1));
                holding.setSymID(cursor.getString(2));
                holding.setName(cursor.getString(3));
                holding.setAsset(cursor.getString(4));
                holding.setSector(cursor.getString(5));
                holding.setGeograph(cursor.getString(6));
                holding.setPercentage(cursor.getDouble(7));
                // Add mutual to mutuals
                holdings.add(holding);
            } while (cursor.moveToNext());
        }
        Log.d("getAllMutuals", holding.toString());


        // return symbol
        return holdings;
    }



    public List<Performance> getPerformancesbySym(String sym) {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        List<Performance> performances = new LinkedList<Performance>();
        // 2. build query

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_PERFORMANCEDATA + " where SymID ='" + sym + "'";
        // 2. get reference to writable DB

        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build symbols and add it to list
        Performance performance = null;

        if (cursor.moveToFirst()) {


            do {
                performance = new Performance();
                //holding.s(Integer.parseInt(cursor.getString(0)));
                performance.setSymID(cursor.getString(1));
                performance.setInceptDate(cursor.getString(2));
                performance.setMER(cursor.getDouble(3));
                performance.setAssets(cursor.getDouble(4));
                performance.setRank(cursor.getDouble(5));
                performance.setMstarRating(cursor.getDouble(6));
                performance.setStdDev(cursor.getDouble(7));
                performance.setVolatileRank(cursor.getDouble(8));
                performance.setMstarRisk(cursor.getDouble(9));
                performance.setAlpha(cursor.getDouble(10));
                performance.setBeta(cursor.getDouble(11));
                performance.setRsquared(cursor.getDouble(12));
                performance.setRRSPElilibility(cursor.getString(13));
                performance.setLoad(cursor.getString(14));
                performance.setMaxFrontEnd(cursor.getDouble(15));
                performance.setMaxBackEnd(cursor.getDouble(16));
                performance.setSaleOpen(cursor.getString(17));
                performance.setNavPs(cursor.getDouble(13));
                performance.setNetAsset(cursor.getDouble(14));
                performance.setYield(cursor.getDouble(15));
                performance.setDividend(cursor.getDouble(16));
                // Add mutual to mutuals
                performances.add(performance);
            } while (cursor.moveToNext());
        }
        Log.d("getPerformancebySymbol(" + sym + ")", performance.toString());
        // 5. return Symbol
        return performances;

    }

    public List<PerformanceStats> getAllPerformanceData() {
        List<PerformanceStats> PerformanceData = new LinkedList<PerformanceStats>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_PERFORMANCEDATA;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build symbols and add it to list
        PerformanceStats performance = null;
        if (cursor.moveToFirst()) {


            do {
                performance= new PerformanceStats();
                //  holding.setId(Integer.parseInt(cursor.getString(0)));
                performance.setSymID(cursor.getString(1));
                performance.setInceptDate(cursor.getString(2));
                performance.setMER(cursor.getDouble(3));
                performance.setAssets(cursor.getDouble(4));
                performance.setRank(cursor.getDouble(5));
                performance.setMstarRating(cursor.getDouble(6));
                performance.setStdDev(cursor.getDouble(7));
                performance.setVolatileRank(cursor.getDouble(8));
                performance.setMstarRisk(cursor.getDouble(9));
                performance.setAlpha(cursor.getDouble(10));
                performance.setBeta(cursor.getDouble(11));
                performance.setRsquared(cursor.getDouble(12));
                performance.setRRSPElilibility(cursor.getString(13));
                performance.setLoad(cursor.getString(14));
                performance.setMaxFrontEnd(cursor.getDouble(15));
                performance.setMaxBackEnd(cursor.getDouble(16));
                performance.setSaleOpen(cursor.getString(17));
                performance.setNavPs(cursor.getDouble(13));
                performance.setNetAsset(cursor.getDouble(14));
                performance.setYield(cursor.getDouble(15));
                performance.setDividend(cursor.getDouble(16));

                // Add mutual to mutuals
                PerformanceData.add(performance);
            } while (cursor.moveToNext());
        }
        Log.d("getAllMutuals", performance.toString());


        // return symbol
        return PerformanceData;
    }


    public List<PerformanceStats> getPerformanceDataList() {
        List<PerformanceStats> PerformanceData = new LinkedList<PerformanceStats>();
        // 1. build the query
        String query = "SELECT DISTINCT SymID FROM " + TABLE_PERFORMANCEDATA;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build symbols and add it to list
        PerformanceStats performance = null;
        if (cursor.moveToFirst()) {


            do {
                performance= new PerformanceStats();
                //  holding.setId(Integer.parseInt(cursor.getString(0)));
                performance.setSymID(cursor.getString(0));

                // Add mutual to mutuals
                PerformanceData.add(performance);
            } while (cursor.moveToNext());
        }
        Log.d("getAllMutuals", performance.toString());
        // return symbol
        return PerformanceData;
    }


    public List<PerformanceReturns> getPerformanceReturnsbySym(String sym) {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        List<PerformanceReturns> performancereturnes = new LinkedList<PerformanceReturns>();
        // 2. build query

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_PERFORMANCERETURNS + " where Return ='" + sym + "'";
        // 2. get reference to writable DB

        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build symbols and add it to list
        PerformanceReturns returns = null;

        if (cursor.moveToFirst()) {


            do {
                returns = new PerformanceReturns();
                //  holding.setId(Integer.parseInt(cursor.getString(0)));
                returns.setReturn(cursor.getString(1));
                returns.setOneMonth(cursor.getDouble(2));
                returns.setThreeMonth(cursor.getDouble(3));
                returns.setSixMonth(cursor.getDouble(4));
                returns.setYTD(cursor.getDouble(5));
                returns.setOneYear(cursor.getDouble(6));
                returns.setThreeYear(cursor.getDouble(7));
                returns.setFiveYear(cursor.getDouble(8));
                returns.setTenYear(cursor.getDouble(9));
                // Add mutual to mutuals
                performancereturnes.add(returns);
            } while (cursor.moveToNext());
        }
        Log.d("getPerformancebySymbol(" + sym + ")", returns.toString());
        // 5. return Symbol
        return performancereturnes;

    }


    public List<PerformanceReturns> getAllPerformanceReturns() {
        List<PerformanceReturns> returnData = new LinkedList<PerformanceReturns>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_PERFORMANCERETURNS;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build symbols and add it to list
        PerformanceReturns returns = null;
        if (cursor.moveToFirst()) {


            do {
                returns = new PerformanceReturns();
                //  holding.setId(Integer.parseInt(cursor.getString(0)));
                returns.setReturn(cursor.getString(1));
                returns.setOneMonth(cursor.getDouble(2));
                returns.setThreeMonth(cursor.getDouble(3));
                returns.setSixMonth(cursor.getDouble(4));
                returns.setYTD(cursor.getDouble(5));
                returns.setOneYear(cursor.getDouble(6));
                returns.setThreeYear(cursor.getDouble(7));
                returns.setFiveYear(cursor.getDouble(8));
                returns.setTenYear(cursor.getDouble(9));
                // Add mutual to mutuals
                returnData.add(returns);
            } while (cursor.moveToNext());
        }
        Log.d("getAllMutuals", returns.toString());


        // return symbol
        return returnData;
    }


    public List<chartSector> getNewsFeed(String fund){
        // 1. get reference to readable DB
        List<chartSector> holdings = new LinkedList<chartSector>();
        SQLiteDatabase db = this.getReadableDatabase();
        chartSector holding = new chartSector();
        // 2. build query
        Cursor cursor =
                db.query(TABLE_CHARTSECTOR, // a. table
                        TABLE_CHARTSECTOR_COLUMNS, // b. column names
                        " FUND = ?", // c. selections
                        new String[] { fund }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
        // 4. build student object
        holding = new chartSector();
        holding.setFund(cursor.getString(1));
        holding.setSector(cursor.getString(2));
        holding.setCount(cursor.getInt(3));
        holding.setPercent(cursor.getInt(4));
        holdings.add(holding);
        Log.d("getStudent("+fund+")", holding.toString());
        // 5. return newsfeed
        return holdings;
    }






    public List<chartSector>   getChartSectorByFund(String sym) {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        List<chartSector> holdings = new LinkedList<chartSector>();
        // 2. build query

        // 1. build the query


        String query ="Select * from "+TABLE_CHARTSECTOR+" where FUND ='"+sym+"'";



     //   String query ="Select * from CHARTSECTOR where FUND = ?";
        Cursor cursor = db.rawQuery(query, new String[] { sym });


       // String[] args={"ARGFX"};
   //     Cursor cursor=db.rawQuery("Select * from CHARTSECTOR",null);



        // 3. go over each row, build symbols and add it to list
        chartSector holding = null;

        if (cursor.moveToFirst()) {


            do {
                holding = new chartSector();
                holding.setFund(cursor.getString(1));
                holding.setSector(cursor.getString(2));
                holding.setCount(cursor.getInt(3));
                holding.setPercent(cursor.getInt(4));

                //  holding.setPercent(cursor.getDouble(2));

                // Add mutual to mutuals
                holdings.add(holding);
            } while (cursor.moveToNext());
        }

        Log.d("getHoldingsbySymbol(" + sym + ")", holding.toString());
        // 5. return Symbol
        return holdings;

    }



    public List<chartAsset>   getChartAssetByFund(String sym) {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        List<chartAsset> holdings = new LinkedList<chartAsset>();
        // 2. build query

        // 1. build the query


        //String query ="Select * from "+TABLE_CHARTSECTOR;//+" where fund ='"+sym+"'";
       // String query = "SELECT  * FROM " + TABLE_CHARTASSET + " where FUND ='" + sym + "'";

        String query ="Select * from CHARTASSET where FUND = ?";
        Cursor cursor = db.rawQuery(query, new String[] { sym });




    //    Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build symbols and add it to list
        chartAsset holding = null;

        if (cursor.moveToFirst()) {


            do {
                holding = new chartAsset();
                holding.setFund(cursor.getString(1));
                holding.setAsset(cursor.getString(2));
                holding.setCount(cursor.getInt(3));
                holding.setPercent(cursor.getInt(4));

                //  holding.setPercent(cursor.getDouble(2));

                // Add mutual to mutuals
                holdings.add(holding);
            } while (cursor.moveToNext());
        }

        Log.d("getAssetbySymbol(" + sym + ")", holding.toString());
        // 5. return Symbol
        return holdings;

    }

    public List<chartGeograph>   getChartGeographByFund(String sym) {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        List<chartGeograph> holdings = new LinkedList<chartGeograph>();
        // 2. build query

        // 1. build the query


        String query ="Select * from "+TABLE_CHARTGEOGRAPH+" where fund = '"+sym+"'";

      //  String query ="Select * from "+TABLE_CHARTSECTOR;

        Cursor cursor = db.rawQuery(query, null);



   //     String query ="Select * from CHARTGEOGRAPH where FUND = ?";
   //     Cursor cursor = db.rawQuery(query, new String[] { sym });





        // 3. go over each row, build symbols and add it to list
        chartGeograph holding = null;

        if (cursor.moveToFirst()) {


            do {
                holding = new chartGeograph();
                holding.setFund(cursor.getString(1));
                holding.setGeograph(cursor.getString(2));
                holding.setCount(cursor.getInt(3));
                holding.setPercent(cursor.getInt(4));
                //  holding.setPercent(cursor.getDouble(2));

                // Add mutual to mutuals
                holdings.add(holding);
            } while (cursor.moveToNext());
        }
        db.endTransaction();
        Log.d("getGeographbySymbol(" + sym + ")", holding.toString());
        // 5. return Symbol
        return holdings;

    }




































    // Updating single Mutual
    public int updateMutualFunds(Mutual mutual) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("symbol", mutual.getSymbol());
        values.put("Date", mutual.getDate());
        values.put("Open", mutual.getOpen());
        values.put("High", mutual.getHigh());
        values.put("Low", mutual.getLow());
        values.put("Close", mutual.getClose());
        values.put("ADJ_Close", mutual.getAdj_close());
        // 3. updating row
        int i = db.update(TABLE_SYMBOL, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(mutual.getId()) }); //selection args
        // 4. close
        db.close();
        return i;
    }

    // Deleting single MutuaL
    public void deleteMutualFund(Mutual mutual) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. delete
        db.delete(TABLE_SYMBOL,
                KEY_ID+" = ?",
                new String[] { String.valueOf(mutual.getId()) });
        // 3. close
        db.close();
        Log.d("deleteSymbols", mutual.toString());
    }

    // Deleting all symbol
    public void deleteAllSymbol() {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_SYMBOL);
        // 3. close
        db.close();
    }

    public void deleteAllPerformance() {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_PERFORMANCEDATA);
        // 3. close
        db.close();
    }

    public void deleteAllPerformReturn() {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_PERFORMANCERETURNS);
        // 3. close
        db.close();
    }

    public void deleteAllHoldings() {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_HOLDINGS);
        // 3. close
        db.close();
    }







    // Get All MutualFunds
    public List<chartSector> getAllSectorstoSend() {
        List<chartSector> holdings = new LinkedList<chartSector>();
        // 1. build the query

        //"SELECT  * FROM users where Symbol = '"+"no"+"'"
        String query = "SELECT  * FROM "+ TABLE_CHARTSECTOR + "where fund = '"+"PGMIX"+"'";
        // String query = "SELECT  * FROM " + TABLE_SYMBOL +"where status = " + "NO" + "" ;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor cursor = db.rawQuery(query, null);
        String NO = "PGMIX";
        Cursor cursor = db.rawQuery("SELECT * FROM CHARTSECTER WHERE FUND = '"+NO+"'", null);

        // 3. go over each row, build symbol and add it to list
        chartSector holding = null;
        if (cursor.moveToFirst()) {
            do {
                holding = new chartSector();
              //  holding.setId(Integer.parseInt(cursor.getString(0)));
                holding = new chartSector();
                holding.setFund(cursor.getString(1));
                holding.setSector(cursor.getString(2));
                holding.setCount(cursor.getInt(3));
                holding.setPercent(cursor.getInt(4));

                // Add newsfeed to newsfeeds
                holdings.add(holding);
            } while (cursor.moveToNext());
        }
        Log.d("getAllMutualsToSend", holding.toString());
        // return symbols
        return holdings;
    }







    // Get All MutualFunds
    public List<Mutual> getAllMutualstoSend() {
        List<Mutual> mutuals = new LinkedList<Mutual>();
        // 1. build the query

        //"SELECT  * FROM users where Symbol = '"+"no"+"'"
        String query = "SELECT  * FROM "+ TABLE_SYMBOL + "where topic = '"+"0"+"'";
       // String query = "SELECT  * FROM " + TABLE_SYMBOL +"where status = " + "NO" + "" ;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor cursor = db.rawQuery(query, null);
        String NO = "NO";
        Cursor cursor = db.rawQuery("SELECT * FROM Symbols WHERE status = '"+NO+"'", null);

        // 3. go over each row, build symbol and add it to list
        Mutual mutual = null;
        if (cursor.moveToFirst()) {
            do {
                mutual = new Mutual();
                mutual.setId(Integer.parseInt(cursor.getString(0)));
                mutual.setSymbol(cursor.getString(1));
                mutual.setDate(cursor.getString(2));
                mutual.setOpen(cursor.getDouble(3));
                mutual.setHigh(cursor.getDouble(4));
                mutual.setLow(cursor.getDouble(5));
                mutual.setClose(cursor.getDouble(7));
                mutual.setAdj_close(cursor.getDouble(8));
                // Add newsfeed to newsfeeds
                mutuals.add(mutual);
            } while (cursor.moveToNext());
        }
        Log.d("getAllMutualsToSend", mutual.toString());
        // return symbols
        return mutuals;
    }

    public int updateSymbolStatus(Mutual mutual) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("status", "YES"); // get topic
        // 3. updating row
        int i = db.update(TABLE_SYMBOL, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(mutual.getId()) }); //selection args
        // 4. close
        db.close();
        return i;
    }





}