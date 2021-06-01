package de.tobias.aliengame.ability;

import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.AbilityInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.tobias.aliengame.effects.AttackEffect;
import de.tobias.aliengame.effects.HitEffect;

@AbilityInfo(impact = 60, value = 10, impactAngle = 100, duration = 800, cooldown = 1200, pivotOffsetY = -7)
public class BaseAttackAbility extends Ability {
	
	public BaseAttackAbility(Creature executor) {
		super(executor);
		this.addEffect(new HitEffect(this));
		this.addEffect(new AttackEffect(this));
	}
}
