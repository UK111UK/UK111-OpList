package uk.uk111.OpList.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import uk.uk111.OpList.OpList;

public class OpListener implements Listener {
    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        Server server = OpList.getPlugin(OpList.class).getServer();

        if (event.getMessage().substring(0, 4).equals("/op ") && player.isOp()) {
            if (event.getMessage().split(" ").length > 0) {
                Player target = server.getPlayerExact(event.getMessage().split(" ")[1]);

                if (target != null) {
                    if (!target.isOp()) {
                        sendOpMessage(server, true, target.getName());
                    }
                } else {
                    sendOpMessage(server, true, event.getMessage().split(" ")[1]);
                }
            }
        } else if (event.getMessage().substring(0, 6).equals("/deop ") && player.isOp()) {
            if (event.getMessage().split(" ").length > 0) {
                Player target = server.getPlayerExact(event.getMessage().split(" ")[1]);

                if (target != null) {
                    if (target.isOp()) {
                        sendOpMessage(server, false, target.getName());
                    }
                } else {
                    sendOpMessage(server, false, event.getMessage().split(" ")[1]);
                }
            }
        }
    }

    private void sendOpMessage(Server server, boolean opEnabled, String playerName) {
        String opMessage = ChatColor.DARK_RED + "[OpList]" + ChatColor.WHITE + ": Player " + ChatColor.GREEN + playerName + ChatColor.WHITE+  " has set OP to: " + (opEnabled ? ChatColor.GREEN + "Enabled" : ChatColor.DARK_RED + "Disabled") + ChatColor.WHITE + "!";
        server.broadcastMessage(opMessage);
    }
}
