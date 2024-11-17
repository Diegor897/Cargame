package es.ucm.tp1.supercars.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.utils.StringUtils;
import es.ucm.tp1.supercars.view.GameSerializer;

public class SaveCommand extends Command {
	
	private static final String SAVED_MSG = "Game successfully saved in file ";
	
	private static final String NUM_ARGS = "Incorrect number of arguments";

	private static final String NAME = "save";

	private static final String DETAILS = "sa[v]e <filename>";

	private static final String SHORTCUT = "v";

	private static final String HELP = "Save the state of the game to a file.";
	
	private static String fileName;

	public SaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		BufferedWriter outChars = null;
		
		try {
			outChars = new BufferedWriter(new FileWriter(fileName));

			outChars.write(new GameSerializer(game).toString());
			
			System.out.println(SAVED_MSG + fileName + StringUtils.LINE_SEPARATOR);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (outChars != null) {
				try {
					outChars.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return false;
	}
	
	@Override
	protected Command parse(String[] commandWords) throws CommandParseException {
		String aux;
		
		if (super.matchCommandName(commandWords[0])) {
			if (commandWords.length != 2) {
				throw new CommandParseException(String.format("[ERROR]: %s", NUM_ARGS));
			}
			
			fileName = (commandWords[1] + ".txt");
			
			aux = commandWords[0];
			commandWords = new String[1];
			commandWords[0] = aux;
		}
		
		return super.parse(commandWords);
	}
}