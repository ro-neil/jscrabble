package jscrabble.models;

public class HumanPlayer implements Player {
	
	String name;
	int score;
	
	
	public HumanPlayer(String name) {
		this.name = name;
		this.score = 0;
	}

	public int getScore() {
		return this.score;
	}

	public String getName() {
		return this.name;
	}

	public void updateScore(int score) {
		this.score += score;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
