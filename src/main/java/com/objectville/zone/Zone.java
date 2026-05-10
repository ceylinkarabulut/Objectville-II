package com.objectville.zone;

import com.objectville.cell.AbstractCell;
import com.objectville.cell.CellType;
import com.objectville.grid.Position;
import com.objectville.service.ServiceType;
import com.objectville.utility.UtilityType;

import java.util.HashMap;
import java.util.Map;

public abstract class Zone extends AbstractCell {
    protected int level;
    protected int output;
    protected Map<UtilityType, Integer> utilitiesReceived = new HashMap<>();
    protected Map<ServiceType, Boolean> servicesReceived = new HashMap<>();

    public Zone(Position position, CellType type) {
        super(position, type);
    }

    public int getDemand() {
        return 0;
    }

    public void receiveUtility(UtilityType type, int amount) {

    }

    public void receiveService(ServiceType type) {

    }

    public void resetTick() {

    }

    public abstract void updateLevel();

    public abstract void computeOutput();

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public String getLabel() {
        return "";
    }

}