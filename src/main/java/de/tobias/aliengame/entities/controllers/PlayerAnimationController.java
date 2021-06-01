package de.tobias.aliengame.entities.controllers;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.CreatureAnimationController;
import de.gurkenlabs.litiengine.resources.Resources;
import de.tobias.aliengame.entities.Player;

public class PlayerAnimationController extends CreatureAnimationController<Player> {
	
	private Creature creature;
	private String defaultAnimation;
	private Direction lastDirection = Direction.RIGHT;
	
	public PlayerAnimationController(Player creature, String animationName) {
		super(creature, new Animation(Resources.spritesheets().get(animationName + "-walk-right"), true));
		this.creature = creature;
		this.defaultAnimation = animationName;
	}
	
	@Override
	public String getCurrentAnimationName() {
		if (creature.getFacingDirection() == Direction.RIGHT) {
			lastDirection = Direction.RIGHT;
		} else if (creature.getFacingDirection() == Direction.LEFT) {
			lastDirection = Direction.LEFT;
		}
		
		if (creature.isIdle()) {
			return defaultAnimation + "-idle-" + lastDirection;
		} else {
			return defaultAnimation + "-walk-" + lastDirection;
		}
	}
}