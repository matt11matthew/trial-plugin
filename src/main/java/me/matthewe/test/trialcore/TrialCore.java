package me.matthewe.test.trialcore;

import me.matthewe.test.trialcore.commands.*;
import me.matthewe.test.trialcore.godmode.GodModeHandler;
import me.matthewe.test.trialcore.profile.TrialProfile;
import me.matthewe.test.trialcore.trash.TrashHandler;
import org.bukkit.plugin.java.JavaPlugin;

import me.matthewe.atheriallibplugin.AtherialAddon;
import me.matthewe.atheriallibplugin.AtherialLibPlugin;
import me.matthewedevelopment.atheriallib.AtherialLib;
import me.matthewedevelopment.atheriallib.handler.HandlerManager;
import org.bukkit.plugin.java.JavaPlugin;

public  class TrialCore extends AtherialAddon {
    private static TrialCore instance;
    private TrialCoreConfig config;
    private HandlerManager<TrialCore> handlerManager;

    public TrialCore() {
        instance = this;
        AtherialLibPlugin.registerAddon(this);
    }

    public static TrialCore get() {
        return instance;
    }


    @Override
    public void onEnable() {
        AtherialLib.getInstance().getProfileManager().registerProfileClass(TrialProfile.class);
        config =new TrialCoreConfig(this);
        config.loadConfig();

        handlerManager =new HandlerManager<>(this);
        handlerManager.registerHandler(new GodModeHandler(this, config));
        handlerManager.registerHandler(new TrashHandler(this, config));
        handlerManager.enableHandlers();

        registerCommand(new GameModeCommand(this.config, this));
        registerCommand(new OpenInventoryCommand(this.config, this));
        registerCommand(new EnderChestCommand(this.config, this));
        registerCommand(new FixCommand(this.config, this));
        registerCommand(new TPACommand(this.config, this));
        registerCommand(new TPAAcceptCommand(this.config, this));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        handlerManager.disableHandlers();
    }

    public void reload() {
        config.reload();
        handlerManager.reload();
    }
}
