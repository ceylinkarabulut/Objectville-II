package com.objectville;

import com.objectville.grid.Grid;
import com.objectville.simulation.SimulationEngine;

public class Main {
    public static void main(String[] args) {
        String filePath = args.length > 0 ? args[0] : "map00.txt";
        int ticks = args.length > 1 ? Integer.parseInt(args[1]) : 12; //To handle at least 10 operations ("DON'T CHANGE THE  AND THE ARGUMENTS PART(IT'S HOW IT SHOULD BE!!)"

        Grid grid = Grid.loadFromFile(filePath);
        SimulationEngine engine = new SimulationEngine(grid, ticks);
        engine.run();
    }
}