package dev.wp.mekanism_unleashed;

import net.neoforged.neoforge.common.ModConfigSpec;

public class MekanismUnleashedConfig {
    private static final ModConfigSpec.Builder BUILDER;
    private static final ModConfigSpec.IntValue MAX_UPGRADES;
    private static final ModConfigSpec.BooleanValue ENCHANTABLE_MEKA_GEAR;
    public static final ModConfigSpec SPEC;

    private static String key(String key) {
        return MekanismUnleashed.ID + ".config." + key;
    }

    static {
        BUILDER = new ModConfigSpec.Builder();
        {
            BUILDER.translation(key("tweaks")).push("Tweaks");
            MAX_UPGRADES = BUILDER
                    .translation(key("maxUpgrades"))
                    .comment("Maximum speed/energy upgrades a machine can accept, values higher than 32 are not recommended")
                    .defineInRange("maxUpgrades", 32, 8, Integer.MAX_VALUE);
            ENCHANTABLE_MEKA_GEAR = BUILDER
                    .translation(key("enchantableMekaGear"))
                    .comment(
                            "Allow Meka Armor/Tool to be enchanted",
                            "Note: You will need to add the items to the enchantable tags yourself, this mod does not do that for you"
                    )
                    .define("enchantableMekaGear", false);
            BUILDER.pop();
        }
        SPEC = BUILDER.build();
    }

    public static int maxUpgrades;
    public static boolean enchantableMekaGear;

    public static void loadConfig(com.electronwill.nightconfig.core.UnmodifiableConfig config) {
        maxUpgrades = config.getIntOrElse("Tweaks.maxUpgrades", 32);
        enchantableMekaGear = config.getOrElse("Tweaks.enchantableMekaGear", false);
    }

    public static void loadConfig() {
        maxUpgrades = MAX_UPGRADES.get();
        enchantableMekaGear = ENCHANTABLE_MEKA_GEAR.get();
    }
}
