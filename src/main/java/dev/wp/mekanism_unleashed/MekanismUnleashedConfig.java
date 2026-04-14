package dev.wp.mekanism_unleashed;

import net.neoforged.neoforge.common.ModConfigSpec;

public class MekanismUnleashedConfig {
    private static final ModConfigSpec.Builder BUILDER;
    
    // Upgrade limits
    private static final ModConfigSpec.IntValue MAX_SPEED_UPGRADES;
    private static final ModConfigSpec.IntValue MAX_ENERGY_UPGRADES;
    
    private static final ModConfigSpec.BooleanValue ENCHANTABLE_MEKA_GEAR;
    public static final ModConfigSpec SPEC;

    private static String key(String key) {
        return MekanismUnleashed.ID + ".config." + key;
    }

    static {
        BUILDER = new ModConfigSpec.Builder();
        {
            BUILDER.translation(key("tweaks")).push("Tweaks");
            
            // Mekanism core upgrade limits
            BUILDER.translation(key("upgrades")).push("Upgrades");
            
            MAX_SPEED_UPGRADES = BUILDER
                    .translation(key("maxSpeedUpgrades"))
                    .comment("Maximum speed upgrades a machine can accept")
                    .defineInRange("maxSpeedUpgrades", 32, 8, Integer.MAX_VALUE);
            
            MAX_ENERGY_UPGRADES = BUILDER
                    .translation(key("maxEnergyUpgrades"))
                    .comment("Maximum energy upgrades a machine can accept")
                    .defineInRange("maxEnergyUpgrades", 32, 8, Integer.MAX_VALUE);
            
            BUILDER.pop(); // Pop Upgrades
            
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

    public static int maxSpeedUpgrades;
    public static int maxEnergyUpgrades;
    public static boolean enchantableMekaGear;

    public static void loadConfig(com.electronwill.nightconfig.core.UnmodifiableConfig config) {
        maxSpeedUpgrades = config.getIntOrElse("Tweaks.Upgrades.maxSpeedUpgrades", 32);
        maxEnergyUpgrades = config.getIntOrElse("Tweaks.Upgrades.maxEnergyUpgrades", 32);
        enchantableMekaGear = config.getOrElse("Tweaks.enchantableMekaGear", false);
    }

    public static void loadConfig() {
        maxSpeedUpgrades = MAX_SPEED_UPGRADES.get();
        maxEnergyUpgrades = MAX_ENERGY_UPGRADES.get();
        enchantableMekaGear = ENCHANTABLE_MEKA_GEAR.get();
    }
}
