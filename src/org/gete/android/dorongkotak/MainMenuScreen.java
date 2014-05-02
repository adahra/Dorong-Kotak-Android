package org.gete.android.dorongkotak;

import java.util.List;

import org.gete.android.framework.Game;
import org.gete.android.framework.Graphics;
import org.gete.android.framework.Input.TouchEvent;
import org.gete.android.framework.Screen;

public class MainMenuScreen extends Screen {

	public MainMenuScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int length = touchEvents.size();
		for (int i = 0; i < length; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBound(event, 0, 0, 250, 250)) {
					game.setScreen(new GameScreen(game));
				}
			}
		}
	}
	
	private boolean inBound(TouchEvent event, int x, int y, int width, int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Asset.menu, 0, 0);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void backButton() {
		
	}

}
