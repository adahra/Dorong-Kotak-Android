package org.gete.android.dorongkotak;

import org.gete.android.framework.Game;
import org.gete.android.framework.Graphics;
import org.gete.android.framework.Screen;
import org.gete.android.framework.Graphics.ImageFormat;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Asset.menu = g.newImage("gfx/gothic_forest.png", ImageFormat.RGB565);
		Asset.actorBack = g.newImage("gfx/actor_back.png", ImageFormat.ARGB4444);
		Asset.actorFront = g.newImage("gfx/actor_front.png", ImageFormat.ARGB4444);
		Asset.actorLeft = g.newImage("gfx/actor_left.png", ImageFormat.ARGB4444);
		Asset.actorRight = g.newImage("gfx/actor_right.png", ImageFormat.ARGB4444);
		Asset.floor = g.newImage("gfx/floor.png", ImageFormat.RGB565);
		Asset.wall = g.newImage("gfx/wall.png", ImageFormat.RGB565);
		Asset.wallCornerLeft = g.newImage("gfx/wall_corner_left.png", ImageFormat.RGB565);
		Asset.wallCornerRight = g.newImage("gfx/wall_corner_right.png", ImageFormat.RGB565);
		Asset.wallLeftFloor = g.newImage("gfx/wall_left_floor.png", ImageFormat.RGB565);
		Asset.wallRightFloor = g.newImage("gfx/wall_right_floor.png", ImageFormat.RGB565);
		Asset.crate = g.newImage("gfx/crate.png", ImageFormat.ARGB4444);
		Asset.woodenHole = g.newImage("gfx/wooden_hole.png", ImageFormat.ARGB4444);
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Asset.splash, 0, 0);
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
