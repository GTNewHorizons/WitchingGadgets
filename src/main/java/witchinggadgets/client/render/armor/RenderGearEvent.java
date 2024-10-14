package witchinggadgets.client.render.armor;

import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class RenderGearEvent extends RenderPlayerEvent {

    public boolean shouldRender = true;

    public ItemStack stack;

    public RenderGearEvent(EntityPlayer player, RenderPlayer renderer, ItemStack stack, float partialRenderTick) {
        super(player, renderer, partialRenderTick);
        this.stack = stack;
    }

}
