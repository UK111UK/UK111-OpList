package uk.uk111.OpList;

import uk.uk111.OpList.Commands.CommandOpList;
import uk.uk111.OpList.Listeners.OpListener;
import org.bukkit.plugin.java.JavaPlugin;

public class OpList extends JavaPlugin {
    @Override
    public void onEnable() {
        sendLog("Enabled!");

        // Register Commands
        this.getCommand("oplist").setExecutor(new CommandOpList());

        // Register Listeners
        this.getServer().getPluginManager().registerEvents(new OpListener(), this);
    }

    @Override
    public void onDisable() {
        sendLog("Disabled!");
    }

    private void sendLog(String log) {
        getLogger().info("[OpList] " + log);
    }
}
