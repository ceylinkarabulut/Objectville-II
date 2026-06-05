package com.objectville.utility;

import com.objectville.cell.AbstractCell;
import com.objectville.cell.CellType;
import com.objectville.grid.Position;

public abstract class UtilityProvider extends AbstractCell {
    private final UtilityType utilityType;
    protected UtilityProvider(Position position, CellType type, UtilityType utilityType) {
        super(position, type);
        this.utilityType = utilityType;
    }

    public UtilityType getUtilityType() {
        return utilityType;
    }

    @Override
    public boolean isConnectable(){
        return false;
    }
    @Override
    public void resetTick() {

    }
}