package dev.wp.mekanism_unleashed.mixin;

import dev.wp.mekanism_unleashed.Utils;
import mekanism.api.math.MathUtils;
import mekanism.common.tile.interfaces.IUpgradeTile;
import mekanism.common.util.MekanismUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = MekanismUtils.class, remap = false)
public class MixinMekanismUtils {

    /**
     * @author nin8995
     * @reason translate multiple operations / tick as minus
     */
    @Overwrite
    public static int getTicks(IUpgradeTile tile, int def) {
        if (tile.supportsUpgrades()) {
            var d = def * Utils.time(tile);
            return d >= 1 ? MathUtils.clampToInt(d) : MathUtils.clampToInt(1 / d) * -1;
        }
        return def;
    }

    /**
     * @author nin8995
     * @reason extension and energy upgrade can affect like before till 8, after that cannot affect more than speed upgrade
     */
    @Overwrite
    public static long getEnergyPerTick(IUpgradeTile tile, long def) {
        if (tile.supportsUpgrades())
            return MathUtils.clampToLong(def *(Utils.electricity(tile)));
        return def;
    }

    /**
     * @author nin8995
     * @reason extension
     */
    @Overwrite
    public static long getMaxEnergy(IUpgradeTile tile, long def) {
        if (tile.supportsUpgrades())
            return MathUtils.clampToLong(def *(Utils.capacity(tile)));
        return def;
    }
}
