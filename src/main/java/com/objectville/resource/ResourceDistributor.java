package com.objectville.resource;

import com.objectville.grid.Grid;
import com.objectville.zone.Commercial;
import com.objectville.zone.Housing;
import com.objectville.zone.Industrial;
import com.objectville.zone.Zone;

import java.util.ArrayList;
import java.util.List;

/*
  Step 3 of tick loop. Must NOT be called on tick 1 (warm-up).
  SimulationEngine is responsible for that guard via TickManager.

  Population  → Industrial + Commercial  (equal share, integer division)
  Goods       → Commercial               (equal share, integer division)
  Lifestyle   → Housing                  (equal share, integer division)

  NOTE: receivePopulation / receiveGoods / receiveLifestyle are defined
  on Zone by Görev 2. The methods are called via casting
  so this file compiles independently until Görev 2 adds them.
*/
public class ResourceDistributor {

    public void distribute(Grid grid, ResourcePool pool) {
        List<Housing>    housing    = grid.getHousingZones();
        List<Industrial> industrial = grid.getIndustrialZones();
        List<Commercial> commercial = grid.getCommercialZones();

        // Population → Industrial + Commercial
        List<Zone> popReceivers = new ArrayList<>();
        popReceivers.addAll(industrial);
        popReceivers.addAll(commercial);
        int popShare = pool.getPopulationPerReceiver(popReceivers.size());
        if (popShare > 0) {
            for (Zone z : popReceivers) receivePopulation(z, popShare);
        }

        // Goods → Commercial
        int goodsShare = pool.getGoodsPerCommercial(commercial.size());
        if (goodsShare > 0) {
            for (Commercial c : commercial) receiveGoods(c, goodsShare);
        }

        // Lifestyle → Housing
        int lifestyleShare = pool.getLifestylePerHousing(housing.size());
        if (lifestyleShare > 0) {
            for (Housing h : housing) receiveLifestyle(h, lifestyleShare);
        }
    }

    // Delegation helpers
    // These will call the real methods once Görev 2 adds them to Zone.
    // Until then, they silently stay so this file compiles cleanly.

    private void receivePopulation(Zone z, int amount) {
        try {
            z.getClass().getMethod("receivePopulation", int.class).invoke(z, amount);
        } catch (Exception ignored) {}
    }

    private void receiveGoods(Zone z, int amount) {
        try {
            z.getClass().getMethod("receiveGoods", int.class).invoke(z, amount);
        } catch (Exception ignored) {}
    }

    private void receiveLifestyle(Zone z, int amount) {
        try {
            z.getClass().getMethod("receiveLifestyle", int.class).invoke(z, amount);
        } catch (Exception ignored) {}
    }
}