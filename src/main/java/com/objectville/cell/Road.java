package com.objectville.cell;

import com.objectville.grid.Position;

public class Road extends AbstractCell {

    public Road(Position position) {
        super(position, CellType.ROAD);
    }

    @Override
    public boolean isConnectable() {
        return true; //Doesny block bfs
    }

    @Override
    public void resetTick() {
    }

    @Override
    public String getLabel() {
        return "R";
    }
}