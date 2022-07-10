package witchinggadgets.common.util.handler;

import java.util.HashMap;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import witchinggadgets.common.util.Utilities;

public class InfusedGemHandler {
    public static HashMap<String, Aspect[]> naturalAffinities = new HashMap<String, Aspect[]>(),
            naturalAversions = new HashMap<String, Aspect[]>();

    static {
        registerAffinities("gemDiamond", Aspect.ORDER);
        registerAversions("gemDiamond", Aspect.ENTROPY, Aspect.AIR);
        registerAffinities("gemEmerald", Aspect.EARTH);
        registerAversions("gemEmerald", Aspect.WATER, Aspect.ENTROPY);

        registerAffinities("crystalNetherQuartz", Aspect.ENTROPY, Aspect.FIRE);
        registerAversions("crystalNetherQuartz", Aspect.ORDER, Aspect.WATER, Aspect.AIR);
        registerAffinities("crystalCertusQuartz", Aspect.ORDER, Aspect.AIR);
        registerAversions("crystalCertusQuartz", Aspect.ENTROPY, Aspect.FIRE, Aspect.EARTH);
        registerAffinities("crystalFluix", Aspect.ENTROPY, Aspect.WATER, Aspect.AIR);
        registerAversions("crystalFluix", Aspect.ORDER, Aspect.EARTH, Aspect.FIRE);
        registerAffinities("crystalQuartzite", Aspect.WATER, Aspect.EARTH);
        registerAversions("crystalQuartzite", Aspect.FIRE, Aspect.ENTROPY, Aspect.AIR);

        registerAffinities("gemTopaz", Aspect.AIR, Aspect.FIRE);
        registerAversions("gemTopaz", Aspect.ENTROPY, Aspect.WATER);
        registerAffinities("gemAmber", Aspect.AIR, Aspect.EARTH);
        registerAversions("gemAmber", Aspect.ENTROPY);
        registerAffinities("gemPeridot", Aspect.EARTH);
        registerAversions("gemPeridot", Aspect.ENTROPY, Aspect.FIRE);
        registerAffinities("gemMalachite", Aspect.EARTH, Aspect.WATER);
        registerAversions("gemMalachite", Aspect.ENTROPY, Aspect.FIRE);
        registerAffinities("gemRuby", Aspect.FIRE);
        registerAversions("gemRuby", Aspect.ENTROPY, Aspect.WATER);
        registerAffinities("gemSapphire", Aspect.WATER);
        registerAversions("gemSapphire", Aspect.ENTROPY, Aspect.FIRE);
        registerAffinities("gemTanzanite", Aspect.WATER);
        registerAversions("gemTanzanite", Aspect.ENTROPY, Aspect.FIRE);

        registerAffinities(
                "gemPrimordial", Aspect.AIR, Aspect.EARTH, Aspect.FIRE, Aspect.WATER, Aspect.ORDER, Aspect.ENTROPY);
        registerAffinities(
                "gemNetherStar", Aspect.AIR, Aspect.EARTH, Aspect.FIRE, Aspect.WATER, Aspect.ORDER, Aspect.ENTROPY);

        registerAffinities("gemApatite", Aspect.WATER, Aspect.EARTH);
        registerAversions("gemApatite", Aspect.AIR, Aspect.ORDER);

        registerAffinities("gemQuicksilver", Aspect.ORDER, Aspect.EARTH);
        registerAversions("gemQuicksilver", Aspect.FIRE, Aspect.AIR);
        registerAffinities("gemJasper", Aspect.FIRE);
        registerAversions("gemJasper", Aspect.ENTROPY, Aspect.WATER);
        registerAffinities("gemFirestone", Aspect.FIRE);
        registerAversions("gemFirestone", Aspect.WATER);
        registerAffinities("gemJade", Aspect.EARTH);
        registerAversions("gemJade", Aspect.ENTROPY, Aspect.AIR);
        registerAffinities("gemGreenSapphire", Aspect.EARTH);
        registerAversions("gemGreenSapphire", Aspect.ENTROPY, Aspect.AIR);
        registerAffinities("gemOlivine", Aspect.EARTH);
        registerAversions("gemOlivine", Aspect.ENTROPY, Aspect.AIR);
        registerAffinities("gemFoolsRuby", Aspect.FIRE);
        registerAversions("gemFoolsRuby", Aspect.ENTROPY, Aspect.WATER, Aspect.AIR, Aspect.ORDER);
        registerAffinities("gemGlas", Aspect.ORDER);
        registerAversions("gemGlass", Aspect.AIR, Aspect.EARTH, Aspect.FIRE, Aspect.WATER, Aspect.ENTROPY);
        registerAffinities("gemDilithium", Aspect.ORDER);
        registerAversions("gemDilithium", Aspect.AIR, Aspect.EARTH, Aspect.FIRE, Aspect.WATER, Aspect.ENTROPY);
        registerAffinities("gemOpal", Aspect.WATER);
        registerAversions("gemOpal", Aspect.ENTROPY, Aspect.FIRE);
        registerAffinities("gemAmethyst", Aspect.WATER);
        registerAversions("gemAmethyst", Aspect.ENTROPY, Aspect.FIRE);
        registerAffinities("gemBlueTopaz", Aspect.WATER);
        registerAversions("gemBlueTopaz", Aspect.ENTROPY, Aspect.WATER);
        registerAffinities("gemForcicium", Aspect.ENTROPY);
        registerAversions("gemForcicium", Aspect.AIR, Aspect.EARTH, Aspect.FIRE, Aspect.WATER, Aspect.ORDER);
        registerAffinities("gemForcilium", Aspect.ENTROPY);
        registerAversions("gemForcilium", Aspect.AIR, Aspect.EARTH, Aspect.FIRE, Aspect.WATER, Aspect.ORDER);
        registerAffinities("gemMonazite", Aspect.ENTROPY);
        registerAversions("gemMonazite", Aspect.AIR, Aspect.EARTH, Aspect.FIRE, Aspect.WATER, Aspect.ORDER);
        registerAffinities("gemVinteum", Aspect.AIR, Aspect.EARTH, Aspect.FIRE, Aspect.WATER);
        registerAversions("gemVinteum", Aspect.ENTROPY, Aspect.WATER);
        // TODO: add missing crystals
    }

