package witchinggadgets.common.recipes.infusion;

import static witchinggadgets.common.recipes.WG_Infusion_recipes.registerInfusionEnchantmentRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Loader;
import fox.spiteful.forbidden.DarkAspects;
import gregtech.api.enums.ItemList;
import gregtech.api.util.GTModHandler;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;

public class WG_infusion_enchantments {

    public static void registerInfusionEnchantments() {
        if (WGConfig.moduleGemcutting) {
            registerInfusionEnchantmentRecipe(
                    "ENCH_INVISIBLEGEAR",
                    "",
                    WGContent.enc_invisibleGear,
                    2,
                    new AspectList().add(Aspect.DARKNESS, 48).add(Aspect.CRYSTAL, 48).add(Aspect.MAGIC, 48)
                            .add(Aspect.ARMOR, 32).add(Aspect.AURA, 24).add(Aspect.SOUL, 8),
                    new ItemStack[] { new ItemStack(Items.quartz), new ItemStack(ConfigItems.itemResource, 1, 14),
                            new ItemStack(WGContent.ItemMaterial, 1, 13),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 0) });
        } else {
            registerInfusionEnchantmentRecipe(
                    "ENCH_INVISIBLEGEAR",
                    "",
                    WGContent.enc_invisibleGear,
                    2,
                    new AspectList().add(Aspect.DARKNESS, 48).add(Aspect.CRYSTAL, 48).add(Aspect.MAGIC, 48)
                            .add(Aspect.ARMOR, 32).add(Aspect.AURA, 24).add(Aspect.SOUL, 8),
                    new ItemStack[] { new ItemStack(Items.quartz), new ItemStack(ConfigItems.itemResource, 1, 14),
                            new ItemStack(ConfigItems.itemEldritchObject, 1, 0) });
        }

        WGModCompat.thaumicTinkererRegisterEnchantment(
                WGContent.enc_invisibleGear,
                "witchinggadgets:textures/gui/research/icon_ench_invisGear.png",
                new AspectList().add(Aspect.DARKNESS, 8).add(Aspect.CRYSTAL, 8).add(Aspect.MAGIC, 8)
                        .add(Aspect.ARMOR, 6).add(Aspect.AURA, 4).add(Aspect.SOUL, 2),
                "ENCH_INVISIBLEGEAR");

        if (WitchingGadgets.isGT5uLoaded) {
            registerInfusionEnchantmentRecipe(
                    "ENCH_UNVEILING",
                    "",
                    WGContent.enc_unveiling,
                    2,
                    new AspectList().add(Aspect.LIGHT, 64).add(Aspect.SENSES, 48).add(Aspect.MAGIC, 48)
                            .add(Aspect.CRYSTAL, 32),
                    new ItemStack[] { new ItemStack(Items.golden_carrot),
                            new ItemStack(ConfigItems.itemResource, 1, 14), ItemList.Emitter_MV.get(1L) });

            registerInfusionEnchantmentRecipe(
                    "ENCH_STEALTH",
                    "",
                    WGContent.enc_stealth,
                    2,
                    new AspectList().add(Aspect.MOTION, 64).add(Aspect.DARKNESS, 48).add(Aspect.MAGIC, 48)
                            .add(Aspect.GREED, 32),
                    new ItemStack[] { new ItemStack(Items.potionitem, 1, 8206),
                            new ItemStack(ConfigItems.itemResource, 1, 14), ItemList.Sensor_MV.get(1L) });

            if (Loader.isModLoaded("ForbiddenMagic")) {
                registerInfusionEnchantmentRecipe(
                        "ENCH_BACKSTAB",
                        "",
                        WGContent.enc_backstab,
                        3,
                        new AspectList().add(Aspect.WEAPON, 48).add(DarkAspects.ENVY, 32).add(Aspect.MAGIC, 16),
                        new ItemStack[] { GTModHandler.getModItem("TConstruct", "knifeBlade", 1L, 2),
                                new ItemStack(Items.potionitem, 1, 8206),
                                new ItemStack(ConfigItems.itemResource, 1, 14) });
            } else {
                registerInfusionEnchantmentRecipe(
                        "ENCH_BACKSTAB",
                        "",
                        WGContent.enc_backstab,
                        3,
                        new AspectList().add(Aspect.WEAPON, 48).add(Aspect.GREED, 32).add(Aspect.MAGIC, 16),
                        new ItemStack[] { GTModHandler.getModItem("TConstruct", "knifeBlade", 1L, 2),
                                new ItemStack(Items.potionitem, 1, 8206),
                                new ItemStack(ConfigItems.itemResource, 1, 14) });
            }

        } else {
            registerInfusionEnchantmentRecipe(
                    "ENCH_UNVEILING",
                    "",
                    WGContent.enc_unveiling,
                    2,
                    new AspectList().add(Aspect.LIGHT, 4).add(Aspect.SENSES, 8).add(Aspect.MAGIC, 8),
                    new ItemStack[] { new ItemStack(Items.golden_carrot),
                            new ItemStack(ConfigItems.itemResource, 1, 14) });

            registerInfusionEnchantmentRecipe(
                    "ENCH_STEALTH",
                    "",
                    WGContent.enc_stealth,
                    2,
                    new AspectList().add(Aspect.MOTION, 6).add(Aspect.DARKNESS, 8).add(Aspect.MAGIC, 8),
                    new ItemStack[] { new ItemStack(Items.potionitem, 1, 8206),
                            new ItemStack(ConfigItems.itemResource, 1, 14) });

            registerInfusionEnchantmentRecipe(
                    "ENCH_BACKSTAB",
                    "",
                    WGContent.enc_backstab,
                    3,
                    new AspectList().add(Aspect.WEAPON, 12).add(Aspect.DARKNESS, 8).add(Aspect.MAGIC, 4),
                    new ItemStack[] { new ItemStack(Items.iron_sword), new ItemStack(Items.potionitem, 1, 8206),
                            new ItemStack(ConfigItems.itemResource, 1, 14) });

        }

