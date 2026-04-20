package com.objectville.cell;

public class CellType {
    public static final CellType HOUSING      = new CellType("HOUSING");
    public static final CellType INDUSTRIAL   = new CellType("INDUSTRIAL");
    public static final CellType COMMERCIAL   = new CellType("COMMERCIAL");
    public static final CellType POWER_PLANT  = new CellType("POWER_PLANT");
    public static final CellType WATER_STATION= new CellType("WATER_STATION");
    public static final CellType INTERNET_HUB = new CellType("INTERNET_HUB");
    public static final CellType POLICE       = new CellType("POLICE");
    public static final CellType HOSPITAL     = new CellType("HOSPITAL");
    public static final CellType SCHOOL       = new CellType("SCHOOL");
    public static final CellType ROAD         = new CellType("ROAD");
    public static final CellType EMPTY        = new CellType("EMPTY");

    private final String name;

    private CellType(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public String toString() { return name; }
}