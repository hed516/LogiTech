package me.matl114.logitech.SlimefunItem.Machines.AutoMachines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.matl114.logitech.Schedule.SchedulePostRegister;
import me.matl114.logitech.SlimefunItem.Machines.AbstractMultiThreadProcessor;
import me.matl114.logitech.Utils.RecipeSupporter;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;

public class MTMachine extends AbstractMultiThreadProcessor {
    public MTMachine(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe,
                                        ItemStack progressItem, int energyConsumption, int energyBuffer,
                                        LinkedHashMap<Object, Integer> customRecipes){
        super(category, item, recipeType, recipe, progressItem, energyConsumption, energyBuffer, customRecipes);
    }
    protected Supplier<List<MachineRecipe>> machineRecipeSupplier=null;
    public MTMachine(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe,
                     ItemStack processbar, int energyConsumption, int energyBuffer,
                    Supplier<List<MachineRecipe>> machineRecipeSupplier) {
        super(category,item,recipeType,recipe,processbar,energyConsumption,energyBuffer,null);
        this.machineRecipeSupplier=machineRecipeSupplier;
        SchedulePostRegister.addPostRegisterTask(()->{
            getMachineRecipes();
        });
    }
    public MTMachine(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe,
                     ItemStack processbar, int energyConsumption, int energyBuffer,
                    RecipeType... recipeTypes) {
        this(category,item,recipeType,recipe,processbar,energyConsumption,energyBuffer,()->{
            if(recipeTypes==null||recipeTypes.length==0){
                return new ArrayList<MachineRecipe>();
            }else {
                List<MachineRecipe>   mr=new ArrayList<>();
                for(RecipeType rt : recipeTypes){
                    if(rt!=null){
                        List<MachineRecipe> rep= RecipeSupporter.PROVIDED_UNSHAPED_RECIPES.get(rt);
                        if(rep==null)rep=new ArrayList<>();
                        mr.addAll(rep);
                    }
                }
                return mr;
            }
        });
    }
    public List<MachineRecipe> getMachineRecipes(){
        if(this.machineRecipes.isEmpty()&&this.machineRecipeSupplier!=null){
            this.machineRecipes=this.machineRecipeSupplier.get();
            if(this.machineRecipes==null) {
                this.machineRecipes = new ArrayList<>();
            }
        }
        return this.machineRecipes;
    }
}