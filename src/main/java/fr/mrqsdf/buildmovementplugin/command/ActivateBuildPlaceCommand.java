package fr.mrqsdf.buildmovementplugin.command;

import fr.mrqsdf.buildmovementplugin.res.BuildData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ActivateBuildPlaceCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2){
            sender.sendMessage("§cErreur: §7Utilisation: /activatebuild <name> <true/false>");
        }
        String name = args[0];
        boolean value = Boolean.parseBoolean(args[1]);
        BuildData buildData = BuildData.buildDataMap.get(name);
        if (buildData == null){
            sender.sendMessage("§cErreur: §7Le build n'existe pas");
            return true;
        }
        buildData.activated = value;
        return true;
    }

}
