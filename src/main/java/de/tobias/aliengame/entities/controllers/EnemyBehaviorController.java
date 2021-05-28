package de.tobias.aliengame.entities.controllers;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.behavior.StateController;
import de.gurkenlabs.litiengine.util.geom.GeometricUtilities;
import de.tobias.aliengame.entities.Player;
import de.tobias.aliengame.entities.Spartan;
import lombok.Getter;
import lombok.Setter;

public class EnemyBehaviorController extends StateController<Spartan> {

  @Getter @Setter private boolean active = false;

  private final int walkingRange = 200;

  private final int attackCooldown = 500;
  private long lastAttack = 0;

  public EnemyBehaviorController(Spartan spartan) {
    super(spartan);
  }

  private boolean isPlayerInRange(int range) {
    return GeometricUtilities.distance(
            Player.instance().getCollisionBoxCenter(), getEntity().getCollisionBoxCenter())
        < range;
  }

  private void attack() {
    getEntity().getAttackAbility().cast();
    lastAttack = Game.time().now();
  }

  private void walkTowardsPlayer() {
    double playerY = Player.instance().getY();
    double creatureY = getEntity().getY();
    if (playerY > creatureY) {
      Game.physics().move(getEntity(), -90, getEntity().getTickVelocity());
    } else {
      Game.physics().move(getEntity(), 90, getEntity().getTickVelocity());
    }

    Game.physics().move(getEntity(), this.getEntity().getAngle(), getEntity().getTickVelocity());
  }

  private void walkRandomly() {
    Game.physics().move(getEntity(), this.getEntity().getAngle(), getEntity().getTickVelocity());
  }

  private boolean canAttack() {
    return Game.time().since(lastAttack) > attackCooldown;
  }

  @Override
  public void update() {
    if (!getEntity().isDead() && !getEntity().getAttackAbility().isActive()) {
      if (isPlayerInRange(getEntity().getAttackAbility().getAttributes().impact().get())
          && canAttack()
          && !Player.instance().isDead()) {
        attack();
      } else if (isPlayerInRange(walkingRange)) {
        walkTowardsPlayer();
      } else {
        walkRandomly();
      }
    }
  }
}
