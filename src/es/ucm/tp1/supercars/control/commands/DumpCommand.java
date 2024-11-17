package es.ucm.tp1.supercars.control.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.ucm.tp1.supercars.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;

public class DumpCommand extends Command {
	
	private static final String NUM_ARGS = "Incorrect number of arguments";

	private static final String NAME = "dump";

	private static final String DETAILS = "[d]ump <filename>";

	private static final String SHORTCUT = "d";

	private static final String HELP = "Shows the content of a saved file";
	
	private static String fileName;

	public DumpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		BufferedReader inChars = null;

		try {
			inChars = new BufferedReader(new FileReader(fileName));

			String l;
			while ((l = inChars.readLine()) != null){
				System.out.println(l);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				inChars.close();
			} catch (IOException ex) {
				ex.printStackTrace();
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