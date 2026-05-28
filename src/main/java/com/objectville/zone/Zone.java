package com.objectville.zone;

import com.objectville.cell.AbstractCell;
import com.objectville.cell.CellType;
import com.objectville.grid.Position;
import com.objectville.resource.ResourceType;
import com.objectville.service.ServiceType;
import com.objectville.utility.UtilityType;

import java.util.HashMap;
import java.util.Map;

public abstract class Zone extends AbstractCell {
    protected int level;
    protected int output;
    protected Map<UtilityType, Integer> utilitiesReceived = new HashMap<>();
    protected Map<ServiceType, Boolean> servicesReceived = new HashMap<>();
    protected Map<ResourceType, Integer> resourcesReceived = new HashMap<>();

    public int getLevel(){
        return level;
    }

    public int getOutput(){
        return output;
    }

    public Zone(Position position, CellType type) {
        super(position, type);
    }

    public int getDemand() {
        if (output < 1) {
            return 1;
        } else {
            return output;
        }
    }

    public void receiveUtility(UtilityType type, int amount) {
        int currentAmount = utilitiesReceived.getOrDefault(type, 0);
        utilitiesReceived.put(type, amount + currentAmount);
    }

    public void receiveService(ServiceType type) {
        servicesReceived.put(type, true);
    }

    public void receiveResource(ResourceType type, int amount) {
        int currentAmount = resourcesReceived.getOrDefault(type, 0);
        resourcesReceived.put(type, amount + currentAmount);
    }

    public void resetTick() {
        servicesReceived.clear();
        utilitiesReceived.clear();
        resourcesReceived.clear();
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