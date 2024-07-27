package dev.wp.mekanism_unleashed;

import net.neoforged.neoforge.common.ModConfigSpec;

public class MekanismUnleashedConfig {
    private static final ModConfigSpec.Builder BUILDER;

    private static final ModConfigSpec.IntValue MAX_UPGRADES;

    public static final ModConfigSpec SPEC;

    static {
        BUILDER = new ModConfigSpec.Builder();
        {
            BUILDER.push("Tweaks");
            MAX_UPGRADES = BUILDER
                    .comment("Maximum speed/energy upgrades a machine can accept, values higher than 32 are not recommended")
                    .defineInRange("maxUpgrades", 32,8,64);
            BUILDER.pop();
        }
        SPEC = BUILDER.build();
    }

    public static int maxUpgrades;

    public static void loadConfig(){
        maxUpgrades = MAX_UPGRADES.get();
    }
}
