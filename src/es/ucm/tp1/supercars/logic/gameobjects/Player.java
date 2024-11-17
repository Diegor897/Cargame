package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public class Player extends GameObject {
	
	private static final String INFO = "[Car] the racing car";
	
	private static final String CAR = ">";
	
	private static final String CRASHED = "@";
	
	private int coins;
	
	public Player(Game game, int x, int y) {
		super(game, x, y);
		
		symbol = CAR;
		coins = 0;
	}
	
	public void move(int n) {
		x += n;
	}
	
	@Override
	public void update() {
		if (!doCollision()) {
			x++;
			doCollision();
		}
	}
	
	public boolean doCollision() {
		Collider other = game.getColliderInPosition(x, y);
		
		if (other != null) {
			return other.receiveCollision(this);
		}
		
		return false;
	}
	
	public void setCrashed() {
		alive = false;
		symbol = CRASHED;
	}
	
	public int distanceToGoal() {
		return game.getRoadLength() - x;
	}
	
	public boolean hasArrived() {
		return x == game.getRoadLength();
	}
	
	public void goUp() {
		if(y > 0) {
			y--;
		}
		x++;
		doCollision();
	}
	
	public void goDown() {
		if (y < (game.getRoadWidth() - 1)) {
			y++;
		}
		x++;
		doCollision();
	}
	
	public void addCoins(int num) {
		coins += num;
	}
	
	public int getCoins() {
		return coins;
	}
	
	@Override
	public boolean isAlive() {
		return alive;
	}
	
	@Override
	public void onDelete() {
		
	}
	
	@Override
	public void onEnter() {
		
	}

	@Override
	public boolean receiveCollision(Player player) {
		return false;
	}
	
	public void endGame() {
		
	}

	@Override
	public boolean receiveShoot() {
		return false;
	}
	
	@Override
	public boolean receiveWave() {
		return false;
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