package de.tobias.aliengame.screens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.resources.Resources;

public class SelectionScreen extends GameScreen {
	
	private double scaleFactor = Game.window().getHeight() / 240;
	private BufferedImage background = Resources.images().get("src/main/resources/sprites/player_selection.png");
	
	public SelectionScreen() {
		super("SELECTION");
	}
	
	@Override
	protected void initializeComponents() {
		//
	}
	
	@Override
	public void render(final Graphics2D g) {
		double imageOffset = Game.window().getWidth() - (background.getWidth() * scaleFactor);
		ImageRenderer.renderScaled(g, background, imageOffset / 2, 0, scaleFactor);
		super.render(g);
	}
}