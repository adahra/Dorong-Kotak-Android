package org.gete.android.dorongkotak;

public class TileData {
	private SpawnData[][] data;
	private int width;
	private int height;
	
	public enum SpawnData {
		EASY_MONSTER, NORMAL_MONSTER, HARD_MONSTER
	}
	
	public TileData(int width, int height) {
		this.width = width;
		this.height = height;
		this.data = new SpawnData[width][height];
	}
	
	public void box(int xx, int yy, int w, int h, SpawnData c) {
		for (int x = xx; x < xx + w; x++) {
			if (x >= 0 && x < this.width) {
				for (int y = yy; y < yy + h; y++) {
					if (y >= 0 && y < this.height){
						data[x][y] = c;
					}
				}
			}
		}
	}
	
	public void putValue(SpawnData value, int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			this.data[x][y] = value;
		}
	}
	
	public SpawnData getValue(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return this.data[x][y];
		}
		
		return null;
	}
 
}
