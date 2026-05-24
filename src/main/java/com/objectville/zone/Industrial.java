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
    public void updateLevel() {
        int electricity = utilitiesReceived.getOrDefault(UtilityType.ELECTRICITY, 0);
        int water = utilitiesReceived.getOrDefault(UtilityType.WATER, 0);
        int population = resourcesReceived.getOrDefault(ResourceType.POPULATION, 0);

        boolean hasSecurity = servicesReceived.getOrDefault(ServiceType.SECURITY, false);

        int m = Math.min(electricity, (Math.min(water, population)));

        if (electricity == 0 || water == 0) {
            level = 0;
            return;
        }

        if (electricity > 0 && water > 0 && population > 0) {
            if (level == 0) {

                level = 1;
            } else if (level == 1) {
                if (hasSecurity) {
                    level = 2;
                }
            } else if (level == 2) {
                if (!hasSecurity) {
                    level = 1;
                } else if (electricity > population && water > population) {
                    level = 3;
                }
            } else if (level == 3) {
                if ((electricity <= population || water <= population) || !hasSecurity) {
                    level = 2;
                }
            }
        } else {
            if (population == 0 && level > 0) {
                level = level - 1;
            }
        }
    }

    @Override
    public void computeOutput() {
        int electricity = utilitiesReceived.getOrDefault(UtilityType.ELECTRICITY, 0);
        int water = utilitiesReceived.getOrDefault(UtilityType.WATER, 0);
        int population = resourcesReceived.getOrDefault(ResourceType.POPULATION, 0);

        int m = Math.min(electricity, water);
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
                output = (m * 2) + population;
                break;
        }
    }

    @Override
    public String getLabel() {
        return "I";
    }

}