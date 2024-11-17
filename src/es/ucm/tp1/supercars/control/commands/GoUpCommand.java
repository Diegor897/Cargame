package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;

public class GoUpCommand extends Command {

	private static final String NAME = "go up";

	private static final String DETAILS = "[q]: go up";

	private static final String SHORTCUT = "q";

	private static final String HELP = "player moves up one position";

	public GoUpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		game.goUp();
		
		return true;
	}
}