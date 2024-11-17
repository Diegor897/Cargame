package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class ShootAction implements InstantAction {

	@Override
	public void execute(Game game) {
		boolean notShot = true;
		int i = game.getPlayerX();
		
		while ((i < game.getLastColumn()) && notShot) {
			if (!game.isPositionEmpty(i, game.getPlayerY())) {
				notShot = !game.getColliderInPosition(i, game.getPlayerY()).receiveShoot();
			}
			
			i++;
		}
		
		game.removeDead();
		game.updatePlayer();
		game.update();
	}
}