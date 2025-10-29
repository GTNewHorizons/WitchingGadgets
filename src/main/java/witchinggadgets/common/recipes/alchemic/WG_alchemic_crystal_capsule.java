package witchinggadgets.common.recipes.alchemic;

import static witchinggadgets.common.recipes.WG_alchemic_recipes.registerAlchemyRecipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;

public class WG_alchemic_crystal_capsule {

    public static void registerCrystalCapsule() {
        if (WitchingGadgets.isGT5uLoaded) {
            registerAlchemyRecipe(
                    "CRYSTALCAPSULE",
                    "_Wax",
                    new ItemStack(WGContent.ItemCapsule),
                    ItemList.FR_WaxCapsule.get(1L, ItemList.FR_RefractoryCapsule.get(1L, Materials.Empty.getCells(1))),
                    new AspectList().add(Aspect.VOID, 8).add(Aspect.CRYSTAL, 16));

            registerAlchemyRecipe(
                    "CRYSTALCAPSULE",
                    "_Refractory",
                    new ItemStack(WGContent.ItemCapsule),
                    ItemList.FR_RefractoryCapsule.get(1L, ItemList.FR_WaxCapsule.get(1L, Materials.Empty.getCells(1))),
                    new AspectList().add(Aspect.VOID, 8).add(Aspect.CRYSTAL, 16));

            registerAlchemyRecipe(
                    "CRYSTALCAPSULE",
                    "_Cell",
                    new ItemStack(WGContent.ItemCapsule),
                    ItemList.Cell_Empty.get(1L, ItemList.FR_WaxCapsule.get(1L, ItemList.FR_RefractoryCapsule.get(1L))),
                    new AspectList().add(Aspect.VOID, 8).add(Aspect.CRYSTAL, 16));
        } else {
            registerAlchemyRecipe(
                    "CRYSTALCAPSULE",
                    "",
                    new ItemStack(WGContent.ItemCapsule),
                    new ItemStack(Items.bucket),
                    new AspectList().add(Aspect.VOID, 2).add(Aspect.CRYSTAL, 4));
        }
    }
}
