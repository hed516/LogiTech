package me.matl114.logitech.SlimefunItem.Items;


import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.matl114.logitech.SlimefunItem.AddHandlers;
import me.matl114.logitech.SlimefunItem.CustomSlimefunItem;
import me.matl114.logitech.SlimefunItem.Machines.MenuBlock;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public final class MaterialItem extends CustomSlimefunItem {
    public MaterialItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }
    public MaterialItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, List<ItemStack> recipeDisplay) {
        super(itemGroup, item, recipeType, recipe,recipeDisplay);
    }
    public void addInfo(ItemStack stack){

    }
    @Override
    public  void preRegister(){
        super.preRegister();
        //addItemHandler(AddHandlers.stopAttackHandler);
        if(this.getItem().getType().isBlock()){
            addItemHandler(AddHandlers.stopPlacementHandler);
            addItemHandler(AddHandlers.stopPlaceerHandler);
        }
    }
}
