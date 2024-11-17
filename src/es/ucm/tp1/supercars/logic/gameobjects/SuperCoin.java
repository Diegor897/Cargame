package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class SuperCoin extends GameObject {
	
	public static final String INFO = "[SUPERCOIN] gives 1000 coins";

	private static final String SUPERCOIN = "$";
	
	private static boolean present;
	
	public static boolean hasSuperCoin() {
		return present;
	}
	
	public SuperCoin(Game game, int x, int y) {
		super(game, x, y);
		symbol = SUPERCOIN;
	}
	
	@Override
	public boolean isAlive() {
		return alive;
	}
	
	@Override
	public void onEnter() {
		present = true;
	}

	@Override
	public void onDelete() {
		present = false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.addCoins(1000);
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