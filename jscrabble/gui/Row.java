package jscrabble.gui;

public class Row{
    Cell[] cells;
    int number;

    public Row(int number){
        this.cells = new Cell[8];
        this.number= number;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell cell) {
        for (int i = 0; i < this.cells.length; i++) {
            if (cells[i] == null) {
                cells[i] = cell;
                break;
            } 
        }
        
    }


    public String toString() {
        String string = "";
        for (int i = 0; i < this.cells.length; i++) {
            if (cells[i] == null) {
                return string  + "\n"; 
            } else {
                string +=  "\n" + cells[i].toString();
            }
        }  
        return string;
    }


    public static void main(String[] args) {
        Row row1 = new Row(1);
        row1.setCells(new Cell(5,3));
        row1.setCells(new Cell(1,8));
        row1.setCells(new Cell(7,4));
        row1.getCells()[0].setValue("A");
        row1.getCells()[1].setValue("Z");
        row1.getCells()[2].setValue("O");
        System.out.println(row1);
    }

}