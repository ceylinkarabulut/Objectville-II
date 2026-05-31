package Simulation;

import Map.*;
import Map.City;

public class Tick {
    private static String getZoneName(char type) {
        if (type == 'H') return "House";
        if (type == 'I') return "Industrial";
        if (type == 'C') return "Commercial";
        return null;
    }
    public static void distributeServices(City city) {
        for (int i = 0; i < city.a; i++) {
            for (int j = 0; j < city.b; j++) {
                char type = city.grid[i][j].getType();
                int radius = 0;

                if (type == 'F') radius = 5;
                else if (type == 'D') radius = 3;
                else if (type == 'S') radius = 4;
                else continue;

                for (int x = 0; x < city.a; x++) {
                    for (int y = 0; y < city.b; y++) {
                        int dist = Math.abs(i - x) + Math.abs(j - y);//manhattan formula
                        if (dist <= radius) {
                            if (type == 'F') city.grid[x][y].setHasSecurity(true);
                            else if (type == 'D') city.grid[x][y].setHasHealth(true);
                            else city.grid[x][y].setHasEducation(true);
                        }
                    }
                }
            }
        }
    }

    //kutaydan bfsyi al
    public static void distributeResources(City city) {

        int population = 0;
        int num = 0;
        int goodsnum = 0;
        int goods = 0;
        for (int i = 0; i < city.a; i++) {
            for (int j = 0; j < city.b; j++) {
                char type = city.grid[i][j].getType();
                if (type == 'H') population += city.grid[i][j].getPopulation();
                if (type == 'C' || type == 'I') num++;
            }
        }
        int resdiv = num == 0 ? 0 : population / num;
        for (int i = 0; i < city.a; i++) {
            for (int j = 0; j < city.b; j++) {
                char t = city.grid[i][j].getType();
                if (t == 'I' || t == 'C') {
                    city.grid[i][j].setPopulation(resdiv);
                    System.out.println(getZoneName(t) + " at (" + i + "," + j + ") received " + resdiv + " population");
                }
            }
        }
        for (int i = 0; i < city.a; i++) {
            for (int j = 0; j < city.b; j++) {
                char type = city.grid[i][j].getType();
                if (type == 'I') goods += city.grid[i][j].getGoods();
                if (type == 'C') goodsnum++;
            }
        }
        int goodsdiv = goodsnum == 0 ? 0 : goods / goodsnum;
        for (int i = 0; i < city.a; i++) {
            for (int j = 0; j < city.b; j++) {
                char type = city.grid[i][j].getType();
                if (type == 'C'){ city.grid[i][j].setGoods(goodsdiv);
                System.out.println(getZoneName(type) + " at (" + i + "," + j + ") received " + goodsdiv + " goods");
                }
            }
        }
        int lifestyle = 0;
        int lifecount = 0;
        for (int i = 0; i < city.a; i++) {
            for (int j = 0; j < city.b; j++) {
                char type = city.grid[i][j].getType();
                if (type == 'C') lifestyle += city.grid[i][j].getLifestyle();
                if (type == 'H') lifecount++;
            }
        }
        int lifediv = lifecount == 0 ? 0 : lifestyle / lifecount;
        for (int i = 0; i < city.a; i++) {
            for (int j = 0; j < city.b; j++) {
                char type = city.grid[i][j].getType();
                if (type == 'H') {
                    city.grid[i][j].setLifestyle(lifediv);
                    System.out.println(getZoneName(type) + " at (" + i + "," + j + ") received " + lifediv + " lifestyle");
                }
            }
        }
    }

    public static void updateZones(City city) {
        for (int i = 0; i < city.a; i++) {
            for (int j = 0; j < city.b; j++) {
                char type = city.grid[i][j].getType();
                if (type == 'H') {
                    if (city.grid[i][j].getElectricity() > 0 && city.grid[i][j].getInternet() > 0 && city.grid[i][j].getWater() > 0) {
                        city.grid[i][j].setLevel(1);
                    }
                    if (city.grid[i][j].getLevel() == 1 && city.grid[i][j].isHasSecurity() && city.grid[i][j].isHasHealth() && city.grid[i][j].isHasEducation())
                        city.grid[i][j].setLevel(2);
                    if (city.grid[i][j].getLevel() == 2 && city.grid[i][j].getLifestyle() > 0)
                        city.grid[i][j].setLevel(3);
                }
                if (type == 'I') {
                    if (city.grid[i][j].getElectricity() > 0 && city.grid[i][j].getPopulation() > 0 && city.grid[i][j].getWater() > 0)
                        city.grid[i][j].setLevel(1);
                    if (city.grid[i][j].getLevel() == 1 && city.grid[i][j].isHasSecurity()) city.grid[i][j].setLevel(2);
                    if (city.grid[i][j].getLevel() == 2 && city.grid[i][j].getPopulation() > 0)
                        city.grid[i][j].setLevel(3);
                }
                if (type == 'C') {
                    if (city.grid[i][j].getElectricity() > 0 && city.grid[i][j].getInternet() > 0 && city.grid[i][j].getWater() > 0 && city.grid[i][j].getPopulation() > 0 && city.grid[i][j].getGoods() > 0)
                        city.grid[i][j].setLevel(1);
                    if (city.grid[i][j].getLevel() == 1 && city.grid[i][j].isHasSecurity()) city.grid[i][j].setLevel(2);
                    if (city.grid[i][j].getLevel() == 2 &&
                            Math.min(city.grid[i][j].getPopulation(), city.grid[i][j].getGoods()) > 0)
                        city.grid[i][j].setLevel(3);
                }

            }

        }

    }

    public static void accumulateProduction(City city) {
        for (int i = 0; i < city.a; i++) {
            for (int j = 0; j < city.b; j++) {
                char type = city.grid[i][j].getType();
                if (type != 'H' && type != 'I' && type != 'C') continue;

                Cell cell = city.grid[i][j];
                int m = 0;
                int output = 0;
                int oldLevel = cell.getLevel();

                if (type == 'H') {
                    m = Math.min(cell.getElectricity(), Math.min(cell.getWater(), cell.getInternet()));
                    if (oldLevel == 1) output = m;
                    else if (oldLevel == 2) output = 2 * m;
                    else if (oldLevel == 3) output = 2 * m + cell.getLifestyle();
                    cell.setPopulation(output);
                    System.out.println(getZoneName(type) + " at (" + i + "," + j + ") generated " + output + " population");
                } else if (type == 'I') {
                    m = Math.min(cell.getElectricity(), cell.getWater());
                    if (oldLevel == 1) output = m;
                    else if (oldLevel == 2) output = 2 * m;
                    else if (oldLevel == 3) output = 2 * m + cell.getPopulation();
                    cell.setGoods(output);
                    System.out.println(getZoneName(type) + " at (" + i + "," + j + ") generated " + output + " goods");
                } else {
                    m = Math.min(cell.getElectricity(), Math.min(cell.getWater(), cell.getInternet()));
                    if (oldLevel == 1) output = m;
                    else if (oldLevel == 2) output = 2 * m;
                    else if (oldLevel == 3) output = 2 * m + Math.min(cell.getPopulation(), cell.getGoods());
                    cell.setLifestyle(output);
                    System.out.println(getZoneName(type) + " at (" + i + "," + j + ") generated " + output + " lifestyle");

                }
            }
        }
    }
}
