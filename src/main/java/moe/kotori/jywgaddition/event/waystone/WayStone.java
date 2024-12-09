package moe.kotori.jywgaddition.event.waystone;

import net.blay09.mods.waystones.api.IWaystone;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class WayStone extends PlayerEvent {
    public final IWaystone targetWayStone;

    public final IWaystone fromWayStone;

    public WayStone(Player entity, IWaystone fromWayStone, IWaystone targetWayStone) {
        super(entity);
        this.fromWayStone = null;
        this.targetWayStone = null;
    }

    public IWaystone getFromWayStone() {
        return fromWayStone;
    }

    public IWaystone getTargetWayStone() {
        return targetWayStone;
    }
}
