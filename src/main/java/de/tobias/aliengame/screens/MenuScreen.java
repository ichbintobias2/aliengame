package de.tobias.aliengame.screens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.resources.Resources;
import de.tobias.aliengame.menu.MainMenu;

public class MenuScreen extends GameScreen {
	
	private double scaleFactor = Game.window().getHeight() / 240;
	private BufferedImage background = Resources.images().get("src/main/resources/sprites/main_menu.png");
	
	public MenuScreen() {
		super("MENU");
	}
	
	@Override
	protected void initializeComponents() {
		this.getComponents().add(new MainMenu());
	}
	
	@Override
	public void render(final Graphics2D g) {
		double imageOffset = Game.window().getWidth() - (background.getWidth() * scaleFactor);
		ImageRenderer.renderScaled(g, background, imageOffset / 2, 0, scaleFactor);
		super.render(g);
	}
}
