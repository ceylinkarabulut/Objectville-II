package com.objectville.grid;

import com.objectville.cell.AbstractCell;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Grid{
private int cols;
private int rows;
private AbstractCell[][] cells;
public Grid(int cols, int rows){
    this.cols = cols;
    this.rows = rows;
}
}