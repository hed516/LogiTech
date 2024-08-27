package me.matl114.logitech.Schedule.PersistentEffects;

import me.matl114.logitech.Utils.AddUtils;
import me.matl114.logitech.Utils.Debug;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomEffects {
    public static void setup(){

    }
    public static void registerEffect(){

    }
    public static AbstractEffect ANTI_GRAVITY= new AbstractEffect("ANTIGRAVITY") {
        public void removeEffect(Player p,int level) {
            p.setGravity(true);
            p.setCustomNameVisible(true);
        }
        public void tickEffect(Player p,int level) {
        }
        public void aquireEffect(Player p,int level) {
            p.setGravity(false);
        }
    };
    public static AbstractEffect SOLAR_BURN= new AbstractEffect("SOLAR_BURN") {
        public void removeEffect(Player p,int level) {
        }
        public void tickEffect(Player p,int level) {
            p.addPotionEffect(new PotionEffect( PotionEffectType.DARKNESS,140,3*level,false ),true);
            p.addPotionEffect(new PotionEffect( PotionEffectType.WITHER,140,3*level,false ),true);
            if(level==1){
                p.damage(5);
            }else {
                AddUtils.damageGeneric(p,50);

            }
        }
        public void aquireEffect(Player p,int level) {
        }
        public void onDeathEvent(PlayerDeathEvent e) {
            e.setDeathMessage(AddUtils.resolveColor( "%s &6在超新星的烈焰中化为灰烬".formatted(e.getEntity().getName())));
        }
    };
    public static AbstractEffect RADIATION= new AbstractEffect("RADIATION") {
        public void removeEffect(Player p,int level) {

        }
        public void tickEffect(Player p,int level) {
            p.addPotionEffect(new PotionEffect( PotionEffectType.DARKNESS,140,10*level,false ),true);
            p.addPotionEffect(new PotionEffect( PotionEffectType.WITHER,140,10*level,false ),true);
            p.addPotionEffect(new PotionEffect( PotionEffectType.SLOW,140,10*level,false ),true);
            p.addPotionEffect(new PotionEffect( PotionEffectType.HUNGER,140,10*level,false ),true);
            p.addPotionEffect(new PotionEffect( PotionEffectType.POISON,140,10*level,false ),true);
            if(level==1){
                p.damage(5);
            }else {
                AddUtils.damageGeneric(p,500);

            }
        }
        public void aquireEffect(Player p,int level) {

        }
        public void onDeathEvent(PlayerDeathEvent e) {
            e.setDeathMessage(AddUtils.resolveColor( "%s &6的身体被核辐射穿成了筛子".formatted(e.getEntity().getName())));
        }
    };
    public static AbstractEffect WRONG_BUTTON= new AbstractEffect("WRONG_BUTTON") {
        @Override
        public void aquireEffect(Player p, int level) {
            p.setHealth(0);

        }

        @Override
        public void removeEffect(Player p, int level) {
        }

        @Override
        public void tickEffect(Player p, int level) {

        }
        public void onDeathEvent(PlayerDeathEvent e) {
            e.setDeathMessage(AddUtils.resolveColor( "%s &6因选错了方向而被系统制裁".formatted(e.getEntity().getName())));
        }
    };
}
