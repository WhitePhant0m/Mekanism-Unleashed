package dev.wp.mekanism_unleashed.mixin;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import dev.wp.mekanism_unleashed.MekanismUnleashedConfig;
import net.neoforged.fml.loading.FMLPaths;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public class MekanismUnleashedMixinPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {
        Path configPath = FMLPaths.CONFIGDIR.get().resolve("mekanism_unleashed-startup.toml");

        try (CommentedFileConfig configData = CommentedFileConfig.builder(configPath)
                .sync()
                .writingMode(WritingMode.REPLACE)
                .build()) {
            configData.load();

            if (configData.isEmpty()) {
                MekanismUnleashedConfig.SPEC.correct(configData);
                configData.save();
            }

            MekanismUnleashedConfig.loadConfig(configData);
        }
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, org.objectweb.asm.tree.ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, org.objectweb.asm.tree.ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
