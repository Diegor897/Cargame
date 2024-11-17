package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Wall extends GameObject {
	
	public static final String INFO = "[WALL] hard obstacle";
	
	private static final String[] HEALTH = {" ", "░", "▒", "█"};
	
	private int hp;

	public Wall(Game game, int x, int y) {
		super(game, x, y);
		this.game = game;
		hp = 3;
		symbol = HEALTH[hp];
	}
	
	@Override
	public boolean isAlive() {
		return hp != 0;
	}
	
	@Override
	public void onEnter() {
		
	}

	@Override
	public void onDelete() {
		game.addCoins(5);
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.setCrashed();
		hp = 0;
		
		return true;
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public boolean receiveShoot() {
		hp--;
		symbol = HEALTH[hp];
		
		return true;
	}

	@Override
	public boolean receiveWave() {
		x++;
		
		return true;
	}
	
	@Override
	public boolean receiveExplosion() {
		hp = 0;
		symbol = HEALTH[hp];
		
		return true;
	}
	
	@Override
	public String serialize() {
		return (symbol + " " + "(" + x + ", " + y + ")" + " " + hp);
	}
	
	@Override
	public String getInfo() {
		return INFO;
	}
}