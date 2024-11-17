package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;

public class CheatCommand extends Command {
	
	private static final String NO_NUMBER_MSG = "Expected integer in command argument in CheatCommand";

	private static final String NAME = "cheat";

	private static final String DETAILS = "Cheat [1..5]";

	private static final String SHORTCUT = "1";

	private static final String HELP = "Removes all elements of last visible column, and adds an Adavanced Object";

	private static final int[] SHORTCUTS = {1, 2, 3, 4, 5};
	
	private static int number;
	
	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		game.clearColumn(game.getLastColumn());
		game.forceAdvancedObject(number);
		
		return true;
	}
	
	@Override
	protected Command parse(String[] commandWords) throws CommandParseException {
		for (int n : SHORTCUTS) {
			try {
				if (Integer.parseInt(commandWords[0]) == n) {
					commandWords[0] = SHORTCUT;
					number = Integer.parseInt(commandWords[0]);
				}
				
			} catch (NumberFormatException ex) {

			}
		}
		
		return super.parse(commandWords);
	}
}