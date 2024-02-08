package fr.mrqsdf.buildmovementplugin;

import fr.mrqsdf.buildmovementplugin.res.Build;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class CloneBlock {

    public static void cloneBlock(Build build, boolean isAirReplace){
        World world = Bukkit.getWorld(build.world);
        Location loc1 = new Location(world, build.locX1, build.locY1, build.locZ1);
        Location loc2 = new Location(world, build.locX2, build.locY2, build.locZ2);
        Location loc3 = new Location(world, build.locX3, build.locY3, build.locZ3);
        int startX = Math.min(loc1.getBlockX(),(loc2.getBlockX()));
        int startY = Math.min(loc1.getBlockY(),(loc2.getBlockY()));
        int startZ = Math.min(loc1.getBlockZ(),(loc2.getBlockZ()));
        int endX = Math.max(loc1.getBlockX(),(loc2.getBlockX()));
        int endY = Math.max(loc1.getBlockY(),(loc2.getBlockY()));
        int endZ = Math.max(loc1.getBlockZ(),(loc2.getBlockZ()));

        for (int x = startX; x <= endX; x++){
            for (int y = startY; y <= endY; y++){
                for (int z = startZ; z <= endZ; z++){
                    Location loc = new Location(world, x, y, z);
                    if (!isAirReplace && loc.getBlock().getType().isAir()) continue;
                    Location locFinal = loc3.clone().add(x - startX, y - startY, z - startZ);
                    locFinal.getBlock().setType(loc.getBlock().getType());
                    locFinal.getBlock().setBlockData(loc.getBlock().getBlockData());
                    locFinal.getBlock().getState().update();
                }
            }
        }

    }

}
