package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Turbo extends GameObject {
	
	public static final String INFO = "[TURBO] pushes the car 3 columns";

	private static final String TURBO = ">>>";
	
	public Turbo(Game game, int x, int y) {
		super(game, x, y);
		symbol = TURBO;
	}
	
	@Override
	public boolean isAlive() {
		return alive;
	}

	@Override
	public boolean receiveCollision(Player player) {
		alive = false;
		
		player.move(3);
		
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
	public void onEnter() {
		
	}

	@Override
	public void onDelete() {

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