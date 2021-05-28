package de.tobias.aliengame;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.tobias.aliengame.entities.Enemy;
import de.tobias.aliengame.entities.Player;
import de.tobias.aliengame.enums.Gamestate;
import lombok.Getter;
import lombok.Setter;

public class GameLogic {
  private GameLogic() {}

  public static String START_LEVEL = "ingame";

  @Getter @Setter private static Gamestate gamestate;

  public static void init() {
    // Register Objects here
    // CreatureMapObjectLoader.registerCustomCreatureType(NPC.class);

    // Register Props here
    // PropMapObjectLoader.registerCustomPropType(HealthPot.class);

    Camera camera = new PositionLockCamera(Player.instance());
    camera.setClampToMap(true);
    camera.setZoom(1.5f, 0);
    Game.world().setCamera(camera);

    Game.world()
        .onLoaded(
            e -> {
              for (Prop prop : Game.world().environment().getProps()) {
                prop.setIndestructible(true);
              }

              // spawn the player instance on the spawn point with the name "spawn"
              Spawnpoint enter = e.getSpawnpoint("spawn");

              if (enter != null) {
                enter.spawn(Player.instance());
              }

              // TODO placing one enemy for testing, remove this later on
              Enemy newEnemy = new Enemy();
              newEnemy.setLocation(150, 50);
              Game.world().environment().add(newEnemy);
            });
  }
}