    public static void registerAffinities(Object o, Aspect... aspects) {
        if (o instanceof String) naturalAffinities.put((String) o, aspects);
        else if (o instanceof ItemStack) naturalAffinities.put(Utilities.getItemStackString((ItemStack) o), aspects);
    }

    public static void removeAffinities(Object o) {
        if (o instanceof String) naturalAffinities.remove((String) o);
        else if (o instanceof ItemStack) naturalAffinities.remove(Utilities.getItemStackString((ItemStack) o));
    }

    public static void registerAversions(Object o, Aspect... aspects) {
        if (o instanceof String) naturalAversions.put((String) o, aspects);
        else if (o instanceof ItemStack) naturalAversions.put(Utilities.getItemStackString((ItemStack) o), aspects);
    }

    public static void removeAversions(Object o) {
        if (o instanceof String) naturalAversions.remove((String) o);
        else if (o instanceof ItemStack) naturalAversions.remove(Utilities.getItemStackString((ItemStack) o));
    }

    public static Aspect[] getNaturalAffinities(ItemStack gem) {
        String s0 = Utilities.getItemStackString(gem);
        if (naturalAffinities.containsKey(s0)) return naturalAffinities.get(s0);
        for (String s1 : naturalAffinities.keySet())
            if (Utilities.compareToOreName(gem, s1)) return naturalAffinities.get(s1);
        return new Aspect[0];
    }

    public static Aspect[] getNaturalAversions(ItemStack gem) {
        String s0 = Utilities.getItemStackString(gem);
        if (naturalAversions.containsKey(s0)) return naturalAversions.get(s0);
        for (String s1 : naturalAversions.keySet())
            if (Utilities.compareToOreName(gem, s1)) return naturalAversions.get(s1);
        return new Aspect[0];
    }

    public static boolean isGem(ItemStack stack) {
        String s0 = Utilities.getItemStackString(stack);
        if (naturalAffinities.containsKey(s0) || naturalAversions.containsKey(s0)) return true;
        for (String s : naturalAffinities.keySet()) if (Utilities.compareToOreName(stack, s)) return true;
        for (String s : naturalAversions.keySet()) if (Utilities.compareToOreName(stack, s)) return true;
        return false;
    }
}
