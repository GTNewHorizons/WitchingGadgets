package witchinggadgets.common.recipes.arcane;

import static witchinggadgets.common.recipes.WG_arcane_recipes.registerShapelessArcaneRecipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;

public class WG_arcane_advanced_scribing_tools {

    public static void registerAdvancedScribingTools() {

        registerShapelessArcaneRecipe(
                "ADVANCEDSCRIBINGTOOLS",
                "refill",
                new ItemStack(WGContent.ItemAdvancedScribingTools),
                new AspectList().add(Aspect.AIR, 10).add(Aspect.FIRE, 10),
                new Object[] { new ItemStack(WGContent.ItemAdvancedScribingTools, 1, 32767), "dustSmallGold",
                        Items.glowstone_dust });

        if (WGModCompat.loaded_Twilight) {

            registerShapelessArcaneRecipe(
                    "ADVANCEDSCRIBINGTOOLS",
                    "",
                    new ItemStack(WGContent.ItemAdvancedScribingTools),
                    new AspectList().add(Aspect.AIR, 50).add(Aspect.FIRE, 50).add(Aspect.ORDER, 50)
                            .add(Aspect.ENTROPY, 50).add(Aspect.EARTH, 50).add(Aspect.WATER, 50),
                    new Object[] { "dustGold", "dustGold", "dustGold", Items.glowstone_dust, Items.glowstone_dust,
                            Items.glowstone_dust, Items.glowstone_dust, new ItemStack(WGModCompat.tfMagicMapFocus),
                            thaumcraft.api.ItemApi.getItem("itemEssence", 0) });
        } else {

            registerShapelessArcaneRecipe(
                    "ADVANCEDSCRIBINGTOOLS",
                    "",
                    new ItemStack(WGContent.ItemAdvancedScribingTools),
                    new AspectList().add(Aspect.AIR, 50).add(Aspect.FIRE, 50).add(Aspect.ORDER, 50)
                            .add(Aspect.ENTROPY, 50).add(Aspect.EARTH, 50).add(Aspect.WATER, 50),
                    new ItemStack(Items.gold_nugget),
                    new ItemStack(Items.feather),
                    thaumcraft.api.ItemApi.getItem("itemEssence", 0));
        }
    }
}
