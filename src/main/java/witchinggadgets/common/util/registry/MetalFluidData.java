package witchinggadgets.common.util.registry;

import java.util.HashMap;

import witchinggadgets.common.WGContent;

public class MetalFluidData {

    static HashMap<String, String> oreFluidName = new HashMap<>();
    static HashMap<String, Integer> oreFluidTemp = new HashMap<>();

    static {
        for (String s : WGContent.ClusterSmeltable.keySet()) addOreFluid(
                s,
                WGContent.ClusterSmeltable.get(s).getUnlocalizedName(),
                WGContent.ClusterSmeltable.get(s).getTemperature());
    }

    public static int getOreFluidTemp(String ore) {
        if (oreFluidTemp.get(ore) != null) return oreFluidTemp.get(ore);
        return 0;
    }

    public static String getOreFluidName(String ore) {
        if (oreFluidTemp.get(ore) != null) return oreFluidName.get(ore);
        return "";
    }

    public static void addOreFluid(String ore, String fluidName, int temp) {
        oreFluidName.put(ore, fluidName);
        oreFluidTemp.put(ore, temp);
    }
}
