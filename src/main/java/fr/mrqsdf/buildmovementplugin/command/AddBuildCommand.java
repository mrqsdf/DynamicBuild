package fr.mrqsdf.buildmovementplugin.command;

import fr.mrqsdf.buildmovementplugin.res.Build;
import fr.mrqsdf.buildmovementplugin.res.BuildData;
import fr.mrqsdf.buildmovementplugin.res.PlayerData;
import fr.mrqsdf.buildmovementplugin.res.PlayerLoc;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddBuildCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player){
            if (strings.length == 3){
                String name = strings[0];
                int frame = Integer.parseInt(strings[1]);
                boolean airReplace = Boolean.parseBoolean(strings[2]);
                PlayerLoc playerLoc = PlayerData.playerLocs.get(player.getUniqueId());
                BuildData builds = BuildData.buildDataMap.getOrDefault(name, new BuildData());
                if (playerLoc.locLeftClick == null || playerLoc.locRightClick == null || playerLoc.origineLoc == null){
                    player.sendMessage("You need to select two points and a Origine location before adding a build");
                    return false;
                }
                Build build = new Build(playerLoc.locLeftClick, playerLoc.locRightClick, playerLoc.origineLoc);
                builds.buildList.add(build);
                builds.frameTick = frame;
                builds.airReplace = airReplace;
                BuildData.buildDataMap.put(name, builds);
                return true;
            } else {
                player.sendMessage("Usage: /addbuild <name> <frameTick> <airReplace>");
            }
        }
        return false;
    }
}
