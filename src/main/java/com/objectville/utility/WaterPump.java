package com.objectville.utility;
import com.objectville.cell.CellType;
import com.objectville.grid.Position;

public class WaterPump extends UtilityProvider{
    public WaterPump(Position position) {
        super(position, CellType.WATER_STATION, UtilityType.WATER);
    }

    @Override
    public String getLabel(){
        return "W";
    }
}