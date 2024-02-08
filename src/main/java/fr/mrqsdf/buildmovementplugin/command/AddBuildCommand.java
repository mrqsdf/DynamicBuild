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
            if (strings.length == 1){
                String name = strings[0];
                if (!BuildData.buildDataMap.containsKey(name)){
                    player.sendMessage("Name Not Found");
                    player.sendMessage("Usage: /addbuild <name> <frameTick> <airReplace>");
                    return false;
                }
                PlayerLoc playerLoc = PlayerData.playerLocs.get(player.getUniqueId());
                BuildData builds = BuildData.buildDataMap.getOrDefault(name, new BuildData());
                if (playerLoc.locLeftClick == null || playerLoc.locRightClick == null || playerLoc.origineLoc == null){
                    player.sendMessage("You need to select two points and a Origine location before adding a build");
                    return false;
                }
                Build build = new Build(playerLoc.locLeftClick, playerLoc.locRightClick, playerLoc.origineLoc);
                int buildIndex = builds.buildList.size();
                builds.buildList.add(build);
                BuildData.buildDataMap.put(name, builds);
                player.sendMessage("Build added at " +name + " at index " + buildIndex);
                return true;
            }
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
                int buildIndex = builds.buildList.size();
                BuildData.buildDataMap.put(name, builds);
                player.sendMessage("Build added at " +name + " at index " + buildIndex);
                return true;
            } else {
                player.sendMessage("Usage: /addbuild <name> <frameTick> <airReplace>");
            }
        }
        return false;
    }
}
