package es.ucm.tp1.supercars.control;

import es.ucm.tp1.supercars.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.supercars.logic.Game;

public interface Buyable {
	
	public static final String NO_COINS = "Not enough coins";

	public int cost();
	
	public default boolean buy(Game game) throws CommandExecuteException {
		boolean result = true;
		
		if (game.playerCoins() < cost()) {
			throw new NotEnoughCoinsException(String.format("[ERROR]: %s", NO_COINS));
		} else {
			game.addCoins(-cost());
		}
		
		return result;
	};
}