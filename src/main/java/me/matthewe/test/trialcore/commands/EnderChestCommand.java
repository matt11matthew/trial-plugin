package me.matthewe.test.trialcore.commands;

import me.matthewe.test.trialcore.TrialCore;
import me.matthewe.test.trialcore.TrialCoreConfig;
import me.matthewe.test.trialcore.profile.TrialProfile;
import me.matthewedevelopment.atheriallib.command.spigot.AtherialLibSpigotCommand;
import me.matthewedevelopment.atheriallib.command.spigot.CommandUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class EnderChestCommand extends AtherialLibSpigotCommand<TrialCoreConfig, TrialCore> {

    public EnderChestCommand(TrialCoreConfig config, TrialCore main) {
        super("enderchest", config, main, "echest");
        this.permission = config.echestOpenPermission;
        this.playerOnly =true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        return CommandUtils.getOnlinePlayersCompletion(args);
    }

    @Override
    public void run(CommandSender s, String[] args) {
        Player player;
        if (args.length == 0) {
            if (!(s instanceof Player)) {
                CommandUtils.sendPlayerOnlyMessage(s);
                return;
            }
            player = (Player) s;
        } else if (args.length==1) {
            player=Bukkit.getPlayer(args[0]);
        } else {
            CommandUtils.sendCommandUsage(s, "/" + label,  "[player]");
            return;
        }

        if ((player == null) || !player.isOnline()) {
            CommandUtils.sendPlayerOfflineMessage(s, args[0]);
            return;
        }

        TrialProfile trialProfile = TrialProfile.get(player);
        if (trialProfile==null)return;

        config.echestOpeningMessage.send(s, TagResolver.builder()
                .tag("player", Tag.inserting(Component.text(player.getName())))
                .build());
        Player sender = (Player) s;
        sender.openInventory(player.getEnderChest());
    }

}
