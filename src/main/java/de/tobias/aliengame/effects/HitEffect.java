package de.tobias.aliengame.effects;

import java.awt.Color;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.effects.Effect;
import de.gurkenlabs.litiengine.abilities.effects.EffectTarget;
import de.gurkenlabs.litiengine.entities.ICombatEntity;
import de.gurkenlabs.litiengine.graphics.OverlayPixelsImageEffect;

public class HitEffect extends Effect {
	
	public HitEffect(Ability ability) {
		super(ability, EffectTarget.ENEMY);
	}
	
	@Override
	public void apply(final ICombatEntity affectedEntity) {
		if (affectedEntity == null || affectedEntity.isIndestructible()) {
			return;
		}
		
		final int damage = this.getAbility().getAttributes().value().get();
		super.apply(affectedEntity);
		
		affectedEntity.hit(damage, this.getAbility());
		
		if (!affectedEntity.isDead()) {
			affectedEntity.animations().add(new OverlayPixelsImageEffect(120, new Color(255, 255, 255, 200)));
			Game.loop().perform(130, () -> affectedEntity.animations()
					.add(new OverlayPixelsImageEffect(120, new Color(167, 16, 16, 170))));
		}
	}
}
