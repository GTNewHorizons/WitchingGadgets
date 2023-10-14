package witchinggadgets.mixins.early.minecraft;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import baubles.api.BaublesApi;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.items.baubles.ItemMagicalBaubles;

@Mixin(EnchantmentHelper.class)
public class MixinEnchantmentHelper {

    @ModifyReturnValue(method = { "getFortuneModifier", "getLootingModifier" }, at = @At("RETURN"))
    private static int witchinggadgets$modifyEnchantLevel(int originalLevel, EntityLivingBase entity) {
        if (WGConfig.coremod_allowEnchantModifications && entity instanceof EntityPlayer) {
            for (int i = 0; i < BaublesApi.getBaubles((EntityPlayer) entity).getSizeInventory(); i++) {
                ItemStack bStack = BaublesApi.getBaubles((EntityPlayer) entity).getStackInSlot(i);
                if (bStack != null && bStack.getItem() != null
                        && bStack.getItem().equals(WGContent.ItemMagicalBaubles)
                        && ItemMagicalBaubles.subNames[bStack.getItemDamage()].equals("ringLuck")) {
                    return originalLevel + 2;
                }
            }
        }
        return originalLevel;
    }

}
