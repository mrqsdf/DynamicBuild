package fr.mrqsdf.dynamicbuild.command;

import fr.mrqsdf.dynamicbuild.res.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ActivateModeCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("buildmode")){
            if (sender instanceof Player player){
                if (args.length == 0){
                    if (PlayerData.playerIsBuilding.get(player.getUniqueId())){
                        PlayerData.playerIsBuilding.put(player.getUniqueId(), false);
                        sender.sendMessage("Build mode deactivated");
                    } else {
                        PlayerData.playerIsBuilding.put(player.getUniqueId(), true);
                        sender.sendMessage("Build mode activated");
                    }
                }
            }

        }
        return false;
    }

}
