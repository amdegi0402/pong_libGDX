package com.amdegient.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicManager {
	private static MusicManager instance; //シングルトンインスタンス
	private Map<String, Music> musicTracks = new HashMap<>(); //音楽トラックの管理
	private Music currentMusic; //現在再生中の音楽
	private float volume = 0.3f; //ボリューム
	private boolean isMuted = false; //ミュート状態
	
	//シングルトンなのでコンストラクタをprivateにしてインスタンス化を防ぐ
	private MusicManager() {}
	
	//シングルトンのインスタンスを取得
	public static synchronized MusicManager getInstance() {
		if(instance == null) {
			instance = new MusicManager();
		}
		return instance;
	}
	
	//トラックを追加
	public void addMusic(String key, String filePath) {
		Music music = Gdx.audio.newMusic(Gdx.files.internal(filePath)); //トラック読み込み
		music.setLooping(true); //音楽繰り返し
		music.setVolume(volume);
		musicTracks.put(key, music);
	}
	
	//音楽再生
	public void play(String key) {
		//nullチェック（Nullでなければ中の処理する）
		if(Objects.nonNull(currentMusic)) {
			currentMusic.stop();
		}
		
		currentMusic = musicTracks.get(key);
		if(Objects.nonNull(currentMusic) && !isMuted) {
			currentMusic.play();
		}
		
	}
	
	//音楽を一時停止
	public void pause() {
		if(Objects.nonNull(currentMusic) && currentMusic.isPlaying()) {
			currentMusic.pause();
		}
	}
	
	//音楽を停止
	public void stop() {
		if(Objects.nonNull(currentMusic) && currentMusic.isPlaying()) {
			currentMusic.stop();
		}
	}
	
	//音量を設定
	public void setVolume(float newVolume) {
		volume = Math.max(0, Math.min(newVolume, 1));
		if(Objects.nonNull(currentMusic)) {
			currentMusic.setVolume(volume);
		}
		for(Music music : musicTracks.values()) {
			music.setVolume(volume);
		}
	}
	
	//音量を取得
	public float getVolume() {
		return volume;
	}
	
	//ミュートの切り替え
	public void toggleMute() {
		isMuted = !isMuted;
		if(isMuted) {
			stop();
		}else if(Objects.nonNull(currentMusic)) {
			currentMusic.play();
		}
	}
	
	//ミュート状態を取得
	public boolean isMuted() {
		return isMuted;
	}
	
	//リソース解放
	public void dispose() {
		for(Music music : musicTracks.values()) {
			music.dispose();
		}
		musicTracks.clear();
	}
}	
