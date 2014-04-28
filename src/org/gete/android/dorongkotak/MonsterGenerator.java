package org.gete.android.dorongkotak;

import org.gete.android.dorongkotak.TileMap.MonsterSpawnLayer;

public class MonsterGenerator {
	private GameGenerator gameGenerator;
	private TileMap tileMap;
	private MonsterSpawnLayer spawnData;
	private MonsterDatabase monsterDatabase;
	
	public MonsterGenerator(GameGenerator gameGenerator) {
		this.gameGenerator = gameGenerator;
		this.tileMap = gameGenerator.getTileMap();
		this.spawnData = this.tileMap.getSpawnData();
		this.monsterDatabase = gameGenerator.getMonsterDatabase();
	}
	
	public void generateToMap() {
		
	}

}
