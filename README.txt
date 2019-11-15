Minimum requirements

32-Bit UNIX OS
1 GB Ram



The instructions below are used to compile and run the game on a Linux platform.

To Compile:

	1 - Navigate bash terminal to JSCRABBLE folder.
	2 - From within the JSCRABBLE folder, run the following the command
	3 - javac jscrabble/*/*.java

To Run:

	1 - java jscrabble.sys.Game


Note: All instructions on how to play the game are given at startup.


Known issues / bugs and their fixes:
	
	Issue : 	Points for existing solved words are reallocated to current player. 
	Condition: 	A letter is played within the row or column of a solved word, 
			given that a space exists between the added letter and the existing word.

	Fix : 		A data structure is needed to keep track of solved words with a particular row
			or column. When a letter is placed on the grid and a word is confirmed, 
			the structure is queried to see if the word was already solved within that 				scope before allocating points to players.  

			Note: A dictionary can work, with solved words as 'keys' and a list of 				coordinates ('values') representing all the locations in which the word has 				been solved.	{8 * 8 Grid fix}	
