package spawn.immunity.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import spawn.immunity.SpawnImmunityAccessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class SpawnImmunityMixin implements SpawnImmunityAccessor {
    private int spawnImmunityTicks = 0;

    @Override
    public void setSpawnImmunityTicks(int ticks) {
        this.spawnImmunityTicks = ticks;
    }

    @Override
    public int getSpawnImmunityTicks() {
        return this.spawnImmunityTicks;
    }

    @Inject(method = "onSpawn", at = @At("TAIL"))
    private void onSpawn(CallbackInfo ci) {
        this.spawnImmunityTicks = 60;
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (this.spawnImmunityTicks > 0) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        if (this.spawnImmunityTicks > 0) {
            this.spawnImmunityTicks--;
        }
    }
}
