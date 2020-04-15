package uk.uk111.minecraft.oplist;

import uk.uk111.minecraft.oplist.commands.CommandOpList;
import uk.uk111.minecraft.oplist.listeners.OpListener;
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
