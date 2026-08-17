package witchinggadgets.common.recipes;

import com.ruling_0.materiallib.api.MaterialLibAPI;
import com.ruling_0.materiallib.api.MaterialRegistrationEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import witchinggadgets.WitchingGadgets;

/// Holds Witching Gadgets' [MaterialRegistrationEvent] subscription, kept off [WitchingGadgets] to keep the
/// main mod class free of MaterialLib types under a soft dependency.
///
/// Shapes are targeted by name: the `Shapes` constants are still null while this event is dispatched.
public class WGShapeConsumers {

    @SubscribeEvent
    public void materialRegistration(MaterialRegistrationEvent event) {
        MaterialLibAPI.registerPostInitShapeConsumer(WitchingGadgets.MODID, "dust", new WG_infernal_recipes());
    }
}
