package com.objectville.grid;

import com.objectville.cell.AbstractCell;
import com.objectville.cell.CellType;
import com.objectville.cell.EmptyCell;
import com.objectville.cell.Road;
import com.objectville.service.Hospital;
import com.objectville.service.PoliceStation;
import com.objectville.service.School;
import com.objectville.service.ServiceBuilding;
import com.objectville.utility.*;
import com.objectville.zone.Commercial;
import com.objectville.zone.Housing;
import com.objectville.zone.Industrial;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        this.cells = new AbstractCell[rows][cols];
    }
    public static Grid loadFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int rows = lines.size();
        int cols = lines.get(0).length();
        Grid grid = new Grid(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = lines.get(i).charAt(j);
                Position pos = new Position(i, j);
                grid.cells[i][j] = createCell(c, pos);
            }
        }
        return grid;
    }
    private static AbstractCell createCell(char c, Position pos) {
        switch (c) {
            case 'H': return new Housing(pos);
            case 'I': return new Industrial(pos);
            case 'C': return new Commercial(pos);
            case 'F': return new PoliceStation(pos);
            case 'D': return new Hospital(pos);
            case 'S': return new School(pos);
            case 'P': return new PowerPlant(pos);
            case 'W': return new WaterPump(pos);
            case 'T': return new InternetHub(pos);
            case 'R': return new Road(pos);
            case 'E': return new EmptyCell(pos);
            default: return null;
        }
    }

    public AbstractCell getCell(int row, int col){
        return cells[row][col];
    }

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public List<AbstractCell> getZones(){
        List<AbstractCell> zones = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                CellType t = cells[i][j].getType();
                if (t == CellType.HOUSING || t == CellType.INDUSTRIAL || t == CellType.COMMERCIAL) {
                    zones.add(cells[i][j]);
                }
            }
        }
        return zones;
    }

    public List<Housing> getHousingZones(){
        List<Housing> list = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cells[i][j] == null) continue;
                if (cells[i][j].getType() == CellType.HOUSING) {
                    list.add((Housing) cells[i][j]);
                }
            }
        }
        return list;
    }

    public List<Industrial> getIndustrialZones(){
        List<Industrial> list = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cells[i][j] == null) continue;
                if (cells[i][j].getType() == CellType.INDUSTRIAL) {
                    list.add((Industrial) cells[i][j]);
                }
            }
        }
        return list;
    }

    public List<Commercial> getCommercialZones(){
        List<Commercial> list = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cells[i][j] == null) continue;
                if (cells[i][j].getType() == CellType.COMMERCIAL) {
                    list.add((Commercial) cells[i][j]);
                }
            }
        }
        return list;
    }

    public List<ServiceBuilding> getServiceBuildings(){
        List<ServiceBuilding> list = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cells[i][j] == null) continue;
                if (cells[i][j] instanceof ServiceBuilding) {
                    list.add((ServiceBuilding) cells[i][j]);
                }
            }
        }
        return list;
    }

    public List<UtilityProvider> getUtilityProviders(){
        List<UtilityProvider> list =  new ArrayList<>();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(cells[i][j] == null) continue;
                if(cells[i][j] instanceof UtilityProvider){
                    list.add((UtilityProvider) cells[i][j]);
                }
            }
        }
        return list;
    }

    public boolean isInBounds(int row, int col){
        if (row < 0 || row >= rows) return false;
        if (col < 0 || col >= cols) return false;
        return true;
    }
}