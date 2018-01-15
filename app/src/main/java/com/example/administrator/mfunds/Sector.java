package com.example.administrator.mfunds;

/**
 * Created by Administrator on 20/07/2017.
 */

public class Sector {

    private int SectorID;
    private String Sector;

    public Sector() {
    }

    public Sector(int sectorID, String sector) {
        SectorID = sectorID;
        Sector = sector;
    }


    public int getSectorID() {
        return SectorID;
    }

    public void setSectorID(int sectorID) {
        SectorID = sectorID;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String sector) {
        Sector = sector;
    }

    @Override
    public String toString() {
        return "Sector{" +
                "SectorID=" + SectorID +
                ", Sector='" + Sector + '\'' +
                '}';
    }
}
