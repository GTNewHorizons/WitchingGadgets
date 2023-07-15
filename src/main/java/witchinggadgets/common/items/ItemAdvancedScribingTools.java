package witchinggadgets.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.IScribeTools;
import thaumcraft.common.items.ItemInkwell;
import witchinggadgets.WitchingGadgets;

public class ItemAdvancedScribingTools extends ItemInkwell implements IScribeTools {

    public ItemAdvancedScribingTools() {
        super();
        this.setMaxDamage(1000);
        this.setCreativeTab(WitchingGadgets.tabWG);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon("witchinggadgets:scribingTools");
    }
}
