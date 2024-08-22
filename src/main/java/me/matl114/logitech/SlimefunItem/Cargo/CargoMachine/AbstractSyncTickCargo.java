package me.matl114.logitech.SlimefunItem.Cargo.CargoMachine;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.matl114.logitech.SlimefunItem.Blocks.AbstractBlock;
import me.matl114.logitech.SlimefunItem.Cargo.Config.ChipCardCode;
import me.matl114.logitech.SlimefunItem.Cargo.Config.ChipControllable;
import me.matl114.logitech.Utils.DataCache;
import me.matl114.logitech.Utils.Settings;
import me.matl114.logitech.Utils.UtilClass.TickerClass.SyncBlockTick;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * controlled by sync tick and chip code to perform certain sequence task
 */
public abstract class AbstractSyncTickCargo extends AbstractBlock implements SyncBlockTick.SyncTickers {
    public static SyncBlockTick CARGO_SYNC_INSTANCE =new SyncBlockTick();

    public AbstractSyncTickCargo(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }
    public abstract int[] getInputSlots();
    public abstract int[] getOutputSlots();
    public abstract void constructMenu(BlockMenuPreset preset);
    public abstract void newMenuInstance(BlockMenu menu,Block b);
    public abstract void updateMenu(BlockMenu blockMenu, Block block, Settings mod);
    public abstract void syncTick(Block b, BlockMenu inv, SlimefunBlockData data, int synTickCount);
    public void preRegister(){
        super.preRegister();
        this.registerBlockMenu(this);
        this.handleMenu(this);
    }
}
