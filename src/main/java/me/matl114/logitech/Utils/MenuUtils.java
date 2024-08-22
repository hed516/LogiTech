package me.matl114.logitech.Utils;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.matl114.logitech.Utils.UtilClass.ItemClass.*;
import me.matl114.logitech.Utils.UtilClass.MenuClass.CustomMenu;
import me.matl114.logitech.Utils.UtilClass.MenuClass.CustomMenuHandler;
import me.matl114.logitech.Utils.UtilClass.MenuClass.MenuFactory;
import me.matl114.logitech.Utils.UtilClass.MenuClass.MenuPreset;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import net.guizhanss.guizhanlib.minecraft.helper.inventory.ItemStackHelper;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MenuUtils {
    public static final ItemStack PROCESSOR_NULL= new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ");
    public static final ItemStack PROCESSOR_SPACE=new CustomItemStack(Material.RED_STAINED_GLASS_PANE,"&6进程完成","&c空间不足");
    public static final ItemStack PREV_BUTTON_ACTIVE = new SlimefunItemStack("_UI_PREVIOUS_ACTIVE", Material.LIME_STAINED_GLASS_PANE, "&r⇦ Previous Page", new String[0]);
    public static final ItemStack NEXT_BUTTON_ACTIVE = new SlimefunItemStack("_UI_NEXT_ACTIVE", Material.LIME_STAINED_GLASS_PANE, "&rNext Page ⇨", new String[0]);
    public static final ItemStack PREV_BUTTON_INACTIVE = new SlimefunItemStack("_UI_PREVIOUS_INACTIVE", Material.BLACK_STAINED_GLASS_PANE, "&8⇦ Previous Page", new String[0]);
    public static final ItemStack NEXT_BUTTON_INACTIVE = new SlimefunItemStack("_UI_NEXT_INACTIVE", Material.BLACK_STAINED_GLASS_PANE, "&8Next Page ⇨", new String[0]);
    public static final ItemStack BACK_BUTTON = new SlimefunItemStack("_UI_BACK", Material.ENCHANTED_BOOK, "&7⇦ 返回", (meta) -> {
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
    });
    public static final ItemStack SEARCH_BUTTON = new SlimefunItemStack("_UI_SEARCH", Material.NAME_TAG, "&b搜索...", "",ChatColor.GRAY + "⇨ " +"&b单击搜索物品");
    public static final ItemStack NO_ITEM = new SlimefunItemStack("_UI_NO_ITEM",Material.BARRIER,"&8 ");
    public static final ItemStack PRESET_INFO=new CustomItemStack(Material.CYAN_STAINED_GLASS_PANE,"&3配方类型信息");
    public static final ItemStack PRESET_MORE=new CustomItemStack(Material.LIME_STAINED_GLASS_PANE,"&a更多物品(已省略)");

    public static final ChestMenu.MenuClickHandler CLOSE_HANDLER=((player, i, itemStack, clickAction) -> {
        player.closeInventory();
        return false;

    });
    /**
     * change the object in the slot to a different object ,in order to trigger save at this slot when server down ,
     * will return the ref to the current object in the slot
     * @return
     */
    public static ItemStack syncSlot(BlockMenu inv ,int slot){
        ItemStack item = inv.getItemInSlot(slot);
        return syncSlot(inv,slot,item);
    }
    /**
     * change the object in the slot to a different object ,in order to trigger save at this slot when server down ,
     * will return the ref to the current object in the slot
     * @return
     */
    public static ItemStack syncSlot(BlockMenu inv ,int slot, ItemStack item){
        if(item instanceof AbstractItemStack){
            item=item.clone();
        }
        inv.replaceExistingItem(slot, item,false);
        return inv.getItemInSlot(slot);
    }

    public static ItemStack getPreviousButton(int page, int pages) {
        return pages != 1 && page != 1 ? new CustomItemStack(PREV_BUTTON_ACTIVE, (meta) -> {
            ChatColor var10001 = ChatColor.WHITE;
            meta.setDisplayName("" + var10001 + "⇦ " + "上一页");
            meta.setLore(Arrays.asList("", ChatColor.GRAY + "(" + page + " / " + pages + ")"));
        }) : new CustomItemStack(PREV_BUTTON_INACTIVE, (meta) -> {
            ChatColor var10001 = ChatColor.DARK_GRAY;
            meta.setDisplayName("" + var10001 + "⇦ " +"上一页");
            meta.setLore(Arrays.asList("", ChatColor.GRAY + "(" + page + " / " + pages + ")"));
        });
    }
    public static ItemStack getNextButton( int page, int pages) {
        return pages != 1 && page != pages ? new CustomItemStack(NEXT_BUTTON_ACTIVE, (meta) -> {
            ChatColor var10001 = ChatColor.WHITE;
            meta.setDisplayName("" + var10001 + "下一页" + " ⇨");
            meta.setLore(Arrays.asList("", ChatColor.GRAY + "(" + page + " / " + pages + ")"));
        }) : new CustomItemStack(NEXT_BUTTON_INACTIVE, (meta) -> {
            ChatColor var10001 = ChatColor.DARK_GRAY;
            meta.setDisplayName("" + var10001 + "下一页" + " ⇨");
            meta.setLore(Arrays.asList("", ChatColor.GRAY + "(" + page + " / " + pages + ")"));
        });
    }
    public static ItemStack getBackButton( String... lore) {
        return new CustomItemStack(BACK_BUTTON, "&7⇦ " +"返回", lore);
    }
    //3x3配方表的显示
    public static final MenuPreset SIMPLE_MENU=new MenuPreset(54,9,9)
            .addItem(BACK_BUTTON,1)
            .addItem(new CustomItemStack(Material.BOOK,"&aINFO_TEST"),7);
    public static final MenuPreset RECIPE_MENU_3X3=new MenuPreset(27,0,0)
            .addItem(BACK_BUTTON,1);
    //not used yet need
    public static final MenuPreset RECIPE_MENU_3x3_PLUS=new MenuPreset(54,0,0)
            .addItem(ChestMenuUtils.getBackground(),27,29,30,31,32,33,35);
    //6x6大配方的显示
    public static final MenuPreset RECIPE_MENU_6X6= new MenuPreset(54,0,0)
            .addItem(BACK_BUTTON,0)
            .addItem(PRESET_INFO,7,16,17)
            .addItem(ChestMenuUtils.getBackground(),9,18,27,36,45)
            .addItem(ChestMenuUtils.getOutputSlotTexture(),25,26,34,43,52);
    public static final  int RECIPEBACKSLOT_3X3=1;
    public static final int RECIPETYPESLOT_3X3=10;
    public static final int RECIPEBACKSLOT_6X6=0;
    public static final int RECIPETYPESLOT_6X6=8;
    public static final int[] RECIPESLOT_3X3=new int[]{3,4,5,12,13,14,21,22,23};
    public static final int[] RECIPEOUT_3X3=new int[]{7,16,25};
    public static final int[] RECIPESLOT_6x6=new int[]{1,2,3,4,5,6,10,11,12,13,14,15,19,20,21,22,23,24,28,29,30,31,32,33,37,38,39,40,41,42,46,47,48,49,50,51};
    public static final int[] RECIPEOUTPUT_6X6=new int[]{35,44,53};
    public interface RecipeMenuConstructor{
        public MenuFactory construct(ItemStack icon, MachineRecipe recipe , CustomMenuHandler backhandler);
    }
    public static MenuFactory createMRecipeListDisplay(ItemStack machine, List<MachineRecipe> machineRecipes, @Nullable CustomMenuHandler backHandler){
        return createMRecipeListDisplay(machine,machineRecipes,backHandler,MenuUtils::createMRecipeDisplay);
    }
    public static MenuFactory createMRecipeListDisplay(ItemStack machine, List<MachineRecipe> machineRecipes, @Nullable CustomMenuHandler backHandler,RecipeMenuConstructor constructor){
        int RecipeSize = machineRecipes.size();
        int pageContent=36;
        int pageNum=(1+(RecipeSize-1)/pageContent);
        ItemStack displayMachine;
        if(machine!=null){
            displayMachine=AddUtils.addLore(machine,"&8机器配方显示");
        }else{
            displayMachine=NO_ITEM;
        }
        MenuFactory a=new MenuFactory(SIMPLE_MENU,ItemStackHelper.getDisplayName(machine),pageNum){
            public void init(){
                setDefaultNPSlots();
            }
        }.addOverrides(7,displayMachine);
        a.setBack(1);
        if(backHandler!=null){
            a.setBackHandler(backHandler);
        }else{
            a.setBackHandler(CustomMenuHandler.from(CLOSE_HANDLER));
        }
        for(int i=0;i<RecipeSize;i++){
            MachineRecipe recipe=machineRecipes.get(i);
            int pageNow=(1+(i)/pageContent);
            if(recipe.getOutput().length>0){
                a.addInventory(i,recipe.getOutput()[0]);
            }
            else{
                a.addInventory(i,NO_ITEM);
            }
            a.addHandler(i,(cm)-> (player, i1, itemStack, clickAction) -> {
                constructor.construct(machine,recipe,cm.getOpenThisHandler(pageNow)).build().open(player);
                return false;
            });
        }
        return a;
    }
    //TODO 增加对随机物品和随机概率物品的支持
    private static String getAmountDisplay(ItemStack stack){
        if(stack instanceof RandAmountStack rand){
            return "%d~%d".formatted(rand.getMin(),rand.getMax());
        }
        else{
            return "x"+stack.getAmount();
        }

    }
    private static ItemStack getDisplayItem(ItemStack it){
        if(it==null||it.getType().isAir())return null;
        ItemStack finalStack;
        if(it instanceof RandomItemStack||it instanceof ProbItemStack){
            List<String> namelist=new ArrayList<>(){{
                add("");
                add("&3随机物品输出~");
                List<ItemStack> list=((RandomItemStack)it).getItemStacks();
                List<Double> wlist=((RandomItemStack)it).getWeight(1.0);
                int len=list.size();
                for(int i=0;i<len;i++){
                    add(AddUtils.concat(  "&f",
                            ItemStackHelper.getDisplayName(list.get(i)),
                            " &e",getAmountDisplay(list.get(i)),
                            " 概率: ",AddUtils.getPercentFormat(wlist.get(i))) );
                }
            }};
            //如果是proItemStack就获得其实例 因为不能整一个air出来
            ItemStack sample=(it instanceof ProbItemStack)?new ItemStack(it):it.clone();
            finalStack= AddUtils.addLore(sample,namelist.toArray(new String[namelist.size()]));
        }else if(it instanceof EquivalItemStack){
            List<String> namelist=new ArrayList<>(){{
                add("");
                add("&3等价物品输入~");
                List<ItemStack> list=((EquivalItemStack)it).getItemStacks();
                int len=list.size();
                for(int i=0;i<len;i++){
                    add(AddUtils.concat(  "&f",
                            ItemStackHelper.getDisplayName(list.get(i)),
                            " ",getAmountDisplay(list.get(i))) );
                }
            }};
            finalStack= AddUtils.addLore(it.clone(),namelist.toArray(new String[namelist.size()]));
        }
        else if(it instanceof RandAmountStack){
            finalStack=AddUtils.addLore(new ItemStack(it),AddUtils.concat("&e随机数量: ",getAmountDisplay(it)));
        }else{
            finalStack=it;
        }
        //防止因为意外出现的air 指
        if(finalStack.getType().isAir()){
            ItemMeta meta=finalStack.getItemMeta();
            finalStack=NO_ITEM.clone();
            finalStack.setItemMeta(meta);
        }
        if(finalStack.getAmount()>64){
            finalStack=AddUtils.addLore(finalStack, "&c数量: "+finalStack.getAmount());
        }
        return finalStack;
    }
    public static MenuFactory createMRecipeDisplay(ItemStack machine,MachineRecipe recipe,@Nullable CustomMenuHandler backHandler){
        ItemStack[] input=recipe.getInput();
        ItemStack[] output=recipe.getOutput();
        int inputlen=input.length;
        if(inputlen<=9){
            MenuFactory a=new MenuFactory(RECIPE_MENU_3X3,AddUtils.colorful("配方展示"),1){
                public void init(){

                }
            };
            a.setBack(RECIPEBACKSLOT_3X3);
            if(backHandler!=null){
                a.setBackHandler(backHandler);
            }else{
                a.setBackHandler(CustomMenuHandler.from(CLOSE_HANDLER));
            }
            if(recipe.getTicks()>=0)
                a.addInventory(RECIPETYPESLOT_3X3,AddUtils.addLore(machine,"","&a在该机器中耗时"+recipe.getTicks()+"&at制作"));
            else
                a.addInventory(RECIPETYPESLOT_3X3,AddUtils.addLore(machine,"","&a在该机器中制作"));
            for(int i=0;i<input.length;++i){
                a.addInventory(RECIPESLOT_3X3[i],getDisplayItem(input[i]) );
                SlimefunItem sfitem=SlimefunItem.getByItem(input[i]);
                if(sfitem!=null){
                    a.addHandler(RECIPESLOT_3X3[i],(cm)->((player, i1, itemStack, clickAction) -> {
                        createItemRecipeDisplay(sfitem,cm.getOpenThisHandler(1)).build().open(player);
                        return false;
                    }));
                }
            }
            if(output==null){
                output=new ItemStack[0];
            }
            if(output.length==1){
                a.addInventory(RECIPEOUT_3X3[(RECIPEOUT_3X3.length-1)/2], getDisplayItem(output[0]));
            }else if(output.length>1&& output.length<=RECIPEOUT_3X3.length){
                for(int s=0;s<output.length;++s){
                    a.addInventory(RECIPEOUT_3X3[s],getDisplayItem(output[s]) );
                }
            }else if(output.length>RECIPEOUT_3X3.length){
                for(int s=0;s<(RECIPEOUT_3X3.length-1);++s){
                    a.addInventory(RECIPEOUT_3X3[s],getDisplayItem(output[s]) );
                }
                a.addInventory(RECIPEOUT_3X3[RECIPEOUT_3X3.length-1],PRESET_MORE );
            }
            return a;
        }
        else{
            MenuFactory a=new MenuFactory(RECIPE_MENU_6X6,AddUtils.colorful("配方展示"),1){
                public void init(){
                }
            };
            a.setBack(RECIPEBACKSLOT_6X6);
            if(backHandler!=null){
                a.setBackHandler (backHandler);
            }else{
                a.setBackHandler(CustomMenuHandler.from(CLOSE_HANDLER));
            }
            if(recipe.getTicks()>=0)
                a.addInventory(RECIPETYPESLOT_6X6,AddUtils.addLore(machine,"","&a在该机器中耗时"+recipe.getTicks()+"&at制作"));
            else
                a.addInventory(RECIPETYPESLOT_6X6,AddUtils.addLore(machine,"","&a在该机器中制作"));
            int len=Math.min(inputlen,RECIPESLOT_6x6.length);

            for(int i=0;i<len;++i){
                if(i==len-1&&len<inputlen){
                    a.addInventory(RECIPESLOT_6x6[i],PRESET_MORE);
                    continue;
                }
                a.addInventory(RECIPESLOT_6x6[i],getDisplayItem(input[i]) );
                SlimefunItem sfitem=SlimefunItem.getByItem(input[i]);
                if(sfitem!=null){
                    a.addHandler(RECIPESLOT_6x6[i],(cm)->((player, i1, itemStack, clickAction) -> {
                        createItemRecipeDisplay(sfitem,cm.getOpenThisHandler(1)).build().open(player);
                        return false;
                    }) );
                }
            }
            if(output==null){
                output=new ItemStack[0];
            }
            int len2=Math.min(output.length,RECIPEOUTPUT_6X6.length);
            for(int i=0;i<len2;++i){
                if(i==len2-1&&len2<output.length){
                    a.addInventory(RECIPEOUTPUT_6X6[i],PRESET_MORE );
                    continue;
                }
                a.addInventory(RECIPEOUTPUT_6X6[i],output[i] );
            }
            return a;
        }

    }
    public static MenuFactory createItemRecipeDisplay(SlimefunItem item,@Nullable CustomMenuHandler backHandler){
        return createMRecipeDisplay(item.getRecipeType().toItem(),new MachineRecipe(-1,item.getRecipe(),new ItemStack[]{item.getRecipeOutput()}),backHandler);
    }

    /**
     * 制造 “制造配方类型展示界面的MenuFactory” 并且将其返回CMHandler设置为backHandler
     * @param recipeTypes
     * @param backHandler
     * @return
     */
    public static MenuFactory createRecipeTypeDisplay(List<RecipeType> recipeTypes, CustomMenuHandler backHandler){
        HashMap<RecipeType,ItemStack> map=RecipeSupporter.RECIPETYPE_ICON;
        int RecipeSize =0;
        for(RecipeType entry:recipeTypes){
            if(map.containsKey(entry)){
                RecipeSize++;
            }
        }
        int pageContent=36;
        int pageNum=(1+(RecipeSize-1)/pageContent);

        MenuFactory a=new MenuFactory(SIMPLE_MENU,AddUtils.colorful("合法配方类型大赏"),pageNum){
            public void init(){
                setDefaultNPSlots();
            }
        }.addOverrides(7, SlimefunGuide.getItem(SlimefunGuide.getDefaultMode()));
        if(backHandler!=null){
            a.setBack(1);
            a.setBackHandler(backHandler);
        }else{
            a.setBack(1);
            a.setBackHandler(CustomMenuHandler.from(CLOSE_HANDLER));
        }
        int i=0;
        for(RecipeType entry:recipeTypes){
            if(!map.containsKey(entry)){
                continue;
            }
            int currentPage=(1+(i)/pageContent);
            a.addInventory(i,map.get(entry));
            List<MachineRecipe> recipe=RecipeSupporter.PROVIDED_SHAPED_RECIPES.get(entry);
            if(recipe==null){
                recipe=RecipeSupporter.PROVIDED_UNSHAPED_RECIPES.get(entry);

            }
            if(recipe==null){
                a.addHandler(1,((player, i1, itemStack, clickAction) -> {
                    player.sendMessage(ChatColors.color("&e该配方类型在本附属的配方类型黑名单中,暂不支持查看"));
                    return false;
                }));
            }else{
                final List<MachineRecipe> machineRecipes=recipe;
                a.addHandler(i,(customMenuHandler) ->((player, i1, itemStack, clickAction) -> {
                    createMRecipeListDisplay(map.get(entry),machineRecipes,customMenuHandler.getOpenThisHandler(pageNum)).build().open(player);
                    return false;
                }));
            }
            ++i;
        }
        return a;
    }
    public static MenuFactory createMachineListDisplay(List<SlimefunItem> machineTypes,CustomMenuHandler backHandler){
        HashMap<SlimefunItem,List<MachineRecipe>> map=RecipeSupporter.MACHINE_RECIPELIST;
        int RecipeSize =0;
        for(SlimefunItem entry:machineTypes){
            if(map.containsKey(entry)){
                RecipeSize++;
            }
        }
        int pageContent=36;
        int pageNum=(1+(RecipeSize-1)/pageContent);

        MenuFactory a=new MenuFactory(SIMPLE_MENU,AddUtils.colorful("识别机器类型大赏"),pageNum){
            public void init(){
                setDefaultNPSlots();
            }
        }.addOverrides(7, SlimefunGuide.getItem(SlimefunGuide.getDefaultMode()));
        a.setBack(1);
        if(backHandler!=null){
            a.setBackHandler(backHandler);
        }else{
            a.setBackHandler(CustomMenuHandler.from( CLOSE_HANDLER));
        }
        int i=0;
        for(SlimefunItem entry:machineTypes){
            if(!map.containsKey(entry)){
                continue;
            }
            int currentPage=(1+(i)/pageContent);
            a.addInventory(i,entry.getItem());
            List<MachineRecipe> recipe=map.get(entry);
            if(recipe==null){
                a.addHandler(i,((player, i1, itemStack, clickAction) -> {
                    player.sendMessage(ChatColors.color("&e该机器类型无法识别,暂不支持查看"));
                    return false;
                }));
            }else{
                final List<MachineRecipe> machineRecipes=recipe;
                a.addHandler(i,(cm)-> (player, i1, itemStack, clickAction) -> {
                    createMRecipeListDisplay(entry.getItem(),machineRecipes,cm.getOpenThisHandler(currentPage)).build().open(player);
                    return false;
                });
            }
            ++i;
        }
        return a;
    }



}
