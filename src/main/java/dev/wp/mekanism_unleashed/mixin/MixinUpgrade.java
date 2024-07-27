package dev.wp.mekanism_unleashed.mixin;

import dev.wp.mekanism_unleashed.MekanismUnleashedConfig;
import dev.wp.mekanism_unleashed.Temp;
import mekanism.api.Upgrade;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = Upgrade.class, remap = false)
public abstract class MixinUpgrade {

    @ModifyVariable(method = "<init>", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private static String getName(String s) {
        Temp.name = s;
        return s;
    }

    @ModifyVariable(method = "<init>", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private static int toFullStack(int i) {
        return Temp.name.equals("speed") || Temp.name.equals("energy") ? MekanismUnleashedConfig.maxUpgrades : i;
    }

}
