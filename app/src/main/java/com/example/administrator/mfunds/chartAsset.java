package com.example.administrator.mfunds;

/**
 * Created by Administrator on 21/07/2017.
 */

public class chartAsset {



    private String Fund;
    private String Asset;
    private int Count;
    private int Percent;

    public chartAsset(String fund, String asset, int count, int percent) {
        Fund = fund;
        Asset = asset;
        Count = count;
        Percent = percent;
    }

    public chartAsset() {

    }

    public String getFund() {
        return Fund;
    }

    public void setFund(String fund) {
        Fund = fund;
    }

    public String getAsset() {
        return Asset;
    }

    public void setAsset(String asset) {
        Asset = asset;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public double getPercent() {
        return Percent;
    }

    public void setPercent(int percent) {
        Percent = percent;
    }

    @Override
    public String toString() {
        return "chartAsset{" +
                "Fund='" + Fund + '\'' +
                ", Asset='" + Asset + '\'' +
                ", Count=" + Count +
                ", Percent=" + Percent +
                '}';
    }
}
