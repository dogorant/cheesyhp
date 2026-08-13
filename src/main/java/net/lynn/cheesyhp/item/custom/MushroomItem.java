package net.lynn.cheesyhp.item.custom;

import net.lynn.cheesyhp.util.HeartData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class MushroomItem extends Item {
    public static final Identifier MODIFIER_ID = Identifier.of("cheesyhp", "extra_hearts_modifier");

    public MushroomItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type){
        tooltip.add(Text.literal("Adds 1 extra heart when consumed")
                .formatted(Formatting.AQUA));

        tooltip.add(Text.literal("Additional hearts stay if your overall heart count is less than 10")
                .formatted(Formatting.GRAY, Formatting.ITALIC));

        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user){
        return 32;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack resultStack = super.finishUsing(stack, world, user);

        if (!world.isClient() && user instanceof PlayerEntity player) {
            applyExtraHearts(player, 2.0);

            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }

        return resultStack;
    }

    public static void applyExtraHearts(PlayerEntity player, double amountToAdd){
        EntityAttributeInstance attrInstance = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (attrInstance == null) return;

        if (attrInstance.getModifier(MODIFIER_ID) != null) {
           attrInstance.removeModifier(MODIFIER_ID);
        }

        HeartData heartData = (HeartData) player;
        double newExtraHealth = heartData.getExtraHearts() + amountToAdd;
        heartData.setExtraHearts(newExtraHealth);

        EntityAttributeModifier modifier = new EntityAttributeModifier(
                MODIFIER_ID, newExtraHealth, EntityAttributeModifier.Operation.ADD_VALUE
        );

        attrInstance.addPersistentModifier(modifier);
        player.heal((float) amountToAdd);
    }


    @Override
    public UseAction getUseAction(ItemStack stack){
    return UseAction.EAT;
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
