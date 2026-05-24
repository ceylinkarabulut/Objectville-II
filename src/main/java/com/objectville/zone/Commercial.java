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
    public void updateLevel() {
        int population = resourcesReceived.getOrDefault(ResourceType.POPULATION, 0);
        int goods = resourcesReceived.getOrDefault(ResourceType.GOODS, 0);
        int electricity = utilitiesReceived.getOrDefault(UtilityType.ELECTRICITY, 0);
        int water = utilitiesReceived.getOrDefault(UtilityType.WATER, 0);
        int internet = utilitiesReceived.getOrDefault(UtilityType.INTERNET, 0);

        boolean hasSecurity = servicesReceived.getOrDefault(ServiceType.SECURITY, false);
        if (electricity == 0 || water == 0 || internet == 0) {
            level = 0;
            return;
        }
        if (population > 0 && goods > 0 && electricity > 0 && water > 0 && internet > 0) {
            if (level == 0) {
                level = 1;
            } else if (level == 1) {
                if (hasSecurity) {
                    level = 2;
                }
            } else if (level == 2) {
                //Excess population and goods threshold is set to 5.
                if (population > 5 && goods > 5) {
                    level = 3;
                }
            }
        }

    }

    @Override
    public void computeOutput() {
        int population = resourcesReceived.getOrDefault(ResourceType.POPULATION, 0);
        int goods = resourcesReceived.getOrDefault(ResourceType.GOODS, 0);
        int electricity = utilitiesReceived.getOrDefault(UtilityType.ELECTRICITY, 0);
        int water = utilitiesReceived.getOrDefault(UtilityType.WATER, 0);
        int internet = utilitiesReceived.getOrDefault(UtilityType.INTERNET, 0);

        int m = Math.min(electricity, Math.min(water, Math.min(internet, Math.min(population, goods))));

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
                output = (m * 2) + (population - m) + (goods - m);
                break;
        }
    }
}