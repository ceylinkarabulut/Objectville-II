package com.objectville.grid;

import com.objectville.cell.CellType;

public interface Cell {

    CellType getType();

    Position getPosition();

    boolean isConnectable();

    void resetTick();

    String getLabel();
}