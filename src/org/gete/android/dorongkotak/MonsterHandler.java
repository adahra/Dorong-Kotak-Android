package org.gete.android.dorongkotak;

public class MonsterHandler {
	private MonsterLayer monsterLayer;
	private TileMap tileMap;
	private GameGenerator gameGenerator;
	private Monster player;
	
	public MonsterHandler(GameGenerator gameGenerator) {
		this.gameGenerator = gameGenerator;
		this.tileMap = gameGenerator.getTileMap();
		monsterLayer = new MonsterLayer(tileMap.getWidth(), tileMap.getHeight());
	}
	
	public class MonsterLayer extends Layer<Monster> {
		public MonsterLayer(int layerWidth, int layerHeight) {
			super(layerWidth, layerHeight);
		}
	}
	
	public boolean isEmpty(Dot dot) {
		return (getMonster(dot) == null);
	}
	
	public Monster getMonster(Dot dot) {
		return monsterLayer.get(dot);
	}
	
	public void putMonster(Monster monster) {
		if (monster != null) {
			Dot coordinate = monster.getCoordinates();
			monsterLayer.put(coordinate, monster);
		}
	}
	
	public void removeMonster(Dot dot) {
		monsterLayer.put(dot, null);
	}
	
	public void setPlayer(Monster player) {
		this.player = player;
	}
	
	public void update() {
		for (int x = 0; x < tileMap.getWidth(); x++) {
			for (int y = 0; y < tileMap.getHeight(); y++) {
				Monster m = monsterLayer.get(x, y);
				if (m != null && m != player) {
					if (m.isAlive()) {
						wanderAround(m);
					} else {
						monsterLayer.put(x, y, null);
					}
				}
			}
		}
	}
	
	public Monster handleNewMonster(Monster monster, Dot dot) {
		if (monster != null) {
			if (dot == null || monster.moveTo(dot) == false) {
				Dot coordinate = monster.getCoordinates();
				if (isEmpty(dot)) {
					removeMonster(coordinate);
				}
				
				spawnToMap(monster);
			}
			
			putMonster(monster);
		}
		
		return monster;
	}
	
	public boolean spawnToMap(Monster monster) {
		if (monster != null) {
			for (int x = 0; x < this.tileMap.getWidth(); x++) {
				for (int y = 0; y < this.tileMap.getHeight(); y++) {
					if (monster.moveTo(new Dot(x, y))) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public void wanderAround(Monster monster) {
		if (monster != null) {
			switch ((int)(Math.random() * 4 + 0.5)) {
			case 1:
				monster.moveBy(gameGenerator.LEFT);
				break;
			case 2:
				monster.moveBy(gameGenerator.RIGHT);
				break;
			case 3:
				monster.moveBy(gameGenerator.UP);
				break;
			case 4:
				monster.moveBy(gameGenerator.DOWN);
				break;
			default:
				break;
			}
		}
	}
 
}
