package de.tobias.aliengame.entities;

import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.tobias.aliengame.ability.ChargeAbility;
import java.awt.event.KeyEvent;
import lombok.Getter;

@CombatInfo(team = 0, hitpoints = 100)
public class Player extends Spartan {

  private static Player instance;

  @Getter private final ChargeAbility chargeAbility;

  private Player() {
    super();

    chargeAbility = new ChargeAbility(this);

    KeyboardEntityController<Player> movementController = new KeyboardEntityController<>(this);
    movementController.addUpKey(KeyEvent.VK_UP);
    movementController.addDownKey(KeyEvent.VK_DOWN);
    movementController.addLeftKey(KeyEvent.VK_LEFT);
    movementController.addRightKey(KeyEvent.VK_RIGHT);

    this.setController(IMovementController.class, movementController);

    this.movement().onMovementCheck(e -> !instance.getAttackAbility().isActive());
  }

  public static Player instance() {
    if (instance == null) {
      instance = new Player();
    }

    return instance;
  }
}
