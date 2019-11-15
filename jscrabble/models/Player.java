package jscrabble.models;

public interface Player{
	
	void updateScore(int score);
	int getScore();
	String getName();
	void setName(String name);

}
