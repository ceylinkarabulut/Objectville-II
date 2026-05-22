package com.objectville.distribution;

import com.objectville.cell.AbstractCell;
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

        while (!queue.isEmpty() && remainingCapacity > 0) {
            Position current = queue.poll();
            AbstractCell currentCell = grid.getCell(current.getRow(), current.getCol());

            if (currentCell instanceof Zone) {
                Zone zone = (Zone) currentCell;
                int requiredAmount = Math.min(remainingCapacity, zone.getDemand());

                zone.receiveUtility(provider.getUtilityType(), requiredAmount);
                remainingCapacity -= requiredAmount;
            }
        }
    }

}
