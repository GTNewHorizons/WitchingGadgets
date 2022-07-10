package witchinggadgets.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import witchinggadgets.common.util.Utilities;

public class WGConfig {
    public static boolean limitBookSearchToCategory,
            allowClusters,
            allowTransmutations,
            coremod_allowBootsRepair,
            coremod_allowEnchantModifications,
            coremod_allowPotionApplicationMod,
            coremod_allowFocusPouchActive,
            modulePrimal,
            moduleBag,
            bagVoid,
            bagEnder,
            bagHungry,
            moduleCloak,
            moduleKama,
            capeSpectral,
            capeStorage,
            capeWolf,
            capeRaven,
            enableSearch,
            soulboundBaubles,
            soulboundGalacticraft,
            moduleGemcutting,
            allowdropsfrommachinery,
            terraformer;
    public static String[] tripplingClusterList, blocksforWGBF;
    public static int smelteryResultForClusters, cloakAnimationMode;
    public static Block[] coremod_worldgenValidBase_HilltopStones, coremod_worldgenValidBase_EldritchRing;

    public static float radialSpeed;
    public static final String[]
            DEFAULTCLUSTERS =
                    {
                        "Infinity",
                        "Infinity Catalyst",
                        "Neutronium",
                        "Indium",
                        "Iridium",
                        "Osmium",
                        "Naquadah",
                        "Niobium",
                        "Americium",
                        "Uranium",
                        "Uranium235",
                        "Thorium",
                        "DeepIron",
                        "Naquadria",
                        "Plutonium",
                        "Plutonium241",
                        "Europium",
                        "Tungsten",
                        "BlackPlutonium",
                        "CosmicNeutronium",
                        "Titanium",
                        "Trinium",
                        "Yttrium",
                        "Platinum",
                        "Draconium",
                        "Oriharukon",
                        "Samarium",
                        "Electrum",
                        "Curium",
                        "Californium",
                        "Flerovium",
                        "Desh",
                        "Ichorium",
                        "InfusedGold",
                        "Palladium",
                        "Quantium"
                    },
            DEFAULTBLOCKS = {"Railcraft:brick.infernal", "Railcraft:stair"};

    static Configuration config;

