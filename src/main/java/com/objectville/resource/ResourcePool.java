package com.objectville.resource;

public class ResourcePool {
    private int totalPopulation;
    private int totalGoods;
    private int totalLifestyle;

    public void addPopulation(int amount)  { if (amount > 0) totalPopulation += amount; }
    public void addGoods(int amount)       { if (amount > 0) totalGoods      += amount; }
    public void addLifestyle(int amount)   { if (amount > 0) totalLifestyle  += amount; }

    public int getTotalPopulation() { return totalPopulation; }
    public int getTotalGoods()      { return totalGoods; }
    public int getTotalLifestyle()  { return totalLifestyle; }

    public int getPopulationPerReceiver(int count) { return count <= 0 ? 0 : totalPopulation / count; }
    public int getGoodsPerCommercial(int count)    { return count <= 0 ? 0 : totalGoods / count; }
    public int getLifestylePerHousing(int count)   { return count <= 0 ? 0 : totalLifestyle / count; }

    public void reset() { totalPopulation = 0; totalGoods = 0; totalLifestyle = 0; }

    @Override
    public String toString() {
        return "ResourcePool{pop=" + totalPopulation + ", goods=" + totalGoods + ", lifestyle=" + totalLifestyle + "}";
    }
}