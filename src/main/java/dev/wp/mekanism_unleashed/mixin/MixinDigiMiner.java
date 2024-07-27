package dev.wp.mekanism_unleashed.mixin;

import mekanism.api.Upgrade;
import mekanism.common.tile.machine.TileEntityDigitalMiner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntityDigitalMiner.class, remap = false)
public abstract class MixinDigiMiner {

    @Shadow
    private int delay;

    @Shadow
    protected abstract void tryMineBlock();

    @Shadow
    public abstract void recalculateUpgrades(Upgrade upgrade);

    @Shadow
    public abstract int getDelay();

    @Inject(method = "onUpdateServer",
            at = @At(value = "INVOKE", target = "Lmekanism/common/capabilities/energy/MinerEnergyContainer;extract(JLmekanism/api/Action;Lmekanism/api/AutomationType;)J", shift = At.Shift.AFTER),
            slice = @Slice(from = @At(value = "INVOKE", target = "Lmekanism/common/tile/machine/TileEntityDigitalMiner;setActive(Z)V")))
    public void injected(CallbackInfoReturnable<Boolean> cir) {
        if (delay < 0) {
            for (var i = delay - 1; i < 0; i++)
                tryMineBlock();
            recalculateUpgrades(Upgrade.SPEED);
            ((DelayAccessor) this).setDelay(getDelay());
        }
    }
}

