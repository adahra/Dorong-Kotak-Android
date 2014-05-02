package org.gete.android.dorongkotak;

import java.util.List;

import org.gete.android.framework.Game;
import org.gete.android.framework.Graphics;
import org.gete.android.framework.Input.TouchEvent;
import org.gete.android.framework.Screen;

import android.graphics.Color;
import android.graphics.Paint;

public class GameScreen extends Screen {
	private enum GameState {
		READY, RUNNING, PAUSED, GAMEOVER
	}
	
	GameState state = GameState.READY;
	int livesLeft = 1;
	Paint paint;
	
	public GameScreen(Game game) {
		super(game);
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvent = game.getInput().getTouchEvents();
		if (state == GameState.READY) {
			updateReady(touchEvent);
		}
		
		if (state == GameState.RUNNING) {
			updateRunning(touchEvent, deltaTime);
		}
		
		if (state == GameState.PAUSED) {
			updatePaused(touchEvent);
		}
		
		if (state == GameState.GAMEOVER) {
			updateGameOver(touchEvent);
		}
	}
	
	private void updateReady(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0) {
			state = GameState.RUNNING;
		}
	}
	
	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (event.x < 640) {
					// bergerak ke kiri
				} else if (event.x > 640) {
					// bergerak ke kanan
				}
			}
			
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x < 640) {
					// berhenti bergerak ke kiri
				} else if (event.x > 640) {
					// berhenti bergerak ke kanan
				}
			}
		}
		
		if (livesLeft == 0) {
			state = GameState.GAMEOVER;
		}
	}
	
	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				
			}
		}
	}
	
	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i =0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x > 300 && event.x < 980 && event.y > 100 && event.y < 500) {
					nullify();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		// Pertama gambar semua elemen dari game
		// Contoh;
		// g.drawImage(Asset.background, 0, 0);
		// g.drawImage(Asset.character, characterX, characterY);
		// Kedua, gambar User Interface dibawah elemen game
		if (state == GameState.READY) {
			drawReadyUI();
		}
		
		if (state == GameState.RUNNING) {
			drawRunningUI();
		}
		
		if (state == GameState.PAUSED) {
			drawPausedUI();
		}
		
		if (state == GameState.GAMEOVER) {
			drawGameOverUI();
		}
	}
	
	private void nullify() {
		// Atur semua variabel menjadi null
		paint = null;
		// Panggil garbage collector untuk membersihkan memory
		System.gc();
	}
	
	private void drawReadyUI() {
		Graphics g = game.getGraphics();
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap layar untuk bergerak di arah itu", 640, 300, paint);
	}
	
	private void drawRunningUI() {
		Graphics g = game.getGraphics();
	}
	
	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Buat layar tambah gelap
		g.drawARGB(155, 0, 0, 0);
	}
	
	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 1281, 801, Color.BLACK);
		g.drawString("GAME OVER", 640, 300, paint);
	}

	@Override
	public void pause() {
		if (state == GameState.RUNNING) {
			state = GameState.PAUSED;
		}
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void backButton() {
		pause();
	}

}
