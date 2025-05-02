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
     * @author WhitePhant0m
     * @reason translate multiple operations / tick as minus
     */
    @Overwrite
    public static double getTicksD(IUpgradeTile tile, int def) {
        var d = def * Utils.time(tile);
        return d >= 1 ? MathUtils.clampToInt(d) : MathUtils.clampToInt(1 / d) * -1;
    }

    /**
     * @author WhitePhant0m
     * @reason works
     */
    @Overwrite
    public static int getOperationsPerTick(IUpgradeTile tile, int defTicks, int defaultOperations) {
        double ticksD = getTicksD(tile, defTicks);
        if (ticksD >= 1) return defaultOperations;
        return MathUtils.clampToInt(Math.max(1, -ticksD) * defaultOperations);
    }

    /**
     * @author nin8995
     * @reason extension and energy upgrade can affect like before till 8, after that cannot affect more than speed upgrade
     */
    @Overwrite
    public static long getEnergyPerTick(IUpgradeTile tile, long def) {
        if (tile.supportsUpgrades()) return MathUtils.clampToLong(def * (Utils.electricity(tile)));
        return def;
    }

    /**
     * @author nin8995
     * @reason extension
     */
    @Overwrite
    public static long getMaxEnergy(IUpgradeTile tile, long def) {
        if (tile.supportsUpgrades()) return MathUtils.clampToLong(def * (Utils.capacity(tile)));
        return def;
    }

    /**
     * @author WhitePhant0m
     * @reason Fix for max energy(item form)
     */
    @Overwrite
    public static long getMaxEnergy(int energyUpgrades, long def) {
        return MathUtils.clampToLong(def * (Utils.capacity(energyUpgrades)));
    }
}
