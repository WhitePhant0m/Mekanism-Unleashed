package dev.wp.mekanism_unleashed.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.wp.mekanism_unleashed.MekanismUnleashedConfig;
import mekanism.common.item.gear.ItemMekaTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemMekaTool.class)
public abstract class MixinItemMekaTool {
    @ModifyReturnValue(method = "isEnchantable", at = @At("RETURN"))
    public boolean isEnchantable(boolean original) {
        return MekanismUnleashedConfig.enchantableMekaGear;
    }

    public int getEnchantmentValue() {
        return MekanismUnleashedConfig.enchantableMekaGear ? 30 : 0;
    }
}
