package com.objectville.service;

import com.objectville.cell.CellType;
import com.objectville.grid.Position;

public class PoliceStation extends ServiceBuilding{
    protected PoliceStation (Position position){
        super(position, 5, CellType.HOSPITAL, ServiceType.HEALTH);
    }
}