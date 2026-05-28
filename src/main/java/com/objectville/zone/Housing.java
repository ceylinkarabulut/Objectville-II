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
    public void updateLevel() {
        int electricity = utilitiesReceived.getOrDefault(UtilityType.ELECTRICITY, 0);
        int internet = utilitiesReceived.getOrDefault(UtilityType.INTERNET, 0);
        int water = utilitiesReceived.getOrDefault(UtilityType.WATER, 0);

        boolean hasSecurity = servicesReceived.getOrDefault(ServiceType.SECURITY, false);
        boolean hasHealth = servicesReceived.getOrDefault(ServiceType.HEALTH, false);
        boolean hasEducation = servicesReceived.getOrDefault(ServiceType.EDUCATION, false);

        int lifeStyle = resourcesReceived.getOrDefault(ResourceType.LIFESTYLE, 0);

        if (electricity == 0 || water == 0 || internet == 0) {
            level = 0;
            return;
        }

        if (electricity > 0 && internet > 0 && water > 0) {
            if (level == 0) {
                level = 1;
            } else if (level == 1) {
                if (hasSecurity && hasEducation && hasHealth) {
                    level = 2;
                }
            } else if (level == 2) {
                if (!hasSecurity || !hasEducation || !hasHealth) {
                    level = 1;
                } else if (lifeStyle > 0) {
                    level = 3;
                }
            } else if (level == 3) {
                if (lifeStyle == 0||!hasSecurity||!hasEducation||!hasHealth) {
                    level = 2;
                }
            }
        }
    }

    @Override
    public void computeOutput() {
        int electricity = utilitiesReceived.getOrDefault(UtilityType.ELECTRICITY, 0);
        int internet = utilitiesReceived.getOrDefault(UtilityType.INTERNET, 0);
        int water = utilitiesReceived.getOrDefault(UtilityType.WATER, 0);
        int lifeStyle = resourcesReceived.getOrDefault(ResourceType.LIFESTYLE, 0);

        int m = Math.min(electricity, (Math.min(internet, water)));

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
                output = (m * 2) + lifeStyle;
                break;
        }

    }

    @Override
    public String getLabel() {
        return "H";
    }


}