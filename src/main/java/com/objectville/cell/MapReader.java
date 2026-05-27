package Map;
import java.io.*;
import java.util.ArrayList;
import Map.Cell;



public class MapReader {
    public static Cell[][] read(String fileName) {
        ArrayList<String> lines= new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\İDİL\\IdeaProjects\\ObjectvilleIIMapOps\\out\\production\\ObjectvilleIIMapOps\\map00.txt"))){
            String line;
            while((line = br.readLine()) != null){
                lines.add(line);
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        int a= lines.size();
        int b= lines.get(0).length();
        Cell[][] grid = new Cell[a][b];
        for(int i=0; i<a; i++ ){
            for(int j=0 ; j<b; j++){
                grid[i][j]= new Cell(lines.get(i).charAt(j));
            }
        }
        return grid;

    }
}
