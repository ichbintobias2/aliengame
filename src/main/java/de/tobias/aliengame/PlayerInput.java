package de.tobias.aliengame;

import de.gurkenlabs.litiengine.input.Input;
import de.tobias.aliengame.entities.Player;
import java.awt.event.KeyEvent;

public final class PlayerInput {
	
	public static void init() {
		Input.keyboard().onKeyReleased(KeyEvent.VK_E, e -> Player.instance().getAttackAbility().cast());
		Input.keyboard().onKeyReleased(KeyEvent.VK_R, e -> Player.instance().getChargeAbility().cast());
	}
}
