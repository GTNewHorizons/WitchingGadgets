package witchinggadgets.mixins.early.minecraft;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import thaumcraft.common.config.Config;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.items.armor.ItemPrimordialArmor;

@Mixin(EntityLivingBase.class)
public class MixinEntityLivingBase {

    @Inject(method = "addPotionEffect", at = @At("HEAD"), cancellable = true)
    private void witchinggadgets$preventAquaDebuffs(PotionEffect effect, CallbackInfo ci) {
        int id = effect.getPotionID();
        if (id != Potion.blindness.id && id != Potion.poison.id
                && id != Potion.wither.id
                && id != Potion.confusion.id
                && id != Config.potionTaintPoisonID
                && id != Potion.digSlowdown.id
                && id != Potion.hunger.id
                && id != Potion.weakness.id
                && id != Potion.moveSlowdown.id) {
            return;
        }
        for (int i = 1; i <= 4; i++) {
            ItemStack armor = ((EntityLivingBase) (Object) this).getEquipmentInSlot(i);
            if (armor != null && armor.getItem() instanceof ItemPrimordialArmor primordialArmor
                    && primordialArmor.getAbility(armor) == 4) {
                ci.cancel();
                return;
            }
        }
    }

    @Inject(method = "onNewPotionEffect", at = @At("RETURN"))
    private void witchinggadgets$onNewPotionEffect(PotionEffect effect, CallbackInfo ci) {
        if (!WGConfig.coremod_allowPotionApplicationMod || effect == null
                || ((EntityLivingBase) (Object) this).worldObj.isRemote) {
            return;
        }
        int id = effect.getPotionID();
        if (id != Config.potionVisExhaustID && id != Config.potionThaumarhiaID
                && id != Config.potionUnHungerID
                && id != Config.potionBlurredID
                && id != Config.potionSunScornedID
                && id != Config.potionInfVisExhaustID
                && id != Config.potionDeathGazeID) {
            return;
        }
        int ordo = 0;
        for (int i = 1; i <= 4; i++) {
            ItemStack armor = ((EntityLivingBase) (Object) this).getEquipmentInSlot(i);
            if (armor != null && armor.getItem() != null
                    && armor.getItem() instanceof ItemPrimordialArmor
                    && ((ItemPrimordialArmor) armor.getItem()).getAbility(armor) == 5)
                ordo++;
        }

        int amplifier = effect.getAmplifier();
        if (amplifier > 0) {
            amplifier = Math.max(0, amplifier - ordo);
        }
        ((PotionEffectAccessor) effect).setAmplifier(amplifier);

        int duration = effect.getDuration();
        if (duration > 0) {
            duration /= (ordo + 1);
        }
        ((PotionEffectAccessor) effect).setDuration(duration);

    }

}
