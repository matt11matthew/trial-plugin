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
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class TPAAcceptCommand extends AtherialLibSpigotCommand<TrialCoreConfig, TrialCore> {

    public TPAAcceptCommand(TrialCoreConfig config, TrialCore main) {
        super("accepttpa", config, main, "tpaaccept", "tpaccept");
        this.permission = config.tpaAcceptPermission;
        this.playerOnly = true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        return CommandUtils.getOnlinePlayersCompletion(args);
    }

    @Override
    public void run(CommandSender s, String[] args) {
        if (args.length != 1) {
            CommandUtils.sendCommandUsage(s, "/" + label, "<player>");
            return;
        }


        Player sender = (Player) s;
        TrialProfile trialProfile = TrialProfile.get(sender);
        if (trialProfile == null) return;


        if (trialProfile.getTpaId() == null) {
            config.noTPARequestMessage.send(s, TagResolver.builder()
                    .build());
            return;
        }
        UUID tpaId = UUID.fromString(trialProfile.getTpaId());

        boolean failure = false;

        Player player = Bukkit.getPlayer(tpaId);
        if (player==null || !player.isOnline()) {
            failure = true;

            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(tpaId);
            if (offlinePlayer!=null && offlinePlayer.getName()!=null) {
                CommandUtils.sendPlayerOfflineMessage(s,offlinePlayer.getName());
            }
        }
        trialProfile.setTpaId(null);
        trialProfile.save();
        if (failure)return;

        sender.teleport(player);

        config.tpaAcceptMessage.send(sender);



    }
}
