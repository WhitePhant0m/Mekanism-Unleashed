package dev.wp.mekanism_unleashed.mixin;

import dev.wp.mekanism_unleashed.Temp;
import mekanism.common.tile.machine.TileEntityFormulaicAssemblicator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntityFormulaicAssemblicator.class, remap = false)
public abstract class MixinFormulaicAssemblicator {

    @Shadow
    private int ticksRequired;

    @Shadow
    protected abstract boolean onUpdateServer();

    @Inject(method = "onUpdateServer", at = @At(value = "INVOKE", target = "Lmekanism/common/tile/machine/TileEntityFormulaicAssemblicator;doSingleCraft()Z", shift = At.Shift.AFTER))
    public void injected(CallbackInfoReturnable<Boolean> cir) {
        Temp.inject.accept(ticksRequired, this::onUpdateServer);
    }
}
