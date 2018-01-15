package com.example.administrator.mfunds;

import java.util.ArrayList;
/**
 * Created by Administrator on 17/07/2017.
 */

public class Lfund {

    private String Symbol;
    private String Name;

    public Lfund() {
    }

    public Lfund(String symbol, String name) {
        Symbol = symbol;
        Name = name;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Lfund{" +
                "Symbol='" + Symbol + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }

    public static ArrayList<Lfund> getFunds() {
        ArrayList<Lfund> funds = new ArrayList<Lfund>();
        funds.add(new Lfund("WGROX", "States"));
        funds.add(new Lfund("SHAG", "States"));
        funds.add(new Lfund("PGMIX", "States"));
        return funds;
    }



}
