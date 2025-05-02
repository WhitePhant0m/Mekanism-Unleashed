package dev.wp.mekanism_unleashed;

import mekanism.api.Upgrade;
import mekanism.common.config.MekanismConfig;
import mekanism.common.tile.interfaces.IUpgradeTile;
import mekanism.common.tile.machine.TileEntityDigitalMiner;
import mekanism.common.tile.machine.TileEntityElectricPump;
import mekanism.common.tile.machine.TileEntityFluidicPlenisher;
import mekanism.common.tile.machine.TileEntityFormulaicAssemblicator;

import java.util.Set;

public class Utils {

    private static final Set<Class<?>> NON_CRAFTER_CLASSES = Set.of(
            TileEntityDigitalMiner.class,
            TileEntityFluidicPlenisher.class,
            TileEntityFormulaicAssemblicator.class,
            TileEntityElectricPump.class
    );

    public static boolean isCrafter(IUpgradeTile tile) {
        return !NON_CRAFTER_CLASSES.contains(tile.getClass());
    }


    public static double time(IUpgradeTile tile) {
        return Math.pow(MekanismConfig.general.maxUpgradeMultiplier.get(), tile.getComponent().getUpgrades(Upgrade.SPEED) / -8D);
    }

    public static double electricity(IUpgradeTile tile) {
        var speed = tile.getComponent().getUpgrades(Upgrade.SPEED);
        var energy = tile.getComponent().getUpgrades(Upgrade.ENERGY);
        return Math.pow(MekanismConfig.general.maxUpgradeMultiplier.get(), (2 * speed - Math.min(energy, Math.max(8, speed))) / 8D);
    }

    public static double capacity(IUpgradeTile tile) {
        return Math.pow(MekanismConfig.general.maxUpgradeMultiplier.get(), tile.getComponent().getUpgrades(Upgrade.ENERGY) / 8D);
    }

    public static double capacity(int upgrades) {
        return Math.pow(MekanismConfig.general.maxUpgradeMultiplier.get(), upgrades / 8D);
    }

    public static String exponential(double d) {
        int significant = 4;
        int exp = (int) Math.floor(Math.log10(d));
        d = d * Math.pow(10, -exp);
        d = (double) ((int) Math.round(d * Math.pow(10, significant - 1))) / Math.pow(10, significant - 1);
        var dt = (double) ((int) Math.round(d * Math.pow(10, significant - 1))) / Math.pow(10, significant - 1 - exp);
        return Math.abs(exp) <= significant - 1 ? dt + "" : d + "E" + exp;
    }
}
