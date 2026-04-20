package com.objectville.utility;

public class UtilityType {
    public static final UtilityType ELECTRICITY = new UtilityType("ELECTRICITY");
    public static final UtilityType WATER       = new UtilityType("WATER");
    public static final UtilityType INTERNET    = new UtilityType("INTERNET");

    private final String name;

    private UtilityType(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public String toString() { return name; }
}