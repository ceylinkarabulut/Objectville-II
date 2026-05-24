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

        int m = Math.min(electricity, Math.min(water, Math.min(internet, Math.min(population, goods))));

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
                if (!hasSecurity) {
                    level = 1;
                }
                else if (electricity > population && water > population &&
                        internet > population && electricity > goods &&
                        water > goods && internet > goods) {
                    level = 3;
                }
            } else if (level == 3) {
                if (!hasSecurity || !(electricity > population && water > population && internet > population && electricity > goods && water > goods && internet > goods)) {
                    level = 2;
                }
            }
        } else {
            if (level > 0) {
                level--; // level = level - 1 demekle aynıdır, daha şık durur!
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

        int m = Math.min(electricity, Math.min(water, internet));

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
                output = (m * 2) +Math.min(population,goods);
                break;
        }
    }
    @Override
    public String getLabel() {
        return "C";
    }

}