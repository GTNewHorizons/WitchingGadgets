package witchinggadgets.common.recipes;

import com.ruling_0.materiallib.api.MaterialLibAPI;
import com.ruling_0.materiallib.api.MaterialRegistrationEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import witchinggadgets.WitchingGadgets;

/// Carries the [MaterialRegistrationEvent] subscription for Witching Gadgets. It is deliberately not on
/// [WitchingGadgets] itself: GregTech and MaterialLib are soft dependencies, so bus registration of this
/// handler happens only once both are present and the main mod class never names a MaterialLib type.
///
/// Shapes are targeted by name rather than by a `Shapes` constant because those constants are still null
/// while this event is being dispatched.
public class WGShapeConsumers {

    @SubscribeEvent
    public void materialRegistration(MaterialRegistrationEvent event) {
        MaterialLibAPI.registerPostInitShapeConsumer(WitchingGadgets.MODID, "dust", new WG_infernal_recipes());
    }
}
