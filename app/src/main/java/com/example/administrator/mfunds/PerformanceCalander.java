package com.example.administrator.mfunds;

import java.util.Date;

/**
 * Created by Administrator on 02/08/2017.
 */

public class PerformanceCalander {



    private String date;
    private String symbol;
    private double navps;

    public PerformanceCalander() {
    }

    public PerformanceCalander(String date, String symbol, double navps) {
        this.date = date;
        this.symbol = symbol;
        this.navps = navps;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getNavps() {
        return navps;
    }

    public void setNavps(double navps) {
        this.navps = navps;
    }

    @Override
    public String toString() {
        return "PerformanceCalander{" +
                "date=" + date +
                ", symbol='" + symbol + '\'' +
                ", navps=" + navps +
                '}';
    }
}
