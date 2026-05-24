package com.objectville.zone;

import com.objectville.cell.CellType;
import com.objectville.grid.Position;
import com.objectville.resource.ResourceType;
import com.objectville.utility.UtilityType;

public class Industrial extends Zone {

    public Industrial(Position position) {
        super(position, CellType.INDUSTRIAL);
    }

    @Override
    public void updateLevel() {

    }

    @Override
    public void computeOutput() {

    }
}