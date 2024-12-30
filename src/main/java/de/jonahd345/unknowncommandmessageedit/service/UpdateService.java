package de.jonahd345.unknowncommandmessageedit.service;

import de.jonahd345.unknowncommandmessageedit.UnknownCommandMessageEdit;
import lombok.Getter;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

public class UpdateService {
    private UnknownCommandMessageEdit plugin;

    private String pluginVersion;

    @Getter
    private String spigotVersion;

    @Getter
    private boolean updateAvailable;

    public UpdateService(UnknownCommandMessageEdit plugin) {
        this.plugin = plugin;
        this.pluginVersion = this.plugin.getDescription().getVersion();
        this.updateAvailable = false;
        this.checkForUpdate();
    }

    public void checkForUpdate() {
        try {
            HttpsURLConnection con = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=100394").openConnection();
            con.setRequestMethod("GET");
            this.spigotVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
        } catch (Exception e) {
            this.plugin.getLogger().info("[UnknownCommandMessageEdit] Failed to check for updates on spigot.");
            return;
        }

        if (this.spigotVersion != null && !this.spigotVersion.isEmpty()) {
            this.updateAvailable = this.spigotIsNewer();
            if (this.updateAvailable) {
                this.plugin.getLogger().info("The new Version from UnknownCommandMessageEdit v" +
                        this.spigotVersion + " is available at: https://www.spigotmc.org/resources/unknowncommandmessageedit.100394/");
            }
        }
    }

    private boolean spigotIsNewer() {
        if (this.spigotVersion != null && !this.spigotVersion.isEmpty()) {
            int[] plV = this.toReadable(this.pluginVersion);
            int[] spV = this.toReadable(this.spigotVersion);
            if (plV[0] < spV[0]) {
                return true;
            } else {
                return (plV[1] < spV[1]);
            }
        } else {
            return false;
        }
    }

    private int[] toReadable(String version) {
        return Arrays.stream(version.split("\\.")).mapToInt(Integer::parseInt).toArray();
    }
}
