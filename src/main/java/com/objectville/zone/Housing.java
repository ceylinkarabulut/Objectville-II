package com.objectville.zone;

import com.objectville.cell.CellType;
import com.objectville.grid.Position;
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

        if (electricity == 0 || internet == 0 || water == 0) {
            level = 0;
            return;
        }
        if (electricity > 0 && internet > 0 && water > 0) {
            if (level == 0) {
                level = 1;
            }
        }

    }

    @Override
    public void computeOutput() {

    }

}