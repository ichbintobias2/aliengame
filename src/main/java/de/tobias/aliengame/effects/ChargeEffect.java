package de.tobias.aliengame.effects;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.effects.Effect;
import de.gurkenlabs.litiengine.abilities.effects.EffectTarget;
import de.gurkenlabs.litiengine.entities.ICombatEntity;
import de.gurkenlabs.litiengine.util.geom.GeometricUtilities;
import de.tobias.aliengame.entities.Player;

public class ChargeEffect extends Effect {
	
	private static int damage = 1;
	private double angle;
	
	private boolean wasHit;
	
	public ChargeEffect(final Ability ability) {
		super(ability, EffectTarget.EXECUTINGENTITY);
	}
	
	@Override
	public void update() {
		final long deltaTime = Game.loop().getDeltaTime();
		final double maxPixelsPerTick = this.getAbility().getAttributes().value().get() / 1000.0
				* Math.min(deltaTime, 50);
		
		if (!wasHit) {
			for (ICombatEntity ent : Game.world().environment().findCombatEntities(this.getAbility().getExecutor().getHitBox(), e -> e.equals(Player.instance()))) {
				ent.hit(damage, this.getAbility());
				wasHit = true;
				// Game.audio().playSound("charge-hit.ogg");
			}
		}
		
		Game.physics().move(this.getAbility().getExecutor(), this.angle, maxPixelsPerTick);
		
		super.update();
	}
	
	@Override
	protected void apply(final ICombatEntity entity) {
		this.angle = GeometricUtilities.calcRotationAngleInDegrees(this.getAbility().getExecutor().getCenter(),
				Player.instance().getCenter());
		wasHit = false;
		// Game.audio().playSound("run.ogg");
		super.apply(entity);
	}
}
