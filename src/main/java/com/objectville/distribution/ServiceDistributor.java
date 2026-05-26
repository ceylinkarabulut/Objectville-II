package com.objectville.distribution;

import com.objectville.cell.AbstractCell;
import com.objectville.grid.Grid;
import com.objectville.service.ServiceBuilding;
import com.objectville.zone.Zone;

import java.util.List;

/*
  Step 1 of tick loop.
  Distance metric: Manhattan distance (documented in project report).
  No BFS, no road connection required. Empty cells do not block.
  Zones ON the boundary (distance == radius) receive the service.
*/
public class ServiceDistributor {

    public void distribute(Grid grid) {
        List<ServiceBuilding> buildings = grid.getServiceBuildings();
        List<AbstractCell>    cells     = grid.getZones(); // Grid returns AbstractCell for now

        for (ServiceBuilding building : buildings) {
            for (AbstractCell cell : cells) {
                if (!(cell instanceof Zone)) continue;
                Zone zone = (Zone) cell;
                int dist = zone.getPosition().manhattanDistanceTo(building.getPosition());
                if (dist <= building.getRadius()) {
                    zone.receiveService(building.getServiceType());
                }
            }
        }
    }
}