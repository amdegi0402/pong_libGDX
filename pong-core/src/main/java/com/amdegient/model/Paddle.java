package com.amdegient.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Paddle {
	private Rectangle paddle;

	public Paddle(float x, float y) {
		paddle = new Rectangle(x, y, 10, 80); //パドルの大きさ
	}

	public void move(float dy) {
		paddle.y += dy;
		if (paddle.y < 0)
			paddle.y = 0;
		if (paddle.y + paddle.height > Gdx.graphics.getHeight()) {
			paddle.y = Gdx.graphics.getHeight() - paddle.height;
		}
	}

	public Rectangle getPaddle() {
		return paddle;
	}
	

}
