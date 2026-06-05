package com.objectville.utility;
import com.objectville.cell.CellType;
import com.objectville.grid.Position;

public class InternetHub extends UtilityProvider {
    public InternetHub(Position position) {
        super(position, CellType.INTERNET_HUB, UtilityType.INTERNET);
    }

    @Override
    public String getLabel(){
        return "T";
    }
}