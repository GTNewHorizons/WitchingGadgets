package witchinggadgets.common.recipes;

import net.minecraft.item.ItemStack;

import com.ruling_0.materiallib.api.Material;
import com.ruling_0.materiallib.api.MaterialLibAPI;
import com.ruling_0.materiallib.api.Shape;
import com.ruling_0.materiallib.api.ShapeConsumer;

import gregtech.GTMod;
import gregtech.api.enums.SubTag;
import gregtech.api.enums.materials.Materials;
import gregtech.api.enums.materials.Shapes;
import gregtech.api.material.MaterialUtils;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.util.recipe.InfernalBlastfurnaceRecipe;

/// Mirrors GregTech's primitive blast furnace into the infernal blast furnace, one recipe per dust-carrying
/// material. Ingots resolve through the ore dictionary: a material carrying a dust need not carry an `ingot`
/// shape, and the vanilla-backed ones (iron, gold) never do.
public class WG_infernal_recipes implements ShapeConsumer {

    @Override
    public void consume(Shape shape, Material material) {
        if (MaterialUtils.hasSubTag(material, SubTag.NO_SMELTING)) return;

        Material directSmelt = MaterialUtils.directSmelting(material);

        if (MaterialUtils.blastFurnaceRequired(material) || MaterialUtils.blastFurnaceRequired(directSmelt)) {
            if (MaterialUtils.blastFurnaceTemp(material) > 1000 || MaterialUtils.blastFurnaceTemp(directSmelt) > 1000) {
                return;
            }

            if (!material.hasShape(Shapes.ingot)) return;

            addRecipe(MaterialLibAPI.getStack(material, Shapes.ingot, 1), MaterialLibAPI.getStack(material, shape, 1));
            return;
        }

        if (material == directSmelt) return;

        int outputAmount = GTMod.proxy.mMixedOreOnlyYieldsTwoThirdsOfPureOre ? 2 : 3;
        if (!directSmelt.hasShape(Shapes.ingot)) return;
        ItemStack ingots = MaterialLibAPI.getStack(directSmelt, Shapes.ingot, outputAmount);

        if (!MaterialUtils.hasSubTag(material, SubTag.DONT_ADD_DEFAULT_BBF_RECIPE)) {
            addRecipe(ingots, MaterialLibAPI.getStack(material, shape, 2));
        } else if (material == Materials.Tetrahedrite) {
            InfernalBlastfurnaceRecipe recipe = addRecipe(ingots, MaterialLibAPI.getStack(material, shape, 2));
            recipe.addBonus(MaterialLibAPI.getStack(Materials.Antimony, Shapes.nugget, 3 * outputAmount));
        }
    }

    private static InfernalBlastfurnaceRecipe addRecipe(ItemStack output, ItemStack input) {
        InfernalBlastfurnaceRecipe recipe = new InfernalBlastfurnaceRecipe(output, input, 240, false);
        InfernalBlastfurnaceRecipe.addRecipe(recipe);

        if (!InfernalBlastfurnaceRecipe.recipes.contains(recipe)) {
            WitchingGadgets.logger.warn("Error at performing GT-Primitive-Blast Recipe -> InfernalBlastfurnaceRecipe");
        }

        return recipe;
    }
}
