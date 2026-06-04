package com.objectville.zone;

import com.objectville.cell.CellType;
import com.objectville.grid.Position;
import com.objectville.resource.ResourceType;
import com.objectville.service.ServiceType;
import com.objectville.utility.UtilityType;

public class Housing extends Zone {
    public Housing(Position position) {
        super(position, CellType.HOUSING);
    }

    @Override
    public int calculateMinUtility() {
        return Math.min(getElectricity(), (Math.min(getInternet(), getWater())));
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


        if (getElectricity() == 0 || getWater() == 0 || getInternet() == 0) {
            level = 0;
            return;
        }

        if (getElectricity() > 0 && getInternet() > 0 && getWater() > 0) {
            if (level == 0) {
                level = 1;
            } else if (level == 1) {
                if (hasSecurity() && hasEducation() && hasHealth()) {
                    level = 2;
                }
            } else if (level == 2) {
                if (!hasSecurity() || !hasEducation() || !hasHealth()) {
                    level = 1;
                } else if (getLifeStyle() > 0) {
                    level = 3;
                }
            } else if (level == 3) {
                if (getLifeStyle() == 0 || !hasSecurity() || !hasEducation() || !hasHealth()) {
                    level = 2;
                }
            }
        }
    }

    @Override
    public void computeOutput() {
        int m = calculateMinUtility();

        switch (level) {
            case 0:
                output = 0;
                break;
            case 1:
                output = m;
                break;
            case 2:
                output = 2 * m;
                break;
            case 3:
                output = (m * 2) + getLifeStyle();
                break;
        }

    }

    @Override
    public String getLabel() {
        return "H";
    }

}