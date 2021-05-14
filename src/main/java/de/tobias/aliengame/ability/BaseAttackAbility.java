package de.tobias.aliengame.ability;

import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.AbilityInfo;
import de.gurkenlabs.litiengine.entities.EntityPivotType;
import de.tobias.aliengame.effects.AttackEffect;
import de.tobias.aliengame.effects.HitEffect;
import de.tobias.aliengame.entities.Spartan;

@AbilityInfo(
    impact = 60,
    value = 10,
    impactAngle = 100,
    duration = 1000,
    pivotOffsetY = -7)
public class BaseAttackAbility extends Ability {

  public BaseAttackAbility(Spartan executor) {
    super(executor);
    this.addEffect(new HitEffect(this));
    this.addEffect(new AttackEffect(this));
  }
}
