package witchinggadgets;

import static witchinggadgets.common.util.Lib.Title;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import baubles.api.expanded.BaubleExpandedSlots;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry.Type;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import witchinggadgets.common.CommonProxy;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.util.WGCreativeTab;
import witchinggadgets.common.util.handler.EventHandler;
import witchinggadgets.common.util.handler.PlayerTickHandler;
import witchinggadgets.common.util.handler.WGWandManager;
import witchinggadgets.common.util.network.message.MessageClientNotifier;
import witchinggadgets.common.util.network.message.MessageOpenCloak;
import witchinggadgets.common.util.network.message.MessagePlaySound;
import witchinggadgets.common.util.network.message.MessagePrimordialGlove;
import witchinggadgets.common.util.network.message.MessageSyncGlide;
import witchinggadgets.common.util.network.message.MessageTileUpdate;
import witchinggadgets.common.world.VillageComponentPhotoshop;

@Mod(
        modid = WitchingGadgets.MODID,
        name = WitchingGadgets.MODNAME,
        version = WitchingGadgets.VERSION,
        dependencies = "required-after:Thaumcraft;" + "after:ForbiddenMagic;"
                + "after:TwilightForest;"
                + "after:TaintedMagic;"
                + "after:gregtech_nh;"
                + "after:miscutils;"
                + "after:Mystcraft;"
                + "after:TConstruct;"
                + "after:ThaumicTinkerer;"
                + "after:ThaumicExploration;"
                + "after:ForgeMultipart")
public class WitchingGadgets {

    public static final String MODID = "WitchingGadgets";
    public static final String MODNAME = "Witching Gadgets";
    public static final String VERSION = Tags.VERSION;

    public PlayerTickHandler playerTickHandler;

    public WGWandManager wgWandManager = new WGWandManager();

    public static final CreativeTabs tabWG = new WGCreativeTab(CreativeTabs.getNextID(), "witchinggadgets");
    public static final Logger logger = LogManager.getLogger("WitchingGadgets");
    public EventHandler eventHandler;

    @Instance("WitchingGadgets")
    public static WitchingGadgets instance = new WitchingGadgets();

    @SidedProxy(clientSide = "witchinggadgets.client.ClientProxy", serverSide = "witchinggadgets.common.CommonProxy")
    public static CommonProxy proxy;

    public static SimpleNetworkWrapper packetHandler;

    public static final boolean IS_DREAMCRAFT_LOADED = Loader.isModLoaded("dreamcraft");

    public static boolean isBootsActive = false;
    public static final String BOOTS = "thaumicboots";

    public static boolean isGT5uLoaded = false;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger.log(Level.INFO, "Setting up 'WitchingGadgets'");

        if (Loader.isModLoaded(BOOTS)) {
            isBootsActive = true;
        }

        if (Loader.isModLoaded("gregtech_nh")) {
            isGT5uLoaded = true;
        }

        WGConfig.loadConfig(event);
        WGContent.preInit();

        packetHandler = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        BaubleExpandedSlots.tryRegisterType(Title);
        BaubleExpandedSlots.tryAssignSlotOfType(BaubleExpandedSlots.capeType);
        BaubleExpandedSlots.tryAssignSlotOfType(BaubleExpandedSlots.gauntletType);
        BaubleExpandedSlots.tryAssignSlotOfType(BaubleExpandedSlots.charmType);
        BaubleExpandedSlots.tryAssignSlotOfType(Title);
        eventHandler = new EventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);
        playerTickHandler = new PlayerTickHandler();
        FMLCommonHandler.instance().bus().register(eventHandler);
        FMLCommonHandler.instance().bus().register(playerTickHandler);

        VillagerRegistry.instance().registerVillageCreationHandler(new VillageComponentPhotoshop.VillageManager());
        try {
            MapGenStructureIO.func_143031_a(VillageComponentPhotoshop.class, "WGVillagePhotoWorkshop");
        } catch (Exception e) {
            logger.log(Level.ERROR, "Photographer's Workshop not added to Villages");
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerRenders();
        // WGPacketPipeline.INSTANCE.initialise();

        WGContent.init();

        proxy.registerHandlers();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

        packetHandler.registerMessage(
                MessageClientNotifier.HandlerClient.class,
                MessageClientNotifier.class,
                0,
                Side.CLIENT);
        packetHandler.registerMessage(MessagePlaySound.HandlerClient.class, MessagePlaySound.class, 1, Side.CLIENT);
        packetHandler.registerMessage(
                MessagePrimordialGlove.HandlerServer.class,
                MessagePrimordialGlove.class,
                2,
                Side.SERVER);
        packetHandler.registerMessage(MessageTileUpdate.HandlerClient.class, MessageTileUpdate.class, 3, Side.CLIENT);
        packetHandler.registerMessage(MessageTileUpdate.HandlerServer.class, MessageTileUpdate.class, 4, Side.SERVER);

        packetHandler.registerMessage(MessageOpenCloak.HandlerClient.class, MessageOpenCloak.class, 5, Side.CLIENT);
        packetHandler.registerMessage(MessageOpenCloak.HandlerServer.class, MessageOpenCloak.class, 6, Side.SERVER);

        packetHandler.registerMessage(MessageSyncGlide.HandlerServer.class, MessageSyncGlide.class, 7, Side.SERVER);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        WGModCompat.init();
        WGContent.postInit();
        // WGPacketPipeline.INSTANCE.postInitialise();

    }

    @Mod.EventHandler
    public void missingMappings(FMLMissingMappingsEvent event) {
        Block[] wgBlocks = { WGContent.BlockWallMirror, WGContent.BlockVoidWalkway, WGContent.BlockPortal,
                WGContent.BlockStoneDevice, WGContent.BlockWoodenDevice, WGContent.BlockMetalDevice,
                WGContent.BlockMagicBed, WGContent.BlockRoseVine, WGContent.BlockCustomAiry };
        for (MissingMapping mapping : event.get()) if (mapping.name.startsWith("WitchingGadgets:")) {
            try {
                String s = mapping.name.substring("WitchingGadgets:".length());
                for (Block b : wgBlocks) if (b != null) {
                    if (s.equalsIgnoreCase(b.getLocalizedName())) {
                        if (mapping.type == Type.BLOCK) mapping.remap(b);
                        else mapping.remap(Item.getItemFromBlock(b));
                        logger.warn("Remapping " + mapping.name + " to " + b.getUnlocalizedName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Mod.EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {}
}
