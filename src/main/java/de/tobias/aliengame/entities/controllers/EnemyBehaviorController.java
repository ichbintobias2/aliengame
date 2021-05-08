package de.tobias.aliengame.entities.controllers;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.behavior.IBehaviorController;
import de.tobias.aliengame.entities.Enemy;
import de.tobias.aliengame.entities.Player;
import de.tobias.aliengame.enums.Animations;
import lombok.Getter;
import lombok.Setter;

public class EnemyBehaviorController implements IBehaviorController {
	
	private Enemy creature;
	
	@Getter @Setter
	private boolean active = false;
	
	private final int attackingRange = 30;
	private final int walkingRange = 80;
	private final int yTolerance = 45;
	
	private final int attackCooldown = 500;
	private long lastAttack = 0;
	
	public EnemyBehaviorController(Enemy creature) {
		this.creature = creature;
	}
	
	private boolean isPlayerInRange(int range) {
		// both need to have about the same y coordinate to give this a 3d-ish feel
		if (Player.instance().getY() >= (creature.getY() - yTolerance) && Player.instance().getY() <= (creature.getY() + yTolerance)) {
			// check range from the right
			double rightDistance = Player.instance().getX() - creature.getX();
			if (rightDistance > 0 && rightDistance <= range) {
				creature.setFacingDirection(Direction.RIGHT);
				return true;
			}
			
			// check range from the left
			double leftDistance = creature.getX() - Player.instance().getX();
			if (leftDistance > 0 && leftDistance <= range) {
				creature.setFacingDirection(Direction.LEFT);
				return true;
			}
		}
		
		return false;
	}
	
	private void attack() {
		creature.animations().play(Animations.SPARTAN_ATTACK_RIGHT);
		creature.getBaseAttackAbility().cast();
	}
	
	private void walkTowardsPlayer() {
		double playerY = Player.instance().getY();
		double creatureY = creature.getY();
		if (playerY > creatureY) {
			Game.physics().move(creature, -90, creature.getTickVelocity());
		} else {
			Game.physics().move(creature, 90, creature.getTickVelocity());
		}
		
		Game.physics().move(creature, this.getEntity().getAngle(), creature.getTickVelocity());
	}
	
	private void walkRandomly() {
		Game.physics().move(creature, this.getEntity().getAngle(), creature.getTickVelocity());
	}
	
	private boolean canAttack() {
		if (Game.time().since(lastAttack) > attackCooldown) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public IEntity getEntity() {
		return creature;
	}
	
	@Override
	public void update() {
		if (!creature.isDead() && !creature.getBaseAttackAbility().isExecuting()) {
			if (isPlayerInRange(attackingRange) && canAttack() && !Player.instance().isDead()) {
				attack();
				lastAttack = Game.time().now();
			} else if (isPlayerInRange(walkingRange)) {
				walkTowardsPlayer();
			} else {
				walkRandomly();
			}
		}
	}
}
