package com.objectville.resource;

public class ResourceType {
    public static final ResourceType POPULATION = new ResourceType("POPULATION");
    public static final ResourceType GOODS      = new ResourceType("GOODS");
    public static final ResourceType LIFESTYLE  = new ResourceType("LIFESTYLE");

    private final String name;

    private ResourceType(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public String toString() { return name; }
}
