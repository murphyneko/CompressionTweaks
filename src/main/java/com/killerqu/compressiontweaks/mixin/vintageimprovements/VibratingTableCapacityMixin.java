package com.killerqu.compressiontweaks.mixin.vintageimprovements;

import com.killerqu.compressiontweaks.CompressionTweaks;
import com.killerqu.compressiontweaks.config.CTCommonConfig;
import com.negodya1.vintageimprovements.content.kinetics.vibration.VibratingTableBlockEntity;
import com.simibubi.create.foundation.item.SmartInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VibratingTableBlockEntity.class)
public class VibratingTableCapacityMixin {
    @Shadow(remap = false) public SmartInventory outputInv;

    @Inject(method = "<init>", at = @At(value = "TAIL"), remap = false)
    private void resizeOutputInv(BlockEntityType<?> type, BlockPos pos, BlockState state, CallbackInfo ci){
        outputInv = new SmartInventory(CTCommonConfig.TABLE_OUTPUT_SLOTS.get(), ((VibratingTableBlockEntity)(Object) this));
    }
}
