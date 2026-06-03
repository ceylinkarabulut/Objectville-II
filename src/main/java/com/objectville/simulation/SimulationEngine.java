package com.objectville.simulation;

import com.objectville.grid.Grid;

public class SimulationEngine {

    private final Grid grid;
    private final int ticks;

    public SimulationEngine(Grid grid, int ticks) {
        this.grid = grid;
        this.ticks = ticks;
    }

    public void run() {
        for (int t = 1; t <= ticks; t++) {
            TickManager.runTick(grid, t);
        }
    }
}