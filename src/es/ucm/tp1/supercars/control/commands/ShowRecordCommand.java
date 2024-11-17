package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;

public class ShowRecordCommand extends Command {

	private static final String NAME = "record";

	private static final String DETAILS = "rec[o]rd";

	private static final String SHORTCUT = "o";

	private static final String HELP = "show level record";

	public ShowRecordCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(game.getLevel().toString() + " record is " + String.format("%.2f s", game.getRecord() / 1000.0));
		
		return false;
	}
}