package de.tobias.aliengame.entities;

import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.behavior.IBehaviorController;
import de.tobias.aliengame.ability.BaseAttackAbility;
import de.tobias.aliengame.constants.Animations;
import de.tobias.aliengame.entities.controllers.EnemyBehaviorController;
import lombok.Getter;

@CombatInfo(team = 1, hitpoints = 40)
@AnimationInfo(spritePrefix = "spartan")
public class Enemy extends Creature {
	
	@Getter
	private final BaseAttackAbility attackAbility;
	
	public Enemy() {
		super();
		
		attackAbility = new BaseAttackAbility(this);
		
		this.createAnimationController();
		this.animations().add(Animations.SPARTAN_ATTACK);
		this.setController(IBehaviorController.class, new EnemyBehaviorController(this));
	}
}
