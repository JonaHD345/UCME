package de.jonahd345.unknowncommandmessageedit;

import de.jonahd345.unknowncommandmessageedit.listener.CommandListener;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class UnknownCommandMessageEdit extends JavaPlugin {
    @Getter
    private boolean isPlaceholderAPIEnabled;

    @Override
    public void onEnable() {
        // Check if PlaceholderAPI is enabled
        this.isPlaceholderAPIEnabled = getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");

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
        return getPlugin(UnknownCommandMessageEdit.class);
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
