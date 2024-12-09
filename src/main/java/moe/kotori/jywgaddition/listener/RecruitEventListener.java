package moe.kotori.jywgaddition.listener;

import moe.kotori.jywgaddition.Jywgaddition;
import moe.kotori.jywgaddition.event.recruit.RecruitEvent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RecruitEventListener {
    @SubscribeEvent
    public static void onRecruitFollow(final RecruitEvent.RecruitFollowEvent event) {
        var recruit = event.getEntity();
        var owner = event.getOwner();

        if (owner != null) {
            CompoundTag tag = owner.getPersistentData().getCompound("FollowRecruits");
            if (tag == null) {
                tag = new CompoundTag();
            }
            if (tag.get(recruit.getStringUUID()) == null) {
                tag.putString(recruit.getStringUUID(), recruit.getName().getString());
            }

            owner.getPersistentData().put("FollowRecruits", tag);

            Jywgaddition.LOGGER.info("触发跟随事件, FollowState: {}", event.getFollowState());
            Jywgaddition.LOGGER.info("添加跟随的士兵: {}", recruit.getStringUUID());
            Jywgaddition.LOGGER.info("玩家: {} 现有跟随士兵 {}", owner.getName().getString(), tag.get(recruit.getStringUUID()).toString());
        }
    }

    @SubscribeEvent
    public static void onRecruitUnFollow(final RecruitEvent.RecruitUnFollowEvent event) {
        var recruit = event.getEntity();
        var owner = event.getOwner();

        if (owner != null) {
            CompoundTag tag = owner.getPersistentData().getCompound("FollowRecruits");
            if (tag != null) {
                if (tag.get(recruit.getStringUUID()) != null) {
                    var entity = tag.get(recruit.getStringUUID());
                    tag.remove(recruit.getStringUUID());
                    Jywgaddition.LOGGER.info("触发取消跟随事件, FollowState: {}", event.getFollowState());
                    Jywgaddition.LOGGER.info("删除已跟随的士兵: {}", recruit.getStringUUID());
                    Jywgaddition.LOGGER.info("玩家: {} 现有跟随士兵 {}", owner.getName().getString(), entity.toString());
                } else {
                    owner.sendSystemMessage(Component.literal("士兵不存在"));
                }
            } else {
                owner.sendSystemMessage(Component.literal("Tag FollowRecruits 是 null"));
            }
        }
    }
}
