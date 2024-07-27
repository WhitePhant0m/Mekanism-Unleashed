package dev.wp.mekanism_unleashed.mixin;

import dev.wp.mekanism_unleashed.Utils;
import mekanism.api.Upgrade;
import mekanism.common.MekanismLang;
import mekanism.common.config.MekanismConfig;
import mekanism.common.tile.interfaces.IUpgradeTile;
import mekanism.common.util.UpgradeUtils;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = UpgradeUtils.class, remap = false)
public class MixinUpgradeUtils {

    /**
     * @author WhitePhant0m
     * @reason Show correct Speed effect on Exponentially scaling machines.
     */
    @Overwrite
    public static List<Component> getExpScaledInfo(IUpgradeTile tile, Upgrade upgrade) {
        var ret = new ArrayList<Component>();
        if (tile.supportsUpgrades() && upgrade.getMax() > 1)
            ret.add(MekanismLang.UPGRADES_EFFECT.translate(Utils.exponential(Math.pow(2, (float) tile.getComponent().getUpgrades(upgrade)))));
        return ret;
    }

    /**
     * @author WhitePhant0m, nin8995(original author)
     * @reason Show correct Speed/Energy effect.
     */
    @Overwrite
    public static List<Component> getMultScaledInfo(IUpgradeTile tile, Upgrade upgrade) {
        var ret = new ArrayList<Component>();
        if (tile.supportsUpgrades() && upgrade.getMax() > 1) {
            var effect = upgrade == Upgrade.ENERGY ? Utils.capacity(tile) : upgrade == Upgrade.SPEED ? 1/Utils.time(tile) : Math.pow(MekanismConfig.general.maxUpgradeMultiplier.get(), (float) tile.getComponent().getUpgrades(upgrade) / (float) upgrade.getMax());
            ret.add(MekanismLang.UPGRADES_EFFECT.translate(Utils.exponential(effect)));
        }
        return ret;
    }
}
