package de.tobias.aliengame.effects;

import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.effects.Effect;
import de.gurkenlabs.litiengine.abilities.effects.EffectTarget;
import de.gurkenlabs.litiengine.entities.ICombatEntity;
import de.tobias.aliengame.GameLogic;
import de.tobias.aliengame.constants.Animations;
import de.tobias.aliengame.constants.Gamestate;

public class AttackEffect extends Effect {
	
	public AttackEffect(Ability ability) {
		super(ability, EffectTarget.EXECUTINGENTITY);
	}
	
	@Override
	protected void apply(ICombatEntity entity) {
		super.apply(entity);
		GameLogic.setGamestate(Gamestate.LOCKED);
		entity.animations().play(Animations.SPARTAN_ATTACK.getName());
	}
}
