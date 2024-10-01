package witchinggadgets.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import witchinggadgets.client.render.cloak.IClientCloakPlayer;
import witchinggadgets.client.render.cloak.ICloakPlayer;

public class ModelCloak extends ModelBase {

    private final ModelRenderer rightWing;
    private final ModelRenderer leftWing = new ModelRenderer(this, 22, 0);
    private double[] circPos = new double[32]; // Circle Position

    public boolean doAnimation = true;
    int colour;

    public ModelCloak(int colour) {
        this.colour = colour;
        this.leftWing.addBox(-10.0F, 0.0F, 0.0F, 10, 20, 2, 1.0F);
        this.rightWing = new ModelRenderer(this, 22, 0);
        this.rightWing.mirror = true;
        this.rightWing.addBox(0.0F, 0.0F, 0.0F, 10, 20, 2, 1.0F);
    }

    @Override
    public void render(Entity entityIn, float p_78088_2_, float limbSwing, float ageInTicks, float netHeadYaw,
            float headPitch, float scale) {

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(GL11.GL_CULL_FACE);

        this.leftWing.render(scale);
        this.rightWing.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
            float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        float f = 0.2617994F; // 15 degrees in radians
        float f1 = -0.2617994F;
        float f2 = 0.0F;
        float f3 = 0.0F;

        if (entityIn instanceof ICloakPlayer && ((ICloakPlayer) entityIn).isElytraFlying()) {
            float f4 = 1.0F;

            if (entityIn.motionY < 0.0D) {
                Vec3 vec3d = (Vec3.createVectorHelper(entityIn.motionX, entityIn.motionY, entityIn.motionZ))
                        .normalize();
                f4 = 1.0F - (float) Math.pow(-vec3d.yCoord, 1.5D);
            }

            f = f4 * 0.34906584F + (1.0F - f4) * f; // 20 degrees in radians
            f1 = f4 * -((float) Math.PI / 2F) + (1.0F - f4) * f1;
        } else if (entityIn.isSneaking()) {
            f = ((float) Math.PI * 2F / 9F);
            f1 = -((float) Math.PI / 4F);
            f2 = 3.0F;
            f3 = 0.08726646F;
        }

        this.leftWing.rotationPointX = 5.0F;
        this.leftWing.rotationPointY = f2;

        if (entityIn instanceof IClientCloakPlayer) {
            IClientCloakPlayer cep = (IClientCloakPlayer) entityIn;
            cep.setRotateCloakX((float) (cep.getRotateCloakX() + (f - cep.getRotateCloakX()) * 0.1D));
            cep.setRotateCloakY((float) (cep.getRotateCloakY() + (f3 - cep.getRotateCloakY()) * 0.1D));
            cep.setRotateCloakZ((float) (cep.getRotateCloakZ() + (f1 - cep.getRotateCloakZ()) * 0.1D));
            this.leftWing.rotateAngleX = cep.getRotateCloakX();
            this.leftWing.rotateAngleY = cep.getRotateCloakY();
            this.leftWing.rotateAngleZ = cep.getRotateCloakZ();
        } else {
            this.leftWing.rotateAngleX = f;
            this.leftWing.rotateAngleZ = f1;
            this.leftWing.rotateAngleY = f3;
        }

        this.rightWing.rotationPointX = -this.leftWing.rotationPointX;
        this.rightWing.rotateAngleY = -this.leftWing.rotateAngleY;
        this.rightWing.rotationPointY = this.leftWing.rotationPointY;
        this.rightWing.rotateAngleX = this.leftWing.rotateAngleX;
        this.rightWing.rotateAngleZ = -this.leftWing.rotateAngleZ;
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_,
            float partialTickTime) {
        super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);
    }
}
