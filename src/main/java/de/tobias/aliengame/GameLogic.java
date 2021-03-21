package de.tobias.aliengame;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.tobias.aliengame.entities.Player;
import de.tobias.aliengame.enums.Gamestate;
import lombok.Getter;
import lombok.Setter;

public class GameLogic {
	
	public static String START_LEVEL = "ingame";
	
	@Getter @Setter
	private static Gamestate gamestate;
	
	public static void init() {
		// Register Objects here
		// CreatureMapObjectLoader.registerCustomCreatureType(NPC.class);
		
		// Register Props here
		// PropMapObjectLoader.registerCustomPropType(HealthPot.class);
		
		Camera camera = new PositionLockCamera(Player.instance());
		camera.setClampToMap(true);
		camera.setZoom(1.5f, 0);
		Game.world().setCamera(camera);
		
		Game.world().onLoaded(e -> {
			for (Prop prop : Game.world().environment().getProps()) {
				prop.setIndestructible(true);
			}
			
			Game.loop().perform(500, () -> Game.window().getRenderComponent().fadeIn(500));
			
			// spawn the player instance on the spawn point with the name "spawn"
			Spawnpoint enter = e.getSpawnpoint("spawn");
			
			if (enter != null) {
				enter.spawn(Player.instance());
			}
		});
	}
}
