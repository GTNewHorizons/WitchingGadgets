package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionRecipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;

public class WG_infusion_primordial_gear {

    public static void registerPrimordialGear() {
        registerInfusionRecipe(
                "EMPOWERPEARL",
                "",
                new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                10,
                new AspectList().add(Aspect.AIR, 64).add(Aspect.FIRE, 64).add(Aspect.EARTH, 64).add(Aspect.WATER, 64)
                        .add(Aspect.ORDER, 64).add(Aspect.ENTROPY, 64).add(Aspect.MAGIC, 384),
                new ItemStack(WGContent.ItemMaterial, 1, 12),
                new ItemStack[] { new ItemStack(ConfigBlocks.blockCrystal, 1, 0),
                        new ItemStack(ConfigBlocks.blockCrystal, 1, 1), new ItemStack(ConfigBlocks.blockCrystal, 1, 2),
                        new ItemStack(ConfigBlocks.blockCrystal, 1, 3), new ItemStack(ConfigBlocks.blockCrystal, 1, 4),
                        new ItemStack(ConfigBlocks.blockCrystal, 1, 5),
                        new ItemStack(ConfigBlocks.blockCrystal, 1, 6) });

        if (WGConfig.moduleGemcutting) {
            registerInfusionRecipe(
                    "PRIMORDIALGLOVE",
                    "",
                    new ItemStack(WGContent.ItemPrimordialGlove),
                    6,
                    new AspectList().add(Aspect.MAGIC, 512).add(Aspect.CRYSTAL, 256).add(Aspect.TOOL, 128)
                            .add(Aspect.AIR, 128).add(Aspect.FIRE, 128).add(Aspect.WATER, 128).add(Aspect.EARTH, 128)
                            .add(Aspect.ORDER, 128).add(Aspect.ENTROPY, 128),
                    new ItemStack(ConfigBlocks.blockStoneDevice, 1, 11),
                    new ItemStack[] { new ItemStack(WGContent.ItemMaterial, 1, 5),
                            new ItemStack(ConfigItems.itemResource, 1, 17),
                            GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                            new ItemStack(ConfigItems.itemResource, 1, 17) });
        }

        registerInfusionRecipe(
                "PRIMORDIALWEAPONRY",
                "_CLAYMORE",
                new ItemStack(WGContent.ItemPrimordialSword),
                10,
                new AspectList().add(Aspect.MAGIC, 512).add(Aspect.METAL, 1024).add(Aspect.WEAPON, 512)
                        .add(Aspect.AIR, 256).add(Aspect.FIRE, 256).add(Aspect.WATER, 256).add(Aspect.EARTH, 256)
                        .add(Aspect.ORDER, 256).add(Aspect.ENTROPY, 256),
                GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),
                new ItemStack[] { new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemSwordVoid),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                        new ItemStack(ConfigItems.itemWandRod, 1, 0), new ItemStack(ConfigItems.itemResource, 1, 15),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemSwordVoid) });

        registerInfusionRecipe(
                "PRIMORDIALWEAPONRY",
                "_HAMMER",
                new ItemStack(WGContent.ItemPrimordialHammer),
                10,
                new AspectList().add(Aspect.MAGIC, 512).add(Aspect.METAL, 1024).add(Aspect.TOOL, 256)
                        .add(Aspect.WEAPON, 256).add(Aspect.AIR, 256).add(Aspect.FIRE, 256).add(Aspect.WATER, 256)
                        .add(Aspect.EARTH, 256).add(Aspect.ORDER, 256).add(Aspect.ENTROPY, 256),
                GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),
                new ItemStack[] { new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemPickVoid),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                        new ItemStack(ConfigItems.itemWandRod, 1, 0), new ItemStack(ConfigItems.itemResource, 1, 15),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemSwordVoid) });

        registerInfusionRecipe(
                "PRIMORDIALWEAPONRY",
                "_GREATAXE",
                new ItemStack(WGContent.ItemPrimordialAxe),
                10,
                new AspectList().add(Aspect.MAGIC, 512).add(Aspect.METAL, 1024).add(Aspect.TOOL, 256)
                        .add(Aspect.WEAPON, 256).add(Aspect.AIR, 256).add(Aspect.FIRE, 256).add(Aspect.WATER, 256)
                        .add(Aspect.EARTH, 256).add(Aspect.ORDER, 256).add(Aspect.ENTROPY, 256),
                GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),
                new ItemStack[] { new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemAxeVoid),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                        new ItemStack(ConfigItems.itemWandRod, 1, 0), new ItemStack(ConfigItems.itemResource, 1, 15),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemAxeVoid) });

        registerInfusionRecipe(
                "PRIMORDIALARMOR",
                "_HELMET",
                new ItemStack(WGContent.ItemPrimordialHelm),
                10,
                new AspectList().add(Aspect.MAGIC, 512).add(Aspect.METAL, 1024).add(Aspect.ARMOR, 256)
                        .add(Aspect.AIR, 256).add(Aspect.FIRE, 256).add(Aspect.WATER, 256).add(Aspect.EARTH, 256)
                        .add(Aspect.ORDER, 256).add(Aspect.ENTROPY, 256),
                GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),
                new ItemStack[] { new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                        GT_ModHandler.getModItem("TaintedMagic", "ItemShadowFortressHelmet", 1L),
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                        new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence),
                        new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence) });

        registerInfusionRecipe(
                "PRIMORDIALARMOR",
                "_CUIRASS",
                new ItemStack(WGContent.ItemPrimordialChest),
                10,
                new AspectList().add(Aspect.MAGIC, 512).add(Aspect.METAL, 1024).add(Aspect.ARMOR, 256)
                        .add(Aspect.AIR, 256).add(Aspect.FIRE, 256).add(Aspect.WATER, 256).add(Aspect.EARTH, 256)
                        .add(Aspect.ORDER, 256).add(Aspect.ENTROPY, 256),
                GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),
                new ItemStack[] { new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                        GT_ModHandler.getModItem("TaintedMagic", "ItemShadowFortressChestplate", 1L),
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                        new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence),
                        new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence) });

        registerInfusionRecipe(
                "PRIMORDIALARMOR",
                "_GREAVES",
                new ItemStack(WGContent.ItemPrimordialLegs),
                10,
                new AspectList().add(Aspect.MAGIC, 512).add(Aspect.METAL, 1024).add(Aspect.ARMOR, 256)
                        .add(Aspect.AIR, 256).add(Aspect.FIRE, 256).add(Aspect.WATER, 256).add(Aspect.EARTH, 256)
                        .add(Aspect.ORDER, 256).add(Aspect.ENTROPY, 256),
                GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),
                new ItemStack[] { new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                        GT_ModHandler.getModItem("TaintedMagic", "ItemShadowFortressLeggings", 1L),
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                        new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence),
                        new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence) });

        registerInfusionRecipe(
                "PRIMORDIALARMOR",
                "_BOOTS",
                new ItemStack(WGContent.ItemPrimordialBoots),
                10,
                new AspectList().add(Aspect.MAGIC, 512).add(Aspect.METAL, 1024).add(Aspect.ARMOR, 256)
                        .add(Aspect.AIR, 256).add(Aspect.FIRE, 256).add(Aspect.WATER, 256).add(Aspect.EARTH, 256)
                        .add(Aspect.ORDER, 256).add(Aspect.ENTROPY, 256),
                GT_OreDictUnificator.get(OrePrefixes.block, Materials.Void, 1L),
                new ItemStack[] { new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                        new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                        GT_ModHandler.getModItem("TaintedMagic", "ItemVoidwalkerBoots", 1L),
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                        new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence),
                        new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence) });

        registerInfusionRecipe(
                "HELMGOGGLES",
                "_PRIMORDIAL",
                new Object[] { "goggles", new NBTTagByte((byte) 1) },
                5,
                new AspectList().add(Aspect.SENSES, 256).add(Aspect.AURA, 128).add(Aspect.ARMOR, 128),
                new ItemStack(WGContent.ItemPrimordialHelm, 1, 32767),
                new ItemStack[] { new ItemStack(Items.slime_ball), new ItemStack(ConfigItems.itemGoggles, 1, 32767) });

        registerInfusionRecipe(
                "MASKGRINNINGDEVIL",
                "_PRIMORDIAL",
                new Object[] { "mask", new NBTTagByte((byte) 0) },
                8,
                new AspectList().add(Aspect.MIND, 512).add(Aspect.HEAL, 512).add(Aspect.ARMOR, 128),
                new ItemStack(WGContent.ItemPrimordialHelm, 1, 32767),
                new ItemStack[] { new ItemStack(Items.dye, 1, 0), new ItemStack(Items.iron_ingot),
                        new ItemStack(Items.leather), new ItemStack(ConfigBlocks.blockCustomPlant, 1, 2),
                        new ItemStack(ConfigItems.itemZombieBrain), new ItemStack(Items.iron_ingot) });

        registerInfusionRecipe(
                "MASKANGRYGHOST",
                "_PRIMORDIAL",
                new Object[] { "mask", new NBTTagByte((byte) 1) },
                8,
                new AspectList().add(Aspect.ENTROPY, 512).add(Aspect.DEATH, 512).add(Aspect.ARMOR, 128),
                new ItemStack(WGContent.ItemPrimordialHelm, 1, 32767),
                new ItemStack[] { new ItemStack(Items.dye, 1, 15), new ItemStack(Items.iron_ingot),
                        new ItemStack(Items.leather), new ItemStack(Items.poisonous_potato),
                        new ItemStack(Items.skull, 1, 1), new ItemStack(Items.iron_ingot) });

        registerInfusionRecipe(
                "MASKSIPPINGFIEND",
                "_PRIMORDIAL",
                new Object[] { "mask", new NBTTagByte((byte) 2) },
                8,
                new AspectList().add(Aspect.UNDEAD, 512).add(Aspect.LIFE, 512).add(Aspect.ARMOR, 128),
                new ItemStack(WGContent.ItemPrimordialHelm, 1, 32767),
                new ItemStack[] { new ItemStack(Items.dye, 1, 1), new ItemStack(Items.iron_ingot),
                        new ItemStack(Items.leather), new ItemStack(Items.ghast_tear), new ItemStack(Items.milk_bucket),
                        new ItemStack(Items.iron_ingot) });
    }

}
