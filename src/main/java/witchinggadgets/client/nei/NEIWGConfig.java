package witchinggadgets.client.nei;

import net.minecraft.item.ItemStack;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;

public class NEIWGConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        API.registerRecipeHandler(new NEISpinningWheelHandler());
        API.registerUsageHandler(new NEISpinningWheelHandler());

        API.registerRecipeHandler(new NEIInfernalBlastfurnaceHandler());
        API.registerUsageHandler(new NEIInfernalBlastfurnaceHandler());

        // if(WGConfig.allowClusters && ItemClusters.materialMap.isEmpty())
        // API.hideItem(new ItemStack(WGContent.ItemCluster,1,OreDictionary.WILDCARD_VALUE));
        API.hideItem(new ItemStack(WGContent.BlockWallMirror));
        API.hideItem(new ItemStack(WGContent.BlockVoidWalkway));
    }

    @Override
    public String getName() {
        return "Witching Gadgets NEI";
    }

    @Override
    public String getVersion() {
        return WitchingGadgets.VERSION;
    }
}
