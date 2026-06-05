package com.objectville.utility;
import com.objectville.cell.CellType;
import com.objectville.grid.Position;

public class PowerPlant extends UtilityProvider {
    public PowerPlant(Position position) {
        super(position, CellType.POWER_PLANT, UtilityType.ELECTRICITY);
    }

    @Override
    public String getLabel(){
        return "P";
    }
}