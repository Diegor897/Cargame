//package es.ucm.tp1.supercars.control;
//
//import java.util.Scanner;
//
//import es.ucm.tp1.supercars.logic.Game;
//
//public class Controller {
//
//	private static final String PROMPT = "Command > ";
//
//	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";
//
//	/* @formatter:off */
//	private static final String[] HELP = new String[] {
//		"Available commands:",
//		"[h]elp: show this help",
//		"[i]nfo: prints gameobjet info",
//		"[n]one | []: update",
//		"[q]: go up",
//		"[a]: go down",
//		"[e]xit: exit game",
//		"[r]eset: reset game",
//		"[t]est: enables test mode",	
//	};
//	/* @formatter:off */
//
//	private Game game;
//
//	private Scanner scanner;
//
//	public Controller(Game game, Scanner scanner) {
//		this.game = game;
//		this.scanner = scanner;
//	}
//
//	public void printGame() {
//		System.out.println(game);
//	}
//
//	public void run() {
//		boolean refreshDisplay = true;
//
//		while (!game.isFinished()) {
//			if (refreshDisplay) {
//				printGame();
//			}
//			/* Update Game */
//			System.out.println(PROMPT);
//			String line = scanner.nextLine();
//			String[] words = line.toLowerCase().trim().split("\\s+");
//			
//			System.out.println("[DEBUG] Executing: " + line);
//			if (words.length == 0) {
//				System.out.println(String.format("[ERROR]: %s", UNKNOWN_COMMAND_MSG));
//			} else {
//				switch (words[0]) {
//				case "h":
//				case "help":
//					System.out.println(String.join("\n", HELP));
//					refreshDisplay = false;
//					
//					break;
//				case "i":
//				case "info":
//					System.out.println(game.getInfo());
//					refreshDisplay = false;
//					
//					break;
//				case "n":
//				case "none":
//				case "":
//					game.update();
//					refreshDisplay = true;
//					
//					break;
//				case "q":
//				case "up":
//					game.goUp();
//					refreshDisplay = true;
//					
//					break;
//				case "a":
//				case "down":
//					game.goDown();
//					refreshDisplay = true;
//					
//					break;
//				case "e":
//				case "exit":
//					game.setUserExit();
//					refreshDisplay = false;
//					
//					break;
//				case "r":
//				case "reset":
//					game.reset();
//					refreshDisplay = true;
//					
//					break;
//				case "t":
//				case "test":
//					game.toggleTest();
//					refreshDisplay = false;
//					
//					break;
//				default:
//					System.out.println(UNKNOWN_COMMAND_MSG);
//					refreshDisplay = false;
//					
//					break;
//				}
//			} 
//		}
//		
//		printGame();
//		System.out.println("[GAME OVER] " + game.getEndGameMessage());
//	}
//
//}

package es.ucm.tp1.supercars.control;

import java.util.Scanner;

import es.ucm.tp1.supercars.control.commands.Command;
import es.ucm.tp1.supercars.exceptions.GameException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.view.GamePrinter;

public class Controller {

	private static final String PROMPT = "Command > ";

	private static final String DEBUG_MSG = "[DEBUG] Executing: %s%n";
	
	private Game game;

	private Scanner scanner;

	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;

		this.printer = new GamePrinter(game);
	}

	public void printGame() {
		System.out.println(printer);
	}

	public void printEndMessage() {
		System.out.println(printer.endMessage());
	}

	public void run() {
		boolean refreshDisplay = true;

		while (!game.isFinished()) {
			if (refreshDisplay) {
				printGame();
			}
			refreshDisplay = false;

			System.out.println(PROMPT);
			String s = scanner.nextLine();

			String[] parameters = s.toLowerCase().trim().split(" ");
			System.out.format(DEBUG_MSG, s);
			
			try {
				Command command = Command.getCommand(parameters);
				refreshDisplay = command.execute(game);
			} catch (GameException ex) {
				System.out.format(ex.getMessage() + " %n %n");
			}
		}
		if (refreshDisplay) {
			printGame();
		}
		printEndMessage();

	}
}