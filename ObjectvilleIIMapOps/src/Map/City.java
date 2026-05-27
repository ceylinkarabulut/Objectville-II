package Map;

public class City {
    public Cell[][] grid;
    public int a;
    public int b;
    public City(Cell[][] grid){
        this.grid= grid;
        this.a= grid.length;
        this.b= grid[0].length;
    }
}
