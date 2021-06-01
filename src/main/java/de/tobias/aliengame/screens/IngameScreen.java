package de.tobias.aliengame.screens;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.aliengame.entities.Player;
import java.awt.Graphics2D;

public class IngameScreen extends GameScreen {
	
	public IngameScreen() {
		super("INGAME");
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		Player.instance().getAttackAbility().render(g);
	}
	
	@Override
	protected void initializeComponents() {
		this.getComponents().add(Hud.instance());
	}
}
