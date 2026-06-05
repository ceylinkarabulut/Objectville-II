package com.objectville;

import com.objectville.grid.Grid;
import com.objectville.simulation.SimulationEngine;

public class Main {
    public static void main(String[] args) {
        Grid grid = Grid.loadFromFile(args[0]);
        int ticks = Integer.parseInt(args[1]);
        SimulationEngine engine = new SimulationEngine(grid, ticks);
        engine.run();
    }
}