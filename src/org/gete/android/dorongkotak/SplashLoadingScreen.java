package org.gete.android.dorongkotak;

import org.gete.android.framework.Game;
import org.gete.android.framework.Graphics;
import org.gete.android.framework.Screen;
import org.gete.android.framework.Graphics.ImageFormat;

public class SplashLoadingScreen extends Screen {

	public SplashLoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Asset.splash = g.newImage("gfx/splash.png", ImageFormat.RGB565);
		game.setScreen(new LoadingScreen(game));
	}

	@Override
	public void paint(float deltaTime) {
		
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
