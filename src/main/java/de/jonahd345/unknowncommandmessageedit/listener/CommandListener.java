package de.jonahd345.unknowncommandmessageedit.listener;

import de.jonahd345.unknowncommandmessageedit.UnknownCommandMessageEdit;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class CommandListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onUnknown(PlayerCommandPreprocessEvent e) {
        if (!(e.isCancelled())) {
            Player player = e.getPlayer();
            String cmd = e.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
            String message = UnknownCommandMessageEdit.getInstance().getConfig().getString("Config.Message");

            message = message.replace("%Command%", cmd);
            if (UnknownCommandMessageEdit.getInstance().isPlaceholderAPIEnabled()) {
                message = PlaceholderAPI.setPlaceholders(player, message);
            }
            if (topic == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                e.setCancelled(true);
            }
        }
    }
}
