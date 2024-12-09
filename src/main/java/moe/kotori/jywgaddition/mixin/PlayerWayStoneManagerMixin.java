package moe.kotori.jywgaddition.mixin;

import com.mojang.datafixers.util.Either;
import moe.kotori.jywgaddition.event.waystone.WayStone;
import net.blay09.mods.waystones.api.IWaystoneTeleportContext;
import net.blay09.mods.waystones.api.WaystoneTeleportError;
import net.blay09.mods.waystones.core.PlayerWaystoneManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(remap = false, value = PlayerWaystoneManager.class)
public class PlayerWayStoneManagerMixin {
    @Inject(method = "tryTeleport", at = @At(value = "TAIL", shift = At.Shift.BEFORE))
    private static void fireWayStoneTpEvent(IWaystoneTeleportContext context, CallbackInfoReturnable<Either<List<Entity>, WaystoneTeleportError>> cir) {
        var target = context.getEntity();
        if (target instanceof Player player) {
            MinecraftForge.EVENT_BUS.post(new WayStone(player, context.getFromWaystone(), context.getTargetWaystone()));
        }
    }
}
