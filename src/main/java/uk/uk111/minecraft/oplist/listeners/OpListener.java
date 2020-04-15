package uk.uk111.minecraft.oplist.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import uk.uk111.minecraft.oplist.OpList;

public class OpListener implements Listener {
    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        Server server = OpList.getPlugin(OpList.class).getServer();

        if (event.getMessage().length() >= 4 && event.getMessage().substring(0, 4).equals("/op ") && player.isOp()) {
            if (event.getMessage().split(" ").length > 0) {
                Player target = server.getPlayerExact(event.getMessage().split(" ")[1]);

                if (target != null) {
                    if (!target.isOp()) {
                        sendOpMessage(server, true, player.getName(), target.getName());
                    }
                } else {
                    sendOpMessage(server, true, player.getName(), event.getMessage().split(" ")[1]);
                }
            }
        } else if (event.getMessage().length() >= 6 && event.getMessage().substring(0, 6).equals("/deop ") && player.isOp()) {
            if (event.getMessage().split(" ").length > 0) {
                Player target = server.getPlayerExact(event.getMessage().split(" ")[1]);

                if (target != null) {
                    if (target.isOp()) {
                        sendOpMessage(server, false, player.getName(), target.getName());
                    }
                } else {
                    sendOpMessage(server, false, player.getName(), event.getMessage().split(" ")[1]);
                }
            }
        }
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        Server server = OpList.getPlugin(OpList.class).getServer();

        if (event.getCommand().length() >= 3 && event.getCommand().substring(0, 3).equals("op ")) {
            if (event.getCommand().split(" ").length > 0) {
                Player target = server.getPlayerExact(event.getCommand().split(" ")[1]);

                if (target != null) {
                    if (!target.isOp()) {
                        sendOpMessage(server, true, "CONSOLE", target.getName());
                    }
                } else {
                    sendOpMessage(server, true, "CONSOLE", event.getCommand().split(" ")[1]);
                }
            }
        } else if (event.getCommand().length() >= 5 && event.getCommand().substring(0, 5).equals("deop ")) {
            if (event.getCommand().split(" ").length > 0) {
                Player target = server.getPlayerExact(event.getCommand().split(" ")[1]);

                if (target != null) {
                    if (target.isOp()) {
                        sendOpMessage(server, false, "CONSOLE", target.getName());
                    }
                } else {
                    sendOpMessage(server, false, "CONSOLE", event.getCommand().split(" ")[1]);
                }
            }
        }
    }

    private void sendOpMessage(Server server, boolean opEnabled, String initiator, String playerName) {
        String opMessage = ChatColor.DARK_RED + "[OpList]" + ChatColor.WHITE + (initiator.equals("CONSOLE") ? ": " : ": Player ") + ChatColor.GOLD + initiator + ChatColor.WHITE+  " has set OP for Player " +
                ChatColor.GOLD + playerName + ChatColor.WHITE + " to: " + (opEnabled ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled") + ChatColor.WHITE + "!";
        server.broadcastMessage(opMessage);
    }
}
