package de.tobias.aliengame.ability;

import de.tobias.aliengame.GameLogic;
import de.tobias.aliengame.entities.Player;
import de.tobias.aliengame.enums.Animations;
import de.tobias.aliengame.enums.Gamestate;

public class BaseAttackAbility {
	
	private Player executor;
	
	public BaseAttackAbility(Player executor) {
		this.executor = executor;
	}
	
	public void cast() {
		GameLogic.setGamestate(Gamestate.LOCKED);
		executor.animations().play(Animations.PLAYER_ATTACK_RIGHT);
	}
}
