package moe.kotori.jywgaddition.listener;

import moe.kotori.jywgaddition.Jywgaddition;
import moe.kotori.jywgaddition.event.waystone.WayStone;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WayStoneTeleportListener {
    @SubscribeEvent
    public static void onWayStoneTeleport(WayStone event) {
        Jywgaddition.LOGGER.info("玩家: {}", event.getEntity().getName().getString());
        Jywgaddition.LOGGER.info("传送石碑A: {}", event.getFromWayStone().getPos().toString());
        Jywgaddition.LOGGER.info("传送石碑B: {}", event.getTargetWayStone().getPos().toString());
    }
}
