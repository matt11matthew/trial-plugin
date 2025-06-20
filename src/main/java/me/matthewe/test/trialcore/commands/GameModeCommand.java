package me.matthewe.test.trialcore.commands;

import me.matthewe.test.trialcore.TrialCore;
import me.matthewe.test.trialcore.TrialCoreConfig;
import me.matthewedevelopment.atheriallib.command.spigot.AtherialLibSpigotCommand;
import me.matthewedevelopment.atheriallib.command.spigot.CommandUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand  extends AtherialLibSpigotCommand<TrialCoreConfig, TrialCore> {

    public GameModeCommand(TrialCoreConfig config, TrialCore main) {
        super("gamemode", config, main, "gm");
        this.permission = config.gameModePermission;
    }

    @Override
    public void run(CommandSender s, String[] args) {
        if (args.length==1) {
            if (!( s instanceof Player)) {
                CommandUtils.sendPlayerOnlyMessage(s);
                return;
            }
            runGameMode(s, (Player) s, args[0]);
        } else if (args.length==2) {

            Player player = Bukkit.getPlayer(args[1]);
            if ((player == null) || !player.isOnline()) {
                CommandUtils.sendPlayerOfflineMessage(s, args[1]);
                return;
            }
            runGameMode(s, (Player) s, args[0]);
            return;
        } else {
            CommandUtils.sendCommandUsage(s, "/" + label,"<type>", "[player]");
        }

    }

    private void runGameMode(CommandSender s, Player target, String gamemodeArg) {
        GameMode foundGameMode = null;
        try {
            foundGameMode = GameMode.valueOf(gamemodeArg.toUpperCase());
        } catch (Exception e) {
            foundGameMode = null;
        }
        if (foundGameMode==null) {
            config.invalidGameModeMessage.send(s, TagResolver.builder()
                    .tag("mode", Tag.inserting(Component.text(gamemodeArg)))
                    .build());
            return;
        }
        config.youAreNowGameModeMessage.send(s, TagResolver.builder()
                .tag("player", Tag.inserting(Component.text(target.getName())))
                .tag("mode", Tag.inserting(Component.text(foundGameMode.name())))
                .build());

        config.swapGameModeOtherMessage.send(s, TagResolver.builder()
                .tag("player", Tag.inserting(Component.text(target.getName())))
                .tag("mode", Tag.inserting(Component.text(foundGameMode.name())))
                .build());
        target.setGameMode(foundGameMode);
    }
}
