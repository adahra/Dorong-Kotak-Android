package org.gete.android.dorongkotak;

import org.gete.android.dorongkotak.TileMap.PassableLayer;

public class Monster {
	private Dot coordinate;
	private int hitpoints;
	private int attack;
	private TileChar tcTileChar;
	private TileScreen tileScreen;
	private GameGenerator gameGenerator;
	private MonsterHandler monsterHandler;
	private String name;
	
	public Monster(TileChar tileChar, String name, GameGenerator gameGenerator, Dot coordinates) {
		tcTileChar = tileChar;
		this.coordinate = null;
		this.gameGenerator = gameGenerator;
		this.name = name;
		tileScreen = gameGenerator.getTileScreen();
		monsterHandler = this.gameGenerator.getMonsterHandler();
		monsterHandler.handleNewMonster(this, coordinates);
	}
	
	public boolean isAlive() {
		return this.hitpoints > 0;
	}
	
	public Monster setStats(int hp, int att) {
		this.attack = att;
		this.hitpoints = hp;
		return this;
	}
	
	public Monster setStats(MonsterData data) {
		this.attack = data.getData(MonsterStat.ATTACK);
		this.hitpoints = data.getData(MonsterStat.HITPOINTS);
		return this;
	}
	
	public TileChar getChar() {
		return tcTileChar;
	}
	
	public Dot getCoordinates() {
		return coordinate;
	}
	
	public boolean attackMonster(Monster monster) {
		if (this.coordinate.distanceTo(monster.coordinate) == 1) {
			monster.hitpoints -= this.attack;
			return true;
		}
		
		return false;
	}
	
	public boolean moveTo(Dot dot) {
		if (dot != null) {
			int x = dot.x;
			int y = dot.y;
			
			PassableLayer plPassableLayer = this.gameGenerator.getTileMap().getPassableData();
			boolean passable = true;
			
			if (plPassableLayer != null) {
				passable = plPassableLayer.get(x, y);
			}
			
			if (passable) {
				if (monsterHandler.isEmpty(dot)) {
					if (coordinate != null) {
						tileScreen.removeMonster(coordinate.x, coordinate.y);
						monsterHandler.removeMonster(coordinate);
					}
					
					this.coordinate = dot;
					tileScreen.putMonster(x, y, this);
					monsterHandler.putMonster(this);
					return true;
				} else {
					this.attackMonster(monsterHandler.getMonster(dot));
					return false;
				}
 			}
		}
		
		return false;
	}
	
	public boolean moveBy(Dot dot) {
		return moveTo(coordinate.add(dot));
	}

}
