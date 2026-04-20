package com.objectville.service;

public class ServiceType {
    public static final ServiceType SECURITY  = new ServiceType("SECURITY");
    public static final ServiceType HEALTH    = new ServiceType("HEALTH");
    public static final ServiceType EDUCATION = new ServiceType("EDUCATION");

    private final String name;

    private ServiceType(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public String toString() { return name; }
}