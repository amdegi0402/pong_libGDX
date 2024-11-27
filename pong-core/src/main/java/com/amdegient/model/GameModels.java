package com.amdegient.model;

import com.badlogic.gdx.Gdx;

public class GameModels {
	
	private final Ball ball;
	private final Paddle playerPaddle;
	private final Paddle aiPaddle;
	private final ItemManager itemManager;
	private final ScoreManager scoreManager;
	private final PauseManager pauseManager;
	
	public GameModel() {
		this.ball = newBall();
		this.playerPaddle = new Paddle(50, Gdx.graphics.getHeight() / 2);
	}
}
//
//public class GameModel {
//    private Ball ball;
//    private Paddle playerPaddle;
//    private Paddle aiPaddle;
//    private ItemManager itemManager;
//    private ScoreManager scoreManager;
//    private PauseManager pauseManager;
//
//    public GameModel() {
//        this.ball = new Ball();
//        this.playerPaddle = new Paddle(50, Gdx.graphics.getHeight() / 2);
//        this.aiPaddle = new Paddle(Gdx.graphics.getWidth() - 60, Gdx.graphics.getHeight() / 2);
//        this.itemManager = new ItemManager();
//        this.scoreManager = new ScoreManager();
//        this.pauseManager = new PauseManager();
//    }
//
//    public void update(float deltaTime) {
//        if (pauseManager.isPaused()) {
//            pauseManager.update(deltaTime);
//            return;
//        }
//
//        ball.update(deltaTime);
//        itemManager.update(deltaTime);
//
//        // 衝突判定
//        if (ball.collidesWith(playerPaddle)) ball.bounce();
//        if (ball.collidesWith(aiPaddle)) ball.bounce();
//        if (itemManager.checkCollision(ball)) ball.doubleSpeed(5);
//    }
//
//    // 必要に応じて、ゲッター・セッターを追加
//}
