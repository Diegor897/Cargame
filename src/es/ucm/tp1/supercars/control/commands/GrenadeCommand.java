package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.exceptions.InvalidPositionException;
import es.ucm.tp1.supercars.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.Grenade;

public class GrenadeCommand extends Command implements Buyable {
	
	private static final String NUM_ARGS = "Incorrect number of arguments";
	
	private static final String INVALID_POS = "Position is not valid";
	
	private static final String NO_NUMBER_MSG = "Expected integer in command argument";

	private static final String NAME = "grenade";

	private static final String DETAILS = "[g]renade <x> <y>";

	private static final String SHORTCUT = "g";

	private static final String HELP = "add a grenade in position x, y";
	
	private int x, y;
	
	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			if (!game.isPositionEmpty(x + game.getPlayerX(), y) || ((x < 4) && (x > game.getVisibility())) || ((y < 0) && (y > game.getRoadWidth()))) {
				throw new InvalidPositionException(String.format("[ERROR]: %s", INVALID_POS));
			}
			
			buy(game);
			
			game.forceAddObject(new Grenade(game, x + game.getPlayerX(), y));
			game.update();
			
		} catch (InvalidPositionException ex) {
			throw new CommandExecuteException(ex.getMessage());
		} catch (NotEnoughCoinsException ex) {
			throw new CommandExecuteException(ex.getMessage());
		}
		
		return true;
	}
	
	@Override
	protected Command parse(String[] commandWords) throws CommandParseException {
		String aux;
		
		if (super.matchCommandName(commandWords[0])) {
			if (commandWords.length != 3) {
				throw new CommandParseException(String.format("[ERROR]: %s", NUM_ARGS));
			} else {
				try {
					x = Integer.parseInt(commandWords[1]);
					y = Integer.parseInt(commandWords[2]);
					
					aux = commandWords[0];
					commandWords = new String[1];
					commandWords[0] = aux;
	
				} catch (NumberFormatException ex) {
					throw new CommandParseException(String.format("[ERROR]: %s", NO_NUMBER_MSG));
				}
			}
		}
		
		return super.parse(commandWords);
	}

	@Override
	public int cost() {
		return 3;
	}
}