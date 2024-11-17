package es.ucm.tp1.supercars.logic;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.exceptions.InputOutputRecordException;
import es.ucm.tp1.supercars.logic.actions.InstantAction;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;
import es.ucm.tp1.supercars.logic.gameobjects.GameObjectContainer;
import es.ucm.tp1.supercars.logic.gameobjects.Player;
import java.util.Random;

import java.util.Scanner;

public class Game {
	
	private static final String FINISH_LINE_SYMBOL = "¦";
	
	private Long seed;
	
	private Level level;
	
	private Random rand;
	
	private GameObjectContainer gameObjects;
	
	private Player player;
	
	private boolean testMode;
	
	private boolean doExit;
	
	private int cycle;
	
	private long initTime;
	
	private long record;
	
	public Game (Long seed, Level level) {
		build(seed, level);
	}
	
	public void reset() {
		System.out.print("New parametres: ");
		
		Scanner scanner = new Scanner(System.in);
		String[] args = scanner.nextLine().split("\\s+");

		this.build(Long.parseLong(args[1]), Level.valueOfIgnoreCase(args[0]));
	}
	
	public void build(Long seed, Level level) {
		
		this.seed = seed;
		this.level = level;
		
		gameObjects = new GameObjectContainer();

		testMode = false;

		initTime = System.currentTimeMillis();
			
		GameObjectGenerator.reset();
		
		rand = new Random(seed);
		
		player = new Player(this, 0, (level.getWidth() / 2));
		
		cycle = 0;
		
		GameObjectGenerator.generateGameObjects(this, level);
		
		try {
			loadRecord();
		} catch (InputOutputRecordException ex) {
			System.out.format(ex.getMessage() + " %n %n");
			doExit = true;
		}
	}
	
	public void clear() {
		gameObjects.clear();
	}
	
	public void updatePlayer() {
		player.update();
	}
	
	public void update() {
		GameObjectGenerator.generateRuntimeObjects(this);
		
		gameObjects.update();
		
		gameObjects.removeDead();

		cycle++;
	}
	
	public int getRoadWidth() {
		return level.getWidth();
	}
	
	public int getRoadLength() {
		return level.getLength();
	}
	
	public int getVisibility() {
		return level.getVisibility();
	}
	
	public boolean isUserExit() {
		return doExit;
	}
	
	public boolean isFinished() {
		boolean result = false;
		
		if (!player.isAlive() || player.hasArrived() || doExit) {
			result = true;
			
			if (player.hasArrived()) {
				try {
					updateRecord();
				} catch (InputOutputRecordException ex) {
					System.out.format(ex.getMessage() + " %n %n");
				}
			}
		}
		
		return result;
	}
	
	public String positionToString(int x, int y) {
		String result = " ";
		int relativeX = (player.getX() + x);
		
		if (player.isInPosition(relativeX, y)) {
			result = player.toString();
			
			if (!gameObjects.isPositionEmpty(relativeX, y)) {
				result += (" " + gameObjects.getPositionToString(relativeX, y));
			}
		} else if (level.getLength() == relativeX) {
			result = FINISH_LINE_SYMBOL;
		} else {
			result = gameObjects.getPositionToString(relativeX, y);
		}
		
		return result;
	}
	
	public long elapsedTime() {
		long result = -1;
		
		if (testMode) {
			result = 0;
		} else {
			result = (System.currentTimeMillis() - initTime);
		}
		
		return result;
	}
	
	private double getRandomNumber() {
		return rand.nextDouble();
	}
	
	public void add(GameObject object) {
		gameObjects.add(object);
	}
	
	public void goUp() {
		player.goUp();
		update();
	}
	
	public void goDown() {
		player.goDown();
		update();
	}
	
	public void setUserExit() {
		doExit = true;
	}
	
	public Collider getColliderInPosition(int x, int y) {
		return gameObjects.getColliderInPosition(x, y);
	}
	
	public boolean isPositionEmpty(int x, int y) {
		return gameObjects.isPositionEmpty(x, y);
	}
	
	public void addCoins(int num) {
		player.addCoins(num);
	}
	
	public int getRandomLane() {
		return (int) (rand.nextDouble() * level.getWidth());
	}
	
	public int getPlayerX() {
		return player.getX();
	}
	
	public int getPlayerY() {
		return player.getY();
	}
	
	public void tryToAddObject(GameObject object, double frequency) {
		if (getRandomNumber() < frequency) {
			if (gameObjects.isPositionEmpty(object.getX(), object.getY())) {
				gameObjects.add(object);
			}
		}
	}
	
	public void forceAddObject(GameObject object) {
		gameObjects.add(object);
	}
	
	public void toggleTest() {
		testMode = !testMode;
	}
	
	public boolean isTestMode() {
		return testMode;
	}
	
	public void clearColumn(int i) {
		gameObjects.clearColumn(i);
	}
	
	public Level getLevel() {
		return level;
	}
	
	public int getCycle() {
		return cycle;
	}
	
	public int playerCoins() {
		return player.getCoins();
	}
	
	public int distanceToGoal() {
		return player.distanceToGoal();
	}
	
	public boolean hasArrived() {
		return player.hasArrived();
	}
	
	public void execute(InstantAction action) {
		action.execute(this);
	}

	public void forceAdvancedObject(int n) {
		GameObjectGenerator.forceAdvancedObject(this, n, this.getLastColumn());
	}
	
	public int getLastColumn() {
		return this.getPlayerX() + this.getVisibility() - 1;
	}
	
	public int getRandomX() {
		return rand.nextInt(this.getVisibility() - 1);
	}
	
	public int getRandomY() {
		return rand.nextInt(level.getWidth());
	}
	
	public String serializePosition(int x, int y) {
		return gameObjects.getObjectInPosition(x, y).serialize();
	}
	
	public void updateRecord() throws InputOutputRecordException {
		if (isNewRecord()) {
			Record.save(this.elapsedTime(), level.toString());
		}
	}
	
	public boolean isNewRecord() {
		return (this.elapsedTime() < record);
	}
	
	public void loadRecord() throws InputOutputRecordException {
		record = Record.load(level.toString());
	}
	
	public long getRecord() {
		return record;
	}
	
	public void removeDead() {
		gameObjects.removeDead();
	}
}