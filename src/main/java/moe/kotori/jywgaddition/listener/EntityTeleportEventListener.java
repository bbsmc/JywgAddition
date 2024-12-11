package moe.kotori.jywgaddition.listener;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.Set;
import java.util.UUID;

public class EntityTeleportEventListener {
    @SubscribeEvent
    public static void onEntityTeleport(EntityTeleportEvent event) {
        var entity = event.getEntity();
        if (entity instanceof Player player) {
            var tag = player.getPersistentData().getCompound("FollowRecruits");
            if (tag != null) {
                tag.getAllKeys().forEach(k -> {
                    if (k != null) {
                        UUID recruitUUID = UUID.fromString(k);
                        if (recruitUUID != null) {
                            if (player.level() instanceof ServerLevel level) {
                                var recruit = level.getEntity(recruitUUID);
                                if (recruit == null) {
                                    return;
                                }
                                recruit.teleportTo(event.getTargetX(), event.getTargetY(), event.getTargetZ());
                            }
                            entity.sendSystemMessage(Component.literal("将跟随士兵: %s 传送至身边".formatted(k)));
                        } else {
                            tag.remove(k);
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public static void onChangeDim(PlayerEvent.PlayerChangedDimensionEvent event) {
        ServerLevel fromLevel = ServerLifecycleHooks.getCurrentServer().getLevel(event.getFrom());
        ServerLevel toLevel = ServerLifecycleHooks.getCurrentServer().getLevel(event.getTo());

        Player toPlayer = toLevel.getPlayerByUUID(event.getEntity().getUUID());
        if (toPlayer != null) {
            var tag = toPlayer.getPersistentData().getCompound("FollowRecruits");
            if (tag != null) {
                tag.getAllKeys().forEach(k -> {
                    if (k != null) {
                        fromLevel.getEntity(UUID.fromString(k)).teleportTo(toLevel, toPlayer.getX(), toPlayer.getY(), toPlayer.getZ(), Set.of(), 0.0F, 0.0F);
                        event.getEntity().sendSystemMessage(Component.literal("将跟随士兵: %s 传送至身边".formatted(k)));
                    }
                });
            }
        }
    }
}
