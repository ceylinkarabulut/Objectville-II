package com.objectville.zone;

import com.objectville.cell.AbstractCell;
import com.objectville.cell.CellType;
import com.objectville.grid.Position;
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
    public int getPopulation() { return population; }
    public void setPopulation(int population) { this.population = population; }

    public int getGoods() { return goods; }
    public void setGoods(int goods) { this.goods = goods; }

    public int getLifestyle() { return lifestyle; }
    public void setLifestyle(int lifestyle) { this.lifestyle = lifestyle; }

    public int getLevel() { return level; }
    public int getPreviousLevel() { return previousLevel; }

    public Zone(Position position, CellType type) {
        super(position, type);
    }

    public int getDemand() {
        return 0;
    }

    public void receiveUtility(UtilityType type, int amount) {
        int current = 0;
        if (utilitiesReceived.containsKey(type)) {
            current = utilitiesReceived.get(type);
        }
        utilitiesReceived.put(type, current + amount);

    }

    public void receiveService(ServiceType type) {
        servicesReceived.put(type, true);


    }

    public void resetTick() {
        utilitiesReceived.clear();
        servicesReceived.clear();
        previousLevel = level;

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

}