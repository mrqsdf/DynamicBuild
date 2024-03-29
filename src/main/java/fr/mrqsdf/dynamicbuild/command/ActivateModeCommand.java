package fr.mrqsdf.dynamicbuild.command;

import fr.mrqsdf.dynamicbuild.res.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ActivateModeCommand implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command cmd,@NotNull String label, String[] args){
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
                if (args.length == 1){
                    Player target = player.getServer().getPlayer(args[0]);
                    if (target == null){
                        sender.sendMessage("Player not found");
                        return false;
                    }
                    if (PlayerData.playerIsBuilding.get(target.getUniqueId())){
                        PlayerData.playerIsBuilding.put(target.getUniqueId(), false);
                        sender.sendMessage("Build mode deactivated for " + target.getName());
                    } else {
                        PlayerData.playerIsBuilding.put(target.getUniqueId(), true);
                        sender.sendMessage("Build mode activated for " + target.getName());
                    }
                }
            }
        }
        return false;
    }

}
