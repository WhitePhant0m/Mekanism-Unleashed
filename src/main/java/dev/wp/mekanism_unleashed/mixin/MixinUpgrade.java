package dev.wp.mekanism_unleashed.mixin;

import dev.wp.mekanism_unleashed.MekanismUnleashed;
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
        if (Temp.name.equals("speed")) {
            MekanismUnleashed.LOGGER.debug("MAX UPGRADES MIXIN APPLIED");
            if (MekanismUnleashedConfig.maxUpgrades < 8)
                MekanismUnleashed.LOGGER.error("CONFIG WAS TOO LATE");
        }
        return Temp.name.equals("speed") || Temp.name.equals("energy") ? MekanismUnleashedConfig.maxUpgrades : i;
    }

}