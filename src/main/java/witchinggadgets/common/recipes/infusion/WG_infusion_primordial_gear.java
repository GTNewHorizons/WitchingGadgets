package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionRecipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;

import cpw.mods.fml.common.Loader;
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
        if (Loader.isModLoaded("gregtech") && !Loader.isModLoaded("gregapi")) {
            registerInfusionRecipe(
                    "EMPOWERPEARL",
                    "",
                    new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                    10,
                    new AspectList().add(Aspect.AIR, 64).add(Aspect.FIRE, 64).add(Aspect.EARTH, 64)
                            .add(Aspect.WATER, 64).add(Aspect.ORDER, 64).add(Aspect.ENTROPY, 64).add(Aspect.MAGIC, 384),
                    new ItemStack(WGContent.ItemMaterial, 1, 12),
                    new ItemStack[] { new ItemStack(ConfigBlocks.blockCrystal, 1, 0),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 1),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 2),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 3),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 4),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 5),
                            new ItemStack(ConfigBlocks.blockCrystal, 1, 6) });

            if (WGConfig.moduleGemcutting) {
                registerInfusionRecipe(
                        "PRIMORDIALGLOVE",
                        "",
                        new ItemStack(WGContent.ItemPrimordialGlove),
                        6,
                        new AspectList().add(Aspect.MAGIC, 512).add(Aspect.CRYSTAL, 256).add(Aspect.TOOL, 128)
                                .add(Aspect.AIR, 128).add(Aspect.FIRE, 128).add(Aspect.WATER, 128)
                                .add(Aspect.EARTH, 128).add(Aspect.ORDER, 128).add(Aspect.ENTROPY, 128),
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
                    new ItemStack[] { new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemSwordVoid), new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWandRod, 1, 0),
                            new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
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
                    new ItemStack[] { new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemPickVoid), new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWandRod, 1, 0),
                            new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
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
                    new ItemStack[] { new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemAxeVoid), new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWandRod, 1, 0),
                            new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
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
                            new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                            GT_ModHandler.getModItem("TaintedMagic", "ItemShadowFortressHelmet", 1L),
                            GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWispEssence) });

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
                            new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                            GT_ModHandler.getModItem("TaintedMagic", "ItemShadowFortressChestplate", 1L),
                            GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWispEssence) });

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
                            new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                            GT_ModHandler.getModItem("TaintedMagic", "ItemShadowFortressLeggings", 1L),
                            GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWispEssence) });

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
                            new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                            GT_ModHandler.getModItem("TaintedMagic", "ItemVoidwalkerBoots", 1L),
                            GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Shadow, 1L),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWispEssence) });

            registerInfusionRecipe(
                    "HELMGOGGLES",
                    "_PRIMORDIAL",
                    new Object[] { "goggles", new NBTTagByte((byte) 1) },
                    5,
                    new AspectList().add(Aspect.SENSES, 256).add(Aspect.AURA, 128).add(Aspect.ARMOR, 128),
                    new ItemStack(WGContent.ItemPrimordialHelm, 1, 32767),
                    new ItemStack[] { new ItemStack(Items.slime_ball),
                            new ItemStack(ConfigItems.itemGoggles, 1, 32767) });

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
                            new ItemStack(Items.leather), new ItemStack(Items.ghast_tear),
                            new ItemStack(Items.milk_bucket), new ItemStack(Items.iron_ingot) });
        } else {
            registerInfusionRecipe(
                    "EMPOWERPEARL",
                    "",
                    new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                    3,
                    new AspectList().add(Aspect.AIR, 16).add(Aspect.FIRE, 16).add(Aspect.EARTH, 16)
                            .add(Aspect.WATER, 16).add(Aspect.ORDER, 16).add(Aspect.ENTROPY, 16),
                    new ItemStack(WGContent.ItemMaterial, 1, 12),
                    new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 0),
                            new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(ConfigItems.itemShard, 1, 2),
                            new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemShard, 1, 4),
                            new ItemStack(ConfigItems.itemShard, 1, 5) });

            if (WGConfig.moduleGemcutting) {
                registerInfusionRecipe(
                        "PRIMORDIALGLOVE",
                        "",
                        new ItemStack(WGContent.ItemPrimordialGlove),
                        6,
                        new AspectList().add(Aspect.MAGIC, 32).add(Aspect.CRYSTAL, 16).add(Aspect.TOOL, 8)
                                .add(Aspect.AIR, 8).add(Aspect.FIRE, 8).add(Aspect.WATER, 8).add(Aspect.EARTH, 8)
                                .add(Aspect.ORDER, 8).add(Aspect.ENTROPY, 8),
                        new ItemStack(ConfigBlocks.blockStoneDevice, 1, 11),
                        new ItemStack[] { new ItemStack(WGContent.ItemMaterial, 1, 5),
                                new ItemStack(ConfigItems.itemResource, 1, 17),
                                new ItemStack(ConfigItems.itemResource, 1, 16),
                                new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                                new ItemStack(ConfigItems.itemResource, 1, 16),
                                new ItemStack(ConfigItems.itemResource, 1, 17) });
            }

            registerInfusionRecipe(
                    "PRIMORDIALWEAPONRY",
                    "_CLAYMORE",
                    new ItemStack(WGContent.ItemPrimordialSword),
                    10,
                    new AspectList().add(Aspect.MAGIC, 64).add(Aspect.METAL, 128).add(Aspect.WEAPON, 64)
                            .add(Aspect.AIR, 32).add(Aspect.FIRE, 32).add(Aspect.WATER, 32).add(Aspect.EARTH, 32)
                            .add(Aspect.ORDER, 32).add(Aspect.ENTROPY, 32),
                    new ItemStack(WGContent.BlockMetalDevice, 1, 1),
                    new ItemStack[] { new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemSwordVoid), new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWandRod, 1, 0),
                            new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemSwordVoid) });

            registerInfusionRecipe(
                    "PRIMORDIALWEAPONRY",
                    "_HAMMER",
                    new ItemStack(WGContent.ItemPrimordialHammer),
                    10,
                    new AspectList().add(Aspect.MAGIC, 64).add(Aspect.METAL, 128).add(Aspect.TOOL, 32)
                            .add(Aspect.WEAPON, 32).add(Aspect.AIR, 32).add(Aspect.FIRE, 32).add(Aspect.WATER, 32)
                            .add(Aspect.EARTH, 32).add(Aspect.ORDER, 32).add(Aspect.ENTROPY, 32),
                    new ItemStack(WGContent.BlockMetalDevice, 1, 1),
                    new ItemStack[] { new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemPickVoid), new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWandRod, 1, 0),
                            new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemSwordVoid) });

            registerInfusionRecipe(
                    "PRIMORDIALWEAPONRY",
                    "_GREATAXE",
                    new ItemStack(WGContent.ItemPrimordialAxe),
                    10,
                    new AspectList().add(Aspect.MAGIC, 64).add(Aspect.METAL, 128).add(Aspect.TOOL, 32)
                            .add(Aspect.WEAPON, 32).add(Aspect.AIR, 32).add(Aspect.FIRE, 32).add(Aspect.WATER, 32)
                            .add(Aspect.EARTH, 32).add(Aspect.ORDER, 32).add(Aspect.ENTROPY, 32),
                    new ItemStack(WGContent.BlockMetalDevice, 1, 1),
                    new ItemStack[] { new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemAxeVoid), new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWandRod, 1, 0),
                            new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemAxeVoid) });

            registerInfusionRecipe(
                    "PRIMORDIALARMOR",
                    "_HELMET",
                    new ItemStack(WGContent.ItemPrimordialHelm),
                    10,
                    new AspectList().add(Aspect.MAGIC, 64).add(Aspect.METAL, 128).add(Aspect.ARMOR, 32)
                            .add(Aspect.AIR, 32).add(Aspect.FIRE, 32).add(Aspect.WATER, 32).add(Aspect.EARTH, 32)
                            .add(Aspect.ORDER, 32).add(Aspect.ENTROPY, 32),
                    new ItemStack(WGContent.BlockMetalDevice, 1, 1),
                    new ItemStack[] { new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemResource, 1, 16),
                            new ItemStack(ConfigItems.itemHelmetFortress),
                            new ItemStack(ConfigItems.itemResource, 1, 16),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWispEssence) });

            registerInfusionRecipe(
                    "PRIMORDIALARMOR",
                    "_CUIRASS",
                    new ItemStack(WGContent.ItemPrimordialChest),
                    10,
                    new AspectList().add(Aspect.MAGIC, 64).add(Aspect.METAL, 128).add(Aspect.ARMOR, 32)
                            .add(Aspect.AIR, 32).add(Aspect.FIRE, 32).add(Aspect.WATER, 32).add(Aspect.EARTH, 32)
                            .add(Aspect.ORDER, 32).add(Aspect.ENTROPY, 32),
                    new ItemStack(WGContent.BlockMetalDevice, 1, 1),
                    new ItemStack[] { new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemResource, 1, 16),
                            new ItemStack(ConfigItems.itemChestFortress),
                            new ItemStack(ConfigItems.itemResource, 1, 16),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWispEssence) });

            registerInfusionRecipe(
                    "PRIMORDIALARMOR",
                    "_GREAVES",
                    new ItemStack(WGContent.ItemPrimordialLegs),
                    10,
                    new AspectList().add(Aspect.MAGIC, 64).add(Aspect.METAL, 128).add(Aspect.ARMOR, 32)
                            .add(Aspect.AIR, 32).add(Aspect.FIRE, 32).add(Aspect.WATER, 32).add(Aspect.EARTH, 32)
                            .add(Aspect.ORDER, 32).add(Aspect.ENTROPY, 32),
                    new ItemStack(WGContent.BlockMetalDevice, 1, 1),
                    new ItemStack[] { new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemLegsFortress),
                            new ItemStack(ConfigItems.itemResource, 1, 16),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWispEssence) });

            registerInfusionRecipe(
                    "PRIMORDIALARMOR",
                    "_BOOTS",
                    new ItemStack(WGContent.ItemPrimordialBoots),
                    10,
                    new AspectList().add(Aspect.MAGIC, 64).add(Aspect.METAL, 128).add(Aspect.ARMOR, 32)
                            .add(Aspect.AIR, 32).add(Aspect.FIRE, 32).add(Aspect.WATER, 32).add(Aspect.EARTH, 32)
                            .add(Aspect.ORDER, 32).add(Aspect.ENTROPY, 32),
                    new ItemStack(WGContent.BlockMetalDevice, 1, 1),
                    new ItemStack[] { new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWispEssence),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemResource, 1, 16),
                            new ItemStack(ConfigItems.itemBootsTraveller),
                            new ItemStack(ConfigItems.itemResource, 1, 16),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 3),
                            new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15),
                            new ItemStack(ConfigItems.itemWispEssence) });

            registerInfusionRecipe(
                    "HELMGOGGLES",
                    "_PRIMORDIAL",
                    new Object[] { "goggles", new NBTTagByte((byte) 1) },
                    5,
                    new AspectList().add(Aspect.SENSES, 32).add(Aspect.AURA, 16).add(Aspect.ARMOR, 16),
                    new ItemStack(WGContent.ItemPrimordialHelm, 1, 32767),
                    new ItemStack[] { new ItemStack(Items.slime_ball),
                            new ItemStack(ConfigItems.itemGoggles, 1, 32767) });

            registerInfusionRecipe(
                    "MASKGRINNINGDEVIL",
                    "_PRIMORDIAL",
                    new Object[] { "mask", new NBTTagByte((byte) 0) },
                    8,
                    new AspectList().add(Aspect.MIND, 64).add(Aspect.HEAL, 64).add(Aspect.ARMOR, 16),
                    new ItemStack(WGContent.ItemPrimordialHelm, 1, 32767),
                    new ItemStack[] { new ItemStack(Items.dye, 1, 0), new ItemStack(Items.iron_ingot),
                            new ItemStack(Items.leather), new ItemStack(ConfigBlocks.blockCustomPlant, 1, 2),
                            new ItemStack(ConfigItems.itemZombieBrain), new ItemStack(Items.iron_ingot) });

            registerInfusionRecipe(
                    "MASKANGRYGHOST",
                    "_PRIMORDIAL",
                    new Object[] { "mask", new NBTTagByte((byte) 1) },
                    8,
                    new AspectList().add(Aspect.ENTROPY, 64).add(Aspect.DEATH, 64).add(Aspect.ARMOR, 16),
                    new ItemStack(WGContent.ItemPrimordialHelm, 1, 32767),
                    new ItemStack[] { new ItemStack(Items.dye, 1, 15), new ItemStack(Items.iron_ingot),
                            new ItemStack(Items.leather), new ItemStack(Items.poisonous_potato),
                            new ItemStack(Items.skull, 1, 1), new ItemStack(Items.iron_ingot) });

            registerInfusionRecipe(
                    "MASKSIPPINGFIEND",
                    "_PRIMORDIAL",
                    new Object[] { "mask", new NBTTagByte((byte) 2) },
                    8,
                    new AspectList().add(Aspect.UNDEAD, 64).add(Aspect.LIFE, 64).add(Aspect.ARMOR, 16),
                    new ItemStack(WGContent.ItemPrimordialHelm, 1, 32767),
                    new ItemStack[] { new ItemStack(Items.dye, 1, 1), new ItemStack(Items.iron_ingot),
                            new ItemStack(Items.leather), new ItemStack(Items.ghast_tear),
                            new ItemStack(Items.milk_bucket), new ItemStack(Items.iron_ingot) });

        }
    }

}
