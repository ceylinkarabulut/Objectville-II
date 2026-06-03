package com.objectville.zone;

import com.objectville.cell.CellType;
import com.objectville.grid.Position;
import com.objectville.resource.ResourceType;
import com.objectville.service.ServiceType;
import com.objectville.utility.UtilityType;

public class Commercial extends Zone {

    public Commercial(Position position) {
        super(position, CellType.COMMERCIAL);
    }

    @Override
    public int calculateMinUtility() {
        return Math.min(getElectricity(), Math.min(getWater(), getInternet()));
    }

    @Override
    public void updateLevel() {

        int m = calculateMinUtility();

        if (getElectricity() == 0 || getWater() == 0 || getInternet() == 0) {
            level = 0;
            return;
        }
        if (getPopulation() > 0 && getGoods() > 0 && getElectricity() > 0 && getWater() > 0 && getInternet() > 0) {
            if (level == 0) {
                level = 1;
            } else if (level == 1) {
                if (hasSecurity()) {
                    level = 2;
                }
            } else if (level == 2) {
                if (!hasSecurity()) {
                    level = 1;
                } else if (getPopulation() > m && getGoods() > m) {
                    level = 3;
                }
            } else if (level == 3) {
                if (!hasSecurity() || getPopulation() <= m || getGoods() <= m) {
                    level = 2;
                }
            }
        } else {
            if (level > 0) {
                level--;
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
                output = (m * 2) + Math.min(getPopulation(), getGoods());
                break;
        }
    }

    @Override
    public String getLabel() {
        return "C";
    }

}