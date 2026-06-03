package com.objectville.resource;

import com.objectville.grid.Grid;
import com.objectville.zone.Commercial;
import com.objectville.zone.Housing;
import com.objectville.zone.Industrial;

import java.util.List;

public class ResourceDistributor {

    public static void distribute(Grid grid) {
        distributePopulation(grid);
        distributeGoods(grid);
        distributeLifestyle(grid);
    }

    private static void distributePopulation(Grid grid) {
        List<Housing> houses = grid.getHousingZones();
        List<Industrial> industries = grid.getIndustrialZones();
        List<Commercial> commercials = grid.getCommercialZones();

        int total = 0;
        for (Housing h : houses) total += h.getPopulation();

        int receivers = industries.size() + commercials.size();
        int share = receivers == 0 ? 0 : total / receivers;

        for (Industrial i : industries) {
            i.setPopulation(share) ;
            System.out.println("Industrial at (" + i.getPosition().getRow() + "," + i.getPosition().getCol() + ") received " + share + " population");
        }
        for (Commercial c : commercials) {
            c.setPopulation(share);
            System.out.println("Commercial at (" + c.getPosition().getRow() + "," + c.getPosition().getCol() + ") received " + share + " population");
        }
    }

    private static void distributeGoods(Grid grid) {
        List<Industrial> industries = grid.getIndustrialZones();
        List<Commercial> commercials = grid.getCommercialZones();

        int total = 0;
        for (Industrial i : industries) total += i.getGoods();

        int receivers = commercials.size();
        int share = receivers == 0 ? 0 : total / receivers;

        for (Commercial c : commercials) {
            c.setGoods(share);
            System.out.println("Commercial at (" + c.getPosition().getRow() + "," + c.getPosition().getCol() + ") received " + share + " goods");
        }
    }

    private static void distributeLifestyle(Grid grid) {
        List<Commercial> commercials = grid.getCommercialZones();
        List<Housing> houses = grid.getHousingZones();

        int total = 0;
        for (Commercial c : commercials) total += c.getLifestyle();

        int receivers = houses.size();
        int share = receivers == 0 ? 0 : total / receivers;

        for (Housing h : houses) {
            h.setLifestyle(share);
            System.out.println("House at (" + h.getPosition().getRow() + "," + h.getPosition().getCol() + ") received " + share + " lifestyle");
        }
    }
}