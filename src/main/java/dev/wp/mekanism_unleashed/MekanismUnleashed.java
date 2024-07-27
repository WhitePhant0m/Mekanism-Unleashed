package dev.wp.mekanism_unleashed;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(MekanismUnleashed.ID)
public class MekanismUnleashed {
    public static final String ID = "mekanism_unleashed";
    public static final String NAME = "Mekanism Unleashed";

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public MekanismUnleashed(ModContainer container){
        LOGGER.info(NAME + " is loaded, some things may differ from base Mekanism.");

        container.registerConfig(ModConfig.Type.STARTUP, MekanismUnleashedConfig.SPEC);
        MekanismUnleashedConfig.loadConfig();

        if(FMLEnvironment.dist == Dist.CLIENT)
            container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
