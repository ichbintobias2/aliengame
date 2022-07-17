package de.tobias.aliengame.entities;

import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.tobias.aliengame.GameLogic;
import de.tobias.aliengame.ability.BaseAttackAbility;
import de.tobias.aliengame.ability.ChargeAbility;
import de.tobias.aliengame.constants.Animations;
import de.tobias.aliengame.constants.Gamestate;
import lombok.Getter;

@CombatInfo(team = 0, hitpoints = 100)
public class Player extends Creature {
	
	private static Player instance;
	
	@Getter
	private final BaseAttackAbility attackAbility;
	
	@Getter
	private final ChargeAbility chargeAbility;
	
	private boolean movementEnabled = true;
	
	private Player() {
		super("spartan");
		
		attackAbility = new BaseAttackAbility(this);
		chargeAbility = new ChargeAbility(this);
		
		this.addController(new KeyboardEntityController<>(this));
		this.movement().onMovementCheck(e -> movementEnabled && !instance.getAttackAbility().isActive());
		
		this.createAnimationController();
		this.animations().add(Animations.SPARTAN_ATTACK);
	}
	
	public static Player instance() {
		if (instance == null) {
			instance = new Player();
		}
		
		return instance;
	}
	
	// TODO I added this because somehow checking for gamestate in onMovementCheck didn't work, fix later
	public void enableMovement(boolean enabled) {
		movementEnabled = enabled;
	}
}
