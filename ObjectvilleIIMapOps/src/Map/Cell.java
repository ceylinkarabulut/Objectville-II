package Map;



public class Cell {
    int level;
    char type;

     public Cell(char type) {
         this.level = 0;
         this.type = type;
     }

     public int getLevel() {
         return level;
     }

     public void setLevel(int level) {
         this.level = level;
     }

     public char getType() {
         return type;
     }

     public void setType(char type) {
         this.type = type;
     }
 }
