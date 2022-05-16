package baraclese.better_doors.mixin;

import net.minecraft.item.WoodenDoorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WoodenDoorItem.class)
public class WoodenDoorItemMixin
{
    // increase door stack size to 64
    @Inject(method = "<init>", at = @At("TAIL"))
    void setMaxStackSize(CallbackInfo ci)
    {
        ((WoodenDoorItem)(Object)this).setMaxCount(64);
    }
}
