package org.gete.android.dorongkotak;

public class GameHandler {
	private GameGenerator gameGenerator;
	private TouchHandler touchHandler;
	private MonsterHandler monsterHandler;
	private Monster player;
	
	public GameHandler(GameGenerator gameGenerator) {
		this.gameGenerator = gameGenerator;
		touchHandler = gameGenerator.getTouchhandler();
		monsterHandler = gameGenerator.getMonsterHandler();
		player = gameGenerator.getPlayer();
	}
	
	public void movePlayer(Dot touch) {
		if (touch != null) {
			if (touch.x == 0 && touch.y == 0) {
				monsterHandler.update();
			} else if (Math.abs(touch.x) <= 1 && Math.abs(touch.y) <= 1) {
				player.moveBy(touch);
				monsterHandler.update();
			}
		}
	}
	
	public void handleNewMonster(Monster monster, Dot dot) {
		monsterHandler.handleNewMonster(monster, dot);
	}

}
