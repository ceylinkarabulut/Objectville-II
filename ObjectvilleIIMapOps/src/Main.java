import Map.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Cell[][] grid = MapReader.read("map00.txt");
        City city = new City(grid);

        for (int i = 0; i < city.a; i++) {
            for (int j = 0; j < city.b; j++) {
                System.out.print(city.grid[i][j].getType());
            }
            System.out.println();
        }
    }
}