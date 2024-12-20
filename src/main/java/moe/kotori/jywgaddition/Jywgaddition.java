package moe.kotori.jywgaddition;

import com.mojang.logging.LogUtils;
import moe.kotori.jywgaddition.listener.EntityTeleportEventListener;
import moe.kotori.jywgaddition.listener.RecruitEventListener;
import moe.kotori.jywgaddition.listener.WayStoneTeleportListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Jywgaddition.MODID)
public class Jywgaddition {

    public static final String MODID = "jywgaddition";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Jywgaddition() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(WayStoneTeleportListener::onWayStoneTeleport);
        MinecraftForge.EVENT_BUS.addListener(RecruitEventListener::onRecruitFollow);
        MinecraftForge.EVENT_BUS.addListener(RecruitEventListener::onRecruitUnFollow);
        MinecraftForge.EVENT_BUS.addListener(EntityTeleportEventListener::onEntityTeleport);
    }
}
