package es.ucm.tp1.supercars.view;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.utils.StringUtils;

public class GameSerializer {
	
	private Game game;
	
	private Level currentLevel;
	
	private static final String SERIALIZE_MSG = "---- ROAD FIGHTER SERIALIZED ----";
	
	private static final String INFO_MSG = "Game Objects:";
	
	private static final String LEVEL_MSG = "Level: ";

	private static final String COINS_MSG = "Coins: ";

	private static final String CYCLE_MSG = "Cycle: ";

	private static final String ELAPSED_TIME_MSG = "Elapsed Time: ";
	
	public GameSerializer(Game game) {
		this.game = game;
		this.currentLevel = game.getLevel();
	}

	private static String formatTime(long t) {
		return String.format("%.2f s", t / 1000.0);
	}
	
	protected String getInfo() {
		StringBuilder buffer = new StringBuilder();
		/* @formatter:off */
		buffer
		.append(SERIALIZE_MSG).append(StringUtils.LINE_SEPARATOR)
		.append(LEVEL_MSG).append(currentLevel).append(StringUtils.LINE_SEPARATOR)
		.append(COINS_MSG).append(game.playerCoins()).append(StringUtils.LINE_SEPARATOR)
		.append(CYCLE_MSG).append(game.getCycle()).append(StringUtils.LINE_SEPARATOR);
		/* @formatter:on */

		if (!game.isTestMode()) {
			/* @formatter:off */
			buffer
			.append(ELAPSED_TIME_MSG).append(formatTime(game.elapsedTime())).append(StringUtils.LINE_SEPARATOR);
			/* @formatter:on */
		}
		
		buffer.append(INFO_MSG).append(StringUtils.LINE_SEPARATOR);

		return buffer.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		// Game Status

		str.append(getInfo());

		// Paint game
		
		for (int x = 0; x < game.getRoadLength(); x++) {
			for (int y = 0; y < game.getRoadWidth(); y++) {
				if (!game.isPositionEmpty(x, y)) {
					str.append(game.serializePosition(x, y)).append(StringUtils.LINE_SEPARATOR);
				}
			}
		}

		return str.toString();
	}
}