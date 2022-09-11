package de.jonahd345.unknowncommandmessageedit;

import de.jonahd345.unknowncommandmessageedit.listener.CommandListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class UnknownCommandMessageEdit extends JavaPlugin {
    private static UnknownCommandMessageEdit instance;

    @Override
    public void onEnable() {
        loadConfig();
        this.init();
    }

    @Override
    public void onDisable() {
    }

    private void init() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new CommandListener(), this);
    }

    public static UnknownCommandMessageEdit getInstance() {
        return instance;
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void onLoad() {
        instance = this;
    }
}
