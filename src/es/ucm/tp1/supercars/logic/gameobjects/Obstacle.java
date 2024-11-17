package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Obstacle extends GameObject {
	
	public static final String INFO = "[Obstacle] hits car";
	
	private static final String OBS = "░";
	
	private static int count = 0;
	
	public static int getObstaclesCount() {
		return count;
	}
	
	public static void reset() {
		count = 0;
	}
	
	public Obstacle(Game game, int x, int y) {
		super(game, x, y);
		symbol = OBS;
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
		player.setCrashed();
		alive = false;
		
		return true;
	}
	
	@Override
	public void update() {
		
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