        WGModCompat.thaumicTinkererRegisterEnchantment(
                WGContent.enc_unveiling,
                "witchinggadgets:textures/gui/research/icon_ench_unveiling.png",
                new AspectList().add(Aspect.LIGHT, 16).add(Aspect.SENSES, 8).add(Aspect.MAGIC, 8)
                        .add(Aspect.CRYSTAL, 4),
                "ENCH_UNVEILING");

        WGModCompat.thaumicTinkererRegisterEnchantment(
                WGContent.enc_stealth,
                "witchinggadgets:textures/gui/research/icon_ench_stealth.png",
                new AspectList().add(Aspect.MOTION, 16).add(Aspect.DARKNESS, 8).add(Aspect.MAGIC, 8)
                        .add(Aspect.GREED, 4),
                "ENCH_STEALTH");

        if (Loader.isModLoaded("ForbiddenMagic")) {
            WGModCompat.thaumicTinkererRegisterEnchantment(
                    WGContent.enc_backstab,
                    "witchinggadgets:textures/gui/research/icon_ench_backstab.png",
                    new AspectList().add(Aspect.WEAPON, 12).add(DarkAspects.ENVY, 8).add(Aspect.MAGIC, 4),
                    "ENCH_BACKSTAB");
        } else {
            WGModCompat.thaumicTinkererRegisterEnchantment(
                    WGContent.enc_backstab,
                    "witchinggadgets:textures/gui/research/icon_ench_backstab.png",
                    new AspectList().add(Aspect.WEAPON, 12).add(Aspect.ENTROPY, 20).add(Aspect.MAGIC, 4),
                    "ENCH_BACKSTAB");
        }

        registerInfusionEnchantmentRecipe(
                "ENCH_RIDEPROTECT",
                "",
                WGContent.enc_rideProtect,
                3,
                new AspectList().add(Aspect.ARMOR, 48).add(Aspect.TRAP, 32).add(Aspect.MAGIC, 16),
                new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Blocks.piston),
                        new ItemStack(Blocks.piston), new ItemStack(Blocks.piston), new ItemStack(Blocks.piston) });

        WGModCompat.thaumicTinkererRegisterEnchantment(
                WGContent.enc_rideProtect,
                "witchinggadgets:textures/gui/research/icon_ench_rideProtect.png",
                new AspectList().add(Aspect.AIR, 20).add(Aspect.ENTROPY, 20).add(Aspect.ORDER, 20),
                "ENCH_RIDEPROTECT");

        registerInfusionEnchantmentRecipe(
                "ENCH_SOULBOUND",
                "",
                WGContent.enc_soulbound,
                1,
                new AspectList().add(Aspect.SOUL, 64).add(Aspect.MAGIC, 48).add(Aspect.GREED, 24)
                        .add(Aspect.ELDRITCH, 32),
                new ItemStack[] { new ItemStack(Items.ender_eye), new ItemStack(Items.ender_pearl),
                        new ItemStack(Items.name_tag) });

        WGModCompat.thaumicTinkererRegisterEnchantment(
                WGContent.enc_soulbound,
                "witchinggadgets:textures/gui/research/icon_ench_soulbound.png",
                new AspectList().add(Aspect.AIR, 10).add(Aspect.ENTROPY, 10).add(Aspect.ORDER, 20),
                "ENCH_SOULBOUND");
    }
}
