package com.amdegient.view;

import com.amdegient.MyGame;
import com.amdegient.controller.GameController;
import com.amdegient.model.GameModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen{
	private final MyGame game;
	private GameModel model;
	private GameView view;
	private GameController controller;
	private final int WINNING_SCORE = 10;
	private BitmapFont font; //カウントダウンを描画するフォント
	private float countdownTimer = 3; //3秒からカウントダウンを描画するフォント
	private boolean gameStarted = false; //ゲームが開始されたか管理
	private SpriteBatch batch;
	private float startTimer = 2;//ゲーム再開までのタイマー
	
	// コンストラクタでゲーム本体を受け取る
		public GameScreen(final MyGame game) {
			this.game = game;
			this.model = new GameModel(); // ゲーム状態を保持するモデル
			this.view = new GameView(); // ゲーム描画を担当するビュー
			this.controller = new GameController(model); // ゲームロジックを処理するコントローラー
			this.font = new BitmapFont(); //デフォルトのフォントを使ってカウントダウン
			font.getData().setScale(4.0f);//フォントの大きさ調整
			this.batch = new SpriteBatch();
		}

		@Override
		public void show() {
			// ゲームが表示されるときに呼ばれる
		}

		// ゲームを毎秒何十回も更新して画面に描画するためのもの
		@Override
		public void render(float delta) {
			// 画面をクリア
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			if(!gameStarted) {
				//カウントダウン処理
				countdownTimer -= delta; //deltaTimeを使ってタイマーを減らす

				if(countdownTimer > 1) {
					drawCountdown((int) Math.ceil(countdownTimer)); //カウントを表示
				}else if(countdownTimer > 0) {
					drawCountdown("START"); //カウントダウンが0にしかづいたら"START"を表示
				}else {
					//カウントダウンが終わったらゲームを開始
					gameStarted = true;
				}
			}else {
				System.out.println("(GameScreen72) isPause: " + model.isPause());
				// ゲームのロジックを更新
				if(!model.isPause()) {
					controller.update(delta);
					// モデルの状態を更新
					model.updateBallPosition(delta);
				}else {
					System.out.println("(GameScreen78) timer: " + startTimer);
					startTimer -= delta;
					if(startTimer <= 0) { 
						startTimer = 2;
						model.setPause(false);
					}
				}
				// ビューで描画
				view.render(model);
				
				//一定時間おきにアイテムを表示
				model.itemUpdate(delta);
				
			model.checkBallItemCollision();

				// 終了条件を確認
				checkForWinner();
			}



		}

		//カウントダウンの数字を描画
		private void drawCountdown(Object text) {
			batch.begin();
			final String countdownText = text.toString();//テキスト（数字または"START!")
			//float textWidth = font.getRegion().getRegionWidth(); //テキストの幅を取得
			//System.out.println(textWidth);

			//文字列のレイアウト（配置情報）を計算するためのクラス
			final GlyphLayout layout = new GlyphLayout();
			layout.setText(font,  countdownText); //テキストに基づいてレイアウトを計算
			final float textWidth = layout.width; //テキストのピクセル幅を取得

			final float xPosition = (Gdx.graphics.getWidth() - textWidth) / 2; //画面幅からテキストの幅を引いて中央位置を計算
			font.draw(batch, countdownText, xPosition, Gdx.graphics.getHeight() / 2); //画面中央に表示
			batch.end();
		}

		/**
		 * @see com.badlogic.gdx.Screen#resize(int, int)
		 */
		@Override
		public void resize(int width, int height) {
			// ウィンドウサイズが変更されたときに呼ばれる（必要に応じて処理を追加）

		}

		/**
		 * @see com.badlogic.gdx.Screen#pause()
		 */
		@Override
		public void pause() {
			// ゲームが一時停止されたときの処理（必要に応じて処理を追加）

		}

		/**
		 * @see com.badlogic.gdx.Screen#resume()
		 */
		@Override
		public void resume() {
			// ゲームが再開されたときの処理（必要に応じて処理を追加）

		}

		/**
		 * @see com.badlogic.gdx.Screen#hide()
		 */
		@Override
		public void hide() {
			// ゲーム画面が非表示になったときの処理（必要に応じて処理を追加）

		}

		/**
		 * @see com.badlogic.gdx.Screen#dispose()
		 */
		@Override
		public void dispose() {
			// リソースを解放（メモリリークを防ぐため）
			view.dispose();
			batch.dispose();
			font.dispose();
		}

		private void checkForWinner() {
			if (model.getPlayerScore() >= WINNING_SCORE) {
				System.out.println("(GameScreen159)Player Wins!");
				game.setScreen(new WinScreen(game, true)); //勝者を表示する画面に遷移
			} else if (model.getAiScore() >= WINNING_SCORE) {
				System.out.println("(GameScreen163)AI Wins!");
				game.setScreen(new WinScreen(game, false)); //勝者を表示する画面に遷移
			}
		}
}
