package com.killerqu.compressiontweaks;

import com.killerqu.compressiontweaks.config.CTCommonConfig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.venturecraft.gliders.common.item.GliderItem;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import vazkii.botania.api.mana.ManaItemHandler;

public class GliderCurioImpl implements ICurioItem {


    @Override
    public void curioTick(SlotContext ctx, ItemStack stack){
        if(ctx.entity() instanceof Player player && !player.level().isClientSide){
            manaMendGlider(player, stack);
        }
    }


    public static void manaMendGlider(Player player, ItemStack stack){
        if(GliderItem.hasNetherUpgrade(stack) && stack.getDamageValue() > 0 &&
                ManaItemHandler.instance().requestManaExact(stack, player, CTCommonConfig.GLIDER_REPAIR_MANA_COST.get(), true)){
            stack.setDamageValue(stack.getDamageValue()-1);

        }
    }

}
