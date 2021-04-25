package de.tobias.aliengame.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.resources.Resources;

public class Hud extends GuiComponent  {
	
	private double scaleFactor = Game.window().getHeight() / 240;
	
	private static final BufferedImage hudImage = Resources.images().get("src/main/resources/sprites/hud/hud.png");
	private final BufferedImage playerIcon1 = Resources.images().get("src/main/resources/sprites/hud/player1.png");
	private final BufferedImage playerIcon2 = Resources.images().get("src/main/resources/sprites/hud/player2.png");
	
	private static Hud instance;
	
	private double x = (Game.window().getWidth() / 2.0) - ((hudImage.getWidth() * scaleFactor) / 2.0);
	private double y =  Game.window().getHeight() * 0.75;
	
	private boolean player2Active = false;
	
	private Hud() {
		super(0, 0);
	}
	
	public static Hud instance() {
		if (instance == null) {
			instance = new Hud();
		}
		
		return instance;
	}
	
	@Override
	public void render(Graphics2D g) {
	//	if (GameLogic.getGamestate() == Gamestate.INGAME) {
			ImageRenderer.renderScaled(g, hudImage, x, y, scaleFactor);
			ImageRenderer.renderScaled(g, playerIcon1, x + 20, y + 20, scaleFactor);
			
			if (player2Active) {
				ImageRenderer.renderScaled(g, playerIcon2, x + 1112, y + 20, scaleFactor);
			}
			
			int currentHp = 50;
			int maxHp = 100;
			
			int w = 580;
			double percentHp = (double) currentHp / (double) maxHp;
			double percentEng = (double) currentHp / (double) maxHp;
			
			// Notification bar
			// y+45
			
			g.setColor(Color.BLUE);
			g.fillRect((int) x + 308, (int) y + 97, (int) (w * percentHp), 28);
			
			g.setColor(Color.ORANGE);
			g.fillRect((int) x + 309, (int) y + 145, (int) (w * percentEng), 28);
		//}
	}
}
