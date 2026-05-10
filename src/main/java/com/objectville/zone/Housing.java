package com.objectville.zone;

import com.objectville.cell.CellType;
import com.objectville.grid.Position;

public class Housing extends Zone {
    public Housing(Position position) {
        super(position, CellType.HOUSING);
    }

    @Override
    public void updateLevel() {

    }

    @Override
    public void computeOutput() {

    }

}