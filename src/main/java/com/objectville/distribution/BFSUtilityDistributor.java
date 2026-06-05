package com.objectville.distribution;

import com.objectville.cell.AbstractCell;
import com.objectville.cell.CellType;
import com.objectville.grid.Grid;
import com.objectville.grid.Position;
import com.objectville.utility.UtilityProvider;
import com.objectville.zone.Zone;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFSUtilityDistributor {



    public void distribute(UtilityProvider provider, Grid grid) {
        int remainingCapacity = 100;
        Queue<Position> queue = new LinkedList<>();
        Set<Position> visited = new HashSet<>();

        queue.add(provider.getPosition());
        visited.add(provider.getPosition());

        /*
         * Neighbor above : row-1, col
         * Neighbor below : row+1, col
         * Neighbor left : row, col-1
         * Neighbor right : row, col+1
         */

        int[] rowDirections = {-1, 1, 0, 0};
        int[] colDirections = {0, 0, -1, 1};

        while (!queue.isEmpty() && remainingCapacity > 0) {
            Position current = queue.poll();        //Current position
            AbstractCell currentCell = grid.getCell(current.getRow(), current.getCol());

            if (currentCell instanceof Zone) {
                Zone zone = (Zone) currentCell; //If zone, zone
                int requiredAmount = Math.min(remainingCapacity, zone.getDemand());

                zone.receiveUtility(provider.getUtilityType(), requiredAmount);
                remainingCapacity -= requiredAmount;
                String zoneName;
                if (zone.getType() == CellType.HOUSING) zoneName = "House";
                else if (zone.getType() == CellType.INDUSTRIAL) zoneName = "Industrial";
                else zoneName = "Commercial";
                System.out.println(zoneName + " at (" + zone.getPosition().getRow() + "," + zone.getPosition().getCol() + ") received " + requiredAmount + " " + provider.getUtilityType().getName().toLowerCase());
            }

            for (int i = 0; i < 4; i++) {                                 //      Up
                int neighborRow = current.getRow() + rowDirections[i]; // Left      Right
                int neighborCol = current.getCol() + colDirections[i]; //      Down

                Position neighborPos = new Position(neighborRow, neighborCol);       // Position
                if (grid.isInBounds(neighborRow, neighborCol) && !visited.contains(neighborPos)) {
                    AbstractCell neighborCell = grid.getCell(neighborRow, neighborCol);  // Cell
                    if (neighborCell != null && neighborCell.isConnectable()) {
                        queue.add(neighborPos);
                        visited.add(neighborPos);
                    }
                }
            }

        }

    }


}
