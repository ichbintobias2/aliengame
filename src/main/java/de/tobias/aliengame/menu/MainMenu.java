package de.tobias.aliengame.menu;

import de.gurkenlabs.litiengine.Game;
import de.tobias.aliengame.GameLogic;
import de.tobias.aliengame.constants.Gamestate;
import de.tobias.aliengame.entities.Player;

public class MainMenu extends KeyboardMenu {
	
	private static final double centerX = Game.window().getResolution().getWidth() / 2.0;
	private static final double centerY = Game.window().getResolution().getHeight() * 1 / 2;
	private static final double buttonWidth = 450;
	
	public MainMenu() {
		super(centerX - buttonWidth / 2, centerY * 1.3, buttonWidth, centerY / 2, "Start 1P", "Start 2P", "Instructions", "Quit");
		
		onConfirm(c -> {
			switch (c) {
			case 0:
				startSelection();
				break;
			case 1:
				startMultiplayer();
			case 2:
				showInstructions();
				break;
			case 3:
				System.exit(0);
			}
		});
	}
	
	private void startSelection() {
		setEnabled(false);
		// Game.window().getRenderComponent().fadeOut(1500);
		
		Game.loop().perform(1000, () -> {
			Player.instance().enableMovement(false);
			Game.screens().display("SELECTION");
			Game.world().loadEnvironment("player_selection");
			GameLogic.setGamestate(Gamestate.SELECTION);
		});
	}
	
	private void startMultiplayer() {
		GameLogic.p2 = true;
		startSelection();
	}
	
	private void showInstructions() {
		// TODO
	}
}
