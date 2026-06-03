package com.objectville.grid;

import java.util.Objects;

/*
  Immutable (row, col) coordinate on the grid.
  Row = vertical axis (top to bottom), Col = horizontal axis (left to right).
 */
public final class Position {

    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }

    /*
      Manhattan distance: |row1 - row2| + |col1 - col2|
      Used by ServiceDistributor for radius checks.
      Chosen over Euclidean for simplicity on a grid; documented in project report.
     */
    public int manhattanDistanceTo(Position other) {
        return Math.abs(this.row - other.row) + Math.abs(this.col - other.col);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position p = (Position) o;
        return row == p.row && col == p.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
    /*
    What is Objects.hash()?
Objects.hash() is a static method provided by the java.util.Objects class, designed to simplify the generation of hash codes.
It takes a variable number of arguments (varargs) and returns a combined hash code based on the values of those arguments.
This makes it extremely useful for classes with multiple fields, as you can simply pass the fields to Objects.hash() rather than writing complex hash code logic manually.
     */

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}