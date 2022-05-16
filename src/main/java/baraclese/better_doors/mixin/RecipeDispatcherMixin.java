package baraclese.better_doors.mixin;

import net.minecraft.item.Item;
import net.minecraft.recipe.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(RecipeDispatcher.class)
public abstract class RecipeDispatcherMixin
{
    @Shadow
    private List recipes = new ArrayList();

    // increase amount of crafted doors to 3
    @Inject(method = "<init>", at = @At("TAIL"))
    private void modifyDoorRecipe(CallbackInfo ci)
    {
        int numAdjusted = 0;
        for (int i = 0; i < this.recipes.size(); ++i)
        {
            RecipeType recipeType = (RecipeType)this.recipes.get(i);
            if (recipeType instanceof ShapedRecipeType)
            {
                ShapedRecipeType shapedRecipe = (ShapedRecipeType)recipeType;
                if (shapedRecipe.field_4437 == Item.WOODEN_DOOR.id || shapedRecipe.field_4437 == Item.IRON_DOOR.id)
                {
                    shapedRecipe.getOutput().count = 3;
                    ++numAdjusted;
                }
            }

            if (numAdjusted >= 2)
                return;
        }
    }
}
