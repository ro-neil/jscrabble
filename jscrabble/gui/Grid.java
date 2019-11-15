package jscrabble.gui;

import java.util.HashMap;

public class Grid {

    public String[][] board;

    public Grid(){
        board =  this.makeBoard();
    
    }// End Grid constructor



    //This function will create an 8x8 board
    private String [][] makeBoard(){
        String b [][] = new String[8][8];
        for (int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                b[i][j]=" ";
            }
        }
        return b;
    }//End makeBoard


    


    //This function will return character at the position x, y given the board
    public String getCell(String [][] b, int x, int y){
        return b [x][y]; 
    }//End getCell


    /**
     * This function is used to draw a text-based grid.
     * params x and y represents coordinates on the grid
     * Arrays are zero indexed but our grid starts at index 1
     */

    public void makePlay(int x, int y, String c){
         board[x-1][y-1] = c;
    }

    //This function returns true if a given position does not have a character
    public boolean isCellAvailable(int x, int y) {
		return getCell(board, x-1, y-1).equals(" ");
    }
    
    // Returns N random integers between 1 and (range + 1)
    public static int[] randomIntArray(int N,int range) { 
    	int[] values = new int[N];
    	for(int i = 0; i < N; i++)
    		values[i] = (int)(Math.ceil(range*Math.random()));
    	return values;
    }
    
    // This method randomly position 10 letters on the board
    public void startBoard(HashMap <String, Integer> LETTER_VALUES) {
    	int[] xValues = randomIntArray(10,8); 	// Random list of x values
    	int[] yValues = randomIntArray(10,8);	// Random list of y values
    	int[] charPos = randomIntArray(10,25); 	// Random list of alphabet indices
    	String[] charValues = new String[10];	
    	
    	// Generates a random list of letters
    	for(int i = 0; i<10; i++) {
    		charValues[i] = (String) LETTER_VALUES.keySet().toArray()[charPos[i]];
    	}
    	
    	// Checks cell availability on random assignment to ensure that 10 values are placed and not less
    	for(int i = 0; i<10; i++) {
    		if(isCellAvailable(xValues[i], yValues[i])) {
    			makePlay(xValues[i], yValues[i],charValues[i]);
    		} else {
                xValues = randomIntArray(10,8);
                yValues = randomIntArray(10,8);
    			makePlay(xValues[i], yValues[i],charValues[i]);
    		}
 
    	}
    }
    
    

    /**
     * This function is used to draw a text-based grid.
     */
    public void drawBoard(){
        String HLINE = "  +----+----+----+----+----+----+----+----+";
        String VLINE = "  |    |    |    |    |    |    |    |    |";

        String HNUMS = "    1    2    3    4    5    6    7    8";
       
        System.out.println(HNUMS);
        System.out.println(HLINE);
        
        for (int i=0;i<8;i++){
            System.out.println(VLINE);
            System.out.print(i+1);
            for(int j=0;j<8;j++){
                System.out.printf(" | %s ",this.getCell(this.board,j,i));
            }
            System.out.println(" |");
            System.out.println(VLINE);
            System.out.println(HLINE);
        }
    }


}//End Grid Class
