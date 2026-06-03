package com.objectville.zone;

import com.objectville.cell.CellType;
import com.objectville.grid.Position;
import com.objectville.service.ServiceType;
import com.objectville.utility.UtilityType;

public class Commercial extends Zone {

    public Commercial(Position position) {
        super(position, CellType.COMMERCIAL);
    }

    @Override
    public void updateLevel() {
        previousLevel = level;

        if (getUtility(UtilityType.ELECTRICITY) > 0 &&
                getUtility(UtilityType.WATER) > 0 &&
                getUtility(UtilityType.INTERNET) > 0 &&
                population > 0 &&
                goods > 0) {
            level = 1;
        }
        if (level == 1 && hasService(ServiceType.SECURITY)) {
            level = 2;
        }
        if (level == 2 && Math.min(population, goods) > 0) {
            level = 3;
        }

    }

    @Override
    public void computeOutput() {
        int m = Math.min(getUtility(UtilityType.ELECTRICITY),
                Math.min(getUtility(UtilityType.WATER),
                        getUtility(UtilityType.INTERNET)));
        if (level == 1) output = m;
        else if (level == 2) output = 2 * m;
        else if (level == 3) output = 2 * m + Math.min(population, goods);
        else output = 0;

        lifestyle = output;

    }
}