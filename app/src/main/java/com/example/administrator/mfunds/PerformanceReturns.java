package com.example.administrator.mfunds;

/**
 * Created by Administrator on 16/07/2017.
 */

public class PerformanceReturns {

    private String Return;
    private double OneMonth;
    private double ThreeMonth;
    private double SixMonth;
    private double YTD;
    private double OneYear;
    private double ThreeYear;
    private double FiveYear;
    private double TenYear;

    public PerformanceReturns() {
    }

    public PerformanceReturns(String aReturn, double oneMonth, double threeMonth, double sixMonth, double YTD, double oneYear, double threeYear, double fiveYear, double tenYear) {
        Return = aReturn;
        OneMonth = oneMonth;
        ThreeMonth = threeMonth;
        SixMonth = sixMonth;
        this.YTD = YTD;
        OneYear = oneYear;
        ThreeYear = threeYear;
        FiveYear = fiveYear;
        TenYear = tenYear;
    }

    public String getReturn() {
        return Return;
    }

    public void setReturn(String aReturn) {
        Return = aReturn;
    }

    public double getOneMonth() {
        return OneMonth;
    }

    public void setOneMonth(double oneMonth) {
        OneMonth = oneMonth;
    }

    public double getThreeMonth() {
        return ThreeMonth;
    }

    public void setThreeMonth(double threeMonth) {
        ThreeMonth = threeMonth;
    }

    public double getSixMonth() {
        return SixMonth;
    }

    public void setSixMonth(double sixMonth) {
        SixMonth = sixMonth;
    }

    public double getYTD() {
        return YTD;
    }

    public void setYTD(double YTD) {
        this.YTD = YTD;
    }

    public double getOneYear() {
        return OneYear;
    }

    public void setOneYear(double oneYear) {
        OneYear = oneYear;
    }

    public double getThreeYear() {
        return ThreeYear;
    }

    public void setThreeYear(double threeYear) {
        ThreeYear = threeYear;
    }

    public double getFiveYear() {
        return FiveYear;
    }

    public void setFiveYear(double fiveYear) {
        FiveYear = fiveYear;
    }

    public double getTenYear() {
        return TenYear;
    }

    public void setTenYear(double tenYear) {
        TenYear = tenYear;
    }


    @Override
    public String toString() {
        return "PerformanceReturns{" +
                "Return='" + Return + '\'' +
                ", OneMonth=" + OneMonth +
                ", ThreeMonth=" + ThreeMonth +
                ", SixMonth=" + SixMonth +
                ", YTD=" + YTD +
                ", OneYear=" + OneYear +
                ", ThreeYear=" + ThreeYear +
                ", FiveYear=" + FiveYear +
                ", TenYear=" + TenYear +
                '}';
    }
}
