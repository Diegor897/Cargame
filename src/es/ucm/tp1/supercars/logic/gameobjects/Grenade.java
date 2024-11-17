package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.ExplosionAction;

public class Grenade extends GameObject {
	
	public static final String INFO = "[GRENADE] Explodes in 3 cycles, harming everyone around";
	
	private static final String GRENADE = "ð";
	
	private int counter;

	public Grenade(Game game, int x, int y) {
		super(game, x, y);
		this.game = game;
		counter = 4;
		symbol = (GRENADE + "[3]");
	}
	
	@Override
	public boolean isAlive() {
		return counter != 0;
	}
	
	@Override
	public void onEnter() {
		
	}

	@Override
	public void onDelete() {
		game.execute(new ExplosionAction(x, y));
	}

	@Override
	public boolean receiveCollision(Player player) {
		return false;
	}
	
	@Override
	public void update() {
		counter--;
		
		if (counter != 0) {
			symbol = (GRENADE + "[" + counter + "]");
		} else {
			symbol = "";
		}
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
		return (symbol + " " + "(" + x + ", " + y + ")" + " " + counter);
	}
	
	@Override
	public String getInfo() {
		return INFO;
	}
}