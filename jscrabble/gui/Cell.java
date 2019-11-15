package jscrabble.gui;


public class Cell{
    int rowNum;
    int colNum;
    String name;
    String value;

    public Cell(int col, int row){
        this.rowNum = row;
        this.colNum = col;
        this.name = "" + col + row;
        this.value = "";
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Cell " + this.getName() +" = [ "+ this.getValue()+" ]";
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        Cell celly = new Cell(1, 5);
        celly.setValue("Z");
        System.out.println(celly);
    }

       
}