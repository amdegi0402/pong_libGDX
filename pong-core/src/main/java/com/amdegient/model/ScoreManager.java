package com.amdegient.model;

public class ScoreManager {
	private int playerScore;
	private int aiScore;

	public void addPlayerScore() {
		playerScore++; //player得点を加算
	}

	public void addAiScore() {
		aiScore++; //ai得点を加算
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public int getAiScore() {
		return aiScore;
	}

}
