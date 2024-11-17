package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Truck extends GameObject {
	
	public static final String INFO = "[TRUCK] moves towards the player";
	
	private static final String TRUCK = "←";
	
	public Truck(Game game, int x, int y) {
		super(game, x, y);
		symbol = TRUCK;
	}
	
	@Override
	public boolean isAlive() {
		return alive;
	}
	
	@Override
	public void onEnter() {

	}

	@Override
	public void onDelete() {

	}

	@Override
	public boolean receiveCollision(Player player) {
		player.setCrashed();
		alive = false;
		
		return true;
	}
	
	@Override
	public void update() {
		x--;
	}

	@Override
	public boolean receiveShoot() {
		alive = false;
		
		return true;
	}
	
	@Override
	public boolean receiveWave() {
		x++;
		
		return true;
	}
	
	@Override
	public boolean receiveExplosion() {
		alive = false;
		
		return true;
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