package fr.mrqsdf.dynamicbuild.command;

import fr.mrqsdf.dynamicbuild.res.BuildData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ListBuildCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage("List of build:");
            for (String build : BuildData.buildDataMap.keySet()){
                BuildData buildData = BuildData.buildDataMap.get(build);
                commandSender.sendMessage("§6name §f: " + build + " frameTick : " + buildData.frameTick + " airReplace : " + buildData.airReplace + " activated : " + buildData.activated);
                commandSender.sendMessage("point1 : [" + buildData.buildList.get(0).locX1 + ", " + buildData.buildList.get(0).locY1 + ", " + buildData.buildList.get(0).locZ1 + "]" +
                        "point2" + " [" + buildData.buildList.get(0).locX2 + ", " + buildData.buildList.get(0).locY2 + ", " + buildData.buildList.get(0).locZ2 + "]" +
                        "origine" + " [" + buildData.buildList.get(0).locX3 + ", " + buildData.buildList.get(0).locY3 + ", " + buildData.buildList.get(0).locZ3 + "]"
                        + " world : " + buildData.buildList.get(0).world);
            }
            return true;
        }
        return false;
    }
}
