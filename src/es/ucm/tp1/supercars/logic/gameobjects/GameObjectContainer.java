package es.ucm.tp1.supercars.logic.gameobjects;

import java.util.ArrayList;
import java.util.List;

import es.ucm.tp1.supercars.logic.Collider;

public class GameObjectContainer {
	
	private List<GameObject> gameObjects;
	
	public GameObjectContainer() {
		
		gameObjects = new ArrayList<>();
	
	}
	
	public void add(GameObject object) {
		object.onEnter();
		gameObjects.add(object);
	}
	
	public GameObject getObjectInPosition(int x, int y) {
		GameObject result = null;
		
		for (GameObject aux : gameObjects) {
			if (aux.isInPosition(x, y)) {
				result = aux;
			}
		}
		
		return result;
	}
	
	public boolean isPositionEmpty(int x, int y) {
		boolean result = true;
		
		for (GameObject aux : gameObjects) {
			if (result) {
				result = !aux.isInPosition(x, y);
			}
		}
		
		return result;
	}
	
	public String getPositionToString(int x, int y) {
		String result = "";
		
		for (GameObject aux : gameObjects) {
			if (aux.isInPosition(x, y)) {
				if (result != "") {
					result += (" " + aux.toString());
				} else {
					result = aux.toString();
				}
			}
		}
		
		return result;
	}
	
	public Collider getColliderInPosition(int x, int y) {
		Collider result = null;
		
		for (GameObject aux : gameObjects) {
			if (aux.isInPosition(x, y)) {
				result = aux;
			}
		}
		
		return result;
	}
	
	public void clear() {
		gameObjects.clear();
	}
	
	public void removeDead() {
		List<GameObject> auxList = new ArrayList<>();
		
		for (GameObject aux : gameObjects) {
			if (!aux.isAlive()) {
				auxList.add(aux);
			}
		}
		
		for (GameObject aux : auxList) {
			aux.onDelete();
			gameObjects.remove(aux);
		}
	}
	
	public void clearColumn(int i) {
		if (!gameObjects.isEmpty()) {
			gameObjects.remove(i);
		}
	}
	
	
	public void update() {
		for (GameObject aux : gameObjects) {
			aux.update();
		}
	}
}