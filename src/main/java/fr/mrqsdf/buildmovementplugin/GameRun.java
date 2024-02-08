package fr.mrqsdf.buildmovementplugin;

import fr.mrqsdf.buildmovementplugin.res.Build;
import fr.mrqsdf.buildmovementplugin.res.BuildData;
import org.bukkit.scheduler.BukkitRunnable;

public class GameRun extends BukkitRunnable {

    @Override
    public void run() {
        for (BuildData buildData : BuildData.buildDataMap.values()) {
            if (buildData.actualFrameTick < buildData.frameTick){
                buildData.actualFrameTick += BuildMovementPlugin.tickInterval;
            } else {
                buildData.actualFrameTick = 0;
                buildData.actualFrame++;
                if (buildData.actualFrame >= buildData.buildList.size()){
                    buildData.actualFrame = 0;
                }
                Build build = buildData.buildList.get(buildData.actualFrame);
                boolean airReplace = buildData.airReplace;
                CloneBlock.cloneBlock(build, airReplace);
            }
        }
    }

}
