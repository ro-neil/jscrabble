package jscrabble.models;

public class ComputerPlayer implements Player {
	
	private String name;
	private int score;
	public Row[] boardRows;  // All rows
	
	
	public ComputerPlayer(String name) {
		setName(name);
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
