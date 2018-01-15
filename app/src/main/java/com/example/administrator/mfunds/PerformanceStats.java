package com.example.administrator.mfunds;

/**
 * Created by Administrator on 17/07/2017.
 */

public class PerformanceStats {

    private String SymID;
    private String InceptDate;
    private double MER;
    private double Assets;
    private double Rank;
    private double MstarRating;
    private double StdDev;
    private double VolatileRank;
    private double MstarRisk;
    private double Alpha;
    private double Beta;
    private double Rsquared;
    private String RRSPElilibility;
    private String Load;
    private double MaxFrontEnd;
    private double MaxBackEnd;
    private String SaleOpen;
    private double NavPs;
    private double NetAsset;
    private double Yield;
    private double Dividend;

    public PerformanceStats(String symID, String inceptDate, double MER, double assets, double rank, double mstarRating, double stdDev, double volatileRank, double mstarRisk, double alpha, double beta, double rsquared, String RRSPElilibility, String load, double maxFrontEnd, double maxBackEnd, String saleOpen, double navPs, double netAsset, double yield, double dividend, String manager, double fees, String fullName) {
        SymID = symID;
        InceptDate = inceptDate;
        this.MER = MER;
        Assets = assets;
        Rank = rank;
        MstarRating = mstarRating;
        StdDev = stdDev;
        VolatileRank = volatileRank;
        MstarRisk = mstarRisk;
        Alpha = alpha;
        Beta = beta;
        Rsquared = rsquared;
        this.RRSPElilibility = RRSPElilibility;
        Load = load;
        MaxFrontEnd = maxFrontEnd;
        MaxBackEnd = maxBackEnd;
        SaleOpen = saleOpen;
        NavPs = navPs;
        NetAsset = netAsset;
        Yield = yield;
        Dividend = dividend;
        this.manager = manager;
        Fees = fees;
        FullName = fullName;
    }

    @Override
    public String toString() {
        return "PerformanceStats{" +
                "SymID='" + SymID + '\'' +
                ", InceptDate='" + InceptDate + '\'' +
                ", MER=" + MER +
                ", Assets=" + Assets +
                ", Rank=" + Rank +
                ", MstarRating=" + MstarRating +
                ", StdDev=" + StdDev +
                ", VolatileRank=" + VolatileRank +
                ", MstarRisk=" + MstarRisk +
                ", Alpha=" + Alpha +
                ", Beta=" + Beta +
                ", Rsquared=" + Rsquared +
                ", RRSPElilibility='" + RRSPElilibility + '\'' +
                ", Load='" + Load + '\'' +
                ", MaxFrontEnd=" + MaxFrontEnd +
                ", MaxBackEnd=" + MaxBackEnd +
                ", SaleOpen='" + SaleOpen + '\'' +
                ", NavPs=" + NavPs +
                ", NetAsset=" + NetAsset +
                ", Yield=" + Yield +
                ", Dividend=" + Dividend +
                ", manager='" + manager + '\'' +
                ", Fees=" + Fees +
                ", FullName='" + FullName + '\'' +
                '}';
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public double getFees() {
        return Fees;
    }

    public void setFees(double fees) {
        Fees = fees;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    private String manager;
    private double Fees;
    private String FullName;





    public PerformanceStats() {
    }




    public String getSymID() {
        return SymID;
    }

    public void setSymID(String symID) {
        SymID = symID;
    }

    public String getInceptDate() {
        return InceptDate;
    }

    public void setInceptDate(String inceptDate) {
        InceptDate = inceptDate;
    }

    public double getMER() {
        return MER;
    }

    public void setMER(double MER) {
        this.MER = MER;
    }

    public double getAssets() {
        return Assets;
    }

    public void setAssets(double assets) {
        Assets = assets;
    }

    public double getRank() {
        return Rank;
    }

    public void setRank(double rank) {
        Rank = rank;
    }

    public double getMstarRating() {
        return MstarRating;
    }

    public void setMstarRating(double mstarRating) {
        MstarRating = mstarRating;
    }

    public double getStdDev() {
        return StdDev;
    }

    public void setStdDev(double stdDev) {
        StdDev = stdDev;
    }

    public double getVolatileRank() {
        return VolatileRank;
    }

    public void setVolatileRank(double volatileRank) {
        VolatileRank = volatileRank;
    }

    public double getMstarRisk() {
        return MstarRisk;
    }

    public void setMstarRisk(double mstarRisk) {
        MstarRisk = mstarRisk;
    }

    public double getAlpha() {
        return Alpha;
    }

    public void setAlpha(double alpha) {
        Alpha = alpha;
    }

    public double getBeta() {
        return Beta;
    }

    public void setBeta(double beta) {
        Beta = beta;
    }

    public double getRsquared() {
        return Rsquared;
    }

    public void setRsquared(double rsquared) {
        Rsquared = rsquared;
    }

    public String getRRSPElilibility() {
        return RRSPElilibility;
    }

    public void setRRSPElilibility(String RRSPElilibility) {
        this.RRSPElilibility = RRSPElilibility;
    }

    public String getLoad() {
        return Load;
    }

    public void setLoad(String load) {
        Load = load;
    }

    public double getMaxFrontEnd() {
        return MaxFrontEnd;
    }

    public void setMaxFrontEnd(double maxFrontEnd) {
        MaxFrontEnd = maxFrontEnd;
    }

    public double getMaxBackEnd() {
        return MaxBackEnd;
    }

    public void setMaxBackEnd(double maxBackEnd) {
        MaxBackEnd = maxBackEnd;
    }

    public String getSaleOpen() {
        return SaleOpen;
    }

    public void setSaleOpen(String saleOpen) {
        SaleOpen = saleOpen;
    }

    public double getNavPs() {
        return NavPs;
    }

    public void setNavPs(double navPs) {
        NavPs = navPs;
    }

    public double getNetAsset() {
        return NetAsset;
    }

    public void setNetAsset(double netAsset) {
        NetAsset = netAsset;
    }

    public double getYield() {
        return Yield;
    }

    public void setYield(double yield) {
        Yield = yield;
    }

    public double getDividend() {
        return Dividend;
    }

    public void setDividend(double dividend) {
        Dividend = dividend;
    }

}
