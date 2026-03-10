package com.killerqu.compressiontweaks.mixin.vintageimprovements;

import com.killerqu.compressiontweaks.config.CTCommonConfig;
import com.negodya1.vintageimprovements.content.kinetics.vibration.VibratingTableBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(VibratingTableBlockEntity.class)
public class VibratingTableCapacityMixin {
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/item/SmartInventory;<init>(ILcom/simibubi/create/foundation/blockEntity/SyncedBlockEntity;)V"), remap = false, index = 0)
    private int resizeOutputInv(int slots){
        if(slots == 9) return CTCommonConfig.TABLE_OUTPUT_SLOTS.get();
        else return slots;
    }
}
