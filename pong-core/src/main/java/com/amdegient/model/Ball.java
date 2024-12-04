package com.amdegient.model;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball {
	private final Rectangle bounds;
	private Vector2 speed;
	private Random rand;

	public Ball() {
		bounds = new Rectangle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 30, 30);//オブジェクトの位置とサイズを管理
		speed = new Vector2(200, 200);//ボール速度
		rand = new Random();//ボール速度変更用乱数
	}

	public void update(float deltaTime) {
		bounds.x += speed.x * deltaTime;
		bounds.y += speed.y * deltaTime;

		if (bounds.y < 0 || bounds.y > Gdx.graphics.getHeight() - bounds.height) {
			speed.y = -speed.y;
		}
	}

	public boolean collidesWith(Paddle paddle) {
		return bounds.overlaps(paddle.getPaddle());//パドルの衝突判定
	}

	public void bounce() {
		speed.x = -speed.x; //ボールが壁やパドルにぶつかったとき、進行方向を反転させる
	}

	public void randSpeed(float duration) {//ボール速度変更
		int randomNum = rand.nextInt(10) + 1;//1-10の乱数発生
		speed.scl(randomNum);
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	public void reset() {
		//ボールの位置を画面を中央に戻す
		bounds.x = Gdx.graphics.getWidth() / 2;
		bounds.y = Gdx.graphics.getWidth() / 2;
		
		//速度を初期状態に戻す
		speed.set(200, 200);
		
		//ランダムな方向に打ち出すための処理
		if(rand.nextBoolean()) {
			speed.x = -speed.x;
		}
	}
	
//	public float getY() {
//		return bounds.y; //Rectangleオブジェクトのy座標を返す
//	}
}
