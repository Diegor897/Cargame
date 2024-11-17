package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Pedestrian extends GameObject {
	
	public static final String INFO = "[PEDESTRIAN] person crossing the road up and down";
	
	private static final String PDS = "☺";
	
	private int direction;
	
	public Pedestrian(Game game, int x, int y) {
		super(game, x, y);
		symbol = PDS;
		direction = 1;
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
		game.addCoins(-game.playerCoins());
	}

	@Override
	public boolean receiveCollision(Player player) {
		player.setCrashed();
		alive = false;
		
		return true;
	}
	
	@Override
	public void update() {
		y += direction;
		
		if ((y >= (game.getRoadWidth() - 1)) || (y <= 0)) {
			direction *= -1;
		}
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
		String str;
		
		if (direction < 0) {
			str = "up";
		} else {
			str = "down";
		}
		
		return (symbol + " " + "(" + x + ", " + y + ")" + " " + str);
	}
	
	@Override
	public String getInfo() {
		return INFO;
	}
}