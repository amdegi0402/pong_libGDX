package com.amdegient.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class ItemManager {
	private Rectangle item;
	private boolean isActive;
	private float spawnTimer;
	
	public ItemManager() {
		item = new Rectangle(0, 0, 100, 100);//アイテムの大きさ
		isActive = false;
		spawnTimer = 0;
	}
	
	public void update(float deltaTime) {
		if(!isActive) { //アイテムが非アクティブの時のみタイマーを進める
			spawnTimer += deltaTime;
			if(spawnTimer > 15) { //アイテムを出現させる
				spawn(); //アイテムを出現させる
				spawnTimer = 0; //タイマーをリセット
			}
		}
		
	}
	
	public void spawn() { //Item表示位置
		item.x = MathUtils.random(Gdx.graphics.getWidth() / 2 - item.width, Gdx.graphics.getWidth() / 2 - item.width);
		item.y = MathUtils.random(0, Gdx.graphics.getHeight() -item.height);
		isActive = true;
	}
	
	public boolean checkCollision(Ball ball) {
		if(isActive && ball.getBounds().overlaps(item)) {
			isActive = false;
			return true;
		}
		return false;
	}
	
	public boolean getIsActive() {
		return isActive;
	}
	public Rectangle getItem() {
		return item;
	}
}


