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
                //Excess population threshold is set to 5.
                if (population > 5) {
                    level = 3;
                }
            }
        }
    }

    @Override
    public void computeOutput() {
        int electricity = utilitiesReceived.getOrDefault(UtilityType.ELECTRICITY, 0);
        int water = utilitiesReceived.getOrDefault(UtilityType.WATER, 0);
        int population = resourcesReceived.getOrDefault(ResourceType.POPULATION, 0);

        int m = Math.min(electricity, (Math.min(water, population)));
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
                output = (m * 2) + (population - m);
                break;
        }
    }
}