package witchinggadgets.common.recipes;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.common.config.Config;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.recipes.infusion.WG_infusion_bags;
import witchinggadgets.common.recipes.infusion.WG_infusion_baubles;
import witchinggadgets.common.recipes.infusion.WG_infusion_enchantments;
import witchinggadgets.common.recipes.infusion.WG_infusion_ice_solidifier;
import witchinggadgets.common.recipes.infusion.WG_infusion_labyrinth_string;
import witchinggadgets.common.recipes.infusion.WG_infusion_mirror_pump;
import witchinggadgets.common.recipes.infusion.WG_infusion_primordial_gear;
import witchinggadgets.common.recipes.infusion.WG_infusion_sauna_stove;
import witchinggadgets.common.recipes.infusion.WG_infusion_snowballer;
import witchinggadgets.common.recipes.infusion.WG_infusion_stone_extruder;
import witchinggadgets.common.recipes.infusion.WG_infusion_terraformer;
import witchinggadgets.common.recipes.infusion.WG_infusion_wall_mirror;

public class WG_Infusion_recipes {

    public static void register_infusion() {

        WG_infusion_stone_extruder.registerStoneExtruder();
        WG_infusion_snowballer.registerSnowballer();
        WG_infusion_ice_solidifier.registerSnowballer();

        if (WGConfig.moduleBag) {
            WG_infusion_bags.registerBags();
        }

        if (Config.allowMirrors) {
            WG_infusion_wall_mirror.registerWallMirror();
            WG_infusion_mirror_pump.registerMirrorPump();
        }

        WG_infusion_baubles.registerInfusionBaubles();
        WG_infusion_labyrinth_string.registerLabyrinthString();

        if (WGConfig.terraformer) {
            WG_infusion_terraformer.registerTerraformer();
        }

        WG_infusion_sauna_stove.registerSaunaStove();

        if (WGConfig.modulePrimal) {
            WG_infusion_primordial_gear.registerPrimordialGear();
        }

        WG_infusion_enchantments.registerInfusionEnchantments();
    }

    public static void registerInfusionRecipe(String tag, String tagAddon, Object result, int difficulty,
            AspectList infusionAspects, ItemStack centralIngredient, ItemStack[] otherIngredients) {
        InfusionRecipe infusionRecipe = ThaumcraftApi.addInfusionCraftingRecipe(
                tag,
                result,
                difficulty,
                infusionAspects,
                centralIngredient,
                otherIngredients);
        WGContent.recipeList.put(tag + tagAddon, infusionRecipe);
    }

    public static void registerInfusionEnchantmentRecipe(String tag, String tagAddon, Enchantment enchantment,
            int difficulty, AspectList infusionAspects, ItemStack[] otherIngredients) {
        InfusionEnchantmentRecipe infusionRecipe = ThaumcraftApi
                .addInfusionEnchantmentRecipe(tag, enchantment, difficulty, infusionAspects, otherIngredients);
        WGContent.recipeList.put(tag + tagAddon, infusionRecipe);
    }
}
