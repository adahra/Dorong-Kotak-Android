package org.gete.android.dorongkotak;

import java.util.HashMap;
import java.util.Map;

public class MonsterDatabase {
	private Map<String, MonsterData> data;
	private GameGenerator gameGenerator;
	
	public MonsterDatabase(GameGenerator gameGenerator) {
		this.gameGenerator = gameGenerator;
		data = new HashMap<String, MonsterData>();
		uglyLoadDatabase();
	}
	
	public void uglyLoadDatabase() {
		this.add(new MonsterData("rat").setTileChar("r", 192, 128, 64).putData(MonsterStat.HITPOINTS, 4).putData(MonsterStat.ATTACK, 1));
		this.add(new MonsterData("goblin").setTileChar("g", 64, 192, 64).putData(MonsterStat.HITPOINTS, 9).putData(MonsterStat.ATTACK, 3));
		this.add(new MonsterData("fire wyrm").setTileChar("W", 128, 0, 0).putData(MonsterStat.HITPOINTS, 70).putData(MonsterStat.ATTACK, 9));
	}
	
	public void add(MonsterData data) {
		if (data != null) {
			this.data.put(data.name, data);
		}
	}
	
	public Monster cloneMonster(String monsterName, Dot dot) {
		if (data != null) {
			MonsterData monsterData = data.get(monsterName);
			if (monsterData != null && gameGenerator != null) {
				TileChar tileChar = monsterData.getTileChar();
				if (tileChar != null) {
					Monster monster = new Monster(tileChar, monsterName, gameGenerator, dot);
					monster.setStats(monsterData);
					return monster;
				}
			}
		}
		
		return null;
	}
	
	public enum MonsterStat {
		HITPOINTS, ATTACK, DEFENCE, DODGE;
	}
	
	public class MonsterData {
		private Map<MonsterStat, Integer> data;
		private String name;
		private TileChar tileChar;
		
		public MonsterData(String monsterName) {
			data = new HashMap<MonsterStat, Integer>();
			name = monsterName;
			this.tileChar = null;
		}
		
		public MonsterData setTileChar(String chString, int r, int g, int b) {
			TileCharset tcCharset = gameGenerator.getCharset();
			this.tileChar = tcCharset.requestChar(chString, name, tcCharset.new CharColor(r, g, b));
			return this;
		}
		
		public TileChar getTileChar() {
			return tileChar;
		}
		
		public String getName() {
			return name;
		}
		
		public Integer getData(MonsterStat monsterStat) {
			return data.get(monsterStat);
		}
		
		public MonsterData putData(MonsterStat monsterStat, int value) {
			data.put(monsterStat, value);
			return this;
		}
	}
 
}
