package com.objectville.simulation;

import com.objectville.cell.AbstractCell;
import com.objectville.distribution.BFSUtilityDistributor;
import com.objectville.resource.ResourceDistributor;
import com.objectville.distribution.ServiceDistributor;
import com.objectville.grid.Grid;
import com.objectville.utility.UtilityProvider;
import com.objectville.zone.Commercial;
import com.objectville.zone.Housing;
import com.objectville.zone.Industrial;

import java.util.List;

public class TickManager {

    public static void runTick(Grid grid, int tickNumber) {
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                AbstractCell cell = grid.getCell(r, c);
                if (cell != null) cell.resetTick();
            }
        }
        System.out.println("Tick " + tickNumber);

        // 1. ServiceDistributor    2. BFS      3. ResourceDistributor

        ServiceDistributor.distribute(grid);
        BFSUtilityDistributor distributor = new BFSUtilityDistributor();
        for(UtilityProvider provider : grid.getUtilityProviders()){
            distributor.distribute(provider, grid);
        }
        ResourceDistributor.distribute(grid);
        updateZones(grid);
        accumulateProduction(grid);
    }

    private static void accumulateProduction(Grid grid) {
        for (Housing h : grid.getHousingZones()) {
            int prev = h.getPreviousLevel();
            h.computeOutput();

            System.out.println("House at: (" + h.getPosition().getRow() + "," + h.getPosition().getCol() + ")generated " + h.getOutput() + " population ");
            printLevelChange("House:", h.getPosition(), prev, h.getLevel());
        }
        for (Industrial i : grid.getIndustrialZones()) {
            int prev = i.getPreviousLevel();
            i.computeOutput();
            System.out.println("Industrial at :(" + i.getPosition().getRow() + "," + i.getPosition().getCol() + ") generated  " + i.getOutput() + "  goods");
            printLevelChange("Industrial: ", i.getPosition(), prev, i.getLevel());
        }
        for (Commercial c : grid.getCommercialZones()) {
            int prev = c.getPreviousLevel();
            c.computeOutput();
            System.out.println("Commercial at:   (" + c.getPosition().getRow() + "," + c.getPosition().getCol() + ") generated  " + c.getOutput() + " lifestyle ");
            printLevelChange("Commercial : ", c.getPosition(), prev, c.getLevel());
        }
    }

    private static void updateZones(Grid grid) {
        for (Housing h : grid.getHousingZones()) h.updateLevel();
        for (Industrial i : grid.getIndustrialZones()) i.updateLevel();
        for (Commercial c : grid.getCommercialZones()) c.updateLevel();
    }

    private static void printLevelChange(String name, com.objectville.grid.Position pos, int prev, int curr) {
        if (curr > prev) {
            System.out.println(name + " at (" + pos.getRow() + "," + pos.getCol() + ") levels up from: " + prev + " to:" + curr);
        } else if (curr < prev) {
            System.out.println(name + " at (" + pos.getRow() + "," + pos.getCol() + ") levels down from " + prev + " to " + curr);
        }
    }
}