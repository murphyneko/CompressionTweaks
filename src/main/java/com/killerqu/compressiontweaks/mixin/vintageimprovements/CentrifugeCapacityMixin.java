package com.killerqu.compressiontweaks.mixin.vintageimprovements;

import com.killerqu.compressiontweaks.config.CTCommonConfig;
import com.negodya1.vintageimprovements.content.kinetics.centrifuge.CentrifugeBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CentrifugeBlockEntity.class)
public class CentrifugeCapacityMixin {
    @ModifyArg(method = "addBehaviours",
            remap = false,
            at = @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/blockEntity/behaviour/fluid/SmartFluidTankBehaviour;<init>(Lcom/simibubi/create/foundation/blockEntity/behaviour/BehaviourType;Lcom/simibubi/create/foundation/blockEntity/SmartBlockEntity;IIZ)V"),
            index = 3)
    private int setNewCapacity(int original){
        return CTCommonConfig.CENTRIFUGE_FLUID_CAPACITY.get();
    }
}
