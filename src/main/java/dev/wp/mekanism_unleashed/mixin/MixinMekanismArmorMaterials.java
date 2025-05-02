package dev.wp.mekanism_unleashed.mixin;

import dev.wp.mekanism_unleashed.MekanismUnleashedConfig;
import mekanism.common.registries.MekanismArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MekanismArmorMaterials.class)
public abstract class MixinMekanismArmorMaterials {
    @ModifyArg(
            method = "lambda$static$7",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ArmorMaterial;<init>(Ljava/util/Map;ILnet/minecraft/core/Holder;Ljava/util/function/Supplier;Ljava/util/List;FF)V"))
    private static int modifyEnchantValue(int orig) {
        return MekanismUnleashedConfig.enchantableMekaGear ? 30 : orig;
    }
}
