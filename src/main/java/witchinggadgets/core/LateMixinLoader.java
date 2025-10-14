package witchinggadgets.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

@LateMixin
public class LateMixinLoader implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.WitchingGadgets.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        final List<String> mixins = new ArrayList<>();
        mixins.add("thaumcraft.ContainerFocusPouchAccessor");
        mixins.add("thaumcraft.MixinItemBootsTraveller");
        mixins.add("thaumcraft.MixinWorldGenEldritchRing");
        mixins.add("thaumcraft.MixinWorldGenHilltopStones");
        return mixins;
    }

}
