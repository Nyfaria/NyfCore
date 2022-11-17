package com.nyfaria.nyfcore.entity.ai.goal;

import com.nyfaria.nyfcore.entity.ai.ifaces.IBird;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;

public class PerchGoal<T extends Mob & IBird> extends Goal {

    private final T bird;
    private final Level level;
    public PerchGoal(T bird) {
        super();
        this.bird = bird;
        this.level = bird.level;
    }

    @Override
    public void tick() {
        if(!level.getBlockState(bird.blockPosition().below()).getFluidState().is(Fluids.WATER)){
            BlockPos blockpos = null;

            for(BlockPos blockpos1 : BlockPos.betweenClosed(Mth.floor(this.bird.getX() - 2.0D), Mth.floor(this.bird.getY() - 2.0D), Mth.floor(this.bird.getZ() - 2.0D), Mth.floor(this.bird.getX() + 2.0D), this.bird.getBlockY(), Mth.floor(this.bird.getZ() + 2.0D))) {
                if (level.getFluidState(blockpos1).is(FluidTags.WATER)) {
                    blockpos = blockpos1;
                    break;
                }
            }
            if(blockpos!=null){
                bird.getNavigation().moveTo(blockpos.getX(),blockpos.getY(),blockpos.getZ(),1);
            }
        }
        if(level.getBlockState(bird.blockPosition().below()).getFluidState().is(Fluids.WATER)){
            this.bird.setDeltaMovement(bird.getDeltaMovement().multiply(1,0,1));
        }
    }

    @Override
    public boolean canUse() {
        return bird.level.getEntities(bird,bird.getBoundingBox().inflate(4),e->!(e instanceof IBird) && !e.equals(bird)).isEmpty();
    }
}
