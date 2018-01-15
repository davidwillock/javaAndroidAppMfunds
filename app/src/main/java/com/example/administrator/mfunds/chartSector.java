package com.example.administrator.mfunds;

/**
 * Created by Administrator on 20/07/2017.
 */

public class chartSector {
    public String getFund() {
        return Fund;
    }

    public void setFund(String fund) {
        Fund = fund;
    }

    private String Fund;
    private String Sector;
    private int Count;
    private int Percent;

    public String getSector() {
        return Sector;
    }

    public chartSector(String fund, String sector, int count, int percent) {
        Fund = fund;
        Sector = sector;
        Count = count;
        Percent = percent;
    }

    public void setSector(String sector) {
        Sector = sector;
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

    public chartSector() {

    }

    @Override
    public String toString() {
        return "chartSector{" +
                "Fund='" + Fund + '\'' +
                ", Sector='" + Sector + '\'' +
                ", Count=" + Count +
                ", Percent=" + Percent +
                '}';
    }
}
