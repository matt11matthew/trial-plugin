package me.matthewe.test.trialcore.godmode;

import me.matthewe.test.trialcore.TrialCore;
import me.matthewe.test.trialcore.TrialCoreConfig;
import me.matthewe.test.trialcore.godmode.commands.GodModeCommand;
import me.matthewe.test.trialcore.profile.TrialProfile;
import me.matthewedevelopment.atheriallib.handler.Handler;
import me.matthewedevelopment.atheriallib.handler.HandlerPriority;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import java.nio.Buffer;

public class GodModeHandler  extends Handler<TrialCore, TrialCoreConfig>   {
    public GodModeHandler(TrialCore core, TrialCoreConfig config) {
        super(core, config, HandlerPriority.NORMAL, HandlerPriority.NORMAL);
    }

    @Override
    public void onLoad() {
        registerCommand(new GodModeCommand(this.c, this.core));


    }


    @Override
    public void reload() {

    }

    @Override
    public void onUnload() {

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        TrialProfile trialProfile = TrialProfile.get(((Player) event.getEntity()).getPlayer());
        if (!trialProfile.isGodMode()){
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        TrialProfile trialProfile = TrialProfile.get((Player) event.getEntity());
        if (!trialProfile.isGodMode()){
            return;
        }
        event.getEntity().setSaturation(1.0F);
        event.setCancelled(true);
    }


}
