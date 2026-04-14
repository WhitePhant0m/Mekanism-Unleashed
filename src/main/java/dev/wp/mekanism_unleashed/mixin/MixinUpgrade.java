package dev.wp.mekanism_unleashed.mixin;

import dev.wp.mekanism_unleashed.MekanismUnleashed;
import dev.wp.mekanism_unleashed.MekanismUnleashedConfig;
import dev.wp.mekanism_unleashed.Temp;
import mekanism.api.Upgrade;
import org.spongepowered.asm.mixin.Mixin;
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
        String upgradeName = Temp.name.toLowerCase();
        
        if (upgradeName.equals("speed")) {
            if (MekanismUnleashedConfig.maxSpeedUpgrades >= i) {
                return MekanismUnleashedConfig.maxSpeedUpgrades;
            }
            MekanismUnleashed.LOGGER.error("Didn't get proper value from config for speed upgrade, defaulting to 32");
            return 32;
        }
        
        if (upgradeName.equals("energy")) {
            if (MekanismUnleashedConfig.maxEnergyUpgrades >= i) {
                return MekanismUnleashedConfig.maxEnergyUpgrades;
            }
            MekanismUnleashed.LOGGER.error("Didn't get proper value from config for energy upgrade, defaulting to 32");
            return 32;
        }
        
        // All other upgrades use original value
        return i;
    }
}
