package de.tobias.aliengame;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;
import de.tobias.aliengame.screens.IngameScreen;

public class AliengameApplication {
	
	public static void main(String[] args) {
		Game.info().setName("ALIENGAME");
		Game.info().setSubTitle("");
		Game.info().setVersion("v0.0.1");
		Game.info().setWebsite("");
		Game.info().setDescription("A small Alien Storm clone by Tobias");
		
		Game.init(args);
		
		// Game.window().setIcon(Resources.images().get("src\\main\\resources\\sprites\\icon.png")); // TODO make icon
		Game.graphics().setBaseRenderScale(4f);
		
		Resources.load("game.litidata");
		
		PlayerInput.init();
		GameLogic.init();
		
		// adding all screens that will be used
		Game.screens().add(new IngameScreen());
		
		Game.screens().display("INGAME");
		Game.world().loadEnvironment(GameLogic.START_LEVEL);
		
		Game.start();
	}
}
