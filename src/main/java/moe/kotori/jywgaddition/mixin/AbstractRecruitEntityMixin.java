package moe.kotori.jywgaddition.mixin;

import com.talhanation.recruits.entities.AbstractRecruitEntity;
import moe.kotori.jywgaddition.event.recruit.RecruitEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(remap = false, value = AbstractRecruitEntity.class)
public abstract class AbstractRecruitEntityMixin {
    @Shadow @Nullable public abstract Player getOwner();

    @Inject(method = "setFollowState", at = @At("HEAD"))
    public void fireEvent(int state, CallbackInfo ci) {
        if (state == 1 || state == 3) {
            MinecraftForge.EVENT_BUS.post(new RecruitEvent.RecruitFollowEvent((AbstractRecruitEntity) (Object) this, this.getOwner(), state));
        } else {
            MinecraftForge.EVENT_BUS.post(new RecruitEvent.RecruitUnFollowEvent((AbstractRecruitEntity) (Object) this, this.getOwner(), state));
        }
    }
}
