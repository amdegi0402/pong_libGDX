package com.amdegient.model;

import com.badlogic.gdx.Gdx;

public class GameModel {

	private final Ball ball;
	private final Paddle playerPaddle;
	private final Paddle aiPaddle;
	private final ItemManager itemManager;
	private final ScoreManager scoreManager;
	private final PauseManager pauseManager;

	public GameModel() {
		this.ball = new Ball(); //Ballオブジェクト生成
		this.playerPaddle = new Paddle(50, Gdx.graphics.getHeight() / 2); //Playerパドルの位置と大きさを設定
		this.aiPaddle = new Paddle(Gdx.graphics.getWidth() - 60, Gdx.graphics.getHeight() / 2); //AIパドルの位置と大きさを設定
		this.itemManager = new ItemManager(); //アイテムオブジェクト生成
		this.scoreManager = new ScoreManager(); //スコアオブジェクト生成
		this.pauseManager = new PauseManager(); //一時停止オブジェクト生成
	}

	public void update(float deltaTime) {
		//ポーズ状態の確認と交信
		if (pauseManager.isPaused()) {
			pauseManager.update(deltaTime);
			return;
		}

		//ゲームオブジェクトの更新
		ball.update(deltaTime);
		updateAIPaddle(deltaTime); //AIパドルの動きを制御
		itemManager.update(deltaTime);
		
		//スコア更新
		checkScore();

		//衝突判定
		handleCollisions();
	}

	private void updateAIPaddle(float deltaTime) {
		//AIパドルの移動ロジック
		float ballY = ball.getBounds().y;
		float paddleY = aiPaddle.getPaddle().y;
		float moveSpeed = 100 * deltaTime; //AI移動速度

		if (ballY > paddleY + aiPaddle.getPaddle().height / 2) {
			aiPaddle.move(moveSpeed);
		} else if (ballY < paddleY + aiPaddle.getPaddle().height / 2) {
			aiPaddle.move(-moveSpeed);
		}

	}
	
	private void checkScore() {
		if(ball.getBounds().x + ball.getBounds().width < 0) {
			scoreManager.addAiScore();
			ball.reset();
			pauseManager.startPause(2);
		}
		else if(ball.getBounds().x > Gdx.graphics.getWidth()) {
			scoreManager.addPlayerScore();
			ball.reset();
			pauseManager.startPause(2);
		}
	}

	private void handleCollisions() {
		//パドルとの衝突判定
		if (ball.collidesWith(playerPaddle)) {
			ball.bounce();
		}
		if (ball.collidesWith(aiPaddle)) {
			ball.bounce();
		}

		//アイテムとの衝突判定
		if (itemManager.checkCollision(ball)) {
			ball.randSpeed(5);
		}
	}

	//パドル移動処理
	public void movePaddle(float dy) {
		playerPaddle.move(dy);
	}

	//一時停止を開始
	public void startPause(float duration) {
		pauseManager.startPause(duration);
	}

	public Paddle getAiPaddle() {
		return aiPaddle;
	}

	public Paddle getPlayerPaddle() {
		return playerPaddle;
	}

	public Ball getBall() {
		return ball;
	}

	public PauseManager getPauseManager() {
		return pauseManager;
	}

	public ScoreManager getScore() {
		return scoreManager;
	}

	public ItemManager getItem() {
		return itemManager;
	}

}
