package spawn.immunity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.Category;

public class SpawnImmunity implements ModInitializer {
	public static final String MOD_ID = "spawnimmunity";

	public static final GameRules.Key<GameRules.IntRule> IMMUNE_TICKS = 
	GameRuleRegistry.register("immuneTicks", Category.SPAWNING, GameRuleFactory.createIntRule(60, 0, Integer.MAX_VALUE));

	@Override
	public void onInitialize() {
	}
}