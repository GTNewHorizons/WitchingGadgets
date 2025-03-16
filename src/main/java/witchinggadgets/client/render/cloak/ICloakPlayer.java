package witchinggadgets.client.render.cloak;

public interface ICloakPlayer {

    void setCloakFlying(boolean flag);

    float getTicksCloakFlying();

    boolean lastCloakFlying();

    void setLastCloakFlying(boolean flag);

    boolean isElytraFlying();

    void tickCloak();
}
