package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Game;

public class ExplosionAction implements InstantAction {
	
	private int x, y;
	
	public ExplosionAction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void execute(Game game) {
		for (int i = (x - 1); i < (x + 1); i++) {
			for (int j = (y - 1); j < (y + 1); j++) {
				if (!game.isPositionEmpty(i, j) && ((i != x) && (j != y))) {
					game.getColliderInPosition(i, j).receiveExplosion();
				}
			}
		}
	}
}