package moe.kotori.jywgaddition.listener;

import moe.kotori.jywgaddition.Jywgaddition;
import moe.kotori.jywgaddition.event.waystone.PlayerWayStoneTeleportEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.Set;
import java.util.UUID;

public class WayStoneTeleportListener {
    @SubscribeEvent
    public static void onWayStoneTeleport(PlayerWayStoneTeleportEvent event) {
        Jywgaddition.LOGGER.info("玩家: {}", event.getEntity().getName().getString());
        Jywgaddition.LOGGER.info("传送石碑A: {}", event.getFromWayStone().getPos().toString());
        Jywgaddition.LOGGER.info("传送石碑B: {}", event.getTargetWayStone().getPos().toString());

        Player player = event.getEntity();
        var tag = player.getPersistentData().getCompound("FollowRecruits");
        if (tag != null) {
            tag.getAllKeys().forEach(k -> {
                ServerLifecycleHooks.getCurrentServer().getLevel(event.getFromWayStone().getDimension())
                        .getEntity(UUID.fromString(k))
                        .teleportTo(
                                ServerLifecycleHooks.getCurrentServer().getLevel(event.getTargetWayStone().getDimension()),
                                event.getTargetWayStone().getPos().getX(),
                                event.getTargetWayStone().getPos().getY(),
                                event.getTargetWayStone().getPos().getZ(),
                                Set.of(),
                                0.0F,
                                0.0F
                                );
                player.sendSystemMessage(Component.literal("将跟随士兵: %s 传送至身边".formatted(k)));
            });
        }
    }
}
