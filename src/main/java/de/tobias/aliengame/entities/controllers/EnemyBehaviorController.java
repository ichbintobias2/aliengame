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
	
	private final int attackingRange = 100;
	private final int walkingRange = 15;
	private final int yTolerance = 50;
	
	private final int attackCooldown = 500;
	private long lastAttack = 0;
	
	public EnemyBehaviorController(Enemy creature) {
		this.creature = creature;
		creature.setFacingDirection(Direction.LEFT); // TODO this is for testing, remove later
	}
	
	private boolean isPlayerInRange(int range) {
		// both have to have the same y coordinate to give this a 3d-ish feel
		if (Player.instance().getY() >= creature.getY() - yTolerance && Player.instance().getY() <= creature.getY() + yTolerance) {
			// check range from the right
			if (creature.getFacingDirection() == Direction.RIGHT && Player.instance().getX() - creature.getX() <= range) {
				return true;
			}
			
			// check range from the leftdd
			if (creature.getFacingDirection() == Direction.LEFT && creature.getX() - Player.instance().getX() <= range) {
				return true;
			}
			
			return false;
		}
		
		return false;
	}
	
	private void attack() {
		creature.animations().play(Animations.SPARTAN_ATTACK_RIGHT);
		creature.getBaseAttackAbility().cast();
	}
	
	private void walkTowardsPlayer() {
		
	}
	
	private void walkRandomly() {
		
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
		if (isPlayerInRange(attackingRange) && canAttack()) {
			attack();
			lastAttack = Game.time().now();
		} else if (isPlayerInRange(walkingRange)) {
			walkTowardsPlayer();
		} else {
			walkRandomly();
		}
	}
}
