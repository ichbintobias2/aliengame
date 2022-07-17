package de.tobias.aliengame;

import java.util.Arrays;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.tobias.aliengame.constants.Gamestate;
import de.tobias.aliengame.entities.Enemy;
import de.tobias.aliengame.entities.Player;
import lombok.Getter;
import lombok.Setter;

public class GameLogic {
	
	public static String START_LEVEL = "ingame";
	public static boolean p2 = false;
	
	@Getter @Setter
	private static Gamestate gamestate;
	
	private static final List<String> spritesheets = Arrays.asList("spartan", "knight");
	private static int spritesheetIterator = 0;
	private static int spritesheetIteratorP2 = 0;
	
	public static void init() {
		Camera camera = new PositionLockCamera(Player.instance());
		camera.setClampToMap(true);
		camera.setZoom(1.5f, 0);
		Game.world().setCamera(camera);
		
		Game.world().onLoaded(e -> {
			// spawn the player instance on the spawn point with the name "spawn"
			Spawnpoint spawnP1 = e.getSpawnpoint("spawnP1");
			Spawnpoint spawnP2 = e.getSpawnpoint("spawnP2");
			
			if (spawnP1 != null) {
				spawnP1.spawn(Player.instance());
			}
			
			if (spawnP2 != null && p2) {
				spawnP2.spawn(Player.instanceP2());
			}
			
			if (getGamestate() == Gamestate.INGAME) {
				// TODO placing one enemy for testing, remove this later on
				Enemy newEnemy = new Enemy();
				newEnemy.setLocation(150, 50);
				Game.world().environment().add(newEnemy);
			}
		});
	}
	
	public static void switchPlayerSprite(int increase) {
		if (getGamestate() != Gamestate.SELECTION) {
			return;
		}
		
		Player.instance().setSpritesheetName(spritesheets.get(spritesheetIterator));
		spritesheetIterator = spritesheetIterator + increase;
		
		if (spritesheetIterator >= spritesheets.size()) {
			// Get back to the first option if you reach the end of selection
			spritesheetIterator = 0;
		} else if (spritesheetIterator < 0) {
			// Get back to the last option if you reach the end of selection
			spritesheetIterator = spritesheets.size() - 1;
		}
	}
	
	public static void switchPlayerSpriteP2(int increase) {
		if (getGamestate() != Gamestate.SELECTION) {
			return;
		}
		
		Player.instanceP2().setSpritesheetName(spritesheets.get(spritesheetIteratorP2));
		spritesheetIteratorP2 = spritesheetIteratorP2 + increase;
		
		if (spritesheetIteratorP2 >= spritesheets.size()) {
			// Get back to the first option if you reach the end of selection
			spritesheetIteratorP2 = 0;
		} else if (spritesheetIteratorP2 < 0) {
			// Get back to the last option if you reach the end of selection
			spritesheetIteratorP2 = spritesheets.size() - 1;
		}
	}
	
	public static void choosePlayerAndStart() {
		if (getGamestate() != Gamestate.SELECTION) {
			return;
		}
		
		// Game.window().getRenderComponent().fadeOut(1500);
		
		Game.loop().perform(1000, () -> {
			Game.screens().display("INGAME");
			Game.world().loadEnvironment(GameLogic.START_LEVEL);
			GameLogic.setGamestate(Gamestate.INGAME);
			Player.instance().enableMovement(true);
		});
	}
}
