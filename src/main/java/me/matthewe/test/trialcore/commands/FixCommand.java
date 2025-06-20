
package me.matthewe.test.trialcore.commands;

import me.matthewe.test.trialcore.TrialCore;
import me.matthewe.test.trialcore.TrialCoreConfig;
import me.matthewedevelopment.atheriallib.command.spigot.AtherialLibSpigotCommand;
import me.matthewedevelopment.atheriallib.command.spigot.CommandUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class FixCommand extends AtherialLibSpigotCommand<TrialCoreConfig, TrialCore> {

    public FixCommand(TrialCoreConfig config, TrialCore main) {
        super("fix", config, main, "fixitem");
        this.permission = config.fixPermission;
        this.playerOnly =true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        return CommandUtils.getOnlinePlayersCompletion(args);
    }

    @Override
    public void run(CommandSender s, String[] args) {
        if (args.length!=0) {
            CommandUtils.sendCommandUsage(s, "/" + label);
            return;
        }


        Player p = (Player) s;
        ItemStack mainItem = p.getEquipment().getItemInMainHand();
        if (mainItem==null || mainItem.getType()== Material.AIR) {
            config.fixNoItemInMainHandMessage.send(s);
            return;
        }
        mainItem.setDurability((short) 0);
        p.getEquipment().setItemInMainHand(mainItem);
        config.fixNoItemInMainHandMessage.send(s, TagResolver.builder()
                .build());

    }

}
