package de.tobias.aliengame.ability;

import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.AbilityInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.tobias.aliengame.effects.ChargeEffect;

@AbilityInfo(name = "Charge", cooldown = 6500, value = 200, duration = 300)
public class ChargeAbility extends Ability {
	
	public ChargeAbility(Creature executor) {
		super(executor);
		
		this.addEffect(new ChargeEffect(this));
	}
}
