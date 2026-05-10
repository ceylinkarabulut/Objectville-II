package com.objectville.service;

import com.objectville.cell.CellType;
import com.objectville.grid.Position;

public class School extends ServiceBuilding{
    protected School (Position position){
        super(position, 4, CellType.HOSPITAL, ServiceType.HEALTH);
    }
}