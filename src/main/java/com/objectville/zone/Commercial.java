package com.objectville.zone;

import com.objectville.cell.CellType;
import com.objectville.grid.Position;

public class Commercial extends Zone {

    public Commercial(Position position) {
        super(position, CellType.COMMERCIAL);
    }

    @Override
    public void updateLevel() {

    }

    @Override
    public void computeOutput() {

    }
}