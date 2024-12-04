package com.amdegient.model;

public class PauseManager {
	private boolean isPaused;
	private float pauseDuration;

	//ゲームや一部の処理を「一時停止」状態にするための設定
	public void startPause(float duration) {
		isPaused = true; //一時停止状態を有効に切り替え
		pauseDuration = duration; //一時停止の「残り時間」を設定
	}

	//
	public void update(float deltaTime) {
		if (isPaused) {
			//pauseDuration -= deltaTime; //一時停止の「残り時間」を、経過時間分だけ減少させる。
			pauseDuration -= deltaTime;
			System.out.println("[PauseManager]17 deltaTime: " + deltaTime);
			System.out.println("[PauseManager]18 pauseDuration: " + pauseDuration);
			if (pauseDuration <= 0)
				isPaused = false; //一時停止を解除し、通常のゲーム進行に戻します
		}
	}
	
	public boolean isPaused() {
		return isPaused;
	}
}
