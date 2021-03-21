package de.tobias.aliengame.entities;

import java.awt.event.KeyEvent;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.resources.Resources;
import de.tobias.aliengame.ability.BaseAttackAbility;
import de.tobias.aliengame.ability.ChargeAbility;
import de.tobias.aliengame.enums.Animations;
import lombok.Getter;

@EntityInfo(width = 48, height = 48)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 48, collisionBoxHeight = 48, collision = true)
public class Player extends Creature implements IUpdateable {
	
	private static Player instance;
	
	@Getter
	private final BaseAttackAbility attackAbility;
	
	@Getter
	private final ChargeAbility chargeAbility;
	
	private Player() {
		super("src\\main\\resources\\sprites\\player");
		
		this.animations().add(new Animation(Resources.spritesheets().get(Animations.PLAYER_ATTACK_RIGHT), false));
		attackAbility = new BaseAttackAbility(this);
		chargeAbility = new ChargeAbility(this);
		
		KeyboardEntityController<Player> movementController = new KeyboardEntityController<>(this);
	    movementController.addUpKey(KeyEvent.VK_UP);
	    movementController.addDownKey(KeyEvent.VK_DOWN);
	    movementController.addLeftKey(KeyEvent.VK_LEFT);
	    movementController.addRightKey(KeyEvent.VK_RIGHT);
	    
		this.setController(IMovementController.class, movementController);
		this.getController(IMovementController.class).onMovementCheck(e -> {
		      return true; // return GameLogic.getGamestate() == Gamestate.INGAME;
		});
	}
	
	public static Player instance() {
		if (instance == null) {
			instance = new Player();
		}
		
		return instance;
	}
	
	@Override
	public void update() {
		
	}
}
