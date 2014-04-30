package org.gete.android.dorongkotak;

import org.gete.android.dorongkotak.TileMap.MonsterSpawnLayer;

public class MonsterGenerator {
	private GameGenerator gameGenerator;
	private TileMap tileMap;
	private MonsterSpawnLayer spawnData;
	private MonsterDatabase monsterDatabase;
	
	public MonsterGenerator(GameGenerator gameGenerator) {
		this.setGameGenerator(gameGenerator);
		this.tileMap = gameGenerator.getTileMap();
		this.spawnData = this.tileMap.getSpawnData();
		this.monsterDatabase = gameGenerator.getMonsterDatabase();
	}
	
	public void generateToMap() {
		if (tileMap != null && spawnData != null && monsterDatabase != null) {
			for (int x = 0; x < tileMap.getWidth(); x++) {
				for (int y = 0; y < tileMap.getHeight(); y++) {
					if (spawnData.get(x, y) == TileMap.MonsterSpawnData.EASY_MONSTER) {
						switch ((int)(Math.random() * 3.0)) {
						case 0:
							monsterDatabase.cloneMonster("rat", new Dot(x, y));
							break;
						case 1:
							monsterDatabase.cloneMonster("goblin", new Dot(x, y));
							break;
						case 2:
							monsterDatabase.cloneMonster("fire wyrm", new Dot(x, y));
							break;
						default:
							break;
						}
					}
				}
			}
		}
	}

	public GameGenerator getGameGenerator() {
		return gameGenerator;
	}

	public void setGameGenerator(GameGenerator gameGenerator) {
		this.gameGenerator = gameGenerator;
	}

}
