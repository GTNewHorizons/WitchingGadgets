package witchinggadgets.common.util;

import net.minecraft.item.ItemStack;

import com.ruling_0.materiallib.api.Material;
import com.ruling_0.materiallib.api.MaterialLibAPI;
import com.ruling_0.materiallib.api.Shape;

import gregtech.api.enums.StoneType;
import gregtech.api.enums.materials.OreShapes;
import gregtech.api.enums.materials.Shapes;
import gregtech.api.material.LegacyNameDomain;

/// GregTech's MaterialLib parts behind WitchingGadgets' ore-dictionary part names. Only loaded with GregTech present.
final class GT5uParts {

    private GT5uParts() {}

    /// The `prefix` part of the material named `materialName`, or null when GregTech does not serve that part.
    static ItemStack get(String prefix, String materialName, int amount) {
        Material material = LegacyNameDomain.lookup(materialName);
        if (material == null) return null;
        if (prefix.equals("ore")) {
            if (!material.hasShape(OreShapes.ore)) return null;
            return MaterialLibAPI
                    .getStack(material, OreShapes.ore, OreShapes.variantOf(StoneType.Stone.name()), amount);
        }
        Shape shape = switch (prefix) {
            case "ingot" -> Shapes.ingot;
            case "nugget" -> Shapes.nugget;
            case "dust" -> Shapes.dust;
            case "dustTiny" -> Shapes.dustTiny;
            default -> null;
        };
        if (shape == null || !material.hasShape(shape)) return null;
        return MaterialLibAPI.getStack(material, shape, amount);
    }
}
