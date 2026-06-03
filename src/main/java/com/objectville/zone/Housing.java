package com.objectville.zone;

import com.objectville.cell.CellType;
import com.objectville.grid.Position;
import com.objectville.service.ServiceType;
import com.objectville.utility.UtilityType;

public class Housing extends Zone {
    public Housing(Position position) {
        super(position, CellType.HOUSING);
    }

    @Override
    public void updateLevel() {
        previousLevel = level;

        if (getUtility(UtilityType.ELECTRICITY) > 0 &&
                getUtility(UtilityType.WATER) > 0 &&
                getUtility(UtilityType.INTERNET) > 0) {
            level = 1;
        }
        if (level == 1 &&
                hasService(ServiceType.SECURITY) &&
                hasService(ServiceType.HEALTH) &&
                hasService(ServiceType.EDUCATION)) {
            level = 2;
        }
        if (level == 2 && lifestyle > 0) {
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
        else if (level == 3) output = 2 * m + lifestyle;
        else output = 0;

        population = output;

    }

}