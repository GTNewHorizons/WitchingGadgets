package witchinggadgets.common;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import enviromine.handlers.EM_StatusManager;
import enviromine.trackers.EnviroDataTracker;
import mods.railcraft.common.core.RailcraftConfig;
import tconstruct.library.crafting.DryingRackRecipes;
import tconstruct.library.crafting.Smeltery;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumic.tinkerer.common.enchantment.core.EnchantmentManager;
import witchinggadgets.common.minetweaker.WGMinetweaker;

public class WGModCompat {

    public static Item tConResource;

    public static Item natBarleyFood;
    public static Item tfRavensFeather;
    public static Item tfMagicMapFocus;
    public static Block tfTowerWood;

    public static boolean loaded_TaintedMagic;
    public static boolean loaded_TCon;
    public static boolean loaded_Twilight;
    public static boolean loaded_Enviromine;
    public static boolean loaded_Railcraft;
    public static boolean loaded_TT;
    public static boolean loaded_ForbiddenMagic;

    public static boolean loadedTG;

    public static boolean loaded_EnderIO;

    public static void init() {
        // Natura
        natBarleyFood = GameRegistry.findItem("Natura", "barleyFood");

        // Twilight Forest
        tfRavensFeather = GameRegistry.findItem("TwilightForest", "item.tfFeather");
        tfMagicMapFocus = GameRegistry.findItem("TwilightForest", "item.magicMapFocus");
        tfTowerWood = GameRegistry.findBlock("TwilightForest", "tile.TFTowerStone");

        tConResource = GameRegistry.findItem("TConstruct", "materials");

        loaded_TaintedMagic = Loader.isModLoaded("TaintedMagic");
        loaded_TCon = Loader.isModLoaded("TConstruct");
        loaded_Twilight = Loader.isModLoaded("TwilightForest");
        loaded_Enviromine = Loader.isModLoaded("enviromine");
        loaded_Railcraft = Loader.isModLoaded("Railcraft");
        loaded_TT = Loader.isModLoaded("ThaumicTinkerer");
        loaded_ForbiddenMagic = Loader.isModLoaded("ForbiddenMagic");
        loaded_EnderIO = Loader.isModLoaded("EnderIO");

        loadedTG = Loader.isModLoaded("TravellersGear");

        if (Loader.isModLoaded("MineTweaker3")) WGMinetweaker.init();
    }

