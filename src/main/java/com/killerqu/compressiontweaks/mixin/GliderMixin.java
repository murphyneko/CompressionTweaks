package com.killerqu.compressiontweaks.mixin;

import com.killerqu.compressiontweaks.GliderCurioImpl;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.venturecraft.gliders.common.item.GliderItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GliderItem.class)
public class GliderMixin {

    public void onArmorTick(ItemStack stack, Level world, Player player){
        if(!world.isClientSide) {
            GliderCurioImpl.manaMendGlider(player, stack);
        }
    }


    @Inject(method = "appendHoverText", at = @At("TAIL"))
    private void addBrokenTooltip(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn, CallbackInfo ci){
        if(GliderItem.isBroken(stack)){
            tooltip.add(Component.translatable("tooltip.vc_gliders.broken"));
        }
    }

}
