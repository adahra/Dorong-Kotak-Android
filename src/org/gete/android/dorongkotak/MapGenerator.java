package org.gete.android.dorongkotak;

import java.util.ArrayList;

import org.gete.android.dorongkotak.TileMap.MonsterSpawnLayer;
import org.gete.android.dorongkotak.TileMap.PassableLayer;

public class MapGenerator {
	private GameGenerator gameGenerator;
	private TileMap tileMap;
	private TileCharset tileCharset;
	private MonsterSpawnLayer monsterSpawnData;
	private PassableLayer passableLayerData;
	private int width;
	private int height;
	private ArrayList<Dot> dots;
	
	public MapGenerator(GameGenerator gameGenerator) {
		this.gameGenerator = gameGenerator;
		this.tileMap = gameGenerator.getTileMap();
		this.monsterSpawnData = this.tileMap.getSpawnData();
		this.passableLayerData = this.tileMap.getPassableData();
		tileCharset = gameGenerator.getCharset();
		width = tileMap.getWidth();
		height = tileMap.getHeight();
	}
	
	public TileLayer makeCorridor(TileLayer tileLayer, TileChar tileChar) {
		int length;
		int nx;
		int ny;
		
		Dot dot = null;
		if (dots.size() > 0) {
			dot = dots.get((int)(Math.random() * Math.max(dots.size() - 1, 0)));
		}
		
		if (dot != null) {
			if (Math.random() > 0.5) {
				length = (int)(-3 - Math.random() * 7);
			} else {
				length = (int)(3 + Math.random() * 7);
			}
			
			if (Math.random() > 0.5) {
				nx = dot.x + length;
				ny = dot.y;
				if (nx > 0 && nx < width - 1) {
					for (int x = -1; x < 2; x++) {
						for (int y = -1; y < 2; y++) {
							if (tileLayer.get(nx + x, ny + y) == tileChar) {
								return makeCorridor(tileLayer, tileChar);
							}
						}
						
						tileLayer.xLine(dot.x, dot.y, nx, tileChar);
						passableLayerData.xLine(dot.x, dot.y, nx, true);
						dots.add(new Dot(nx, ny));
					}
				} else {
					nx = dot.x;
					ny = dot.y + length;
					if (ny > 0 && ny < height - 1) {
						for (int x = -1; x < 2; x++) {
							for (int y = -1; y < 2; y++) {
								if (tileLayer.get(nx + x, ny + y) == tileChar) {
									return makeCorridor(tileLayer, tileChar);
								}
							}
						}
						
						tileLayer.yLine(dot.x, dot.y, ny, tileChar);
						passableLayerData.yLine(dot.x, dot.y, ny, true);
						dots.add(new Dot(nx, ny));
					}
				}
			}
		}
		
		return tileLayer;
	}
	
	public TileLayer makeRoom(TileLayer data, TileChar floor, TileMap.MonsterSpawnData spawn) {
		Dot dot = null;
		if (dots.size() > 0) {
			dot = dots.get((int)(Math.random() * Math.max(dots.size() - 1, 0)));
		}
		
		if (dot != null) {
			int w = (int)(2 + Math.random() * 2);
			int h = (int)(2 + Math.random() * 2);
			int nx = Math.max(dot.x - w, 1);
			int ny = Math.max(dot.y - h, 1);
			int nw = Math.min(nx + w * 2, width - 1);
			int nh = Math.min(ny + h * 2, height - 1);
			
			data.box(nx, ny, nw, nh, floor);
			monsterSpawnData.box(nx, ny, nw, nh, spawn);
			passableLayerData.box(nx, ny, nw, nh, true);
		}
		
		return data;
	}
	
	public void generateDungeon() {
		TileLayer data = tileMap.getCharData();
		TileChar wall = tileCharset.getChar("wall");
		TileChar floor = tileCharset.getChar("floor");
		dots = new ArrayList<Dot>();
		data.fill(wall);
		passableLayerData.fill(false);
		dots.add(new Dot(width / 2, height / 2));
		for (int i = 0; i < 60; i++) {
			makeCorridor(data, floor);
		}
		
		for (int i = 0; i < 8; i++) {
			makeRoom(data, floor, TileMap.MonsterSpawnData.EASY_MONSTER);
		}
		
		gameGenerator.getTileScreen().loadMap(tileMap);
	}

}
