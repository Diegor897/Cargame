package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.utils.StringUtils;

public class ThunderAction implements InstantAction {

	@Override
	public void execute(Game game) {
		int x = game.getRandomX();
		int y = game.getRandomY();
		String s = "Thunder hit position: (" + x + ", " + y + ")";
		
		if (!game.isPositionEmpty(x + game.getPlayerX(), y)) {
			s += " -> " + game.positionToString(x, y) + " hit";
			game.getColliderInPosition(x + game.getPlayerX(), y).receiveExplosion();
		}
		
		s += StringUtils.LINE_SEPARATOR;
		
		System.out.print(s);
	}
}