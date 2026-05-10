package com.objectville.grid;

import com.objectville.cell.AbstractCell;
import com.objectville.service.ServiceBuilding;
import com.objectville.utility.UtilityProvider;
import com.objectville.zone.Commercial;
import com.objectville.zone.Housing;
import com.objectville.zone.Industrial;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int cols;
    private int rows;
    private AbstractCell[][] cells;

    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    public AbstractCell getCell(int row, int col){
        return null;
    }

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public List<AbstractCell> getZones(){
        return null;
    }

    public List<Housing> getHousingZones(){
        return null;
    }

    public List<Industrial> getIndustrialZones(){
        return null;
    }

    public List<Commercial> getCommercialZones(){
        return null;
    }

    public List<ServiceBuilding> getServiceBuildings(){
        return null;
    }

    public List<UtilityProvider> getUtilityProviders(){
        return null;
    }

    public boolean isInBounds(int row, int col){
        return false;
    }
}