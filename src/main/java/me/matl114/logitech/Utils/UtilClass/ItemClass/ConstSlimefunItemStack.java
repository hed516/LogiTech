package me.matl114.logitech.Utils.UtilClass.ItemClass;

import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.matl114.logitech.SlimefunItem.CustomSlimefunItem;
import me.matl114.logitech.Utils.ReflectUtils;
import me.matl114.logitech.Utils.Settings;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;

public class ConstSlimefunItemStack extends SlimefunItemStack {
    public SlimefunItemStack data;
    public ItemMeta thismeta;
    Field lockedField;
    public ConstSlimefunItemStack(SlimefunItemStack stack){
        super(stack.getItemId(),stack);
        this.data =stack ;
        this.thismeta = stack.getItemMeta();
        this.lockedField=ReflectUtils.getDeclaredFieldsRecursively(this.getClass(),"locked").getFirstValue();
        try{
            Object locked=this.lockedField.get(this);
            this.lockedField.set(this, Boolean.FALSE);
            this.setItemMeta(data.getItemMeta());
            this.lockedField.set(this, locked);
        }catch (Throwable e){

        }
    }
    public ItemStack clone() {
        SlimefunItemStack stack=(SlimefunItemStack) super.clone();
        try{
            Object locked=this.lockedField.get(stack);
            this.lockedField.set(stack, Boolean.FALSE);
            stack.setItemMeta(thismeta);
            this.lockedField.set(stack, locked);
        }catch (Throwable e){

        }
        return stack;
    }


}