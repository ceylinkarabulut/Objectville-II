package com.objectville.service;

import com.objectville.cell.AbstractCell;
import com.objectville.cell.CellType;
import com.objectville.grid.Position;

/*
 * Base class for Police Station, Hospital, and School.
 * Services are broadcast to all zones within a radius.
 * No road connection is required.
 */

public abstract class ServiceBuilding extends AbstractCell {

    private final ServiceType serviceType;
    private final int radius;

    protected ServiceBuilding(Position position, int radius, CellType cellType, ServiceType serviceType) {
        super(position, cellType);
        this.serviceType = serviceType;
        this.radius      = radius;
    }

    public ServiceType getServiceType() { return serviceType; }
    public int getRadius() { return radius; }

    /* Service buildings are connectable (utility BFS may pass through). */
    @Override public boolean isConnectable() { return true; }

    @Override
    public void resetTick() {}

    @Override
    public String getLabel() { return getServiceType().getName(); }
}