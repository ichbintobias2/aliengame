package de.tobias.aliengame.entities.controllers;

import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.tobias.aliengame.GameLogic;
import de.tobias.aliengame.entities.Player;

import java.awt.event.KeyEvent;

public class PlayerMovementController extends KeyboardEntityController<Player> {
	
	private final boolean p1;
	
	public PlayerMovementController(Player entity) {
		super(entity);
		this.p1 = true;
	}
	
	public PlayerMovementController(Player entity, boolean p1) {
		super(entity, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
		this.p1 = p1;
	}
	
	@Override
	public void handlePressedKey(final KeyEvent keyCode) {
		super.handlePressedKey(keyCode);
		if (KeyEvent.VK_E == keyCode.getKeyCode()) {
			getEntity().getAttackAbility().cast();
		} else if (KeyEvent.VK_R == keyCode.getKeyCode()) {
			getEntity().getChargeAbility().cast();
		}
	}
}
