package de.tobias.aliengame.entities;

import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.behavior.IBehaviorController;
import de.tobias.aliengame.entities.controllers.EnemyBehaviorController;

@CombatInfo(team = 1, hitpoints = 40)
public class Enemy extends Spartan {

  public Enemy() {
    super();
    this.setController(IBehaviorController.class, new EnemyBehaviorController(this));
  }
}
