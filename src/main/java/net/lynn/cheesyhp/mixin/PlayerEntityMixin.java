package net.lynn.cheesyhp.mixin;

import net.lynn.cheesyhp.util.HeartData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements HeartData {
    @Unique
    private double extraHearts = 0.0;

    @Override
    public double getExtraHearts() {
        return this.extraHearts;
    }

    @Override
    public void setExtraHearts(double value) {
        this.extraHearts = value;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomHearts (NbtCompound nbt, CallbackInfo ci){
        nbt.putDouble("CheesyHPExtraHealth", this.extraHearts);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomHearts(NbtCompound nbt, CallbackInfo ci){
        if (nbt.contains("CheesyHPExtraHealth")){
            this.extraHearts = nbt.getDouble("CheesyHPExtraHealth");
        }
    }
}
