package com.example.administrator.mfunds;


public class Mutual {

    private int id;
    private String symbol;
    private String date;
    private double open;
    private double high;
    private double low;
    private double close;
    private double adj_close;
    private int volume;


    public Mutual(){}

    public Mutual(String symbol, String epoch, double open, double low, double high,  double close, double adj_close, int volume) {
        super();
        this.symbol = symbol;
        this.date = epoch;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.adj_close = adj_close;
        this.volume = volume;
    }

    //getters & setters

    @Override
    public String toString() {
        return "Mutual [id=" + id + " , + symbol= " + symbol + " date=" + date + ", open=" + open + ", high=" + high + ", low=" + low + ", close =" + close + ", adj_close =" +
                 adj_close + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() { return low; }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getAdj_close() {
        return adj_close;
    }

    public void setAdj_close(double adj_close) {this.adj_close = adj_close;  }


}

