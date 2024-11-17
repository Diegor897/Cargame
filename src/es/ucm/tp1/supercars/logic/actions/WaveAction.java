package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class WaveAction implements InstantAction {

	@Override
	public void execute(Game game) {
		int i = (game.getLastColumn() - 1);
		
		while (i >= game.getPlayerX()) {
			for (int j = 0; j < game.getRoadWidth(); j++) {
				if (!game.isPositionEmpty(i, j) && game.isPositionEmpty(i, j + 1)) {
					game.getColliderInPosition(i, j).receiveWave();
				}
			}
			
			i++;
		}
		
		game.update();
	}
}