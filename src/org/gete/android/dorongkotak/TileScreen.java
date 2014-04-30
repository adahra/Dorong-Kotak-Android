package org.gete.android.dorongkotak;

import android.graphics.Canvas;

public class TileScreen {
	private static final int BUFFER_LAYER = 0;
	private static final int MAP_LAYER = 1;
	private static final int ITEM_LAYER = 2;
	private static final int MONSTER_LAYER = 3;
	private static final int IMAGE_LAYER = 4;
	public TileLayer[] layerData;
	private int width;
	private int height;
	private TileMap map;
	
	public TileScreen(int screenWidth, int screenHeight) {
		width = screenWidth;
		height = screenHeight;
		layerData = new TileLayer[5];
		layerData[BUFFER_LAYER] = new TileLayer(width, height);
		layerData[MONSTER_LAYER] = new TileLayer(width, height);
		layerData[ITEM_LAYER] = new TileLayer(width, height);
		layerData[IMAGE_LAYER] = new TileLayer(width, height);
	}
	
	public boolean putMonster(int x, int y, Monster monster) {
		return this.layerData[MONSTER_LAYER].put(x, y, monster.getChar());
	}
	
	public boolean removeMonster(int x, int y) {
		return this.layerData[MONSTER_LAYER].put(x, y, null);
	}
	
	public void loadMap(TileMap tileMap) {
		this.map = tileMap;
	}
	
	public void draw(int startX, int startY, int w, int h, Canvas canvas) {
		if (this.map != null) {
			this.layerData[MAP_LAYER] = this.map.getCharData();
		}
		
		this.layerData[IMAGE_LAYER].fill(null);
		
		if (this.layerData[MAP_LAYER] != null) {
			this.layerData[MAP_LAYER].mergeTo(this.layerData[IMAGE_LAYER]);
		}
		
		this.layerData[ITEM_LAYER].mergeTo(this.layerData[IMAGE_LAYER]);
		this.layerData[MONSTER_LAYER].mergeTo(this.layerData[IMAGE_LAYER]);
		this.layerData[IMAGE_LAYER].differenceWith(this.layerData[BUFFER_LAYER]);
		this.layerData[IMAGE_LAYER].draw(startX, startY, w, h, canvas);
		this.layerData[IMAGE_LAYER].mergeTo(this.layerData[BUFFER_LAYER]);
	}

}
