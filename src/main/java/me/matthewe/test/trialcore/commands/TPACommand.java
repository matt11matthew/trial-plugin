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

public class TPACommand extends AtherialLibSpigotCommand<TrialCoreConfig, TrialCore> {

    public TPACommand(TrialCoreConfig config, TrialCore main) {
        super("tpa", config, main, "tpacmd");
        this.permission = config.tpaPermission;
        this.playerOnly =true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        return CommandUtils.getOnlinePlayersCompletion(args);
    }

    @Override
    public void run(CommandSender s, String[] args) {
        if (args.length!=1) {
            CommandUtils.sendCommandUsage(s, "/" + label, "<player>");
            return;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if ((player == null) || !player.isOnline()) {
            CommandUtils.sendPlayerOfflineMessage(s, args[0]);
            return;
        }
        TrialProfile trialProfile = TrialProfile.get(player);
        if (trialProfile==null)return;


        Player sender = (Player) s;
        trialProfile.setTpaId(sender.getUniqueId().toString());
        trialProfile.save();

        config.tpaSendMessage.send(s, TagResolver.builder()
                .tag("player", Tag.inserting(Component.text(player.getName())))
                .build());

        config.tpaGetMessage.send(player, TagResolver.builder()
                .tag("player", Tag.inserting(Component.text(s.getName())))
                .build());


    }

}
