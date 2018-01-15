package com.example.administrator.mfunds;

/**
 * Created by Administrator on 04/08/2017.
 */

public class Indexes {


    private String Symbol;
    private String FullName;


    public Indexes() {
    }

    public Indexes(String symbol, String fullName) {
        Symbol = symbol;
        FullName = fullName;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }


    @Override
    public String toString() {
        return "Indexes{" +
                "Symbol='" + Symbol + '\'' +
                ", FullName='" + FullName + '\'' +
                '}';
    }
}
