package de.tobias.aliengame.ability;

import java.util.List;
import java.util.stream.Collectors;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.AbilityExecution;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.ICombatEntity;
import de.gurkenlabs.litiengine.util.geom.GeometricUtilities;
import de.tobias.aliengame.GameLogic;
import de.tobias.aliengame.effects.HitEffect;
import de.tobias.aliengame.enums.Animations;
import de.tobias.aliengame.enums.Gamestate;

public class BaseAttackAbility extends Ability {
	
	private Creature executor;
	
	private HitEffect hitEffect = new HitEffect(this);
	
	public BaseAttackAbility(Creature executor) {
		super(executor);
		this.executor = executor;
		
		this.getAttributes().impact().setBaseValue(10);
		this.getAttributes().impactAngle().setBaseValue(180);
	}
	
	@Override
	public AbilityExecution cast() {
		GameLogic.setGamestate(Gamestate.LOCKED);
		executor.animations().play(Animations.SPARTAN_ATTACK_RIGHT);
		hitEffect.apply(findAffectedEnemy());
		
		return super.cast();
	}
	
	private ICombatEntity findAffectedEnemy() {
		double dist = 0;
		ICombatEntity closestInRange = null;
		
		this.getAttributes().impact().setBaseValue(10);
		this.getAttributes().impactAngle().setBaseValue(180);
		
		List<ICombatEntity> enemies = Game.world().environment().getCombatEntities().stream()
				.filter(en -> GeometricUtilities.shapeIntersects(en.getHitBox(), this.calculateImpactArea()))
				.collect(Collectors.toList());
		
		for (ICombatEntity enemy : enemies) {
			if (enemy.getClass() != executor.getClass()) { // We do not want enemies or players attacking each other
				double newDist = enemy.getLocation().distance(this.getExecutor().getCenter());
				if (closestInRange == null || newDist < dist) {
					dist = newDist;
					closestInRange = enemy;
				}
			}
		}
		
		return closestInRange;
	}
}
