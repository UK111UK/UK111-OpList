package uk.uk111.OpList.Commands;

import uk.uk111.OpList.Utils.OpListJson;
import com.google.gson.Gson;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.uk111.OpList.OpList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommandOpList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            StringBuilder ops = new StringBuilder("");

            Gson gson = new Gson();
            try {
                OpListJson[] opList = gson.fromJson(new String(Files.readAllBytes(Paths.get(OpList.getPlugin(OpList.class).getServer().getWorldContainer().getAbsolutePath() + "/ops.json"))), OpListJson[].class);

                if (opList.length == 0) {
                    ops.append("None!");
                } else {
                    for (OpListJson json : opList) {
                        if (ops.toString().equals("")) {
                            ops = new StringBuilder(json.getName());
                        } else {
                            ops.append(", ").append(json.getName());
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                ops = new StringBuilder("ERROR");
            }

            player.sendMessage("List of Current Server Operators:");
            player.sendMessage(ops.toString());
        }

        return true;
    }
}
