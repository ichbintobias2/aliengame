package de.tobias.aliengame.constants;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.resources.Resources;

public abstract class Animations {
	
	public static final Animation SPARTAN_DIE = createAnimation("spartan-die-right", false);
	public static final Animation SPARTAN_DEAD = createAnimation("spartan-dead-right", true);
	public static final Animation SPARTAN_ATTACK = createAnimation("spartan-attack-right", false);
	
	private static Animation createAnimation(String name, boolean loop) {
		return new Animation(Resources.spritesheets().get(name), loop);
	}
	
	public static String getDirectedName(Animation animation, Creature creature) {
		return animation.getName() +"-"+ Direction.fromAngle(creature.getAngle()).toString();
	}
}
