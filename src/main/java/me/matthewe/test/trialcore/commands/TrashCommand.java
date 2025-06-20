
package me.matthewe.test.trialcore.commands;

import me.matthewe.test.trialcore.TrialCore;
import me.matthewe.test.trialcore.TrialCoreConfig;
import me.matthewe.test.trialcore.trash.TrashHandler;
import me.matthewedevelopment.atheriallib.command.spigot.AtherialLibSpigotCommand;
import me.matthewedevelopment.atheriallib.command.spigot.CommandUtils;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TrashCommand extends AtherialLibSpigotCommand<TrialCoreConfig, TrialCore> {

    private TrashHandler trashHandler;
    public TrashCommand(TrialCoreConfig config, TrialCore main, TrashHandler trashHandler) {
        super("trash", config, main, "trashcan");
        this.trashHandler = trashHandler;
        this.permission = config.trashPermission;
        this.playerOnly =true;
    }



    @Override
    public void run(CommandSender s, String[] args) {
        if (args.length!=0) {
            CommandUtils.sendCommandUsage(s, "/" + label);
            return;
        }


        Player p = (Player) s;

        trashHandler.openTrash(p);
        config.trashOpenMessage.send(s, TagResolver.builder()
                .build());

    }

}
