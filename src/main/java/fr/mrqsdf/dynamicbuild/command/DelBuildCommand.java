package fr.mrqsdf.dynamicbuild.command;

import fr.mrqsdf.dynamicbuild.res.BuildData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DelBuildCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1){
            sender.sendMessage("§cErreur: §7Utilisation: /delbuild <name>");
            return true;
        }
        String name = args[0];
        BuildData.buildDataMap.remove(name);
        sender.sendMessage("§aSuccès: §7Le build " + name + " a été supprimé");
        return true;
    }

}
