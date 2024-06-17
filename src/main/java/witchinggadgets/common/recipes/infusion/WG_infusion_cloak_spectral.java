package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionRecipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import witchinggadgets.common.WGContent;

public class WG_infusion_cloak_spectral {

    public static void registerCloakSpectral() {
        registerInfusionRecipe(
                "CLOAK_SPECTRAL",
                "",
                new ItemStack(WGContent.ItemCloak, 1, 1),
                5,
                new AspectList().add(Aspect.SOUL, 48).add(Aspect.TRAVEL, 48).add(Aspect.ELDRITCH, 24)
                        .add(Aspect.SENSES, 24),
                new ItemStack(WGContent.ItemCloak),
                new ItemStack[] { new ItemStack(Items.potionitem, 1, 8270), new ItemStack(WGContent.ItemMaterial, 1, 5),
                        new ItemStack(Items.ender_pearl), new ItemStack(WGContent.ItemMaterial, 1, 5) });
    }
}
