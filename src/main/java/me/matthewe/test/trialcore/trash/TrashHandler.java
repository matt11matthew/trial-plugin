package me.matthewe.test.trialcore.trash;

import me.matthewe.test.trialcore.TrialCore;
import me.matthewe.test.trialcore.TrialCoreConfig;
import me.matthewe.test.trialcore.commands.TrashCommand;
import me.matthewe.test.trialcore.godmode.commands.GodModeCommand;
import me.matthewe.test.trialcore.profile.TrialProfile;
import me.matthewedevelopment.atheriallib.handler.Handler;
import me.matthewedevelopment.atheriallib.handler.HandlerPriority;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.Inventory;

import static me.matthewedevelopment.atheriallib.utilities.ChatUtils.colorize;

public class TrashHandler extends Handler<TrialCore, TrialCoreConfig>   {
    public TrashHandler(TrialCore core, TrialCoreConfig config) {
        super(core, config, HandlerPriority.NORMAL, HandlerPriority.NORMAL);
    }

    @Override
    public void onLoad() {
        registerCommand(new TrashCommand(this.c, this.core, this));


    }


    @Override
    public void reload() {

    }

    @Override
    public void onUnload() {

    }


    public void openTrash(Player p) {
        Inventory inventory = Bukkit.createInventory(null, (9 * c.trashMenuRows), colorize(c.trashMenuTitle));
        p.openInventory(inventory);
    }
}
