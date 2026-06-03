package com.objectville.service;

import com.objectville.cell.CellType;
import com.objectville.grid.Position;

public class PoliceStation extends ServiceBuilding{
    public PoliceStation (Position position){
        super(position, 5,CellType.POLICE, ServiceType.SECURITY );
    }
}