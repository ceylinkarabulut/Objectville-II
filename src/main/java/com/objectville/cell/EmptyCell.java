package com.objectville.cell;

import com.objectville.grid.Position;

public class EmptyCell extends AbstractCell {

    public EmptyCell(Position position) {
        super(position, CellType.EMPTY);
    }

    @Override
    public boolean isConnectable() {
        return false; // Stopped bfs
    }

    @Override
    public void resetTick() {
    }

    @Override
    public String getLabel() {
        return "E";
    }
}