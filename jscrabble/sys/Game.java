package jscrabble.sys;

import jscrabble.gui.*;
import jscrabble.models.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Game{

	
	/*		ATTRIBUTES		*/
	
    Grid grid;
    Player player1;
	Player player2;
    
    Scanner scanner;
	String input;

    
    //Dictionary to associate letters with values
    static HashMap <String, Integer> LETTER_VALUES = new HashMap <String, Integer>();
    static ArrayList<String> wordList;
    /*This method must first ask for the letter to play or
    for the user to type 'quit' if they want to end the game. This method should then get the x and y
    coordinates on the grid to place the letter in the form xy. Therefore, if the position that the player wants
    to move is x = 4 and y =7, the player should enter xy.*/
    
    /*		METHODS		*/
    
	
	/* 		AI FUNCTIONALITY	*/
	
	
	
	//This function will create an 8x8 board mapping of all letters on the grid
    private Row[] gameBoardMap(){
		Row[] boardRows = new Row[8];		// All rows  - - - AI attribute
        Row row;
        for (int i=0;i<8;i++){ // Columns
            row = new Row(i+1);
            for(int j=0;j<8;j++){ //Rows
				Cell cell = new Cell(i+1,j+1);
				cell.setValue(this.grid.board[i][j]);
				row.setCells(cell);
			}
            boardRows[i] = row;
		}
		return boardRows;
     
    }



	/* 	END	AI FUNCTIONALITY	*/



	public static void print(String s) {
		System.out.print(s);
	}
	
	public static void print(int s) {
		System.out.print(s);
	}
	public static void print(double s) {
		System.out.print(s);
	}
	
	
	
	// Loads and stores internally; the list of words with lengths greater than 3 
	
	private static void generateWordList() {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
			wordList = new ArrayList<>();
			String word;
			while ((word = reader.readLine()) != null) {
				if(word.length() >=4 ) {
					wordList.add(word);
				 }
			 }  
	         reader.close();
		} catch (Exception e) {
			System.out.println("\n:(  Issues were found while generating the database of words... ");
			System.exit(0);
		} 
	}
	
	// Initializes 2 players within the game
	
	public void playerSetup() {
		System.out.println("\n--- SELECT A GAME MODE TO CONTINUE ---\n");
		System.out.println("1. Human Player vs Human Player"); 
		System.out.println("2. Computer Player vs Computer Player");
		System.out.println("3. Computer Player vs Human Player\n\n");

		input = scanner.nextLine();
		switch (input) {
			case "1":
				print("\nHuman Player 1:\t");
				String name1 = scanner.nextLine();
				print("Human Player 2:\t");
				String name2 = scanner.nextLine();
				this.player1 = new HumanPlayer(name1);
				this.player2 = new HumanPlayer(name2);
				break;
			case "2":
				System.out.println("\nWe're sorry :( \n\nThis game mode is currently under construction.");
				playerSetup();
				break;
			case "3":
				System.out.println("\nWe're sorry :( \n\nThis game mode is currently under construction.");
				playerSetup();
				break;
			default:
				System.out.println("\nNote : Only the values 1, 2 or 3 are valid commands!");
				playerSetup();
				break;
		}
		/** 
		  */
	}
	
	// Prompts a current player to quit the game or to continue playing
	
	public void playOrQuit() {
		boolean invalid;
		System.out.println();
		print(player1.getName()+"\t\t"+"Score:\t"+player1.getScore()+"\n");
		print(player2.getName()+"\t\t"+"Score:\t"+player2.getScore()+"\n");
		System.out.println();
		do {
			System.out.println("\nWould you like to continue playing?\t[Y/N]\n");
            input = scanner.nextLine().toUpperCase();
            print("\n");
	        switch (input) {
	        	case "Y":
	        		grid.drawBoard();
	        		invalid = false;
	        		getPlayerMove();
	        		break;
	        	case "N":
	        		print("Thanks for playing!\n\n");
	        		print(player1.getName()+"\t\t"+"Score:\t"+player1.getScore()+"\n");
	        		print(player2.getName()+"\t\t"+"Score:\t"+player2.getScore()+"\n");
	        		invalid = false;
	        		break;
	        	default:
	        		print("Invalid Command");
	        		invalid = true;
	        		break;
	        }
        }while(invalid == true);	
	}
	
	
	// Returns 1 if the input coordinates are within the range of the grid
	
	public int isOnBoard(int x, int y) {
		if( !(x > 8 || y > 8 || x < 1 || y < 1) )
			return 1;
		return -1;
	}
	
	// Verifies that a given word is in the predefined list of words ( wordList )
	
	public boolean isValidWord(String word) {
		
        int result = Collections.binarySearch(Game.wordList, word);
        if(result >= 0) {
        	return true;
        } else {
        	String reversedWord = "";
            for(int i = word.length() - 1; i >= 0; i--){
                reversedWord = reversedWord + word.charAt(i);
            }
        	result = Collections.binarySearch(Game.wordList, reversedWord);
        	return result >= 0;
        }
	}
	
	// Validates words within the grid, allocates and update player points
	public void wordCheck(int x, int y){
		String row = "";
		String column = "";
		
		// Extracts the row and column of the last played letter
        for(int j=0;j<8;j++){
			row += this.grid.board[j][y-1];
			column += this.grid.board[x-1][j];
		}
		System.out.println("Col "+column);	// Temp
        System.out.println("Row "+row); // Temp
        // Removes blank spaces from rows and columns to get word clusters
	    String[] rows = row.split(" ");
	    String[] columns = column.split(" ");
	     
	    // Checks the current row for words that have four or more characters and allocates player points accordingly
	    for(String wrd : rows) {
	    	if(wrd.length() >= 4) {
	    		if(isValidWord(wrd)) {
	    			calculateScore(wrd, this.currentPlayer);
	    		 }
	    	}
	    }
	    
	    // Checks the current column for words that have four or more characters and allocates player points accordingly
	    for(String wrd : columns) {
	    	if(wrd.length() >= 4) {
	    		if(isValidWord(wrd)) {
	    			calculateScore(wrd, this.currentPlayer);
	    		}
	    	}
	    }
	}
	
	// Calculates and allocates player score, given a player and a word
	
	public void calculateScore(String wrd, Player p) {
		
		for(int i = 0; i < wrd.length(); i++) {
			String letter = String.valueOf(wrd.charAt(i));	
            p.updateScore(LETTER_VALUES.get(letter));
		}	
	}
	
	
	Player currentPlayer;
	
	// Gets the move from the current player 
	
    public void getPlayerMove() {

    	scanner = new Scanner(System.in);
    	Player[] players = {player1, player2};
    	
	    for(Player player : players) {
	    	currentPlayer = player;
	    	int x = 0;
	    	int y = 0;
	    	String z = null;
	    	
	    	// Loops continuously while the input criteria is not met
	    	do {
		    	print("\n\t---I N S T R U C T I O N S---\n");
				System.out.println("\n"+currentPlayer.getName()+" it's your turn...\n");
				
				print("Note: Let x-columns and y-rows");
		    	print("Type your command in the form 'xyz', where 'x' is the column, 'y' is the row and 'z' is the letter to be placed.\t");
		    	print("\nType here:\t");
		    	input = scanner.nextLine().toUpperCase().strip();
		    	print("\n");
		    	
				// Validates the input 
		    	try {
					x = Integer.parseInt(input.substring(0, 1));
					y = Integer.parseInt(input.substring(1, 2));
					z = input.substring(2);
					
					if(!(LETTER_VALUES.containsKey(z))){
						System.out.println("\nSingle letter inputs only! Change the z value -> "+z+" to a single letter of the english alphabet.\n\n");
					} else if(grid.isCellAvailable(x, y)) {
						grid.makePlay(x, y, z);
						wordCheck(x, y);
						grid.drawBoard();
					} else {
						System.out.println("\nCell is already in use. Please select new coordinates.\n\n");
						x = 0; // To re-run the loop since a condition is not met
					}
		    	} catch (Exception e) {
		    		System.out.println("\nInvalid coordinates\n\n");
		    	}
	    	} while (isOnBoard(x, y) < 0 || !(LETTER_VALUES.containsKey(z))); // Conditions
	    }
	    playOrQuit();
	}
    
	
	// Starts the main game loop
    
    public void GamePlay() {
		//System.out.println("\n*-* W E L C O M E   TO   J_S C R A B B L E *-*\n");

		/*grid.startBoard(Game.LETTER_VALUES);
	    grid.drawBoard();
		
		String string = "";
		for(Row row : this.mapGameBoard()){
			string += row.toString();
		}
		System.out.println(string); */
		
		scanner = new Scanner(System.in);
		playerSetup();
		boolean invalid;
        do {
        	System.out.println("\nReady?\t[Y/N]\n");
            input = scanner.nextLine().toUpperCase();
            print("\n");
	        switch (input) {
	        	case "Y":
	        		invalid = false;
	        		grid.startBoard(Game.LETTER_VALUES);
	        		grid.drawBoard();
	        		getPlayerMove();
	        		break;
	        	case "N":
	        		print("Thanks for playing!\n\n");
	        		print(player1.getName()+"\t\t"+"Score:\t"+player1.getScore()+"\n");
	        		print(player2.getName()+"\t\t"+"Score:\t"+player2.getScore()+"\n");
	        		invalid = false;
	        		break;
	        	default:
	        		print("Invalid Command");
	        		invalid = true;
	        		break;
	        }
        }while(invalid == true);
	}
	
    
    // Game Constructor

    public Game() {
        this.grid = new Grid();

        LETTER_VALUES.put("A",1);
        LETTER_VALUES.put("B",3);
        LETTER_VALUES.put("C",3);
        LETTER_VALUES.put("D",2);
        LETTER_VALUES.put("E",1);
        LETTER_VALUES.put("F",4);
        LETTER_VALUES.put("G",2);
        LETTER_VALUES.put("H",4);
        LETTER_VALUES.put("I",1);
        LETTER_VALUES.put("J",8);
        LETTER_VALUES.put("K",5);
        LETTER_VALUES.put("L",1);
        LETTER_VALUES.put("M",3);
        LETTER_VALUES.put("N",1);
        LETTER_VALUES.put("O",1);
        LETTER_VALUES.put("P",3);
        LETTER_VALUES.put("Q",10);
        LETTER_VALUES.put("R",1);
        LETTER_VALUES.put("S",1);
        LETTER_VALUES.put("T",1);
        LETTER_VALUES.put("U",1);
        LETTER_VALUES.put("V",4);
        LETTER_VALUES.put("W",4);
        LETTER_VALUES.put("X",8);
        LETTER_VALUES.put("Y",4);
        LETTER_VALUES.put("Z",10);
        
        generateWordList();
    }

 public static void main(String[] args){
     //This is the driver program
     Game game = new Game();  
     game.GamePlay();


	 	
}



}
