package com.objectville.zone;

import com.objectville.cell.CellType;
import com.objectville.grid.Position;
import com.objectville.service.ServiceType;
import com.objectville.utility.UtilityType;

public class Industrial extends Zone {

    public Industrial(Position position) {
        super(position, CellType.INDUSTRIAL);
    }

    @Override
    public void updateLevel() {
        previousLevel = level;

        if (getUtility(UtilityType.ELECTRICITY) > 0 &&
                getUtility(UtilityType.WATER) > 0 &&
                population > 0) {
            level = 1;
        }
        if (level == 1 && hasService(ServiceType.SECURITY)) {
            level = 2;
        }
        if (level == 2 && population > 0) {
            level = 3;
        }
    }

    @Override
    public void computeOutput() {
        int m = Math.min(getUtility(UtilityType.ELECTRICITY),
                getUtility(UtilityType.WATER));
        if (level == 1) output = m;
        else if (level == 2) output = 2 * m;
        else if (level == 3) output = 2 * m + population;
        else output = 0;

        goods = output;
    }
}