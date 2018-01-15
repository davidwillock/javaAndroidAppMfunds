package com.example.administrator.mfunds;

/**
 * Created by Administrator on 21/07/2017.
 */

public class chartGeograph {

    private String Fund;
    private String Geograph;
    private int Count;
    private int Percent;




    public chartGeograph() {
    }


    public chartGeograph(String fund, String geograph, int count, int percent) {
        Fund = fund;
        Geograph = geograph;
        Count = count;
        Percent = percent;
    }

    public String getFund() {
        return Fund;
    }

    public void setFund(String fund) {
        Fund = fund;
    }

    public String getGeograph() {
        return Geograph;
    }

    public void setGeograph(String geograph) {
        Geograph = geograph;
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
        return "chartGeograph{" +
                "Fund='" + Fund + '\'' +
                ", Geograph='" + Geograph + '\'' +
                ", Count=" + Count +
                ", Percent=" + Percent +
                '}';
    }
}
