package org.gete.android.dorongkotak;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;

public class GameGenerator {
	private TileCharset tileCharset;
	private TileScreen tileScreen;
	private TileMap tileMap;
	private int screenWidth;
	private int screenHeight;
	private int charWidth = 13;
	private int charHeight = 17;
	public final Dot WAIT = new Dot(0, 0);
	public final Dot LEFT = new Dot(-1, 0);
	public final Dot RIGHT = new Dot(1, 0);
	public final Dot UP = new Dot(0 , -1);
	public final Dot DOWN = new Dot(0, 1);
	public final Dot UPLEFT = UP.add(LEFT);
	public final Dot UPRIGHT = UP.add(RIGHT);
	public final Dot DOWNLEFT = DOWN.add(LEFT);
	public final Dot DOWNRIGHT = DOWN.add(RIGHT);
	private int worldWidth;
	private int worldHeight;
	private TouchHandler touchHandler;
	private MapGenerator mapGenerator;
	private Monster player;
	private MonsterDatabase monsterDatabase;
	private MonsterGenerator monsterGenerator;
	private MonsterHandler monsterHandler;
	
	public GameGenerator(Resources res, int width, int height) {
		screenWidth = width;
		screenHeight = height;
		worldWidth = (screenWidth / charWidth);
		worldHeight = (screenHeight / charHeight);
		int buttonWMargin = (screenWidth / 6);
		int buttonW = ((screenWidth / 3) * 2) / 3;
		int buttonH = (int) ((double) buttonW / 1.5);
		int buttonOriginX = buttonWMargin;
		int buttonOriginY = screenHeight - buttonH * 3;
		touchHandler = new TouchHandler();
		touchHandler.add(new TouchBox(LEFT, buttonOriginX, buttonOriginY + buttonH, buttonW, buttonH));
		touchHandler.add(new TouchBox(RIGHT, buttonOriginX + buttonW * 2, buttonOriginY + buttonH, buttonW, buttonH));
		touchHandler.add(new TouchBox(UP, buttonOriginX + buttonW, buttonOriginY, buttonW, buttonH));
		touchHandler.add(new TouchBox(DOWN, buttonOriginX + buttonW, buttonOriginY + buttonH * 2, buttonW, buttonH));
		touchHandler.add(new TouchBox(WAIT, buttonOriginX + buttonW, buttonOriginY + buttonH, buttonW, buttonH));
		touchHandler.add(new TouchBox(UPLEFT, buttonOriginX, buttonOriginY, buttonW, buttonH));
		touchHandler.add(new TouchBox(UPRIGHT, buttonOriginX + buttonW * 2, buttonOriginY, buttonW, buttonH));
		touchHandler.add(new TouchBox(DOWNLEFT, buttonOriginX, buttonOriginY + buttonH * 2, buttonW, buttonH));
		touchHandler.add(new TouchBox(DOWNRIGHT, buttonOriginX + buttonW * 2, buttonOriginY + buttonH * 2, buttonW, buttonH));
		tileCharset = new TileCharset(res, charHeight);
		tileScreen = new TileScreen(worldWidth, worldHeight);
		tileMap = new TileMap(worldWidth, worldHeight);
		mapGenerator = new MapGenerator(this);
		mapGenerator.generateDungeon();
		monsterDatabase = new MonsterDatabase(this);
		monsterHandler = new MonsterHandler(this);
		monsterGenerator = new MonsterGenerator(this);
		monsterGenerator.generateToMap();
		player = new Monster(tileCharset.getChar("player"), "player", this, null);
		player.setStats(20, 3);
		monsterHandler.setPlayer(player);
	}
	
	public MonsterHandler getMonsterHandler() {
		return monsterHandler;
	}
	
	public MonsterDatabase getMonsterDatabase() {
		return monsterDatabase;
	}
	
	public Monster getPlayer() {
		return player;
	}
	
	public TileCharset getCharset() {
		return tileCharset;
	}
	
	public TileScreen getTileScreen() {
		return tileScreen;
	}
	
	public TileMap getTileMap() {
		return tileMap;
	}
	
	public TouchHandler getTouchHandler() {
		return touchHandler;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		tileScreen.draw(0, 0, charWidth, charHeight, canvas);
		touchHandler.drawTouchables(canvas);
	}

}
