package moe.kotori.jywgaddition.event.recruit;

import com.talhanation.recruits.entities.AbstractRecruitEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityEvent;

public class RecruitEvent extends EntityEvent {
    private final Player owner;

    public RecruitEvent(AbstractRecruitEntity entity, Player owner) {
        super(entity);
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public static class RecruitFollowEvent extends RecruitEvent {
        private final int followState;

        public RecruitFollowEvent(AbstractRecruitEntity entity, Player owner, int followState) {
            super(entity, owner);
            this.followState = followState;
        }

        public int getFollowState() {
            return followState;
        }
    }

    public static class RecruitUnFollowEvent extends RecruitEvent {
        private final int followState;

        public RecruitUnFollowEvent(AbstractRecruitEntity entity, Player owner, int followState) {
            super(entity, owner);
            this.followState = followState;
        }

        public int getFollowState() {
            return followState;
        }
    }
}
