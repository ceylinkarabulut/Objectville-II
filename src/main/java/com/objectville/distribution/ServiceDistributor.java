package com.objectville.distribution;

import com.objectville.grid.Grid;
import com.objectville.grid.Position;
import com.objectville.service.ServiceBuilding;
import com.objectville.zone.Zone;
import com.objectville.cell.AbstractCell;
import com.objectville.cell.CellType;

import java.util.List;

public class ServiceDistributor {

    public static void distribute(Grid grid) {
        List<ServiceBuilding> buildings = grid.getServiceBuildings();
        for (ServiceBuilding building : buildings) {
            Position bPos = building.getPosition();
            int radius = building.getRadius();

            for (int i = 0; i < grid.getRows(); i++) {
                for (int j = 0; j < grid.getCols(); j++) {
                    AbstractCell cell = grid.getCell(i, j);
                    if (cell == null) continue;
                    CellType t = cell.getType();
                    if (t != CellType.HOUSING && t != CellType.INDUSTRIAL && t != CellType.COMMERCIAL) continue;

                    int dist = bPos.manhattanDistanceTo(new Position(i, j));
                    if (dist <= radius) {
                        Zone zone = (Zone) cell;
                        zone.receiveService(building.getServiceType());
                        System.out.println(getZoneName(t) + " at (" + i + "," + j + ") received " + building.getServiceType().getName().toLowerCase() + " service");
                    }
                }
            }
        }
    }

    private static String getZoneName(CellType type) {
        if (type == CellType.HOUSING) return "House";
        if (type == CellType.INDUSTRIAL) return "Industrial";
        if (type == CellType.COMMERCIAL) return "Commercial";
        return "";
    }
}