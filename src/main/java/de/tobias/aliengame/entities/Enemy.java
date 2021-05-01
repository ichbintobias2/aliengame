package de.tobias.aliengame.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.behavior.IBehaviorController;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.resources.Resources;
import de.tobias.aliengame.ability.BaseAttackAbility;
import de.tobias.aliengame.entities.controllers.EnemyBehaviorController;
import de.tobias.aliengame.enums.Animations;
import lombok.Getter;

@EntityInfo(width = 48, height = 48)
@AnimationInfo(spritePrefix = "spartan")
public class Enemy extends Creature {
	
	@Getter
	private BaseAttackAbility baseAttackAbility;
	
	public Enemy(double x, double y) {
		super();
		this.setX(x);
		this.setY(y);
		
		baseAttackAbility = new BaseAttackAbility(this);
		this.setController(IBehaviorController.class, new EnemyBehaviorController(this));
		
		this.createAnimationController();
		this.animations().add(new Animation(Resources.spritesheets().get(Animations.SPARTAN_ATTACK_RIGHT), false));
		this.animations().add(new Animation(Resources.spritesheets().get(Animations.SPARTAN_DEATH), false));
		
		// the starting health of a normal enemy
		this.getHitPoints().setBaseValue(40);
		
		this.onDeath(l -> {
			this.animations().play(Animations.SPARTAN_DEATH);
			int removeDelay = this.animations().get(Animations.SPARTAN_DEATH).getTotalDuration();
			Game.loop().perform(removeDelay, () -> {
			      Game.world().environment().remove(this);
			});
		});
	}
}
