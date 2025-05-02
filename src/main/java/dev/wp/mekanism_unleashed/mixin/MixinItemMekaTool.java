package dev.wp.mekanism_unleashed.mixin;

import dev.wp.mekanism_unleashed.MekanismUnleashedConfig;
import mekanism.common.item.gear.ItemMekaTool;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ItemMekaTool.class)
public abstract class MixinItemMekaTool {
    /**
     * @author Mekanism
     * @reason Allow Mekanism tools to be enchantable.
     */
    @Overwrite
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return MekanismUnleashedConfig.enchantableMekaGear;
    }

    public int getEnchantmentValue() {
        return MekanismUnleashedConfig.enchantableMekaGear ? 30 : 0;
    }
}
