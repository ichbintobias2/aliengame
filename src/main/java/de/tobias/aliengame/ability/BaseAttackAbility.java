package de.tobias.aliengame.ability;

import java.util.List;
import java.util.stream.Collectors;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.AbilityExecution;
import de.gurkenlabs.litiengine.entities.ICombatEntity;
import de.gurkenlabs.litiengine.util.geom.GeometricUtilities;
import de.tobias.aliengame.GameLogic;
import de.tobias.aliengame.effects.HitEffect;
import de.tobias.aliengame.entities.Player;
import de.tobias.aliengame.enums.Animations;
import de.tobias.aliengame.enums.Gamestate;

public class BaseAttackAbility extends Ability {
	
	private Player executor;
	
	private HitEffect hitEffect = new HitEffect(this);
	
	public BaseAttackAbility(Player executor) {
		super(executor);
		this.executor = executor;
	}
	
	@Override
	public AbilityExecution cast() {
		GameLogic.setGamestate(Gamestate.LOCKED);
		executor.animations().play(Animations.PLAYER_ATTACK_RIGHT);
		hitEffect.apply(findAffectedEnemy());

		return super.cast();
	}
	
	private ICombatEntity findAffectedEnemy() {
		double dist = 0;
		ICombatEntity closestInRange = null;
		
		List<ICombatEntity> enemies = Game.world().environment().getCombatEntities().stream()
				.filter(en -> GeometricUtilities.shapeIntersects(en.getHitBox(), this.calculateImpactArea()))
				.collect(Collectors.toList());
		
		for (ICombatEntity enemy : enemies) {
			double newDist = enemy.getLocation().distance(this.getExecutor().getCenter());
			if (closestInRange == null || newDist < dist) {
				dist = newDist;
				closestInRange = enemy;
			}
		}
		
		return closestInRange;
	}
}