    public static void loadConfig(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        // Random Config Options
        allowdropsfrommachinery = config.getBoolean(
                "Drop Items from Generators",
                "Other Options",
                false,
                "Whether the cobble and snow gen can drop Items or just put out into inventorys, should be disabled on MP servers with chunkloading");
        smelteryResultForClusters = config.get(
                        "Other Options",
                        "Smeltery Result for Clusters",
                        144 * 3,
                        "How many milliBuckets of molten Metal a cluster should give. 144mB equal 1 ingot. Set to 0 to disable smeltery recipes.")
                .getInt();
        allowClusters = config.get(
                        "Ore/Crucible",
                        "Enable clusters",
                        true,
                        "Set this to false to disable clusters, useful when you are using AOBD.")
                .getBoolean(true);
        allowTransmutations = config.get(
                        "Ore/Crucible",
                        "Enable transmutations",
                        true,
                        "Set this to false to disable nugget transmutations, this should fix the infinite loop glitch")
                .getBoolean(true);
        terraformer = config.getBoolean("Enable Terraformer", "Other Options", true, "If the Terraformer is enabled");
        tripplingClusterList = config.get(
                        "Ore/Crucible",
                        "Not Trippling Cluster List",
                        DEFAULTCLUSTERS,
                        "A list of ore names for which no native Cluster will get generated")
                .getStringList();

        blocksforWGBF = config.get(
                        "Other Options",
                        "Blocks for the WG Blast Furnace",
                        DEFAULTBLOCKS,
                        "Insert the Modname and the blockname for the WG Blast Furnace. The first pair is for the construction of the main ofen, the second pair for the stairs on top. Must be exactly 2 Values!")
                .getStringList();
        // search
        limitBookSearchToCategory = config.get(
                        "Search",
                        "Limit Thaumonomicon Search to currently active category",
                        false,
                        "Thaumonomicon Search to currently active category")
                .getBoolean(false);

        enableSearch = config.get(
                        "Search",
                        "Enables the search function in the Thaumonomicon",
                        true,
                        "Thaumonomicon Search enabled")
                .getBoolean(true);

        // cloak related
        radialSpeed = config.getFloat(
                "Selection Radial Speed",
                "Other Options",
                .15f,
                .15f,
                1,
                "The speed at which the gem-selection for the primordial glove opens. 15% is the minimum.");
        cloakAnimationMode = config.get(
                        "Other Options",
                        "Cloak Animation Mode",
                        2,
                        "0 = no animation, 1 = rotate cloak when legs move, 2 = stretch cloak when legs move")
                .getInt();
        coremod_allowFocusPouchActive = config.get(
                        "Other Options",
                        "Allow FocusPouch active ability",
                        true,
                        "Dis-/enable the IActiveAbiltiy on the FocusPouch. With this enabled, TGs active ability menu will allow you to open the pouch.")
                .getBoolean(true);

        coremod_allowBootsRepair = config.get(
                        "Other Options",
                        "Dis-/enable repairing the Boots of the Traveller with leather",
                        true,
                        "Dis-/enable repairing the Boots of the Traveller with leather")
                .getBoolean(true);

        // coin
        coremod_allowEnchantModifications = config.get(
                        "Other Options",
                        "Dis-/enable the modification of looting and fortune modifications with the Ring of the Covetous Coin",
                        true,
                        "Dis-/enable the modification of looting and fortune modifications with the Ring of the Covetous Coin")
                .getBoolean(true);

        // primal
        coremod_allowPotionApplicationMod = config.get(
                        "Other Options",
                        "Allow modifications to newly applied PotionEffects",
                        true,
                        "Dis-/enable the modification of newly applied PotionEffects. (Primordial Armor affects newly applied Warp Effects)")
                .getBoolean(true);

        // new
        modulePrimal = config.get(
                        "Modules",
                        "Enable Primordial gear and weapons",
                        true,
                        "Dis-/enable the primordial gauntlet, armor and weapons")
                .getBoolean(true);
        moduleBag = config.get("Modules", "Enable Bags", true, "Enable Bags").getBoolean(true);
        bagVoid = config.get(
                        "Modules", "Enable Voidlinked Bag", true, "Enable Voidlinked Bag. Requires Bags to be enabled")
                .getBoolean(true);
        bagEnder = config.get(
                        "Modules",
                        "Enable Enderlinked Bag",
                        true,
                        "Enable Enderlinked Bag. Requires Bags to be enabled")
                .getBoolean(true);
        bagHungry = config.get("Modules", "Enable Hungry Bag", true, "Enable Hungry Bag. Requires Bags to be enabled")
                .getBoolean(true);
        moduleCloak =
                config.get("Modules", "Enable Cloaks", true, "Enable Cloaks").getBoolean(true);
        moduleKama = config.get(
                        "Modules",
                        "Enable Kamas and variants",
                        true,
                        "Enable Kamas and variants. Requires Cloaks, and respective capes to be enabled")
                .getBoolean(true);
        capeSpectral = config.get(
                        "Modules",
                        "Enable Spectral Mantle",
                        true,
                        "Enable Spectral Mantle. Requires Cloaks to be enabled")
                .getBoolean(true);
        capeStorage = config.get(
                        "Modules", "Enable Cloak of Voluminous Pockets", true, "Enable Cloak of Voluminous Pockets")
                .getBoolean(true);
        capeWolf = config.get(
                        "Modules", "Enable Wolven Cloak", true, "Enable Wolven Cloak. Requires Cloaks to be enabled")
                .getBoolean(true);
        capeRaven = config.get(
                        "Modules",
                        "Enable Mantle of the Raven",
                        true,
                        "Enable Mantle of the Raven. Requires Cloaks to be enabled, and Twilight Forest installed")
                .getBoolean(true);

        moduleGemcutting = config.get(
                        "Modules", "Enable Gemcutting", true, "Dis-/enable the gem table, and other crystal things")
                .getBoolean(true);

        // enchants
        soulboundBaubles = config.get(
                        "Enchantments",
                        "Soul Tether handles Baubles inventory",
                        true,
                        "Set to false to disable Soul Tether from handling Baubles inventory if handled by another mod's soulbound, such as EnderIO.")
                .getBoolean(true);

        soulboundGalacticraft = config.get(
                        "Enchantments",
                        "Soul Tether handles Galacticraft inventory",
                        true,
                        "Set to false to disable Soul Tether from handling Galacricraft inventory if handled by another mod's soulbound, such as EnderIO.")
                .getBoolean(true);

        // spawn settings for structures
        String[] cm_allowedSpawnblocks_HilltopStones = config.getStringList(
                "Valid generation bases: HilltopStones",
                "Other",
                new String[] {
                    "minecraft:stone",
                    "minecraft:sand",
                    "minecraft:packed_ice",
                    "minecraft:grass",
                    "minecraft:gravel",
                    "minecraft:dirt"
                },
                "A list of valid blocks that Thaumcraft's hilltop stones can spawn upon");
        Set<Block> validBlocks = new HashSet();
        for (String cm_allowedSpawnblocks_hilltopStone : cm_allowedSpawnblocks_HilltopStones) {
            String[] ssA = cm_allowedSpawnblocks_hilltopStone.split(":", 2);
            if (ssA.length > 1) {
                Block b = GameRegistry.findBlock(ssA[0], ssA[1]);
                if (b != null) validBlocks.add(b);
            }
        }
        coremod_worldgenValidBase_HilltopStones = validBlocks.toArray(new Block[0]);

        String[] cm_allowedSpawnblocks_EldritchRing = config.getStringList(
                "Valid generation bases: EldritchRing",
                "Other",
                new String[] {
                    "minecraft:stone",
                    "minecraft:sand",
                    "minecraft:packed_ice",
                    "minecraft:grass",
                    "minecraft:gravel",
                    "minecraft:dirt"
                },
                "A list of valid blocks that Thaumcraft's eldritch obelisks can spawn upon");
        validBlocks = new HashSet();
        for (String s : cm_allowedSpawnblocks_EldritchRing) {
            String[] ssA = s.split(":", 2);
            if (ssA.length > 1) {
                Block b = GameRegistry.findBlock(ssA[0], ssA[1]);
                if (b != null) validBlocks.add(b);
            }
        }
        coremod_worldgenValidBase_EldritchRing = validBlocks.toArray(new Block[0]);

        config.save();
    }

    public static int getPotionID(int base, String key) {
        config.load();
        int i = config.get("Potions", "Potion ID: " + key, Utilities.getNextPotionId(base))
                .getInt();
        config.save();
        return i;
    }

    public static int getEnchantmentID(int base, String key) {
        config.load();
        int i = config.get("Enchantments", "Enchantment ID: " + key, Utilities.getNextEnchantmentId(base))
                .getInt();
        config.save();
        return i;
    }
}
