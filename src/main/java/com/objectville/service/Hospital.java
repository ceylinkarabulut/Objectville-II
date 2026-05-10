package com.objectville.service;

import com.objectville.cell.CellType;
import com.objectville.grid.Position;

public class Hospital extends ServiceBuilding{
    protected Hospital (Position position){
        super(position, 3, CellType.HOSPITAL, ServiceType.HEALTH);
    }
}