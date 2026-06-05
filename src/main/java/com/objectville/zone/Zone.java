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
    protected int population;
    protected int goods;
    protected int lifestyle;
    protected int previousLevel;
    protected int level;
    protected int output;
    protected Map<UtilityType, Integer> utilitiesReceived = new HashMap<>();
    protected Map<ServiceType, Boolean> servicesReceived = new HashMap<>();
    protected Map<ResourceType, Integer> resourcesReceived = new HashMap<>();

    public int getLevel() {
        return level;
    }

    public int getOutput() {
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

    public int getUtility(UtilityType type) {
        if (utilitiesReceived.containsKey(type)) {
            return utilitiesReceived.get(type);
        }
        return 0;
    }

    public boolean hasService(ServiceType type) {
        if (servicesReceived.containsKey(type)) {
            return servicesReceived.get(type);
        }
        return false;
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

    public abstract int calculateMinUtility();

    public int getPopulation() {
        return resourcesReceived.getOrDefault(ResourceType.POPULATION, 0);
    }

    public void setPopulation(int amount) {
        resourcesReceived.put(ResourceType.POPULATION, amount);
    }

    public int getGoods() {
        return resourcesReceived.getOrDefault(ResourceType.GOODS, 0);
    }

    public void setGoods(int amount) {
        resourcesReceived.put(ResourceType.GOODS, amount);
    }

    public int getElectricity() {
        return utilitiesReceived.getOrDefault(UtilityType.ELECTRICITY, 0);
    }

    public int getWater() {
        return utilitiesReceived.getOrDefault(UtilityType.WATER, 0);
    }

    public int getInternet() {
        return utilitiesReceived.getOrDefault(UtilityType.INTERNET, 0);
    }

    public int getLifestyle() {
        return resourcesReceived.getOrDefault(ResourceType.LIFESTYLE, 0);
    }

    public void setLifestyle(int amount) {
        resourcesReceived.put(ResourceType.LIFESTYLE, amount);
    }

    public boolean hasSecurity() {
        return servicesReceived.getOrDefault(ServiceType.SECURITY, false);
    }

    public boolean hasEducation() {
        return servicesReceived.getOrDefault(ServiceType.EDUCATION, false);
    }

    public boolean hasHealth() {
        return servicesReceived.getOrDefault(ServiceType.HEALTH, false);
    }

    public int getPreviousLevel() {
        return previousLevel;
    }

}