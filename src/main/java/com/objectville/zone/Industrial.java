package com.objectville.zone;

import com.objectville.cell.CellType;
import com.objectville.grid.Position;
import com.objectville.resource.ResourceType;
import com.objectville.service.ServiceType;
import com.objectville.utility.UtilityType;

public class Industrial extends Zone {

    public Industrial(Position position) {
        super(position, CellType.INDUSTRIAL);
    }

    @Override
    public int calculateMinUtility() {
        return Math.min(getElectricity(), getWater());
    }

    @Override
    public void updateLevel() {

        int m = calculateMinUtility();

        if (getElectricity() == 0 || getWater() == 0) {
            level = 0;
            return;
        }

        if (getElectricity() > 0 && getWater() > 0 && getPopulation() > 0) {
            if (level == 0) {

                level = 1;
            } else if (level == 1) {
                if (hasSecurity()) {
                    level = 2;
                }
            } else if (level == 2) {
                if (!hasSecurity()) {
                    level = 1;
                } else if (getPopulation() > m) {
                    level = 3;
                }
            } else if (level == 3) {
                if (m >= getPopulation() || !hasSecurity()) {
                    level = 2;
                }
            }
        } else {
            if (getPopulation() == 0 && level > 0) {
                level = level - 1;
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
                output = m * 2;
                break;
            case 3:
                output = (m * 2) + getPopulation();
                break;
        }
    }

    @Override
    public String getLabel() {
        return "I";
    }

}