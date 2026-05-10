package com.objectville.cell;

import com.objectville.grid.Position;

public abstract class AbstractCell {

    private final Position position;
    private final CellType type;

    protected AbstractCell(Position position, CellType type) {
        this.position = position;
        this.type = type;
    }

    public Position getPosition() { return position; }
    public CellType getType() { return type; }

    public abstract boolean isConnectable();
    public abstract void resetTick();
    public abstract String getLabel();
}
