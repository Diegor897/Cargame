package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Coin extends GameObject {
	
	public static final String INFO = "[Coin] gives 1 coin to the player";

	private static final String COIN = "¢";

	private static int count = 0;
	
	public static int getCoinsCount() {
		return count;
	}
	
	public static void reset() {
		count = 0;
	}
	
	public Coin(Game game, int x, int y) {
		super(game, x, y);
		symbol = COIN;
	}
	
	@Override
	public boolean isAlive() {
		return alive;
	}
	
	@Override
	public void onEnter() {
		count++;
	}

	@Override
	public void onDelete() {
		count--;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.addCoins(1);
		alive = false;
		
		return true;
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public boolean receiveShoot() {
		return false;
	}

	@Override
	public boolean receiveWave() {
		x++;
		
		return true;
	}

	@Override
	public boolean receiveExplosion() {
		return false;
	}

	@Override
	public String serialize() {
		return (symbol + " " + "(" + x + ", " + y + ")");
	}

	@Override
	public String getInfo() {
		return INFO;
	}
}