    public static void addTags() {
        registerOreDictAspects("nuggetAluminum", new AspectList().add(Aspect.METAL, 1));
        registerOreDictAspects("ingotAluminum", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 1));
        registerOreDictAspects(
                "dustAluminum",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 1));
        registerOreDictAspects(
                "oreAluminum",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.EXCHANGE, 1));

        registerOreDictAspects("nuggetAluminium", new AspectList().add(Aspect.METAL, 1));
        registerOreDictAspects("ingotAluminium", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 1));
        registerOreDictAspects(
                "dustAluminium",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 1));
        registerOreDictAspects(
                "oreAluminium",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.EXCHANGE, 1));

        registerOreDictAspects("nuggetAluminumBrass", new AspectList().add(Aspect.METAL, 1));
        registerOreDictAspects("ingotAluminumBrass", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 2));
        registerOreDictAspects(
                "dustAluminumBrass",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 2));
        registerOreDictAspects(
                "oreAluminumBrass",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.EXCHANGE, 2));

        registerOreDictAspects("nuggetAluminiumBrass", new AspectList().add(Aspect.METAL, 1));
        registerOreDictAspects("ingotAluminiumBrass", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 2));
        registerOreDictAspects(
                "dustAluminiumBrass",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 2));
        registerOreDictAspects(
                "oreAluminiumBrass",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.EXCHANGE, 2));

        registerOreDictAspects("nuggetCobalt", new AspectList().add(Aspect.METAL, 1));
        registerOreDictAspects(
                "ingotCobalt",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.FIRE, 1).add(Aspect.MOTION, 1));
        registerOreDictAspects(
                "dustCobalt",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 1).add(Aspect.MOTION, 1));
        registerOreDictAspects(
                "oreCobalt",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1).add(Aspect.MOTION, 1));

        registerOreDictAspects("nuggetArdite", new AspectList().add(Aspect.METAL, 1));
        registerOreDictAspects(
                "ingotArdite",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.FIRE, 1).add(Aspect.EARTH, 1));
        registerOreDictAspects(
                "dustArdite",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 1).add(Aspect.EARTH, 1));
        registerOreDictAspects(
                "oreArdite",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1).add(Aspect.EARTH, 1));

        registerOreDictAspects("nuggetManyullyn", new AspectList().add(Aspect.METAL, 1));
        registerOreDictAspects(
                "ingotManyullyn",
                new AspectList().add(Aspect.METAL, 3).add(Aspect.FIRE, 2).add(Aspect.MAGIC, 2));
        registerOreDictAspects(
                "dustManyullyn",
                new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 1).add(Aspect.MAGIC, 1));

        registerOreDictAspects("nuggetPigIron", new AspectList().add(Aspect.METAL, 1));
        registerOreDictAspects("ingotPigIron", new AspectList().add(Aspect.METAL, 3).add(Aspect.FLESH, 1));

        if (tConResource != null) {
            ThaumcraftApi.registerObjectTag(
                    new ItemStack(tConResource, 1, 8),
                    new AspectList().add(Aspect.DEATH, 4).add(Aspect.UNDEAD, 2).add(Aspect.HUNGER, 2));
            ThaumcraftApi.registerObjectTag(
                    new ItemStack(tConResource, 1, 8),
                    new AspectList().add(Aspect.DEATH, 4).add(Aspect.UNDEAD, 2).add(Aspect.HUNGER, 2));
        }
    }

    private static void registerOreDictAspects(String oreName, AspectList aspects) {
        if (!OreDictionary.getOres(oreName).isEmpty()) ThaumcraftApi.registerObjectTag(oreName, aspects);
    }

    public static void addTConSmelteryRecipe(String oreName, String blockName, int temperature, String fluidName,
            int fluidAmount) {
        if (!OreDictionary.getOres(blockName).isEmpty()) {
            ItemStack blockStack = OreDictionary.getOres(blockName).get(0);

            if (blockStack == null) return;

            Block block = Block.getBlockFromItem(blockStack.getItem());

            if (block == null) return;

            for (ItemStack oreStack : OreDictionary.getOres(oreName)) {
                if (oreStack != null) {
                    addTConSmelteryRecipe(
                            oreStack,
                            block,
                            blockStack.getItemDamage(),
                            temperature,
                            fluidName,
                            fluidAmount);
                }
            }
        }
    }

    public static void addTConSmelteryRecipe(ItemStack ore, Block block, int blockMeta, int temperature,
            String fluidName, int fluidAmount) {
        if (!loaded_TCon || FluidRegistry.getFluid(fluidName) == null) return;

        FluidStack fluid = new FluidStack(FluidRegistry.getFluid(fluidName), fluidAmount);

        Smeltery.addMelting(ore, block, blockMeta, temperature, fluid);
    }

    public static void addTConDryingRecipe(Object input, int time, Object output) {
        if (!loaded_TCon) return;
        DryingRackRecipes.addDryingRecipe(input, time, output);
    }

    static final float SANITYBUFF = .02f;

    public static void enviromineDoSaunaStuff(EntityLivingBase player, float deh, float temp) {
        if (!loaded_Enviromine) return;

        EnviroDataTracker tracker = EM_StatusManager.lookupTracker(player);
        float curTemp = tracker.bodyTemp;
        if (curTemp + temp < 37.5f) {
            tracker.bodyTemp = curTemp + temp;
        }

        float curSane = tracker.sanity;
        if (curSane + SANITYBUFF <= 100f) {
            tracker.sanity = curSane + SANITYBUFF;
        }

        float curHyd = tracker.hydration;
        tracker.dehydrate(curHyd > 80 ? deh : 0);
    }

    public static boolean railcraftAllowBlastFurnace() {
        if (!loaded_Railcraft) return false;

        boolean enabled = RailcraftConfig.isSubBlockEnabled("machine.alpha.blast.furnace");
        boolean block = GameRegistry.findBlock("Railcraft", "brick.infernal") != null;
        boolean stair = GameRegistry.findBlock("Railcraft", "stair") != null;

        return enabled && block && stair;
    }

    public static void thaumicTinkererRegisterEnchantment(Enchantment enchantment, String texture, AspectList aspects,
            String research) {
        if (!loaded_TT) return;
        EnchantmentManager.registerExponentialCostData(enchantment, texture, false, aspects, research);
    }
}
