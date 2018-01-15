package com.example.administrator.mfunds;

/**
 * Created by Administrator on 16/07/2017.
 */

public class Holdings {


    private String FundSymbol;
    private String SymID;
    private String Name;
    private String Asset;
    private String Sector;
    private String Geograph;
    private double Percentage;

    public Holdings() {
    }

    public Holdings(String fundSymbol, String symID, String name, String asset, String sector, String geograph, double percentage) {
        FundSymbol = fundSymbol;
        SymID = symID;
        Name = name;
        Asset = asset;
        Sector = sector;
        Geograph = geograph;
        Percentage = percentage;
    }

    public String getFundSymbol() {
        return FundSymbol;
    }

    public void setFundSymbol(String fundSymbol) {
        FundSymbol = fundSymbol;
    }

    public String getSymID() {
        return SymID;
    }

    public void setSymID(String symID) {
        SymID = symID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAsset() {
        return Asset;
    }

    public void setAsset(String asset) {
        Asset = asset;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String sector) {
        Sector = sector;
    }

    public String getGeograph() {
        return Geograph;
    }

    public void setGeograph(String geograph) {
        Geograph = geograph;
    }

    public double getPercentage() {
        return Percentage;
    }

    public void setPercentage(double percentage) {
        Percentage = percentage;
    }

    @Override
    public String toString() {
        return "Holdings{" +
                "FundSymbol='" + FundSymbol + '\'' +
                ", SymID='" + SymID + '\'' +
                ", Name='" + Name + '\'' +
                ", Asset=" + Asset +
                ", Sector=" + Sector +
                ", Geograph=" + Geograph +
                ", Percentage=" + Percentage +
                '}';
    }
}
