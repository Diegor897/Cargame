package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.*;

public class InfoCommand extends Command {

	private static final String NAME = "info";

	private static final String DETAILS = "[i]nfo";

	private static final String SHORTCUT = "i";

	private static final String HELP = "prints gameobject info";
	
	private static final GameObject[] AVAILABLE_OBJECTS = {
		new Coin(null, 0, 0),
		new Grenade(null, 0, 0),
		new Obstacle(null, 0, 0),
		new Pedestrian(null, 0, 0),
		new Player(null, 0, 0),
		new SuperCoin(null, 0, 0),
		new Truck(null, 0, 0),
		new Turbo(null, 0, 0),
		new Wall(null, 0, 0)
	};

	public InfoCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println("Available objects:");
		
		for (GameObject aux : AVAILABLE_OBJECTS) {
			System.out.println(aux.getInfo());
		}
		
		return false;
	}
}